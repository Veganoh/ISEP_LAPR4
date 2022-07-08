package eapli.base.common.domain;

import org.apache.commons.lang3.SerializationUtils;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;

public class PacketUtils {

    public static final int COMMTEST = 0;
    public static final int DISCONN = 1;
    public static final int ACK = 2;
    /**
     * Packet verion
     */
    private static byte VERSION = 1;

    /**
     * Default Communication request packet
     */
    public static byte[] COMM_DATA = new byte[]{VERSION, 0, 0, 0};

    /**
     * Default Exit request packet
     */
    public static byte[] EXIT_DATA = new byte[]{VERSION, 1, 0, 0};

    /**
     * Default acknowledgement request packet
     */
    public static byte[] ACK_DATA = new byte[]{VERSION, 2, 0, 0};

    /**
     * Version offset
     */
    public static int VERSION_OFFSET = 0;
    /**
     * Code offset
     */
    public static int CODE_OFFSET = 1;
    /**
     * Length offset
     */
    public static int D_LENGTH_1_OFFSET = 2;
    public static int D_LENGTH_2_OFFSET = 3;
    /**
     * Data offset
     */
    public static int DATA_OFFSET = 4;

    /**
     * Serializes an object and creates a byte array that is tailored to the SPOMMS module
     *
     * @param code The packet's code
     * @return returns an array of bytes that is supported by the SPOMMS module
     */
    public static byte[] toCode(int code) {
        byte[] data = new byte[4];

        data[VERSION_OFFSET] = VERSION;
        data[CODE_OFFSET] = (byte) code;
        data[D_LENGTH_1_OFFSET] = (byte) 0;
        data[D_LENGTH_2_OFFSET] = (byte) 0;

        return data;
    }

    /**
     * Serializes an object and creates a byte array that is tailored to the SPOMMS module
     *
     * @param code The packet's code
     * @param e    The object to be serialized
     * @param <E>  The object's type which must extend serializable
     * @return returns an array of bytes that is supported by the SPOMMS module
     */
    public static <E extends Serializable> byte[] toData(int code, E e) {
        byte[] object = SerializationUtils.serialize(e);
        byte[] data = new byte[object.length + 4];

        data[VERSION_OFFSET] = VERSION;
        data[CODE_OFFSET] = (byte) code;
        data[D_LENGTH_1_OFFSET] = (byte) (object.length % 256);
        data[D_LENGTH_2_OFFSET] = (byte) (object.length / 256);
        System.arraycopy(object, 0, data, DATA_OFFSET, object.length);

        return data;
    }

    public static <E extends Serializable> E read(DataInputStream sIn, int expectedCode) {
        try {
            int version = sIn.readUnsignedByte();
            int code = sIn.readUnsignedByte();
            System.out.println("Received reply with code " + code);

            if (code == expectedCode) {
                return readObject(sIn);
            } else {
                throw new IllegalArgumentException("Unexpected code received. Expected code " + expectedCode + "but was " + code);
            }
        } catch (IOException e) {
            System.out.println("Could not open data stream.");
            System.exit(1);
        }
        return null;
    }

    public static <E extends Serializable> E readObject(DataInputStream sIn) {
        try {
            int size = sIn.readUnsignedByte() + sIn.readUnsignedByte() * 256;
            byte[] data = new byte[size];
            int recieved = 0;

            while (recieved != size)
                recieved += sIn.read(data, recieved, size - recieved);

            return SerializationUtils.deserialize(data);

        } catch (IOException e) {
            System.out.println("Could not open data stream.");
            System.exit(1);
        }
        return null;
    }

    public static void assertACK(DataInputStream sIn) {
        try {
            byte[] response = new byte[4];
            sIn.read(response);
            System.out.println("Received reply with code " + response[CODE_OFFSET]);

            if (response[CODE_OFFSET] != ACK)
                throw new IllegalArgumentException("Response packet is faulty");
        } catch (IOException e) {
            System.out.println("Could not open data stream.");
            System.exit(1);
        }
    }

    public static void assertEXIT(DataInputStream sIn) {
        try {
            byte[] response = new byte[4];
            sIn.read(response);
            System.out.println("Received reply with code " + response[CODE_OFFSET]);

            if (response[CODE_OFFSET] != DISCONN)
                throw new IllegalArgumentException("Response packet is faulty");
        } catch (IOException e) {
            System.out.println("Could not open data stream.");
            System.exit(1);
        }
    }
}
