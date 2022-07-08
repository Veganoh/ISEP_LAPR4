package eapli.base.surveymanagement.controller;

import eapli.base.Application;
import eapli.base.common.domain.ConnectionEstablisher;
import eapli.base.common.domain.PacketUtils;
import eapli.base.ordermanagement.domain.Order;
import eapli.base.surveymanagement.domain.Questionnaire;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.validations.Preconditions;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.Set;

public class AnswerQuestionaireRequest {
    /**
     * The connected server's socket
     */
    private Socket sock;

    /**
     * Input and output streams to the remote server
     */
    private DataInputStream sIn;
    private DataOutputStream sOut;

    /**
     * Connection to server
     */
    private ConnectionEstablisher conn;

    /**
     * The authenticated system user
     */
    private SystemUser usr;

    public AnswerQuestionaireRequest(SystemUser usr) {
        Preconditions.nonNull(usr);
        this.usr = usr;

        this.conn = new ConnectionEstablisher(Application.settings().getOrderServer(), Application.settings().getOrderServerPort(), Application.settings().getOrdersKeyPath());

        try {
            this.sock = conn.establishConnection();
            sIn = new DataInputStream(sock.getInputStream());
            sOut = new DataOutputStream(sock.getOutputStream());
        } catch (Exception e) {
            System.out.println("Could not open data stream.");
            System.exit(1);
        }
    }

    public List<Questionnaire> getQuestionnaires() {
        try {
            sOut.write(PacketUtils.toData(6, usr));

            List<Questionnaire> questionnaires = PacketUtils.read(sIn, 2);

            this.conn.closeConnection();

            return questionnaires;
        } catch (IOException e) {
            System.out.println("Could not communicate with Orders server.");
            System.exit(1);
        }
        return null;
    }
}
