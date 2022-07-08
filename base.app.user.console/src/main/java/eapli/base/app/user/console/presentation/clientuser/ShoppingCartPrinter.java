package eapli.base.app.user.console.presentation.clientuser;

import eapli.base.productmanagement.domain.Product;
import eapli.base.shoppingcartmanagement.domain.ShoppingCart;
import eapli.base.shoppingcartmanagement.domain.ShoppingCartLineItem;
import eapli.framework.visitor.Visitor;

import java.util.List;

public class ShoppingCartPrinter implements Visitor<ShoppingCart> {


    @Override
    public void visit(final ShoppingCart sp) {

        List<ShoppingCartLineItem> lines = sp.lines();
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for(ShoppingCartLineItem l : lines){

            Product p = l.product();
            sb.append(i + ".");
            sb.append("\n");
            sb.append("Product: " + p.identity() + " - "+ p.getProductDescriptions().shortestDescription());
            sb.append("\n");
            sb.append("Quantity: " + l.quantity());
            sb.append("\n");
            sb.append("Price: " + p.getPriceWithTax());
            sb.append("\n");
            sb.append("-------------------------------------------------------------------------");
            sb.append("\n");
            i++;
        }
        sb.append("Total: " + sp.totalWithTax());
        sb.append("\n");
        System.out.println(sb);

    }
}
