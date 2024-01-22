package com.digytal.control.infra.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;
import java.time.ZoneId;


@Component
public class SendEmail{
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
    public void send(String title, String nome, String email, Integer id, String senhaTemporaria, Long expiracao, String login ) throws Exception{
        String ctx = ServletUriComponentsBuilder.fromCurrentContextPath().toUriString();
        String url = String.format("%s/auth/reset-password?expiration=%d&user=%d&token=%s", ctx, expiracao,id,senhaTemporaria);
        StringBuilder sb = new StringBuilder();
        sb.append("<body>");
        sb.append("<p>Olá " + nome +",</p>");
        sb.append("<p>Seja bem-vindo ao Control Click, seu gestor financeiro pessoal, digite o token informado para definir sua senha pessoal.</p>");
        sb.append(String.format("<p>Token: %s </p>", senhaTemporaria));
        sb.append(String.format("<p>Nome: %s </p>", nome));
        sb.append(String.format("<p>Login: %s </p>", login));

        //sb.append("<p><a href=\""+url+"\">CLICK AQUI</a></p>");
        //sb.append("<p>Ou copie este link no navegador</p>");
        //sb.append(url);
        sb.append("<p>Atenciosamente,</p>");
        sb.append("<p>Gleyson</p>");
        sb.append("</body>");

        try {
            Thread sender = new Thread(process(mailSender,createHtmlMessage(title,"gleyson@digytal.com.br", email, sb.toString())));
            sender.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private MimeMessage createHtmlMessage(String subject, String from, String to, String content) throws Exception{
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
        //mimeMessage.setContent(sb.toString(), "text/html");
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content, true); // Use this or above line.
        return  mimeMessage;

    }
    public void sendResetSenha(String title, String nome, String email, Integer id, String senhaTemporaria ) throws Exception{
        String ctx = ServletUriComponentsBuilder.fromCurrentContextPath().toUriString();
        long timestamp = LocalDateTime.now().plusHours(1).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        String url = String.format("%s/auth/reset-password?expiration=%d&user=%d&token=%s", ctx, timestamp,id,senhaTemporaria);
        StringBuilder sb = new StringBuilder();
        sb.append("<body>");
        sb.append("<p>Olá " + nome +",</p>");
        sb.append("<p>Informamos que houve uma solicitação de redefinição de sua senha, para prosseguir click no link abaixo ou utilize o token na tela</p>");
        sb.append(String.format("<p>Nome: %s </p>", nome));
        sb.append(String.format("<p>Token: %s </p>", senhaTemporaria));
        sb.append("<p><a href=\""+url+"\">CLICK AQUI</a></p>");
        sb.append("<p>Ou copie este link no navegador</p>");
        sb.append(url);
        sb.append("<p>Atenciosamente,</p>");
        sb.append("<p>Gleyson</p>");
        sb.append("</body>");

        try {
            Thread sender = new Thread(process(mailSender,createHtmlMessage(title,"gleyson@digytal.com.br", email, sb.toString())));
            sender.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private Runnable process(JavaMailSender mailSender, MimeMessage msg){
        return new Runnable() {
            @Override
            public void run() {
                mailSender.send(msg);
            }
        };
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