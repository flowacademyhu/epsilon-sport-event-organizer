package hu.flowacademy.epsilon.sport_event_organizer.email;

import hu.flowacademy.epsilon.sport_event_organizer.model.Team;
import hu.flowacademy.epsilon.sport_event_organizer.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


@Component
@EnableAsync
public class MailService {


    @Value("${app.url}")
    private String homePageUrl;

    final String username = "projektmunkasports@gmail.com";
    final String password = "Flow123456";

    Properties props = new Properties();

    public MailService() {
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
    }

    Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });


    @Async
    public void sendMailAddUserToTeamMember(User user, Team team) {

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("projektmunkasports@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(user.getEmail()));
            message.setSubject(team.getName() + "Team Invitation From ");
            message.setText("Hello " + user.getGoogleName() + ",\n  Team leader invited you to " + team.getCompany() + "'s " + team.getName() + " team. Check out our page: " + homePageUrl);
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @Async
    public void sendMailDeleteUserFromTeam(User user, Team team) {

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("projektmunkasports@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(user.getEmail()));
            message.setSubject(team.getName() + "Team Invitation From ");
            message.setText("Hello " + user.getGoogleName() + ",\n  Team leader deleted you from " + team.getCompany() + "'s " + team.getName() + " team. Check out our page: " + homePageUrl);
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @Async
    public void sendMailAddUserToTeamLeader(User user, Team team) {

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("projektmunkasports@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(user.getEmail()));
            message.setSubject(team.getName() + "Team Invitation From ");
            message.setText("Hello " + user.getGoogleName() + ",\n  Team leader promote you to leader in" + team.getCompany() + "'s " + team.getName() + " team. Check out our page: " + homePageUrl);
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}