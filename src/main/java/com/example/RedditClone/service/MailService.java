package com.example.RedditClone.service;

import com.example.RedditClone.exception.RedditCloneException;
import com.example.RedditClone.model.EmailNotification;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class MailService {

    private final JavaMailSender javaMailSender;
    private final MailContentBuilder mailContentBuilder;

    @Async
    void sendEmail(EmailNotification emailNotification) {
        MimeMessagePreparator mimeMessagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("ervar_reddit@reddit.com");
            messageHelper.setTo(emailNotification.getEmailReceiver());
            messageHelper.setSubject(emailNotification.getEmailSubject());
            messageHelper.setText(mailContentBuilder.build(emailNotification.getEmailBody()));
        };
        try {
            javaMailSender.send(mimeMessagePreparator);
            log.info("The email activation message is sent");
        } catch (MailException e) {
            throw new RedditCloneException("Exception occured when sending the email to "+ emailNotification.getEmailReceiver());

        }
    }
}
