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
package eapli.base.infrastructure.persistence;

import eapli.base.agvdockmanagement.repositories.AGVDockRepository;
import eapli.base.agvmanagement.repositories.AGVRepository;
import eapli.base.agvmanagermanagement.repositories.AGVManagerRepository;
import eapli.base.clientusermanagement.repositories.ClientUserRepository;
import eapli.base.clientusermanagement.repositories.SignupRequestRepository;
import eapli.base.customermanagement.repositories.CustomerRepository;
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

/**
 * @author Paulo Gandra Sousa
 *
 */
public interface RepositoryFactory {

    /**
     * factory method to create a transactional context to use in the repositories
     *
     * @return
     */
    TransactionalContext newTransactionalContext();

    /**
     *
     * @param autoTx
     *            the transactional context to enrol
     * @return
     */
    UserRepository users(TransactionalContext autoTx);

    /**
     * repository will be created in auto transaction mode
     *
     * @return
     */
    UserRepository users();

    /**
     *
     * @param autoTx
     *            the transactional context to enroll
     * @return
     */
    ClientUserRepository clientUsers(TransactionalContext autoTx);

    /**
     * repository will be created in auto transaction mode
     *
     * @return
     */
    ClientUserRepository clientUsers();

    /**
     *
     * @param autoTx the transactional context to enroll
     *
     * @return a new categoryRepository
     */
    ProductCategoryRepository Categories(TransactionalContext autoTx);

    /**
     * repository will be created in auto transaction mode
     *
     * @return a new categoryRepository
     */
    ProductCategoryRepository Categories();

    /**
     *
     * @param autoTx the transactional context to enroll
     *
     * @return a new warehouseRepository
     */
    WarehouseRepository Warehouses(TransactionalContext autoTx);

    /**
     * repository will be created in auto transaction mode
     *
     * @return a new warehouseRepository
     */
    WarehouseRepository Warehouses();

    /**
     *
     * @param autoTx the transactional context to enroll
     *
     * @return a new orderRepository
     */
    OrderRepository Orders(TransactionalContext autoTx);

    /**
     * repository will be created in auto transaction mode
     *
     * @return a new orderRepository
     */
    OrderRepository Orders();

    /**
     *
     * @param autoTx the transactional context to enroll
     *
     * @return a new product repository
     */
    CustomerRepository Customer(TransactionalContext autoTx);

    /**
     * repository will be created in auto transaction mode
     *
     * @return a new warehouseRepository
     */
    CustomerRepository Customer();

    /**
     *
     * @param autoTx the transactional context to enroll
     *
     * @return a new Sales Clerk repository
     */
    SalesClerkRepository SalesClerk(TransactionalContext autoTx);

    /**
     * repository will be created in auto transaction mode
     *
     * @return a new SalesClerk Repository
     */
    SalesClerkRepository SalesClerk();

    /**
     *
     * @param autoTx the transactional context to enroll
     *
     * @return a new Sales Clerk repository
     */
    WarehouseEmployeeRepository WarehouseEmployee(TransactionalContext autoTx);

    /**
     * repository will be created in auto transaction mode
     *
     * @return a new SalesClerk Repository
     */
    WarehouseEmployeeRepository WarehouseEmployee();

    /**
     *
     * @param autoTx the transactional context to enroll
     *
     * @return a new product repository
     */
    AGVManagerRepository AGVManager(TransactionalContext autoTx);

    /**
     * repository will be created in auto transaction mode
     *
     * @return a new warehouseRepository
     */
    AGVManagerRepository AGVManager();

    /**
     *
     * @param autoTx the transactional context to enroll
     *
     * @return a new AGV Repository
     */
    AGVRepository AGV(TransactionalContext autoTx);

    /**
     * repository will be created in auto transaction mode
     *
     * @return a new AGV Repository
     */
    AGVRepository AGV();

    /**
     *
     * @param autoTx the transactional context to enroll
     *
     * @return a new product repository
     */
    ProductRepository Products(TransactionalContext autoTx);

    /**
     * repository will be created in auto transaction mode
     *
     * @return a new product repository
     */
    ProductRepository Products();

    /**
     *
     * @param autoTx the transactional context to enroll
     *
     * @return a new shipping method repository
     */
    ShippingMethodRepository ShippingMethods (TransactionalContext autoTx);

    /**
     * repository will be created in auto transaction mode
     *
     * @return a new shipping method repository
     */
    ShippingMethodRepository ShippingMethods();

    /**
     *
     * @param autoTx the transactional context to enroll
     *
     * @return a new AGVDock repository
     */
    AGVDockRepository Docks(TransactionalContext autoTx);

    /**
     * repository will be created in auto transaction mode
     *
     * @return a new AGVDock repository
     */
    AGVDockRepository Docks();

    /**
     *
     * @param autoTx the transactional context to enroll
     *
     * @return a new AGVDock repository
     */
    QuestionnaireRepository Questionnaire(TransactionalContext autoTx);

    /**
     * repository will be created in auto transaction mode
     *
     * @return a new Questionnaire repository
     */
    QuestionnaireRepository Questionnaire();

    /**
     *
     * @param autoTx the transactional context to enroll
     *
     * @return a new AnsweredQuestionnaire repository
     */
    AnsweredQuestionnaireRepository AnsweredQuestionnaire(TransactionalContext autoTx);

    /**
     * repository will be created in auto transaction mode
     *
     * @return a new AnsweredQuestionnaire repository
     */
    AnsweredQuestionnaireRepository AnsweredQuestionnaire();

    /**
     *
     * @param autoTx the transactional context to enroll
     *
     * @return a new ShoppingCart repository
     */
    ShoppingCartRepository ShoppingCarts(TransactionalContext autoTx);

    /**
     * repository will be created in auto transaction mode
     *
     * @return a new ShoppingCart Repository
     */
    ShoppingCartRepository ShoppingCarts();

    /**
     *
     * @param autoTx
     *            the transactional context to enroll
     * @return
     */
    SignupRequestRepository signupRequests(TransactionalContext autoTx);

    /**
     * repository will be created in auto transaction mode
     *
     * @return
     */
    SignupRequestRepository signupRequests();

}
