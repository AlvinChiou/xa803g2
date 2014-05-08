package com.utilities;
public class GmailSenderMain {
    public static void main(String[] args) throws ClassNotFoundException{
        String fromMail = "ipetcareservice@gmail.com";
        String toMail = "to-mail@gmail.com";
        String password = "MD6WHCXLYQzrgG";
        String subject = "´ú¸Õ¶l¥ó";
        String content = "https://java.net/projects/javamail/pages/Home";
        GmailSender gmailSender = new GmailSender();
        gmailSender.GmailAuth(fromMail,toMail, password, subject, content);
    }
}
