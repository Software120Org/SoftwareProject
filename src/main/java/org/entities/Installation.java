package org.entities;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.logging.Logger;
public class Installation {
    private int id;
    private String make;
    private String model;
    private String date;
    private String time;
    private String product;
    private String email;
    public Installation() {}
    public Installation(int id, String product, String make, String model, String date, String time, String email) {
        this.email = email;
        this.id = id;
        this.product = product;
        this.date = date;
        this.time = time;
        this.model = model;
        this.make = make;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setProduct(String product){
        this.product=product;}
    public void setMake(String make){
        this.make=make;}
    public void setModel(String model){
        this.model=model;}
    public String getEmail() {
        return email;
    }
    public void setEmail(String email){
        this.email=email;}
    public void setDate(String date){
        this.date=date;}
    public void setTime(String time){
        this.time=time;
    }
    @Override
    public String toString() {
        return  id +
                ",," + product +
                ",,"  +make+
                ",," + model +
                ",," + date +
                ",," + time +
                ",," + email +"\n";
    }
    public void sendEmail(String title, String message, String msg) {
        final String user = "rubasalon5@gmail.com";
        final String emailPass = "wnjj@120";
        String to = this.getEmail();
        Session session = getMailSession(user, emailPass);

        try {
            Message emailMessage = createEmailMessage(session, user, to, title, message);
            Transport.send(emailMessage);
            logger.info(msg);
        } catch (Exception ignored) {
            // Handle exceptions as needed
        }
    }

    private Session getMailSession(final String user, final String emailPass) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.starttls.enable", "true");

        return Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(user, emailPass);
            }
        });
    }

    private Message createEmailMessage(Session session, String user, String to, String title, String messageContent) throws MessagingException {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(user));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        message.setSubject(title);
        message.setText(messageContent);
        return message;
    }
    static Logger logger = Logger.getLogger(Installation.class.getName());
}