package com.back.myhousedata.service;


import jakarta.activation.DataHandler;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import jakarta.mail.util.ByteArrayDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Properties;
import java.util.UUID;


@Service
@Slf4j
@EnableAsync
public class MailService {

    private static final Logger logger = LogManager.getLogger( MailService.class );


    private static final Properties PROPERTIES = new Properties();
    private static final String USERNAME = "pierre.chanfrault@gmail.com";
    private static final String PASSWORD = "agfg plwi pfkk ifcv";

    static {
        PROPERTIES.put("mail.smtp.host", "smtp.gmail.com");
        PROPERTIES.put("mail.smtp.port", "587");
        PROPERTIES.put("mail.smtp.auth", "true");
        PROPERTIES.put("mail.smtp.starttls.enable", "true");
        PROPERTIES.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        PROPERTIES.put("mail.smtp.ssl.protocols", "TLSv1.2");
    }

    public void sendMail(String destinataire) {
        Authenticator authenticator = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        };
        Session session = Session.getInstance(PROPERTIES, authenticator);
        session.setDebug(true);
        try {
            // Création du message
            Message message = prepareMessage(session, destinataire);
            // Envoi du message
            if(message != null) {
                Transport.send(message);
                System.out.println("Email sent successfully");
            } else {
                System.out.println("Email not send, no message");
            }
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private static Message prepareMessage(Session session, String recipient) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(USERNAME));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject("Evenement programmé");


            // Contenu de l'événement iCalendar
            /*String calendarContent = "BEGIN:VCALENDAR\n" +
                    "VERSION:2.0\n" +
                    "PRODID:-//FootManagment//FR\n" +
                    "CALSCALE:GREGORIAN\n" +
                    "METHOD:REQUEST\n" +
                    "BEGIN:VEVENT\n" +
                    "UID:"+ UUID.randomUUID() + "\n" + //id unique a regénérer a chaque fois (ou garder si on veut modifier un event)
                    "DTSTAMP:20240719T120000Z\n" + // a virer car on a le method = request normalement
                    "DTSTART;ZTZID=Europe/Paris:20240719T200000\n" + //date début.
                    "DTEND;ZTZID=Europe/Paris:20240719T210000\n" + //date fin
                    "SUMMARY:Match de foot\n" + //libellé
                    "DESCRIPTION:Le match sera diffusé sur TF1\n" + //description
                    "LOCATION:Stade de France\n" + //lieu
                    "END:VEVENT\n" +
                    "END:VCALENDAR";
*/
            // Texte du body
            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText("Veuillez télécharger le fichier depuis votre téléphone pour l'ajouter à votre calendrier Google.");

            // Calendrier
           /* MimeBodyPart calendarPart = new MimeBodyPart();
            calendarPart.setHeader("Content-Class", "urn:content-classes:calendarmessage");
            calendarPart.setHeader("Content-ID", "calendar_message");
            calendarPart.setDataHandler(new DataHandler(new ByteArrayDataSource(calendarContent, "text/calendar;method=REQUEST;charset=UTF-8")));
*/
            // Créer le multipart/alternative avec les deux parties

            Multipart multipart = new MimeMultipart("alternative");
            multipart.addBodyPart(textPart);
            /*multipart.addBodyPart(calendarPart);*/

            // Mettre le multipart dans le message
            message.setContent(multipart);

            return message;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}


