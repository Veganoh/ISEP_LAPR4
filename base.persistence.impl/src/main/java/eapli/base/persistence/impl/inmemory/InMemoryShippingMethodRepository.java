package eapli.base.persistence.impl.inmemory;

import eapli.base.shipmentmanagement.domain.ShippingMethod;
import eapli.base.shipmentmanagement.repositories.ShippingMethodRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryShippingMethodRepository extends InMemoryDomainRepository<ShippingMethod, Long> implements ShippingMethodRepository {

    /*
     * Initializes the inmemory repository
     */
    static {
        InMemoryInitializer.init();
    }

}
