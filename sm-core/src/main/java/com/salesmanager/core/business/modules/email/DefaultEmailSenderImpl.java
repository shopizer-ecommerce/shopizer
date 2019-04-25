package com.salesmanager.core.business.modules.email;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.Map;
import java.util.Properties;
import javax.inject.Inject;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.springframework.mail.MailPreparationException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Component("defaultEmailSender")
public class DefaultEmailSenderImpl implements EmailModule {

  @Inject
  private Configuration freemarkerMailConfiguration;
  
  @Inject
  private JavaMailSender mailSender;

  private static final String CHARSET = "UTF-8";
  private EmailConfig emailConfig;

  private final static String TEMPLATE_PATH = "templates/email";

  @Override
  public void send(Email email) throws Exception {

    final String eml = email.getFrom();
    final String from = email.getFromEmail();
    final String to = email.getTo();
    final String subject = email.getSubject();
    final String tmpl = email.getTemplateName();
    final Map<String, String> templateTokens = email.getTemplateTokens();

    MimeMessagePreparator preparator = new MimeMessagePreparator() {
      public void prepare(MimeMessage mimeMessage) throws MessagingException, IOException {

        JavaMailSenderImpl impl = (JavaMailSenderImpl) mailSender;
        // if email configuration is present in Database, use the same
        if (emailConfig != null) {
          impl.setProtocol(emailConfig.getProtocol());
          impl.setHost(emailConfig.getHost());
          impl.setPort(Integer.parseInt(emailConfig.getPort()));
          impl.setUsername(emailConfig.getUsername());
          impl.setPassword(emailConfig.getPassword());

          Properties prop = new Properties();
          prop.put("mail.smtp.auth", emailConfig.isSmtpAuth());
          prop.put("mail.smtp.starttls.enable", emailConfig.isStarttls());
          impl.setJavaMailProperties(prop);
        }

        mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));

        InternetAddress inetAddress = new InternetAddress();

        inetAddress.setPersonal(eml);
        inetAddress.setAddress(from);

        mimeMessage.setFrom(inetAddress);
        mimeMessage.setSubject(subject);

        Multipart mp = new MimeMultipart("alternative");

        // Create a "text" Multipart message
        BodyPart textPart = new MimeBodyPart();
        freemarkerMailConfiguration.setClassForTemplateLoading(DefaultEmailSenderImpl.class, "/");
        Template textTemplate = freemarkerMailConfiguration.getTemplate(
            new StringBuilder(TEMPLATE_PATH).append("").append("/").append(tmpl).toString());
        final StringWriter textWriter = new StringWriter();
        try {
          textTemplate.process(templateTokens, textWriter);
        } catch (TemplateException e) {
          throw new MailPreparationException("Can't generate text mail", e);
        }
        textPart.setDataHandler(new javax.activation.DataHandler(new javax.activation.DataSource() {
          public InputStream getInputStream() throws IOException {
            // return new StringBufferInputStream(textWriter
            // .toString());
            return new ByteArrayInputStream(textWriter.toString().getBytes(CHARSET));
          }

          public OutputStream getOutputStream() throws IOException {
            throw new IOException("Read-only data");
          }

          public String getContentType() {
            return "text/plain";
          }

          public String getName() {
            return "main";
          }
        }));
        mp.addBodyPart(textPart);

        // Create a "HTML" Multipart message
        Multipart htmlContent = new MimeMultipart("related");
        BodyPart htmlPage = new MimeBodyPart();
        freemarkerMailConfiguration.setClassForTemplateLoading(DefaultEmailSenderImpl.class, "/");
        Template htmlTemplate = freemarkerMailConfiguration.getTemplate(
            new StringBuilder(TEMPLATE_PATH).append("").append("/").append(tmpl).toString());
        final StringWriter htmlWriter = new StringWriter();
        try {
          htmlTemplate.process(templateTokens, htmlWriter);
        } catch (TemplateException e) {
          throw new MailPreparationException("Can't generate HTML mail", e);
        }
        htmlPage.setDataHandler(new javax.activation.DataHandler(new javax.activation.DataSource() {
          public InputStream getInputStream() throws IOException {
            // return new StringBufferInputStream(htmlWriter
            // .toString());
            return new ByteArrayInputStream(textWriter.toString().getBytes(CHARSET));
          }

          public OutputStream getOutputStream() throws IOException {
            throw new IOException("Read-only data");
          }

          public String getContentType() {
            return "text/html";
          }

          public String getName() {
            return "main";
          }
        }));
        htmlContent.addBodyPart(htmlPage);
        BodyPart htmlPart = new MimeBodyPart();
        htmlPart.setContent(htmlContent);
        mp.addBodyPart(htmlPart);

        mimeMessage.setContent(mp);

        // if(attachment!=null) {
        // MimeMessageHelper messageHelper = new
        // MimeMessageHelper(mimeMessage, true);
        // messageHelper.addAttachment(attachmentFileName, attachment);
        // }

      }
    };

    mailSender.send(preparator);
  }

  public Configuration getFreemarkerMailConfiguration() {
    return freemarkerMailConfiguration;
  }

  public void setFreemarkerMailConfiguration(Configuration freemarkerMailConfiguration) {
    this.freemarkerMailConfiguration = freemarkerMailConfiguration;
  }

  public JavaMailSender getMailSender() {
    return mailSender;
  }

  public void setMailSender(JavaMailSender mailSender) {
    this.mailSender = mailSender;
  }

  public EmailConfig getEmailConfig() {
    return emailConfig;
  }

  public void setEmailConfig(EmailConfig emailConfig) {
    this.emailConfig = emailConfig;
  }

}
