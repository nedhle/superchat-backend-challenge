package app.util.sender;

import app.enums.PlaceHolderMapper;
import app.enums.ResponseObject;
import app.model.Contact;
import app.util.GenericUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class EmailSender implements Sender{

    @Autowired
    private JavaMailSender mailSender;

    private String username;

    private String password;

   public EmailSender(String emailUsername,String emailPassword) {
        this.username = emailUsername;
        this.password = emailPassword;
    }

    @Override
    public Map<String,Object> send(Contact receiverContact, String text) {
        Map<String,Object> map = new HashMap<>();
        String tmpText = "";

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
            //placeholder check
            tmpText = GenericUtil.filter4placeholder(text, contactMapper(receiverContact));
            Message message = new MimeMessage(session);
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(receiverContact.getEmail()));
            message.setSubject("Subject");//hardcoded?
            message.setText(tmpText);
            Transport.send(message);

        } catch (MessagingException e) {
            map.put(ResponseObject.SUCCESS,false);
            map.put(ResponseObject.RESPONSE,e.getMessage());

            return map;
        }
        map.put(ResponseObject.SUCCESS,true);
        map.put(ResponseObject.RESPONSE,tmpText);

        return map;
    }

    public Map<String,String> contactMapper(Contact contact){
        Map<String,String> map = new HashMap<>();
        map.put(PlaceHolderMapper.ContactName,contact.getName());
        map.put(PlaceHolderMapper.ContactEmail,contact.getEmail());
        map.put(PlaceHolderMapper.ContactTel,contact.getTelNo());
        return map;
    }
}
