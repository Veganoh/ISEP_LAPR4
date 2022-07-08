package eapli.base.app.server.orders;


import eapli.base.app.server.orders.requestHandlers.AddShoppingCartHandler;
import eapli.base.app.server.orders.requestHandlers.AnswerSurveysHandler;
import eapli.base.app.server.orders.requestHandlers.GetOpenOrdersHandler;
import eapli.base.app.server.orders.requestHandlers.GetProductsHandler;
import eapli.base.common.domain.PacketUtils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class OrdersRequestHandler extends Thread{


    public static final int COMMTEST = 0;
    public static final int DISCONN = 1;
    public static final int ACK = 2;
    public static final int PRODUCT = 3;
    public static final int ADD_SHOPPING_CART = 4;
    public static final int OPEN_ORDERS = 5;
    public static final int ANSWER_SURVEY = 6;

    private Socket myS;

    public OrdersRequestHandler(Socket socket){
        this.myS = socket;
    }

    @Override
    public void run(){
        try {

            DataInputStream sIn = new DataInputStream(myS.getInputStream());
            DataOutputStream sOut = new DataOutputStream(myS.getOutputStream());

            boolean flag = true;
            while(flag) {
                sIn.readUnsignedByte();
                int code = sIn.readUnsignedByte();
                switch (code) {
                    case COMMTEST:
                        sIn.skipBytes(2);
                        sOut.write(PacketUtils.ACK_DATA);
                        break;
                    case DISCONN:
                        sIn.skipBytes(2);
                        sOut.write(PacketUtils.ACK_DATA);
                        System.out.println("Disconnecting user " + myS.getInetAddress().getHostAddress());
                        myS.close();
                        flag = false;
                        break;
                    case ACK:
                        System.out.println("\nConnection test acknowledged");
                        break;
                    case PRODUCT:
                        sIn.skipBytes(2);
                        GetProductsHandler productsHandler = new GetProductsHandler(myS,sIn,sOut);
                        productsHandler.run();
                        break;
                    case ADD_SHOPPING_CART:
                        AddShoppingCartHandler shoppingCartHandler = new AddShoppingCartHandler(myS,sIn,sOut);
                        shoppingCartHandler.run();
                        myS.close();
                        flag = false;
                        break;
                    case OPEN_ORDERS:
                        GetOpenOrdersHandler openOrdersHandler = new GetOpenOrdersHandler(myS, sIn, sOut);
                        openOrdersHandler.run();
                        break;
                    case ANSWER_SURVEY:
                        AnswerSurveysHandler answerSurveysHandler = new AnswerSurveysHandler(myS, sIn, sOut);
                        answerSurveysHandler.run();
                        break;
                }
            }
        }catch(Exception ex) {
            ex.printStackTrace();
        }
    }

}
