package model;

import config.ConfigurationManager;
import model.mail.Group;
import model.mail.Mail;
import model.mail.Person;
import smtp.SmtpClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class represents a prank generator.
 * The prank generator create groups, for each group it choose randomly a sender of the mail for the prank
 * and also choose randomly a message for the prank which will be sent by mail.
 *
 * @author Luca Sivillica
 * @author Dany Tchente
 */
public class PrankGenerator {
    private final int nbVictimsPerGroup;

    private ConfigurationManager config;
    private SmtpClient smtpClient;
    private List<Prank> pranks = new ArrayList<>();
    private List<Group> groups = new ArrayList<>();
    private Random rand = new Random();

    public PrankGenerator(String fileProperties, String fileVictims, String fileMessages) throws IOException {
        config = new ConfigurationManager(fileProperties, fileVictims, fileMessages);
        nbVictimsPerGroup = config.getNumberOfVictimsPerGroup();
        smtpClient = new SmtpClient(config.getSmtpServerDomain(), config.getSmtpPort());
    }

    /**
     * This method allows to send by mail all the pranks.
     *
     * @throws IOException
     */
    public void sendPranks() throws IOException, ErrorConfigPropertiesException {
        Mail mail;

        if (groups.isEmpty()) {
            createGroups();
        }

        if (pranks.isEmpty()) {
            createPranks();
        }

        for (Prank prank : pranks) {
            mail = new Mail(prank.getEmailSender(), prank.getEmailRecipients(), prank.getMessage());
            smtpClient.sendMail(mail);
        }
    }

    /**
     * This method creates the pranks
     */
    private void createPranks() {
        final int NUMBER_OF_GROUPS = groups.size();
        List<String> messages = config.getListOfMessages();

        /* for each group we pick a random message, a random sender and
           we create a prank with these elements */
        for (int i = 0; i < NUMBER_OF_GROUPS; ++i) {
            String message = messages.get(rand.nextInt(messages.size()));

            List<Person> victims = groups.get(i).getListOfPersons();

            int indexSender = rand.nextInt(victims.size());
            Person sender = victims.get(indexSender);

            victims.remove(indexSender); // we remove the sender for used this list like recipients

            pranks.add(new Prank(message, sender, victims));
        }
    }

    /**
     * This method creates the groups
     */
    private void createGroups() throws ErrorConfigPropertiesException {
        List<Person> victims = config.getListOfVictims();

        final int NUMBER_OF_VICTIMS = victims.size();
        final int NUMBER_OF_GROUPS  = config.getNumberOfGroups();

        /* if there isn't too much victims for create groups or the number of victims isn't a multiple
           of the number of victims per group then we throw an ErrorConfigPropertiesException exception */
        if (NUMBER_OF_VICTIMS / nbVictimsPerGroup != NUMBER_OF_GROUPS ||
            NUMBER_OF_VICTIMS % nbVictimsPerGroup != 0) {

            throw new ErrorConfigPropertiesException("Not possible to create " + NUMBER_OF_GROUPS + " groups" +
                                                     " with " + NUMBER_OF_VICTIMS + " victims");
        }

        /* we create groups by adding a list of persons */
        for (int i = 0; i < NUMBER_OF_GROUPS; ++i) {
            int fromIndex = i * nbVictimsPerGroup;
            int toIndex   = fromIndex + nbVictimsPerGroup;

            Group currentGroup = new Group();
            currentGroup.addPersons(victims.subList(fromIndex, toIndex));
            groups.add(currentGroup);
        }
    }
}
