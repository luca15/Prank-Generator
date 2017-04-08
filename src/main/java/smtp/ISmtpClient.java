package smtp;

import model.mail.Mail;

import java.io.IOException;

/**
 * This interface represents a SMTP client.
 *
 * @author Luca Sivillica
 * @author Dany Tchente
 */
public interface ISmtpClient {
    public static final String CMD_EHLO       = "EHLO";
    public static final String CMD_MAIL_FROM  = "MAIL FROM:";
    public static final String CMD_RCPT_TO    = "RCPT TO:";
    public static final String CMD_DATA       = "DATA";
    public static final String CMD_QUIT       = "QUIT";

    public static final String HEADER_FROM    = "From:";
    public static final String HEADER_TO      = "To:";
    public static final String HEADER_SUBJECT = "Subject:";

    public static final String END_LINE       = "\r\n";

    /**
     * This method allows to send an e-mail.
     *
     * @param mail the mail that we want to send
     * @throws IOException
     */
    void sendMail(Mail mail) throws IOException;
}
