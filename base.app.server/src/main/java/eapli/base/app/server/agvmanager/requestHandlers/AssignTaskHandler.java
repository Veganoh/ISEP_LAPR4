package eapli.base.app.server.agvmanager.requestHandlers;

import eapli.base.Application;
import eapli.base.agvmanagement.domain.AGV;
import eapli.base.agvmanagement.repositories.AGVRepository;
import eapli.base.agvmanagermanagement.domain.Coordinates;
import eapli.base.agvmanagermanagement.domain.Square;
import eapli.base.agvmanagermanagement.repositories.AGVManagerRepository;
import eapli.base.agvtaskmanagement.domain.AGVTask;
import eapli.base.common.domain.ConnectionEstablisher;
import eapli.base.common.domain.PacketUtils;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordermanagement.domain.Order;
import eapli.base.ordermanagement.domain.OrderLineItem;
import eapli.base.ordermanagement.repositories.OrderRepository;
import eapli.base.ordermanagement.domain.OrderNotificationService;
import eapli.base.warehousemanagement.domain.Shelf;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.*;

import static eapli.base.app.server.agvmanager.AGVManagerRequestHandler.*;
import static eapli.base.common.domain.PacketUtils.ACK;

public class AssignTaskHandler extends Thread {

    /**
     * The connected server's ip
     */
    static InetAddress serverIP;

    private Map<String, String> digitalTwins;

    /**
     * The connected server's socket
     */
    static Socket outS;

    /**
     * The socket that connects the server
     */
    private Socket inS;

    /**
     * input and output streams to the remote server
     */
    private DataInputStream sIn;
    private DataOutputStream sOut;


    /**
     * The AGV Repository
     */
    AGVRepository agvRep = PersistenceContext.repositories().AGV();

    AGVManagerRepository managerRep = PersistenceContext.repositories().AGVManager();

    /**
     * The Orders Repository
     */
    OrderRepository orderRep = PersistenceContext.repositories().Orders();

    public AssignTaskHandler(Socket myS, DataInputStream sIn, DataOutputStream sOut, Map<String, String> digitalTwins) {
        this.inS = myS;
        this.sIn = sIn;
        this.sOut = sOut;
        this.digitalTwins = digitalTwins;
    }

    public AssignTaskHandler() {}

    @Override
    public void run() {
        AGV agv = assignToAGV();
        assignToDigitalTwin(agv, digitalTwins);
    }

    /**
     * Connects to the database in order to associate task to AGV and change order status
     */
    private AGV assignToAGV() {
        try {
            AGVTask task = PacketUtils.readObject(sIn);
            System.out.println("Data received: " + task);

            sOut.write(PacketUtils.ACK_DATA);
            AGV agv = PacketUtils.read(sIn, AGV_ASSIGN);
            System.out.println("Data received: " + agv);


            if (digitalTwins.containsKey(agv.identity().toString())) {
                OrderNotificationService.assignTask(agv, task);

                sOut.write(PacketUtils.EXIT_DATA);

                System.out.println("Assigned task to AGV.");
            } else {
                System.out.println("Given AGV does not contain a Digital Twin associated!");
                sOut.write(PacketUtils.EXIT_DATA);
                System.exit(1);
            }

            return agv;
        } catch (IOException e) {
            System.out.println("Could not open data stream.");
            System.exit(1);
        }
        return null;
    }

    /**
     * Starts the connection to the agv twin application and sends in the target agv's information
     */
    private void assignToDigitalTwin(AGV agv, Map<String, String> digitalTwins) {
        String ipPort = digitalTwins.get(agv.identity().toString());
        String[] address = ipPort.split(":");
        address[0] = address[0].replace("/", "");

        ConnectionEstablisher connectionEstablisher = new ConnectionEstablisher(address[0], Application.settings().getDigitalTwinPort(), Application.settings().getAgvManagerKeyPath());

        try {
            outS = connectionEstablisher.establishConnection();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Failed to connect. - " + ex.getMessage());
            System.exit(1);
        }

        byte[] response;
        assignTaskOneByOne(agv);

        try {
            response = new byte[4];
            sIn.read(response);

            if (response[PacketUtils.CODE_OFFSET] != TASK_FINISH) {
                System.out.println("Unexpected packet received! Was expecting code " + TASK_FINISH + " received code " + response[PacketUtils.CODE_OFFSET]);
                outS.close();
            } else {
                OrderNotificationService.finishTask(agv);
                System.out.println("Finished Task");

                sOut.write(PacketUtils.EXIT_DATA);

                response = new byte[4];
                sIn.read(response);
                if (response[PacketUtils.CODE_OFFSET] == ACK)
                    outS.close();
            }
        } catch (IOException e) {
            System.out.println("Could not open data stream.");
            System.exit(1);
        }
    }

    /**
     * Sends the task to the Digital Twin, product by product. Makes use of the
     * OrderLineItem object.
     *
     * @param agv The AGV that contains the task
     */
    private void assignTaskOneByOne(AGV agv) {
        try {
            DataInputStream sIn = new DataInputStream(outS.getInputStream());
            DataOutputStream sOut = new DataOutputStream(outS.getOutputStream());
            byte[] response;

            for (OrderLineItem orderItem : agv.task().order().items()) {
                Shelf shelf = orderItem.product().availableShelves().get(0);

                if (shelf != null) {
                    Iterable<Square> productSquares = managerRep.squaresWithRowAisle(shelf.rowId(), shelf.aisleId());

                    if (productSquares.iterator().hasNext()) {
                        Square square = productSquares.iterator().next();

                        Coordinates coords = getClosestAccessibleSquareCoordinates(square);

                        response = new byte[12];

                        response[PacketUtils.VERSION_OFFSET] = (byte) 1;
                        response[PacketUtils.CODE_OFFSET] = (byte) TASK_ASSIGN;
                        response[PacketUtils.D_LENGTH_1_OFFSET] = (byte) 8;
                        response[PacketUtils.D_LENGTH_2_OFFSET] = (byte) 0;

                        int offset = 4;
                        for (Byte intByte : ByteBuffer.allocate(4).putInt(coords.widthSquare()).array()) {
                            response[offset] = intByte;
                            offset++;
                        }

                        for (Byte intByte : ByteBuffer.allocate(4).putInt(coords.lengthSquare()).array()) {
                            response[offset] = intByte;
                            offset++;
                        }

                        System.out.println(coords.lengthSquare() + " - " + coords.widthSquare());

                        sOut.write(response);

                        response = new byte[4];
                        sIn.read(response);

                        if (response[PacketUtils.CODE_OFFSET] != TASK_FINISH) {
                            System.out.println("Unexpected packet received! Was expecting code " + TASK_FINISH + " received code " + response[PacketUtils.CODE_OFFSET]);
                            outS.close();
                            System.exit(1);
                        }
                    }
                }
            }

            response = new byte[12];

            response[PacketUtils.VERSION_OFFSET] = (byte) 1;
            response[PacketUtils.CODE_OFFSET] = (byte) TASK_ASSIGN;
            response[PacketUtils.D_LENGTH_1_OFFSET] = (byte) 8;
            response[PacketUtils.D_LENGTH_2_OFFSET] = (byte) 0;

            int offset = 4;
            for (Byte intByte : ByteBuffer.allocate(4).putInt(agv.baseLocation().beginSquare().squareCoordinates().widthSquare()).array()) {
                response[offset] = intByte;
                offset++;
            }

            for (Byte intByte : ByteBuffer.allocate(4).putInt(agv.baseLocation().beginSquare().squareCoordinates().lengthSquare()).array()) {
                response[offset] = intByte;
                offset++;
            }

            sOut.write(response);

        } catch (IOException e) {
            System.out.println("Could not open data stream.");
            System.exit(1);
        }
    }

    /**
     * Returns the nearest accessible Square to the given square
     *
     * @param square A Square
     * @return The Coordinates of the nearest accessible square
     */
    private Coordinates getClosestAccessibleSquareCoordinates(Square square) {
        Coordinates coords;

        switch (square.accessibilityDirection()) {
            case Up:
                do {
                    coords = Coordinates.valueOf(
                            square.squareCoordinates().widthSquare() - 1,
                            square.squareCoordinates().lengthSquare());
                    Optional<Square> tmpSquare = managerRep.squareWithCoords(coords);
                    if (tmpSquare.isPresent())
                        square = tmpSquare.get();
                    else
                        break;
                } while (square.hasAisle());
                break;
            case Right:
                do {
                    coords = Coordinates.valueOf(
                            square.squareCoordinates().widthSquare(),
                            square.squareCoordinates().lengthSquare() + 1);
                    Optional<Square> tmpSquare = managerRep.squareWithCoords(coords);
                    if (tmpSquare.isPresent())
                        square = tmpSquare.get();
                    else
                        break;
                } while (square.hasAisle());
                break;
            case Down:
                do {
                    coords = Coordinates.valueOf(
                            square.squareCoordinates().widthSquare() + 1,
                            square.squareCoordinates().lengthSquare());
                    Optional<Square> tmpSquare = managerRep.squareWithCoords(coords);
                    if (tmpSquare.isPresent())
                        square = tmpSquare.get();
                    else
                        break;
                } while (square.hasAisle());
                break;
            case Left:
                do {
                    coords = Coordinates.valueOf(
                            square.squareCoordinates().widthSquare(),
                            square.squareCoordinates().lengthSquare() - 1);
                    Optional<Square> tmpSquare = managerRep.squareWithCoords(coords);
                    if (tmpSquare.isPresent())
                        square = tmpSquare.get();
                    else
                        break;
                } while (square.hasAisle());
                break;
            default:
                throw new IllegalStateException("Unexpected error!");
        }

        return coords;
    }

    /**
     * Gets the list of orders in the state of "to be prepared"
     * @return Iterable of Orders
     */
    public Iterable<Order> getOrdersToBePrepared() {
        return orderRep.toBePreparedSortedByDate();
    }

    /**
     * Gets a list of currently available AGV
     * @return Iterable of AGV
     */
    public Iterable<AGV> getFreeAGV() {
        return agvRep.available();
    }

    /**
     * Adds new orders to the given queue (adds them in the last position of the queue)
     * If the queue already contains that order, then the order is skipped.
     *
     * @param queue a FIFO queue of orders
     * @param list an iterable of orders
     */
    public void addNewOrdersToQueue(LinkedList<Order> queue, Iterable<Order> list) {
        for (Order order : list)
            if(!queue.contains(order))
                queue.addLast(order);
    }

    /**
     * Assigns the tasks in the queue to a capable AGV following a FIFO order.
     * Also sends that task to the digital twin.
     *
     * In case there is no currently available AGV that is capable of carrying out the task
     * then it will skip to the next task and try to assign it. Will repeat this process until
     * the queue runs out of tasks or there are no more available AGV.
     *
     * @param queue a FIFO queue of orders
     * @param freeAGVIterable an iterable of available AGV
     */
    public void assignTaskToFreeAGV(LinkedList<Order> queue, Iterable<AGV> freeAGVIterable, Map<String, String> digitalTwins) {
        boolean assignedFlag = false;
        ArrayList<AGV> agvList = (ArrayList<AGV>) freeAGVIterable;
        int freeAGVCount = agvList.size();
        int iCount = 0;
        Order nextOrder;

        while (freeAGVCount > 0 && queue.size() > 0) {
            if (iCount == queue.size())
                break;

            nextOrder = queue.get(iCount);

            for (AGV agv : agvList) {
                if (digitalTwins == null)
                    break;

                if (digitalTwins.containsKey(agv.identity().toString())) {
                    if (agv.isAGVCapable(nextOrder)) {
                        AGVTask task = new AGVTask(nextOrder);

                        OrderNotificationService.assignTask(agv, task);
                        assignToDigitalTwin(agv, digitalTwins);
                        agvList.remove(agv);
                        assignedFlag = true;

                        agvRep.save(agv);
                        orderRep.save(nextOrder);

                        break;
                    }
                }
            }

            if (assignedFlag) {
                assignedFlag = false;
                queue.remove(iCount);
                iCount--;
                freeAGVCount--;
            }

            iCount++;
        }
    }
}
