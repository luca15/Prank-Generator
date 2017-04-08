package smtp;

import model.mail.Mail;

import java.io.*;
import java.net.Socket;

/**
 * This class represents a SMTP client.
 *
 * @author Luca Sivillica
 * @author Dany Tchente
 */
public class SmtpClient implements ISmtpClient {
    private static final String CHARSET = "UTF-8"; // default charset mail

    private String serverDomain;
    private int destinationPort;

    public SmtpClient(String serverDomain, int destinationPort) throws IOException {
        this.serverDomain = serverDomain;
        this.destinationPort = destinationPort;
    }

    public void sendMail(Mail mail) throws IOException {
        Socket clientSocket = new Socket(serverDomain, destinationPort);

        BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), CHARSET));
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream(), CHARSET));

        reader.readLine(); // Read the welcome message of the server

        writer.print(CMD_EHLO + " localhost" + END_LINE);
        writer.flush();

        /* Read all lines sent by the server, until that we can send the sender of the mail */
        String serverResponse = reader.readLine();

        while (serverResponse != null && serverResponse.indexOf("250 ") == -1) {
            serverResponse = reader.readLine();
        }

        writer.print(CMD_MAIL_FROM + " "  + mail.getFrom() + END_LINE);
        writer.flush();
        reader.readLine(); // Read the response message of the server

        /* Send all recipients of the mail */
        for (String recipient : mail.getTo()) {
            writer.print(CMD_RCPT_TO + " "  + recipient + END_LINE);
            writer.flush();
            reader.readLine(); // Read the response message of the server
        }

        writer.print(CMD_DATA + END_LINE);
        writer.flush();
        reader.readLine(); // Read the response message of the server

        /* Send header data */
        writer.print("Content-Type: text/plain; charset=" + CHARSET + END_LINE);

        /* Send the sender of the mail */
        writer.print(HEADER_FROM + " " + mail.getFrom() + END_LINE);

        /* Send all the recipients of the mail */
        writer.print(HEADER_TO + " ");

        for (int i = 0; i < mail.getTo().length; ++i) {
            if (i != 0) {
                writer.print(", ");
            }

            writer.print(mail.getTo()[i]);
        }
        writer.print(END_LINE);

        /* Send mail data */
        writer.print(mail.getBody());
        writer.print(END_LINE);
        writer.print(".");
        writer.print(END_LINE);
        writer.flush();
        reader.readLine(); // Read the response message of the server

        /* Quit the session */
        writer.print(CMD_QUIT + END_LINE);
        writer.flush();

        reader.close();
        writer.close();

        clientSocket.close();
    }
}
