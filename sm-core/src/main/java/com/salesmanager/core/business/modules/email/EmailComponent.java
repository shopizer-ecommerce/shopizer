package com.salesmanager.core.business.modules.email;

import javax.inject.Inject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component("htmlEmailSender")
@RequiredArgsConstructor
public class EmailComponent implements HtmlEmailSender {

    @Value("${config.emailSender}")
    private String emailSender;

    private final EmailModule defaultEmailSender;
    private final EmailModule sesEmailSender;

    @Override
    public void send(Email email) throws Exception {
        switch (emailSender) {
            case "default":
                defaultEmailSender.send(email);
                break;
            case "ses":
                sesEmailSender.send(email);
                break;
            default:
                throw new Exception("No email implementation for " + emailSender);
        }
    }

    @Override
    public void setEmailConfig(EmailConfig emailConfig) {
        switch (emailSender) {
            case "default":
                defaultEmailSender.setEmailConfig(emailConfig);
                break;
            case "ses":
                sesEmailSender.setEmailConfig(emailConfig);
                break;
            default:
                // No-op for unknown configs
        }
    }
}