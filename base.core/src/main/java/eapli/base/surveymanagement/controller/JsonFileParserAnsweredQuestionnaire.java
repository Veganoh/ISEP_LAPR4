package eapli.base.surveymanagement.controller;

import com.google.gson.Gson;

import java.io.File;
import java.util.List;

public class JsonFileParserAnsweredQuestionnaire {

    public class JSONQuestionnaire{
        public String Document;
        private String ID;
        private String Title;
        private String WelcomeMessage;
        private List<Sections> Sections;
        public class Sections{
            private int SectionID;
            private String SectionTitle;
            private String SectionDescription;
            private String Obligatoriness;
            private String Repeatability;
            private List<Content> Content;
            public class Content{
                private int QuestionID;
                private String Question;
                private String Instruction;
                private String Type;
                private ExtraInfo ExtraInfo;
                private String Obligatoriness;
                private Answer Answer;

                public class ExtraInfo{
                    private List<String> Options;
                    private int MaximumOptions;
                    private int MaximumCharacters;

                    public void content(List<String> Options){
                        this.Options = Options;
                    }

                    public void maximumOptions(int MaximumOptions) {
                        this.MaximumOptions = MaximumOptions;
                    }

                    public void maximumCharacters(int MaximumCharacters) {
                        this.MaximumCharacters = MaximumCharacters;
                    }
                }

                public class Answer{
                    private AnsweredQuestionnaireJSONAdapter answer;
                }

                public void questionID(int QuestionID) {
                    this.QuestionID = QuestionID;
                }

                public void question(String Question){
                    this.Question = Question;
                }

                public void instruction(String Instruction){
                    this.Instruction = Instruction;
                }

                public void type(String Type){
                    this.Type = Type;
                }

                public void extraType(ExtraInfo ExtraInfo){
                    this.ExtraInfo = ExtraInfo;
                }

                public void obligatoriness(String Obligatoriness) {
                    this.Obligatoriness = Obligatoriness;
                }

                public void answer(Answer Answer){
                    this.Answer = Answer;
                }
            }

            public void sectionID(int SectionID) {
                this.SectionID = SectionID;
            }

            public void sectionTitle(String SectionTitle) {
                this.SectionTitle = SectionTitle;
            }

            public void sectionDescription(String SectionDescription) {
                this.SectionDescription = SectionDescription;
            }

            public void obligatoriness(String Obligatoriness) {
                this.Obligatoriness = Obligatoriness;
            }

            public void repeatability(String Repeatability) {
                this.Repeatability = Repeatability;
            }

            public void content(List<Content> Content){
                this.Content = Content;
            }

            public Content jsonContent(){
                return new Content();
            }
        }

        private String FinalMessage;

        public void document(String Document) {
            this.Document = Document;
        }

        public void ID(String ID) {
            this.ID = ID;
        }

        public void title(String Title) {
            this.Title = Title;
        }

        public void welcomeMessage(String WelcomeMessage) {
            this.WelcomeMessage = WelcomeMessage;
        }

        public void sections(List<Sections> Sections){
            this.Sections = Sections;
        }

        public void finalMessage(String FinalMessage){
            this.FinalMessage = FinalMessage;
        }

        public Sections jsonSections(){
            return new Sections();
        }
    }

    private final Gson parser = new Gson();
    private final File file;

    public JsonFileParserAnsweredQuestionnaire() {
        this.file = null;
    }

    public JsonFileParserAnsweredQuestionnaire(String filepath) {
        this.file = new File(filepath);
    }

    public JSONQuestionnaire jsonQuestionnaire(){
        return new JSONQuestionnaire();
    }
}
