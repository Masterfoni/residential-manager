package ifpe.edu.utils;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender extends Thread {
    Properties props;
    Session session;
    
    String message;
    String remetente = "MySindico";
    String destinatario;
    
    public EmailSender(String message, final String username, final String password)
    {
        this.message = message;
        
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        
        session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }
    
    public void run() {
        try {
            MimeMessage mensagem = new MimeMessage(session);
            
            mensagem.setFrom(new InternetAddress(remetente));
            mensagem.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
            mensagem.setSubject("Aviso do MySindico!");
            mensagem.setText(this.message);
            
            Transport.send(mensagem);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public boolean send()
    {
        try {
            MimeMessage mensagem = new MimeMessage(session);
            
            mensagem.setFrom(new InternetAddress(remetente));
            mensagem.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
            mensagem.setSubject("Aviso do MySindico!");
            mensagem.setText(this.message);
            
            Transport.send(mensagem);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return true;
    }
    
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRemetente() {
        return remetente;
    }

    public void setRemetente(String remetente) {
        this.remetente = remetente;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }
}
