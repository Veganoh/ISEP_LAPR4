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
package eapli.base.app.other.console.salesClerk;

import eapli.base.app.common.console.presentation.authz.LoginAction;
import eapli.base.app.other.console.salesClerk.manageUsers.AddCustomerAction;
import eapli.base.app.other.console.salesClerk.salesandorders.*;
import eapli.base.app.other.console.shared.MainMenu;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.usermanagement.domain.BasePasswordPolicy;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.actions.Actions;
import eapli.framework.actions.menu.Menu;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import eapli.framework.infrastructure.authz.domain.model.Role;

/**
 *
 * @author Paulo Gandra Sousa
 */
@SuppressWarnings("squid:S106")
public final class SalesClerkApp extends MainMenu {

    // MAIN MENU
    private static final int SALES_OPTION = 2;
    private static final int CUSTOMER_OPTION = 3;

    //SALES MENU
    private static final int ADD_CATEGORY = 1;
    private static final int ADD_PRODUCT = 2;
    private static final int ADD_ORDER = 3;
    private static final int SHOW_CATALOG = 4;
    private static final int SHOW_ORDERS = 5;
    private static final int SHOW_DISPATCHED_ORDERS = 6;


    //CUSTOMER MENU
    private static final int ADD_CUSTOMER = 1;

    private static final Role role = BaseRoles.SALES_CLERK;
    /**
     * Empty constructor is private to avoid instantiation of this class.
     */
    private SalesClerkApp() {
        super(role);
    }

    public static void main(final String[] args) {
        System.out.println("=====================================");
        System.out.println("Base POS");
        System.out.println("(C) 2016 - 2022");
        System.out.println("=====================================");

        AuthzRegistry.configure(PersistenceContext.repositories().users(),
                new BasePasswordPolicy(), new PlainTextEncoder());

        // login and go to main menu
        if (new LoginAction(role).execute()) {
            new SalesClerkApp().mainLoop();
        }

        // exiting the application, closing all threads
        System.exit(0);
    }


    @Override
    public void addSubMenus(Menu mainMenu) {
        final Menu cashierMenu = buildSalesMenu();
        mainMenu.addSubMenu(SALES_OPTION, cashierMenu);
        final Menu customerMenu = buildCustomerMenu();
        mainMenu.addSubMenu(CUSTOMER_OPTION, customerMenu);
    }

    private Menu buildSalesMenu() {
        final Menu cashierMenu = new Menu("Sales and Orders  >");

        cashierMenu.addItem(ADD_CATEGORY, "Add a new Product Category", new RegisterCategoryAction());
        cashierMenu.addItem(ADD_PRODUCT, "Add a new Product", new AddProductAction());
        cashierMenu.addItem(ADD_ORDER, "Add a new Order", new RegisterOrderAction());
        cashierMenu.addItem(SHOW_CATALOG,"Show catalog",new ShowCatalogAction());
        cashierMenu.addItem(SHOW_ORDERS,"Show Saved Orders",new ShowSavedOrdersAction());
        cashierMenu.addItem(SHOW_DISPATCHED_ORDERS, "Show Dispatched Order", new ShowDispatchedOrderAction());

        cashierMenu.addItem(EXIT_OPTION, "Return", Actions.SUCCESS);

        return cashierMenu;
    }

    private Menu buildCustomerMenu(){
        final Menu cashierMenu = new Menu("Customer and Users  >");

        cashierMenu.addItem(ADD_CUSTOMER, "Add a new Customer", new AddCustomerAction());

        cashierMenu.addItem(EXIT_OPTION, "Return", Actions.SUCCESS);

        return cashierMenu;
    }
}
