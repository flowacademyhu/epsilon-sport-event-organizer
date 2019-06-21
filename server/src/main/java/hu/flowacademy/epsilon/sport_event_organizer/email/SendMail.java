package hu.flowacademy.epsilon.sport_event_organizer.email;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class SendMail {

    public static void sendMail(String email, String teamLeader, String team) {

        final String username = "projektmunkasports@gmail.com";
        final String password = "Flow123456";

        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("projektmunkasports@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("email"));
            message.setSubject("Team Invitation From " + teamLeader);
            message.setText("Hello,\n" + teamLeader + "invited you " + team + " team. Check our page: http://localhost:4200/"
                    + "\n\n No spam to my email, please!");

            Transport.send(message);


        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}