package eapli.base.ordermanagement.application;

import eapli.base.common.domain.AddressType;
import eapli.base.common.domain.Quantity;
import eapli.base.common.domain.RegisterTime;
import eapli.base.customermanagement.domain.Customer;
import eapli.base.customermanagement.domain.CustomerAddress;
import eapli.base.customermanagement.repositories.CustomerRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordermanagement.domain.*;
import eapli.base.ordermanagement.repositories.OrderRepository;
import eapli.base.ordermanagement.repositories.SalesClerkRepository;
import eapli.base.ordermanagement.requests.GetOpenOrdersRequest;
import eapli.base.productmanagement.domain.Product;
import eapli.base.productmanagement.repositories.ProductRepository;
import eapli.base.shipmentmanagement.domain.ShippingMethod;
import eapli.base.shipmentmanagement.repositories.ShippingMethodRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.Username;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

public class ShowOpenOrdersController {
    /**
     * Authorization service that is used to make sure only authorized users can use the controller
     */
    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    /**
     * The system user authenticated
     */
    private final SystemUser usr = authz.session().get().authenticatedUser();

    public ShowOpenOrdersController() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.CLIENT_USER);
    }

    public Set<Order> listOpenOrders() {
        return new GetOpenOrdersRequest(usr).get();
    }

}