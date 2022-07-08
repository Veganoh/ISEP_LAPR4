package eapli.base.ordermanagement.presentation;

import eapli.base.ordermanagement.domain.Order;
import eapli.base.ordermanagement.domain.OrderLineItem;
import eapli.framework.visitor.Visitor;

public class OrderPrinter implements Visitor<Order> {
    @Override
    public void visit(final Order order) {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("Customer ID: %s\n", order.proprietary().name().toString()));
        if(order.responsibleEmployee() != null) {
            sb.append(String.format("Sales clerk responsible: - %s", order.responsibleEmployee().user().email().toString()));
        }
        sb.append(order.getOrderStates().getState()).append("\n\n\n");;
        for (OrderLineItem orderLineItem : order.items())
            sb.append(orderLineItem.toString()).append("\n");
        sb.append("\n\n\nShipping: ")
                .append(order.shippingTotal())
                .append("\nShipping With Tax: ")
                .append(order.shippingTotalWithTax());
        sb.append("\n\n\nTotal: ")
                .append(order.total())
                .append("\nTotal With Tax: ")
                .append(order.totalWithTax())
                .append("\n\n\n-------------------------------------------------------------------------------------------------------------------");
        System.out.println(sb);
    }
}
