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
package eapli.base.app.other.console.shared;

import eapli.base.Application;
import eapli.base.app.common.console.presentation.authz.MyUserMenu;
import eapli.framework.actions.menu.Menu;
import eapli.framework.actions.menu.MenuItem;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.ExitWithMessageAction;
import eapli.framework.presentation.console.menu.HorizontalMenuRenderer;
import eapli.framework.presentation.console.menu.MenuItemRenderer;
import eapli.framework.presentation.console.menu.MenuRenderer;
import eapli.framework.presentation.console.menu.VerticalMenuRenderer;

/**
 *
 * @author Paulo Gandra Sousa
 */
public abstract class MainMenu extends AbstractUI {

    protected static final String SEPARATOR_LABEL = "--------------";
    protected static final int EXIT_OPTION = 0;
    private static final int MY_USER_OPTION = 1;

    protected final AuthorizationService authz = AuthzRegistry.authorizationService();

    protected final Menu menu;
    protected final MenuRenderer renderer;

    public MainMenu(Role role) {
        menu = buildMainMenu(role);
        renderer = getRenderer(menu);
    }

    private MenuRenderer getRenderer(final Menu menu) {
        final MenuRenderer theRenderer;
        if (Application.settings().isMenuLayoutHorizontal()) {
            theRenderer = new HorizontalMenuRenderer(menu, MenuItemRenderer.DEFAULT);
        } else {
            theRenderer = new VerticalMenuRenderer(menu, MenuItemRenderer.DEFAULT);
        }
        return theRenderer;
    }

    @Override
    public boolean doShow() {
        return renderer.render();
    }

    @Override
    public boolean show() {
        drawFormTitle();
        return doShow();
    }

    @Override
    public String headline() {

        return authz.session().map(s -> "Base [ @" + s.authenticatedUser().identity() + " ]")
                .orElse("Base [ ==Anonymous== ]");
    }

    public Menu buildMainMenu(Role role) {
        final Menu mainMenu = new Menu();

        final Menu myUserMenu = new MyUserMenu(role);
        mainMenu.addSubMenu(MY_USER_OPTION, myUserMenu);

        if (!Application.settings().isMenuLayoutHorizontal()) {
            mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));
        }

        if (authz.isAuthenticatedUserAuthorizedTo(role)) {
             addSubMenus(mainMenu);
        }

        if (!Application.settings().isMenuLayoutHorizontal()) {
            mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));
        }

        mainMenu.addItem(EXIT_OPTION, "Exit", new ExitWithMessageAction("Bye, Bye"));

        return mainMenu;
    }

    protected void addSubMenus(Menu mainMenu) {}
}
