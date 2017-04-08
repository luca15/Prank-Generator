package model;

import model.mail.Person;
import java.util.List;

/**
 * This class represents a prank.
 * A prank is just a sender who send mail to recipients and he uses a message for the body of the mail.
 *
 * @author Luca Sivillica
 * @author Dany Tchente
 */
public class Prank {
    private String message;
    private Person sender;
    private List<Person> recipients;

    public Prank(String message, Person sender, List<Person> recipients) {
        this.message = message;
        this.sender = sender;
        this.recipients = recipients;
    }

    public String getMessage() {
        return message;
    }

    public String getEmailSender() {
        return sender.getEmailAddress();
    }

    public String[] getEmailRecipients() {
        final int RECIPIENTS_SIZE = recipients.size();
        String[] emailRecipients = new String[RECIPIENTS_SIZE];

        for (int i = 0; i < RECIPIENTS_SIZE; ++i) {
            emailRecipients[i] = recipients.get(i).getEmailAddress();
        }

        return emailRecipients;
    }
}
