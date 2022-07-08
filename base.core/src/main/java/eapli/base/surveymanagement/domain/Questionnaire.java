package eapli.base.surveymanagement.domain;

import eapli.base.surveymanagement.application.questionnaireValidator;
import eapli.base.surveymanagement.application.questionnairesLexer;
import eapli.base.surveymanagement.application.questionnairesParser;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.io.util.Files;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.misc.Pair;

import javax.persistence.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Questionnaire implements AggregateRoot<QuestionnaireID> {

    @EmbeddedId
    private QuestionnaireID id;

    @Embedded
    private QuestionnaireFileText text;

    @Embedded
    private AudienceSize audienceSize;

    @Transient
    private List<TargetRule> targetRules = new ArrayList<>();



    public Questionnaire(String filePath) throws IOException {
        CharStream in = CharStreams.fromFileName(filePath);
        questionnairesLexer lexer = new questionnairesLexer(in);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        questionnairesParser parser = new questionnairesParser(tokens);

        questionnaireValidator validator = new questionnaireValidator();

        validator.visit(parser.questionnaires());

        if (validator.isQuestionnaireValid() && !(parser.getNumberOfSyntaxErrors() > 0)) {
            String fileContent = Files.textFrom(new FileInputStream(filePath));

            this.id = QuestionnaireID.valueOf(validator.getQuestionnaireId());
            this.text = QuestionnaireFileText.valueOf(fileContent);

            if (!validator.targetRules().isEmpty())
                for (List<Pair<String, String>> targetPair : validator.targetRules())
                    this.targetRules.add(TargetRule.valueOf(targetPair));
        } else {
            throw new IllegalArgumentException("The given questionnaire is invalid!");
        }

        audienceSize = AudienceSize.valueOf(0);
    }

    protected Questionnaire() {
        //for ORM only
    }

    public void attributeAudienceSize(AudienceSize audienceSize) {
        this.audienceSize = audienceSize;
    }

    /**
     * compares if two questionnaires are equal
     * @param other the questionnaire used for comparation
     * @return true if they are equal
     */
    @Override
    public boolean sameAs(Object other) {
        return DomainEntities.areEqual(this, other);
    }

    /**
     * gets the questionnaire's id
     * @return questionnaire id
     */
    @Override
    public QuestionnaireID identity() {
        return id;
    }

    public List<TargetRule> rules() {
        return targetRules;
    }

    public String text() {return text.toString();}

    public int audienceSize() {return audienceSize.value();}

    @Override
    public String toString() {
        return id.toString();
    }
}
