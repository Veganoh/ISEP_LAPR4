package eapli.base.app.server.orders.requestHandlers;

import eapli.base.app.server.orders.OrdersRequestHandler;
import eapli.base.common.domain.PacketUtils;
import eapli.base.customermanagement.domain.Customer;
import eapli.base.customermanagement.repositories.CustomerRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordermanagement.domain.Order;
import eapli.base.ordermanagement.repositories.OrderRepository;
import eapli.base.surveymanagement.domain.Questionnaire;
import eapli.base.surveymanagement.repositories.QuestionnaireRepository;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class AnswerSurveysHandler implements Runnable{
    /**
     * The socket that connects the server
     */
    private Socket myS;

    /**
     * input and output streams to the remote server
     */
    private DataInputStream sIn;
    private DataOutputStream sOut;

    public AnswerSurveysHandler(Socket myS, DataInputStream sIn, DataOutputStream sOut) {
        this.myS = myS;
        this.sIn = sIn;
        this.sOut = sOut;
    }

    @Override
    public void run() {
        try {
            SystemUser user = PacketUtils.readObject(sIn);

            QuestionnaireRepository questionnaireRepository = PersistenceContext.repositories().Questionnaire();
            ArrayList<Questionnaire> questionnaireList = (ArrayList<Questionnaire>) questionnaireRepository.findClientSurveys(user);

            sOut.write(PacketUtils.toData(PacketUtils.ACK, questionnaireList));

            //TODO add form of getting answered survey
        } catch (IOException e) {
            System.out.println("Could not open data stream.");
            System.exit(1);
        }
    }
}
