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
package eapli.base.app.other.console.warehouseEmployee;

import eapli.base.app.common.console.presentation.authz.LoginAction;
import eapli.base.app.other.console.shared.MainMenu;
import eapli.base.app.other.console.warehouseEmployee.agvConfiguration.ConfigureAGVAction;
import eapli.base.app.other.console.warehouseEmployee.agvConfiguration.ShowConfiguredAGVAction;
import eapli.base.app.other.console.warehouseEmployee.agvDashboard.LoadAGVDashboardAction;
import eapli.base.app.other.console.warehouseEmployee.agvTasks.ShowToBePreparedOrdersAction;
import eapli.base.app.other.console.warehouseEmployee.salesAndOrders.ShowPreparedOrdersAction;
import eapli.base.app.other.console.warehouseEmployee.warehouseplant.LoadWarehousePlantAction;
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
public final class WarehouseEmployeeApp extends MainMenu {

    private static final String RETURN_LABEL = "Return ";

    // MAIN MENU
    private static final int WAREHOUSE_OPTION = 2;

    private static final int AGV_OPTION = 3;

    private static final int ORDERS_OPTION = 4;

    //SUB MENU WAREHOUSE
    private static final int LOAD_WAREHOUSE_PLANT = 1;

    //SUB MENU AGV
    private static final int CONFIGURE_AGV_OPTION = 1;
    private static final int LIST_AGV_OPTION = 2;
    private static final int ASSIGN_AGV_TASK = 3;
    private static final int SHOW_AGV_DASHBOARD = 4;

    //SUB MENU ORDERS
    private static final int SHOW_ORDERS_FOR_DISPATCH = 1;

    private static final Role role = BaseRoles.WAREHOUSE_EMPLOYEE;

    /**
     * Empty constructor is private to avoid instantiation of this class.
     */
    private WarehouseEmployeeApp() {
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
            new WarehouseEmployeeApp().mainLoop();
        }

        // exiting the application, closing all threads
        System.exit(0);
    }

    /**
     * adds the needed submenus
     * @param mainMenu the main menu
     */
    @Override
    protected void addSubMenus(Menu mainMenu) {
        final Menu warehouseMenu = buildWarehouseMenu();
        final Menu agvMenu = buildAGVConfigurationMenu();
        final Menu ordersMenu = buildSalesAndOrdersMenu();

        mainMenu.addSubMenu(WAREHOUSE_OPTION, warehouseMenu);
        mainMenu.addSubMenu(AGV_OPTION, agvMenu);
        mainMenu.addSubMenu(ORDERS_OPTION,ordersMenu);
    }

    /*
     * Sub-menu for Warehouse
     */
    private Menu buildWarehouseMenu() {
        final Menu menu = new Menu("Warehouse >");

        menu.addItem(LOAD_WAREHOUSE_PLANT, "Load warehouse plant", new LoadWarehousePlantAction());
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    /*
     * Sub-menu for AGV Configuration
     */
    private Menu buildAGVConfigurationMenu() {
        final Menu agvMenu = new Menu("AGV Configuration  >");

        agvMenu.addItem(CONFIGURE_AGV_OPTION, "Configure AGV", new ConfigureAGVAction());
        agvMenu.addItem(LIST_AGV_OPTION, "Show all configured AGV", new ShowConfiguredAGVAction());
        agvMenu.addItem(ASSIGN_AGV_TASK, "Assign orders to AGV", new ShowToBePreparedOrdersAction());
        agvMenu.addItem(SHOW_AGV_DASHBOARD, "Show AGV status dashboard", new LoadAGVDashboardAction());
        agvMenu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return agvMenu;
    }

    /*
     * Sub-menu for Sales and Orders
     */
    private Menu buildSalesAndOrdersMenu(){
        final Menu ordersMenu = new Menu("Sales and Orders >");

        ordersMenu.addItem(SHOW_ORDERS_FOR_DISPATCH, "Show All Orders Ready for Dispatch", new ShowPreparedOrdersAction());
        ordersMenu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return ordersMenu;
    }
}
