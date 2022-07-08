package eapli.base.app.server.orders.requestHandlers;

import eapli.base.app.server.orders.OrdersRequestHandler;
import eapli.base.common.domain.PacketUtils;
import eapli.base.common.domain.Quantity;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.productmanagement.domain.Product;
import eapli.base.productmanagement.repositories.ProductRepository;
import eapli.base.shoppingcartmanagement.domain.ShoppingCart;
import eapli.base.shoppingcartmanagement.repositories.ShoppingCartRepository;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import org.apache.commons.lang3.SerializationUtils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.NoSuchElementException;

public class AddShoppingCartHandler implements Runnable {

    private Socket myS;

    private DataInputStream sIn;

    private DataOutputStream sOut;

    /**
     * The product repository
     */
    private final ProductRepository productRepo = PersistenceContext.repositories().Products();


    public AddShoppingCartHandler(Socket myS, DataInputStream sIn, DataOutputStream sOut) {
        this.myS = myS;
        this.sIn = sIn;
        this.sOut = sOut;
    }

    @Override
    public void run() {
        try {
            // Gets the user session
            SystemUser systemUser = PacketUtils.readObject(sIn);
            sOut.write(PacketUtils.ACK_DATA);

            ShoppingCartRepository repo = PersistenceContext.repositories().ShoppingCarts();
            ShoppingCart sp = repo.findBySystemUser(systemUser).orElseThrow();
            while(true){
                sIn.readUnsignedByte();
                int code = sIn.readUnsignedByte();
                if (code == OrdersRequestHandler.DISCONN){
                    sOut.write(PacketUtils.ACK_DATA);
                    System.out.println("Disconnecting user" + myS.getInetAddress().getHostAddress());
                    break;
                }
                // Gets the product
                String pCode = PacketUtils.readObject(sIn);
                Product product = productRepo.find(pCode).orElseThrow();
                sOut.write(PacketUtils.ACK_DATA);

                sIn.readUnsignedByte();
                code = sIn.readUnsignedByte();
                if (code == OrdersRequestHandler.DISCONN){
                    sOut.write(PacketUtils.ACK_DATA);
                    System.out.println("Disconnecting user" + myS.getInetAddress().getHostAddress());
                    break;
                }

                // Gets the quantity
                Quantity quantity = PacketUtils.readObject(sIn);;
                sOut.write(PacketUtils.ACK_DATA);

                sp.addShoppingCartLineItem(product, quantity);
                repo.save(sp);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    /**
     * Checks a given product code, to associate a Product to the order
     *
     * @param productCode the Product Code
     * @return the Product
     * @throws NoSuchElementException, if product doesn't exist
     */
    private Product checkProductCode(String productCode) {
        return productRepo.find(productCode).orElseThrow();
    }
}