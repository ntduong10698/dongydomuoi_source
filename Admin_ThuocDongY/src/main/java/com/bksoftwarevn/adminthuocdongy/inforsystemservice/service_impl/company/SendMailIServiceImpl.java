package com.bksoftwarevn.adminthuocdongy.inforsystemservice.service_impl.company;


import com.bksoftwarevn.adminthuocdongy.inforsystemservice.service.company.SendMailIService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@Slf4j
public class SendMailIServiceImpl implements SendMailIService {

    @Autowired
    private JavaMailSender javaMailSender;

//    @Autowired
//    @Qualifier("natalieSender")
//    private JavaMailSender natalieSender;

    @Value("${spring.mail.username}")
    private String emailSender;

    @Override
    public boolean sendMail(String userMail, String header, String content) {
        try {
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setTo(userMail);
            mail.setFrom(emailSender);
            mail.setSubject(header);
            mail.setText(content);
            javaMailSender.send(mail);
            return true;
        } catch (MailException ex) {
            log.error("send-mail-error : {}", ex.getMessage());
        }
        return false;
    }

//    @Override
//    public boolean natalieMail(String userMail, String header, String content) {
//        try {
//            MimeMessage mimeMessage = natalieSender.createMimeMessage();
//            MimeMessageHelper mail = new MimeMessageHelper(mimeMessage, "utf-8");
//            mail.setTo(userMail);
//            mail.setFrom("quynhnganguyen411@gmail.com");
//            mail.setSubject(header);
//            mail.setText(content, true);
//            natalieSender.send(mimeMessage);
//            return true;
//        } catch (MailException | MessagingException ex) {
//            log.error("send-mail-error : {}", ex.getMessage());
//        }
//        return false;
//    }

    @Override
    public boolean sendHtmlMail(String userMail, String header, String content) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mail = new MimeMessageHelper(mimeMessage, "utf-8");
            mail.setTo(userMail);
            mail.setFrom(emailSender);
            mail.setSubject(header);
            mail.setText(content, true);
            javaMailSender.send(mimeMessage);
            return true;
        } catch (MailException | MessagingException ex) {
            log.error("send-mail-error : {}", ex.getMessage());
        }
        return false;
    }
}
