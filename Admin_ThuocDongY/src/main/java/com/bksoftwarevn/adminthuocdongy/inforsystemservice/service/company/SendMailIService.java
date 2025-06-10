package com.bksoftwarevn.adminthuocdongy.inforsystemservice.service.company;

public interface SendMailIService {
    boolean sendMail(String userMail, String header, String content);

    boolean sendHtmlMail(String userMail, String header, String content);

//    boolean natalieMail(String userMail, String header, String content);
}
