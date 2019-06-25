package hu.flowacademy.epsilon.sport_event_organizer.email;

import hu.flowacademy.epsilon.sport_event_organizer.model.Cup;
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

    @Value("${app.username}")
    private String username;

    @Value("${app.password}")
    private String password;

    Properties props = new Properties();

    public MailService() {
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
    }

    private Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });


    @Async
    public void sendMailAddUserToTeamMember(User user, Team team) {

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(user.getEmail()));
            message.setSubject(team.getName() + " Team Invitation");
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
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(user.getEmail()));
            message.setSubject(team.getName() + " Team delete");
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
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(user.getEmail()));
            message.setSubject(team.getName() + "Team Promotion");
            message.setText("Hello " + user.getGoogleName() + ",\n  Team leader promote you to leader in" + team.getCompany() + "'s " + team.getName() + " team. Check out our page: " + homePageUrl);
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @Async
    public void sendMailOrganizersToAppliedTeam(Team team, Cup cup) {
        cup.getOrganizers().forEach(organizer -> {
            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(username));
                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(organizer.getEmail()));
                message.setSubject(team.getName() + "Team join request");
                message.setText("Hello " + organizer.getGoogleName() + ",\n" +
                        team.getName() + " would like to join your " + cup.getName() + " cup. Check out our page: " + homePageUrl);
                Transport.send(message);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Async
    public void sendMailTeamLeaderBecauseTeamApproved(Team team, Cup cup) {
        team.getLeaders().forEach(leader -> {
            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(username));
                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(leader.getEmail()));
                message.setSubject(team.getName() + "Approved request");
                message.setText("Hello " + leader.getGoogleName() + ",\n" +
                        cup.getName() + " leader approved " + team.getName() + "'s request. Check out our page: " + homePageUrl);
                Transport.send(message);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Async
    public void sendMailTeamLeaderBecauseTeamRefused(Team team, Cup cup) {
        team.getLeaders().forEach(leader -> {
            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(username));
                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(leader.getEmail()));
                message.setSubject(team.getName() + "Refused request");
                message.setText("Hello " + leader.getGoogleName() + ",\n" +
                        cup.getName() + " leader refused " + team.getName() + "'s request. Check out our page: " + homePageUrl);
                Transport.send(message);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        });
    }

}