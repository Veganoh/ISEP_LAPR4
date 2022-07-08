package eapli.base.app.user.console.presentation.clientuser;

import eapli.base.productmanagement.domain.Product;
import eapli.framework.visitor.Visitor;

class ProductPrinter implements Visitor<Product> {

    @Override
    public void visit(final Product p){
       String sb = String.format("%s %6s %-30s %20s %-15s %20s %-15s %20s %sEUR",
               p.identity(),
               "|",
               p.getProductDescriptions().shortestDescription(),
               "|",
               p.getBrand(),
               "|",
               p.getCategory().identity(),
               "|",
               p.getPrice().amount());
        System.out.println(sb);
    }
}
