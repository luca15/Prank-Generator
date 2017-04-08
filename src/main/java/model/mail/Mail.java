package model.mail;

/**
 * This class represents an e-mail.
 * In the boby there also is the subject of the mail.
 *
 * @author Luca Sivillica
 * @author Dany Tchente
 */
public class Mail {
    private String from;
    private String[] to;
    private String body;

    public Mail(String from, String[] to, String body) {
        this.from = from;
        this.to = to;
        this.body = body;
    }

    public String getFrom() {
        return from;
    }

    public String[] getTo() {
        return to;
    }

    public String getBody() {
        return body;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String[] to) {
        this.to = to;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
