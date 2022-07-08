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
package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.agvdockmanagement.repositories.AGVDockRepository;
import eapli.base.agvmanagement.repositories.AGVRepository;
import eapli.base.agvmanagermanagement.repositories.AGVManagerRepository;
import eapli.base.clientusermanagement.repositories.SignupRequestRepository;
import eapli.base.customermanagement.repositories.CustomerRepository;
import eapli.base.infrastructure.persistence.RepositoryFactory;
import eapli.base.ordermanagement.repositories.OrderRepository;
import eapli.base.ordermanagement.repositories.SalesClerkRepository;
import eapli.base.shipmentmanagement.repositories.ShippingMethodRepository;
import eapli.base.shoppingcartmanagement.repositories.ShoppingCartRepository;
import eapli.base.surveymanagement.repositories.AnsweredQuestionnaireRepository;
import eapli.base.surveymanagement.repositories.QuestionnaireRepository;
import eapli.base.warehousemanagement.repositories.WarehouseEmployeeRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import eapli.framework.infrastructure.authz.repositories.impl.JpaAutoTxUserRepository;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

/**
 *
 * Created by nuno on 21/03/16.
 */
public class JpaRepositoryFactory implements RepositoryFactory {

    @Override
    public UserRepository users(final TransactionalContext autoTx) {
        return new JpaAutoTxUserRepository(autoTx);
    }

    @Override
    public UserRepository users() {
        return new JpaAutoTxUserRepository(Application.settings().getPersistenceUnitName(),
                Application.settings().getExtendedPersistenceProperties());
    }

    @Override
    public JpaClientUserRepository clientUsers(final TransactionalContext autoTx) {
        return new JpaClientUserRepository(autoTx);
    }

    @Override
    public JpaClientUserRepository clientUsers() {
        return new JpaClientUserRepository(Application.settings().getPersistenceUnitName());
    }

    /**
     * creates a new category repository
     * @param autoTx the transactional context to enroll
     *
     * @return a new category repository
     */
    @Override
    public JpaCategoryRepository Categories(final TransactionalContext autoTx) {
        return new JpaCategoryRepository(autoTx);
    }

    /**
     * creates a new category repository
     * @return a new category repository
     */
    @Override
    public JpaCategoryRepository Categories() {
        return new JpaCategoryRepository(Application.settings().getPersistenceUnitName());
    }

    /**
     * creates a new warehouse repository
     * @param autoTx the transactional context to enroll
     *
     * @return a new warehouse repository
     */
    @Override
    public JpaWarehouseRepository Warehouses(final TransactionalContext autoTx) {
        return new JpaWarehouseRepository(autoTx);
    }

    /**
     * creates a new warehouse repository
     * @return a new warehouse repository
     */
    @Override
    public JpaWarehouseRepository Warehouses() {
        return new JpaWarehouseRepository(Application.settings().getPersistenceUnitName());
    }
    /**
     * creates a new orders repository
     * @param autoTx the transactional context to enroll
     *
     * @return a new orders repository
     */
    @Override
    public OrderRepository Orders(TransactionalContext autoTx) {
        return new JpaOrderRepository(autoTx);
    }
    /**
     * creates a new orders repository
     * @return a new orders repository
     */
    @Override
    public OrderRepository Orders() {
        return new JpaOrderRepository(Application.settings().getPersistenceUnitName());
    }

    /**
     * creates a new shipping method repository
     * @param autoTx the transactional context to enroll
     *
     * @return a new shipping method repository
     */
    @Override
    public ShippingMethodRepository ShippingMethods(TransactionalContext autoTx) {
        return new JpaShippingMethodRepository(autoTx);
    }
    /**
     * creates a new shipping method repository
     * @return a new shipping method repository
     */
    @Override
    public ShippingMethodRepository ShippingMethods() {
        return new JpaShippingMethodRepository(Application.settings().getPersistenceUnitName());
    }


    @Override
    public CustomerRepository Customer(TransactionalContext autoTx) {
        return new JpaCustomerRepository(autoTx);
    }

    @Override
    public CustomerRepository Customer() {
        return new JpaCustomerRepository(Application.settings().getPersistenceUnitName());
    }

    /**
     * creates a new sales clerk repository
     * @param autoTx the transactional context to enroll
     *
     * @return a new sales clerk repository
     */
    @Override
    public SalesClerkRepository SalesClerk(TransactionalContext autoTx) {
        return new JpaSalesClerkRepository(autoTx);
    }

    /**
     * creates a new sales clerk repository
     * @return a new sales clerk repository
     */
    @Override
    public SalesClerkRepository SalesClerk() {
        return new JpaSalesClerkRepository(Application.settings().getPersistenceUnitName());
    }

    /**
     * creates a new warehouse employee repository
     * @param autoTx the transactional context to enroll
     *
     * @return a new warehouse employee repository
     */
    @Override
    public WarehouseEmployeeRepository WarehouseEmployee(TransactionalContext autoTx) {
        return new JpaWarehouseEmployeeRepository(autoTx);
    }

    /**
     * creates a new warehouse employee repository
     * @return a new warehouse employee repository
     */
    @Override
    public WarehouseEmployeeRepository WarehouseEmployee() {
        return new JpaWarehouseEmployeeRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public ShoppingCartRepository ShoppingCarts(){
        return new JpaShoppingCartRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public ShoppingCartRepository ShoppingCarts(TransactionalContext autoTx){
        return new JpaShoppingCartRepository(autoTx);
    }

    @Override
    public AGVManagerRepository AGVManager(TransactionalContext autoTx) {
        return new JpaAGVManagerRepository(autoTx);
    }

    @Override
    public AGVManagerRepository AGVManager() {
        return new JpaAGVManagerRepository(Application.settings().getPersistenceUnitName());
    }

    /**
     * creates a new agv repository
     * @param autoTx the transactional context to enroll
     *
     * @return a new agv repository
     */
    @Override
    public AGVRepository AGV(TransactionalContext autoTx) {
        return new JpaAGVRepository(autoTx);
    }

    /**
     * creates a new agv repository
     * @return a new agv repository
     */
    @Override
    public AGVRepository AGV() {
        return new JpaAGVRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public JpaProductRepository Products(TransactionalContext autoTx) {
        return new JpaProductRepository(autoTx);
    }

    @Override
    public JpaProductRepository Products() {
        return new JpaProductRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public AGVDockRepository Docks(TransactionalContext autoTx) {
        return new JpaAGVDockRepository(autoTx);
    }

    @Override
    public AGVDockRepository Docks() {
        return new JpaAGVDockRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public QuestionnaireRepository Questionnaire(TransactionalContext autoTx){
        return new JpaQuestionnaireRepository(autoTx);
    }

    @Override
    public AnsweredQuestionnaireRepository AnsweredQuestionnaire() {
        return new JpaAnsweredQuestionnaireRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public AnsweredQuestionnaireRepository AnsweredQuestionnaire(TransactionalContext autoTx){
        return new JpaAnsweredQuestionnaireRepository(autoTx);
    }

    @Override
    public QuestionnaireRepository Questionnaire() {
        return new JpaQuestionnaireRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public SignupRequestRepository signupRequests(final TransactionalContext autoTx) {
        return new JpaSignupRequestRepository(autoTx);
    }

    @Override
    public SignupRequestRepository signupRequests() {
        return new JpaSignupRequestRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public TransactionalContext newTransactionalContext() {
        return JpaAutoTxRepository.buildTransactionalContext(Application.settings().getPersistenceUnitName(),
                Application.settings().getExtendedPersistenceProperties());
    }

}
