package com.dbc.pessoaapi.service;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender emailSender;
    @Value("${spring.mail.username}")
    private String remetente;
    private final Configuration configuration;

    public void enviarEmailSimples(String destinatario,int id,String nome) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(remetente);
        message.setTo(destinatario);
        message.setSubject("Assunto do e-mail");
        message.setText("Ola "+ nome+",\n" +
                "\n" +
                "Estamos felizes em ter você em nosso sistema :)\n" +
                "Seu cadastro foi realizado comsucesso,seu identificador é "+id+".\n" +
                "\n" +
                "Qualquer dúvida é só contatar o suporte pelo e-mail "+this.remetente+
                "\n" +
                "att\n" +
                "Sistema");
        emailSender.send(message);
    }

    public void enviarEmailComAnexos() throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom(remetente);
        helper.setTo("tiago.coelho@dbccompany.com.br");
        helper.setSubject("Email com anexo Exemplo");
        helper.setText("Teste");

        File file = new File("imagem.jpg");
        FileSystemResource fileSystemResource = new FileSystemResource(file);
        helper.addAttachment(file.getName(), fileSystemResource);

        File file2 = new File("HELP.md");
        FileSystemResource fileSystemResource2 = new FileSystemResource(file2);
        helper.addAttachment(file2.getName(), fileSystemResource2);

        emailSender.send(message);
    }

    public void enviarEmailComTemplate(String destinatario,int id,String nome) throws MessagingException, IOException, TemplateException {
        MimeMessage mimeMessage = emailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setFrom(remetente);
        helper.setTo(destinatario);
        helper.setSubject("Email com template Exemplo");

        Template template = configuration.getTemplate("email-template.ftl");
        Map<String, Object> dados = new HashMap<>();
        dados.put("nome", nome);
        dados.put("id", id);
        dados.put("emailSuporte", this.remetente);
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, dados);

        helper.setText(html, true);

        emailSender.send(mimeMessage);
    }

    public void enviarEmailComTemplateAndAnexos() throws MessagingException, IOException, TemplateException {
        MimeMessage mimeMessage = emailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setFrom(remetente);
        helper.setTo("tiago.coelho@hotmail.com");
        helper.setSubject("Email com template Exemplo");

        Template template = configuration.getTemplate("email-template.ftl");
        Map<String, Object> dados = new HashMap<>();
        dados.put("nomeUsuario", "Maicon Gerardi");
        dados.put("idade", 30);
        dados.put("cidade", "Florianópolis");
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, dados);

        helper.setText(html, true);

        File file = new File("imagem.jpg");
        FileSystemResource fileSystemResource = new FileSystemResource(file);
        helper.addAttachment("foto.jpg", fileSystemResource);

        emailSender.send(mimeMessage);
    }

    public void enviaremailcomtemplatepessoaexcluida(String destinatario,String nome) throws MessagingException, IOException, TemplateException {
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setFrom(remetente);
        helper.setTo(destinatario);
        helper.setSubject("Email com template Exemplo");

        Template template = configuration.getTemplate("email-template-pessoa-excluida.ftl");
        Map<String, Object> dados = new HashMap<>();
        dados.put("nome", nome);
        dados.put("emailSuporte", this.remetente);
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, dados);

        helper.setText(html, true);

        emailSender.send(mimeMessage);
    }


}
