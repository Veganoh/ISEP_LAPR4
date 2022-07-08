package eapli.base.productmanagement.application;

import eapli.base.Application;
import eapli.base.common.domain.ConnectionEstablisher;
import eapli.base.common.domain.PacketUtils;
import eapli.base.common.domain.Quantity;
import eapli.base.productmanagement.domain.Product;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;



public class AddProductShoppingCartController {

    /**
     * Input stream to the remote server
     */
    private DataInputStream sIn;

    /**
     * Output stream to the remote server
     */
    private DataOutputStream sOut;

    /**
     * Authorization service that is used to make sure only authorized users can use the controller
     */
    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    /**
     * The system user authenticated
     */
    private final SystemUser usr = authz.session().get().authenticatedUser();

    /**
     * Connection Establisher
     */
    private final ConnectionEstablisher cd = new ConnectionEstablisher(Application.settings().getOrderServer(),Application.settings().getOrderServerPort(), Application.settings().getOrdersKeyPath());

    public AddProductShoppingCartController(){
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.CLIENT_USER);
    }

    public void openConnection(){
        try {
            cd.establishConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        sIn = cd.sIn;
        sOut = cd.sOut;
    }
    public void closeConnection(){
        cd.closeConnection();
    }

    /**
     * Adds a new product to the shopping cart
     * @param product the selected product
     * @param quantity the selected quantity
     */
    public void addProductShoppingCart(String product, int quantity){
        try {
            sOut.write(PacketUtils.toData(4,product));
            sIn.readUnsignedByte();

            if(sIn.readUnsignedByte() != 2) throw new IOException("Did not receive the expected answer from the orders server");
            sIn.skipBytes(2);

            sOut.write(PacketUtils.toData(4,Quantity.valueOf(quantity)));
            sIn.readUnsignedByte();

            if(sIn.readUnsignedByte() != 2) throw new IOException("Did not receive the expected answer from the orders server");
            sIn.skipBytes(2);

        }catch (IOException e){
            System.out.println("Could not open data stream.");
            System.exit(1);
        }
    }

    public void addSystemUser(){
        try{
            sOut.write(PacketUtils.toData(4,usr));
            sIn.readUnsignedByte();
            if(sIn.readUnsignedByte() != 2) throw new IllegalArgumentException("Did not receive the expected answer from the client server");
            sIn.skipBytes(2);
        }catch (IOException e){
            System.out.println("Could not open data stream.");
            System.exit(1);
        }
    }


    public  ArrayList<Product> getProductList(){
        try {
            sOut.write(PacketUtils.toCode(3));
            return PacketUtils.read(sIn, 3);
        }catch (IOException e) {
            System.out.println("Could not open data stream.");
            System.exit(1);
        }
        return null;
    }
}