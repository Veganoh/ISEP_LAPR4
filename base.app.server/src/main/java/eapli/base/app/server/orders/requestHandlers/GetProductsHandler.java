package eapli.base.app.server.orders.requestHandlers;

import eapli.base.app.server.orders.OrdersRequestHandler;
import eapli.base.common.domain.PacketUtils;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.productmanagement.domain.Product;
import eapli.base.productmanagement.repositories.ProductRepository;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class GetProductsHandler implements Runnable{

    private Socket myS;

    private DataInputStream sIn;

    private DataOutputStream sOut;


    public GetProductsHandler(Socket myS, DataInputStream sIn, DataOutputStream sOut) {
        this.myS = myS;
        this.sIn = sIn;
        this.sOut = sOut;
    }

    @Override
    public void run() {
        try {
            byte[] response = new byte[4];

            sOut.write(PacketUtils.toData(OrdersRequestHandler.PRODUCT, productsList()));

        } catch (IOException e) {
            System.out.println("Could not open data stream.");
            System.exit(1);
        }
    }
    private ArrayList<Product> productsList() {
        ProductRepository repo = PersistenceContext.repositories().Products();
        return (ArrayList<Product>) repo.findAll();
    }
}
