/*
 * Copyright (c) 2013-2021 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package eapli.base.persistence.impl.inmemory;

import eapli.base.agvdockmanagement.repositories.AGVDockRepository;
import eapli.base.agvmanagement.repositories.AGVRepository;
import eapli.base.agvmanagermanagement.repositories.AGVManagerRepository;
import eapli.base.clientusermanagement.repositories.ClientUserRepository;
import eapli.base.clientusermanagement.repositories.SignupRequestRepository;
import eapli.base.customermanagement.repositories.CustomerRepository;
import eapli.base.infrastructure.bootstrapers.BaseBootstrapper;
import eapli.base.infrastructure.persistence.RepositoryFactory;
import eapli.base.ordermanagement.repositories.OrderRepository;
import eapli.base.ordermanagement.repositories.SalesClerkRepository;
import eapli.base.productcategorymanagement.repositories.ProductCategoryRepository;
import eapli.base.productmanagement.repositories.ProductRepository;
import eapli.base.shipmentmanagement.repositories.ShippingMethodRepository;
import eapli.base.shoppingcartmanagement.repositories.ShoppingCartRepository;
import eapli.base.surveymanagement.repositories.AnsweredQuestionnaireRepository;
import eapli.base.surveymanagement.repositories.QuestionnaireRepository;
import eapli.base.warehousemanagement.repositories.WarehouseEmployeeRepository;
import eapli.base.warehousemanagement.repositories.WarehouseRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import eapli.framework.infrastructure.authz.repositories.impl.InMemoryUserRepository;

/**
 *
 * Created by nuno on 20/03/16.
 */
public class InMemoryRepositoryFactory implements RepositoryFactory {

    @Override
    public UserRepository users(final TransactionalContext tx) {
        final InMemoryUserRepository repo = new InMemoryUserRepository();
        BaseBootstrapper.registerPowerUser(repo);
        return repo;
    }

    @Override
    public UserRepository users() {
        return users(null);
    }

    @Override
    public ClientUserRepository clientUsers(final TransactionalContext tx) {

        return new InMemoryClientUserRepository();
    }

    @Override
    public ClientUserRepository clientUsers() {
        return clientUsers(null);
    }

    /**
     * Creates an inmemory repository of product categories
     * @param autoTx the transactional context to enroll
     *
     * @return an inmemory repository of product categories
     */
    @Override
    public ProductCategoryRepository Categories(TransactionalContext autoTx) {
        return new InMemoryCategoryRepository();
    }

    /**
     * Creates an inmemory repository of product categories
     * @return an inmemory repository of product categories
     */
    @Override
    public ProductCategoryRepository Categories() {
        return Categories(null);
    }

    /**
     * Creates an inmemory repository of products
     * @param autoTx the transactional context to enroll
     *
     * @return an inmemory repository of products
     */
    @Override
    public ProductRepository Products(TransactionalContext autoTx) {
        return new InMemoryProductRepository();
    }

    /**
     * Creates an inmemory repository of products
     * @return an inmemory repository of products
     */
    @Override
    public ProductRepository Products() {
        return Products(null);
    }

    /**
     * Creates an inmemory repository of AGVDocks
     * @param autoTx the transactional context to enroll
     *
     * @return an inmemory repository of AGVDocks
     */
    @Override
    public AGVDockRepository Docks(TransactionalContext autoTx) {
        return new InMemoryAGVDockRepository();}


    /**
     * Creates an inmemory repository of AGVDocks
     * @return an inmemory repository of AGVDocks
     */
    @Override
    public AGVDockRepository Docks() {return Docks(null);}

    /**
     * Creates an inmemory repository of warehouses
     * @param autoTx the transactional context to enroll
     *
     * @return an inmemory repository of warehouses
     */
    @Override
    public WarehouseRepository Warehouses(TransactionalContext autoTx) {
        return new InMemoryWarehouseRepository();
    }

    /**
     * Creates an inmemory repository of warehouses
     * @return an inmemory repository of warehouses
     */
    @Override
    public WarehouseRepository Warehouses() {
        return Warehouses(null);
    }

    /**
     * Creates an inmemory repository of orders
     * @param autoTx the transactional context to enroll
     *
     * @return an inmemory repository of orders
     */
    @Override
    public OrderRepository Orders(TransactionalContext autoTx) {
        return new InMemoryOrderRepository();
    }

    /**
     * Creates an inmemory repository of orders
     * @return an inmemory repository of orders
     */
    @Override
    public OrderRepository Orders() {
        return Orders(null);
    }

    /**
     * Creates an inmemory repository of shipping methods
     * @param autoTx the transactional context to enroll
     *
     * @return an inmemory repository of shipping methods
     */
    @Override
    public ShippingMethodRepository ShippingMethods(TransactionalContext autoTx) {
        return new InMemoryShippingMethodRepository();
    }

    /**
     * Creates an inmemory repository of shipping methods
     * @return an inmemory repository of shipping methods
     */
    @Override
    public ShippingMethodRepository ShippingMethods() {
        return ShippingMethods(null);
    }


    @Override
    public CustomerRepository Customer(TransactionalContext autoTx) {
        return new InMemoryCustomerRepository();
    }

    @Override
    public CustomerRepository Customer() {
        return Customer(null);
    }

    /**
     * Creates an inmemory repository of sales clerks
     * @param autoTx the transactional context to enroll
     *
     * @return an inmemory repository of sales clerks
     */
    @Override
    public SalesClerkRepository SalesClerk(TransactionalContext autoTx) {
        return new InMemorySalesClerkRepository();
    }

    /**
     * Creates an inmemory repository of sales clerks
     * @return an inmemory repository of sales clerks
     */
    @Override
    public SalesClerkRepository SalesClerk() {
        return SalesClerk(null);
    }

    /**
     * Creates an inmemory repository of warehouse employees
     * @param autoTx the transactional context to enroll
     *
     * @return an inmemory repository of warehouse employees
     */
    @Override
    public WarehouseEmployeeRepository WarehouseEmployee(TransactionalContext autoTx) {
        return new InMemoryWarehouseEmployeeRepository();
    }

    /**
     * Creates an inmemory repository of warehouse employees
     *
     * @return an inmemory repository of warehouse employees
     */
    @Override
    public WarehouseEmployeeRepository WarehouseEmployee() {
        return WarehouseEmployee(null);
    }

    @Override
    public AGVManagerRepository AGVManager(TransactionalContext autoTx) {
        return new InMemoryAGVManagerRepository();
    }

    @Override
    public AGVManagerRepository AGVManager() {
        return AGVManager(null);
    }

    @Override
    public ShoppingCartRepository ShoppingCarts(TransactionalContext autoTx){ return new InMemoryShoppingCart();}

    @Override
    public ShoppingCartRepository ShoppingCarts() {return ShoppingCarts(null);}

    @Override
    public AGVRepository AGV(TransactionalContext autoTx) {
        return new InMemoryAGVRepository();
    }

    @Override
    public AGVRepository AGV() {
        return AGV(null);
    }

    @Override
    public QuestionnaireRepository Questionnaire(TransactionalContext autoTx) {
        return new InMemoryQuestionnaireRepository();
    }

    @Override
    public QuestionnaireRepository Questionnaire() {
        return Questionnaire(null);
    }

    @Override
    public AnsweredQuestionnaireRepository AnsweredQuestionnaire(TransactionalContext autoTx) {
        return new InMemoryAnsweredQuestionnaireRepository();
    }

    @Override
    public AnsweredQuestionnaireRepository AnsweredQuestionnaire() {
        return AnsweredQuestionnaire(null);
    }

    @Override
    public SignupRequestRepository signupRequests() {
        return signupRequests(null);
    }

    @Override
    public SignupRequestRepository signupRequests(final TransactionalContext tx) {
        return new InMemorySignupRequestRepository();
    }

    @Override
    public TransactionalContext newTransactionalContext() {
        // in memory does not support transactions...
        return null;
    }

}
