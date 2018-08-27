package com.yaochow.user.util;

import com.sun.mail.util.MailSSLSocketFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.GeneralSecurityException;
import java.util.Properties;

@Component
public class MailUtil {

    public static String from;
    public static String password;
    public static String host;

    public static boolean send(String email, String content) throws GeneralSecurityException {
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        properties.put("mail.smtp.auth", "true");
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.ssl.socketFactory", sf);

        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setSubject("Register Confirm Email");
            message.setText(content);
            Transport.send(message);
            System.out.println("Sent register message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
            return false;
        }
        return true;
    }

    @Value("${mail.from.account}")
    public void setFrom(String from) {
        MailUtil.from = from;
    }

    @Value("${mail.from.password}")
    public void setPassword(String password) {
        MailUtil.password = password;
    }

    @Value("${mail.host}")
    public void setHost(String host) {
        MailUtil.host = host;
    }
}
