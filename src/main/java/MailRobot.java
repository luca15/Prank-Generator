import model.ErrorConfigPropertiesException;
import model.PrankGenerator;

import java.io.IOException;

/**
 * This program allows to send by mails pranks using a list of victims and a list of messages
 * that are stored in some files.
 *
 * @author Luca Sivillica
 * @author Dany Tchente
 */
public class MailRobot {
    /* configuration files */
    private final static String PATH = "config/";
    private final static String FILE_PROPERTIES = PATH + "config.properties";
    private final static String FILE_VICTIMS = PATH + "victims.utf8";
    private final static String FILE_MESSAGES = PATH + "messages.utf8";

    public static void main(String[] args) throws IOException {
        PrankGenerator prankGenerator = new PrankGenerator(FILE_PROPERTIES, FILE_VICTIMS, FILE_MESSAGES);

        try {
            prankGenerator.sendPranks();

        } catch (ErrorConfigPropertiesException e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }
    }
}
