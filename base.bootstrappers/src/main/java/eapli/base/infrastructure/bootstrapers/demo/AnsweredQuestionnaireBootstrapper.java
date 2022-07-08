package eapli.base.infrastructure.bootstrapers.demo;


import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.surveymanagement.domain.AnsweredQuestionnaire;
import eapli.base.surveymanagement.domain.AudienceSize;
import eapli.base.surveymanagement.domain.Questionnaire;
import eapli.base.surveymanagement.domain.QuestionnaireFileText;
import eapli.base.surveymanagement.repositories.AnsweredQuestionnaireRepository;
import eapli.base.surveymanagement.repositories.QuestionnaireRepository;
import eapli.framework.actions.Action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class AnsweredQuestionnaireBootstrapper implements Action {

    AnsweredQuestionnaireRepository repo = PersistenceContext.repositories().AnsweredQuestionnaire();

    Questionnaire baseQuestionnaire;

    public AnsweredQuestionnaireBootstrapper() {
        registerBaseQuestionnaire();
    }

    @Override
    public boolean execute() {
        registerAnsweredQuestionnaire("base.bootstrappers/src/main/java/eapli/base/infrastructure/bootstrapers/demo/bootstrapfiles/questionnaire_statistics_answer_1.json", baseQuestionnaire);
        registerAnsweredQuestionnaire("base.bootstrappers/src/main/java/eapli/base/infrastructure/bootstrapers/demo/bootstrapfiles/questionnaire_statistics_answer_2.json", baseQuestionnaire);
        registerAnsweredQuestionnaire("base.bootstrappers/src/main/java/eapli/base/infrastructure/bootstrapers/demo/bootstrapfiles/questionnaire_statistics_answer_3.json", baseQuestionnaire);
        registerAnsweredQuestionnaire("base.bootstrappers/src/main/java/eapli/base/infrastructure/bootstrapers/demo/bootstrapfiles/questionnaire_statistics_answer_4.json", baseQuestionnaire);
        return true;
    }

    private void registerAnsweredQuestionnaire(String path, Questionnaire questionnaire) {
        String fileContent = null;
        try {
            fileContent = Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Unable to find bootstrapper file for questionnaires.");
        }

        AnsweredQuestionnaire answer = new AnsweredQuestionnaire(QuestionnaireFileText.valueOf(fileContent), questionnaire);
        repo.save(answer);
    }

    private void registerBaseQuestionnaire() {
        try {
            baseQuestionnaire = new Questionnaire("configfiles/questionnaire.json");
            baseQuestionnaire.attributeAudienceSize(AudienceSize.valueOf(4));

            QuestionnaireRepository qRepo = PersistenceContext.repositories().Questionnaire();
            qRepo.save(baseQuestionnaire);
        } catch (IOException e) {
            System.out.println("Unable to register base questionnaire to bootstrap.");
        }
    }
}
