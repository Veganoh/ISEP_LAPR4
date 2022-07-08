/*
 * Copyright (c) 2013-2019 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package eapli.base.app.user.console.presentation;

import eapli.base.app.common.console.presentation.authz.MyUserMenu;
import eapli.base.app.user.console.presentation.clientuser.AddProductShoppingCartAction;
import eapli.base.app.user.console.presentation.clientuser.AnswerQuestionnaireAction;
import eapli.base.app.user.console.presentation.clientuser.ShowOpenOrdersAction;
import eapli.base.app.user.console.presentation.clientuser.ShowShoppingCartAction;
import eapli.framework.actions.Actions;
import eapli.framework.actions.menu.Menu;
import eapli.framework.actions.menu.MenuItem;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.ExitWithMessageAction;
import eapli.framework.presentation.console.menu.MenuItemRenderer;
import eapli.framework.presentation.console.menu.MenuRenderer;
import eapli.framework.presentation.console.menu.VerticalMenuRenderer;

/**
 * @author Paulo Gandra Sousa
 */
class MainMenu extends ClientUserBaseUI {

    private static final String SEPARATOR_LABEL = "--------------";

    private static final String RETURN = "Return ";

    private static final String NOT_IMPLEMENTED_YET = "Not implemented yet";

    private static final int EXIT_OPTION = 0;

    // MAIN MENU
    private static final int MY_USER_OPTION = 1;

    private static final int SHOPPING_CART_OPTION = 2;
    private static final int ORDERS_OPTION = 3;
    private static final int SURVEY_OPTION = 4;

    // SHOPPING CART MENU
    private static final int ADD_PRODUCT_SHOPPING_CART = 1;
    private static final int SHOW_SHOPPING_CART = 2;
    // ORDERS MENU
    private static final int SHOW_OPEN_ORDERS = 1;

    //SURVEY MENU
    private static final int ANSWER_QUESTIONNAIRE_OPTION = 1;

    // SETTINGS
    private static final int SET_USER_ALERT_LIMIT_OPTION = 1;

    private final AuthorizationService authz =
            AuthzRegistry.authorizationService();

    @Override
    public boolean show() {
        drawFormTitle();
        return doShow();
    }

    /**
     * @return true if the user selected the exit option
     */
    @Override
    public boolean doShow() {
        final Menu menu = buildMainMenu();
        final MenuRenderer renderer =
                new VerticalMenuRenderer(menu, MenuItemRenderer.DEFAULT);
        return renderer.render();
    }

    private Menu buildMainMenu() {
        final Menu mainMenu = new Menu();

        final Menu myUserMenu = new MyUserMenu();
        mainMenu.addSubMenu(MY_USER_OPTION, myUserMenu);
        final Menu shoppingCartMenu = buildShoppingCartMenu();
        mainMenu.addSubMenu(SHOPPING_CART_OPTION,shoppingCartMenu);
        final Menu ordersMenu = buildOrdersMenu();
        mainMenu.addSubMenu(ORDERS_OPTION,ordersMenu);
        final Menu surveyMenu = buildSurveyMenu();
        mainMenu.addSubMenu(SURVEY_OPTION,surveyMenu);

        mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));

        mainMenu.addItem(EXIT_OPTION, "Exit", new ExitWithMessageAction("Bye, Bye"));

        return mainMenu;
    }

    private Menu buildSurveyMenu() {
        final Menu customerMenu = new Menu("Surveys  >");

        customerMenu.addItem(ANSWER_QUESTIONNAIRE_OPTION, "Answer a questionnaire", new AnswerQuestionnaireAction());
        customerMenu.addItem(EXIT_OPTION, "Return", Actions.SUCCESS);

        return customerMenu;
    }

    private Menu buildShoppingCartMenu(){
        final Menu customerMenu = new Menu("Shopping Cart  >");

        customerMenu.addItem(ADD_PRODUCT_SHOPPING_CART, "Add a new product to Shopping Cart", new AddProductShoppingCartAction());
        customerMenu.addItem(SHOW_SHOPPING_CART,"See Shopping Cart",new ShowShoppingCartAction());
        customerMenu.addItem(EXIT_OPTION, "Return", Actions.SUCCESS);

        return customerMenu;
    }

    private Menu buildOrdersMenu(){
        final Menu customerMenu = new Menu("Orders  >");

        customerMenu.addItem(SHOW_OPEN_ORDERS, "Show open orders", new ShowOpenOrdersAction());
        customerMenu.addItem(EXIT_OPTION, "Return", Actions.SUCCESS);

        return customerMenu;
    }
}
