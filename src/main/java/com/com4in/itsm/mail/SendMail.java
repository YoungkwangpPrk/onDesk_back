package com.com4in.itsm.mail;

import com.com4in.itsm.dto.IssueDto;
import com.com4in.itsm.dto.MailTemplateDto;
import com.com4in.itsm.service.MailTemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

@Service
public class SendMail {

    final String user = "donghee.kim@com4in.com"; //발신자의 이메일 아이디를 입력
    final String password = "itsm!!2023!!";         //발신자 이메일의 패스워드를 입력

    private final MailTemplateService mailTemplateService;

    public SendMail(MailTemplateService mailTemplateService) {
        this.mailTemplateService = mailTemplateService;
    }

    public void sendEMail(IssueDto issueDto) {

        System.out.println("============================email============================");
        Properties prop = System.getProperties();
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.office365.com");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.port", "587");

        Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });

        MimeMessage msg = new MimeMessage(session);

        try {
            String title = new String();
            String content = new String();
            msg.setSentDate(new Date());
            InternetAddress from = new InternetAddress();

            from = new InternetAddress("donghee.kim@com4in.com");
            msg.setFrom(from);

            InternetAddress to= new InternetAddress();
            System.out.println("stautscode   ==================================   "+issueDto.getStatus_code());
            MailTemplateDto mailTemplateDto = new MailTemplateDto();
            if(issueDto.getStatus_code().equals("10")){
                mailTemplateDto = mailTemplateService.getMailTemplateStatus("10");
                title =mailTemplateDto.getTitle();
                content = mailTemplateDto.getContent();
                to = new InternetAddress("suhee.lee@com4in.com");
            }else if(issueDto.getStatus_code().equals("20")){
                mailTemplateDto = mailTemplateService.getMailTemplateStatus("20");
                title =mailTemplateDto.getTitle();
                content = mailTemplateDto.getContent();
                to = new InternetAddress("suhee.lee@com4in.com");
            }else if(issueDto.getStatus_code().equals("30")) {
                mailTemplateDto = mailTemplateService.getMailTemplateStatus("30");
                title =mailTemplateDto.getTitle();
                content = mailTemplateDto.getContent();
                to = new InternetAddress("suhee.lee@com4in.com");
            }else if(issueDto.getStatus_code().equals("40")) {
                mailTemplateDto = mailTemplateService.getMailTemplateStatus("40");
                title =mailTemplateDto.getTitle();
                content = mailTemplateDto.getContent();
                to = new InternetAddress("suhee.lee@com4in.com");
            }else if(issueDto.getStatus_code().equals("50")) {
                mailTemplateDto = mailTemplateService.getMailTemplateStatus("50");
                title =mailTemplateDto.getTitle();
                content = mailTemplateDto.getContent();
                to = new InternetAddress("suhee.lee@com4in.com");
            }else {
                to = new InternetAddress("suhee.lee@com4in.com");
            }
            msg.setRecipient(Message.RecipientType.TO, to);
            msg.setSubject(title, "UTF-8");
            msg.setText(content, "UTF-8");

            msg.setHeader("content-Type", "text/html");

            javax.mail.Transport.send(msg);
        } catch (AddressException addr_e){
            addr_e.printStackTrace();
        } catch (MessagingException msg_e){
            msg_e.printStackTrace();
        }
    }
}