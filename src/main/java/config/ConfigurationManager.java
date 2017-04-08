package config;

import model.mail.Person;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * This class allows to parse configuration files where all the informations for pranks are stored.
 * After parsing it's possible to get each information.
 *
 * @author Luca Sivillica
 * @author Dany Tchente
 */
public class ConfigurationManager {
    public static final String CHARSET = "UTF-8"; // the default charset of the files
    public static final String END_LINE = "\r\n";

    private Properties properties = new Properties();
    private List<Person> victims = new ArrayList<>();
    private List<String> messages = new ArrayList<>();

    public ConfigurationManager(String fileProperties, String fileVictims, String fileMessages) throws IOException {
        /* Parse all config files */
        parseFileProperties(fileProperties);
        parseFileVictims(fileVictims);
        parseFileMessages(fileMessages);
    }

    /**
     * This method returns the domain of the server SMTP.
     *
     * @return the domain of the server SMTP
     */
    public String getSmtpServerDomain() {
        return properties.getProperty("smptServerDomain");
    }

    /**
     * This method returns the port on which the server SMTP listen.
     *
     * @return the port on which the server SMTP listen.
     */
    public int getSmtpPort() {
        return Integer.parseInt(properties.getProperty("smtpServerPort"));
    }

    /**
     * This method returns the number of groups of victims that we want to use for pranks.
     *
     * @return the number of groups of victims
     */
    public int getNumberOfGroups() {
        return Integer.parseInt(properties.getProperty("numberOfGroups"));
    }

    /**
     * This method returns the number of victims per group that we want to use for pranks.
     *
     * @return the number of victims per group
     */
    public int getNumberOfVictimsPerGroup() {
        return Integer.parseInt(properties.getProperty("numberOfVictimsPerGroup"));
    }

    /**
     * This method returns the list of all victims that we can use for pranks.
     *
     * @return the list of all victims
     */
    public List<Person> getListOfVictims() {
        return victims;
    }

    /**
     * This method returns the list of all messages that we can use for pranks.
     *
     * @return the list of all messages
     */
    public List<String> getListOfMessages() {
        return messages;
    }

    /**
     * This method parses a Java Properties file.
     *
     * @param fileProperties the path of the properties file
     * @throws IOException
     */
    private void parseFileProperties(String fileProperties) throws IOException {
        InputStreamReader reader = new InputStreamReader(new FileInputStream(fileProperties), CHARSET);

        properties.load(reader);

        reader.close();
    }

    /**
     * This method parses a file which contains a list of victims e-mail address for pranks.
     *
     * @param fileVictims the path of the victims file
     * @throws IOException
     */
    private void parseFileVictims(String fileVictims) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileVictims), CHARSET));
        String currentLine;

        currentLine = reader.readLine();

        /* each line of the file is an e-mail address, so we create a person with this address
           and we add this person to the list of victims */
        while (currentLine != null) {
            victims.add(new Person(currentLine));
            currentLine = reader.readLine();
        }

        reader.close();
    }

    /**
     * This method parses a file which contains a list of messages for pranks.
     *
     * @param fileMessages the path of the messages file
     * @throws IOException
     */
    private void parseFileMessages(String fileMessages) throws IOException {
        final String DELIMITER_MESSAGES = "=="; // The messages are separated with this delimiter in the file

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileMessages), CHARSET));

        String[] messages;
        StringBuilder messagesText = new StringBuilder();

        String lineText = reader.readLine();

        /* we read all the file */
        while (lineText != null) {
            messagesText.append(lineText).append(END_LINE);
            lineText = reader.readLine();
        }

        /* we separate each message */
        messages = messagesText.toString().split(DELIMITER_MESSAGES);

        /* we add each message to the list of messages */
        for (String message : messages) {
            this.messages.add(message.trim());
        }

        reader.close();
    }
}
