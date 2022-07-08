package eapli.base.app.other.console.salesManager.questionnaires;

import eapli.base.Application;
import eapli.base.surveymanagement.controller.CreateQuestionnaireController;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.io.IOException;

public class CreateSurveyUI extends AbstractUI {

    CreateQuestionnaireController controller = new CreateQuestionnaireController();

    @Override
    protected boolean doShow(){
        try{
            String jsonFileName = Application.settings().getSurveyFilePath();
            final String json = "Would you like to create survey from file " + jsonFileName + "? (Y/N)\n";

            if(Console.readBoolean(json)) {
                if (this.controller.load(jsonFileName)) {
                    System.out.println("\nSurvey successfully loaded!\n");
                } else {
                    System.out.println("\nUnable to create survey\n");
                }
            }
        } catch (final Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public String headline() {
        return "Create new Survey";
    }
}
