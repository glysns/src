package digytal.backend.api.infra.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;


@Component
//@Profile("!dev")
public class SendEmail {
    @Autowired
    private JavaMailSender mailSender;
    public SendEmail(){
        System.out.println("CRIANDO SEND EMAIL SERVICE REAL");
    }
    public void send(Message message) {
        SimpleMailMessage messageSender = new SimpleMailMessage();
        messageSender.setFrom(message.getFrom());
        messageSender.setTo(message.getTo());
        messageSender.setSubject(message.getTitle());
        messageSender.setText(message.getBody());
        try {
            Thread sender = new Thread(process(mailSender,messageSender));
            sender.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private Runnable process(JavaMailSender mailSender, SimpleMailMessage msg){
        return new Runnable() {
            @Override
            public void run() {
                mailSender.send(msg);
            }
        };
    }
}
