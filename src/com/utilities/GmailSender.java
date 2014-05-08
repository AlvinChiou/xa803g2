package com.utilities;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

class GmailSender {
    private String fromMail;
    private String toMail;
    private String password;
    private String subject;
    private String content;
    public void GmailAuth(final String fromMail, String toMail, final String password, String subject, String content) {
        this.fromMail = fromMail;
        this.toMail = toMail;
        this.password = password;
        this.subject = subject;
        this.content = content;
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(fromMail, password);
                    }
                }
        );
        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromMail));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(toMail));
            message.setSubject(subject);
            message.setText(content);

            Transport.send(message);

            //System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
