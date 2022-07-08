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
package eapli.base.app.other.console.salesManager;

import eapli.base.app.common.console.presentation.authz.LoginAction;
import eapli.base.app.other.console.salesManager.questionnaires.CreateSurveyAction;
import eapli.base.app.other.console.salesManager.questionnaires.ShowStatisticalReportAction;
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
public final class SalesManagerApp extends MainMenu {

    // MAIN MENU
    private static final int SURVEY_OPTION = 2;

    //SUB MENU SURVEY
    private static final int CREATE_SURVEY = 1;
    private static final int SURVEY_STATISTICS = 2;

    private static final Role role = BaseRoles.SALES_MANAGER;
    /**
     * Empty constructor is private to avoid instantiation of this class.
     */
    private SalesManagerApp() {
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
            new SalesManagerApp().mainLoop();
        }

        // exiting the application, closing all threads
        System.exit(0);
    }

    /**
     * adds the needed submenus
     * @param mainMenu
     */
    @Override
    protected void addSubMenus(Menu mainMenu) {
        final Menu surveyMenu = buildSurveyMenu();

        mainMenu.addSubMenu(SURVEY_OPTION, surveyMenu);
    }

    private Menu buildSurveyMenu(){
        final Menu surveyMenu = new Menu("Survey >");

        surveyMenu.addItem(CREATE_SURVEY, "Create new Survey", new CreateSurveyAction());
        surveyMenu.addItem(SURVEY_STATISTICS, "View Survey statistics", new ShowStatisticalReportAction());
        surveyMenu.addItem(EXIT_OPTION, "Return", Actions.SUCCESS);

        return surveyMenu;
    }
}
