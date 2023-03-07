package br.com.ProjetoEnvioEmail.services;

import br.com.ProjetoEnvioEmail.enuns.StatusEmail;
import br.com.ProjetoEnvioEmail.model.EmailModel;
import br.com.ProjetoEnvioEmail.repositorys.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
public class EmailService {

    @Autowired
    EmailRepository emailRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Transactional
    public EmailModel sendEmail(EmailModel emailModel)
    {
        emailModel.setSendDateEmail(LocalDateTime.now());
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailModel.getEmailFrom());
            message.setTo(emailModel.getEmailTo());
            message.setSubject(emailModel.getSubject());
            message.setText(emailModel.getText());
            emailSender.send(message);

            emailModel.setStatusEmail(StatusEmail.SEND);
        } catch (MailException e){
            emailModel.setStatusEmail(StatusEmail.ERRROR);
        } finally {
            return emailRepository.save(emailModel);
        }
    }
}
