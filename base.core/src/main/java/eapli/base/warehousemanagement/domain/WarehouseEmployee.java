package eapli.base.warehousemanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.infrastructure.authz.domain.model.Name;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import javax.persistence.*;

@Entity
public class WarehouseEmployee implements AggregateRoot<Long> {
    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    private Name name;

    @OneToOne()
    private SystemUser systemUser;

    protected WarehouseEmployee(){
    }

    public WarehouseEmployee(final SystemUser user, final Name name){
        if(name == null || user == null) {
            throw new IllegalArgumentException("At least one argument is null");
        }
        this.systemUser = user;
        this.name = name;
    }

    @Override
    public boolean sameAs(Object other) {
        return DomainEntities.areEqual(this, other);
    }

    @Override
    public Long identity() {
        return id;
    }
}
