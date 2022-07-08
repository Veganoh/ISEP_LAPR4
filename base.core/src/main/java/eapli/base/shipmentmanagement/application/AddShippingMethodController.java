package eapli.base.shipmentmanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.shipmentmanagement.domain.ShippingMethod;
import eapli.base.shipmentmanagement.repositories.ShippingMethodRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

public class AddShippingMethodController {
    /**
     * Authorization service that is used to make sure only authorized users can use the controller
     */
    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    /**
     * The object repository
     */
    private final ShippingMethodRepository repo = PersistenceContext.repositories().ShippingMethods();

    public AddShippingMethodController(){
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.WAREHOUSE_EMPLOYEE, BaseRoles.POWER_USER, BaseRoles.ADMIN);
    }

    /**
     * Registers a new Shipping Method into the repository
     * @param designation the method's name
     * @param carrier the carrier that offers said shipping
     * @param price the price per weight unit
     * @param unit the weight unit, refereced in param above
     * @param tax the shipping tax
     * @return the created Shipping Method
     */
    public ShippingMethod register(String designation, String carrier, String price, String unit, double tax) {

        ShippingMethod shipment = ShippingMethod.valueOf(designation, carrier, price, unit, tax);

        return repo.save(shipment);
    }
}

