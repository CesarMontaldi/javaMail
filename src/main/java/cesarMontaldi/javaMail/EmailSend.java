package cesarMontaldi.javaMail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class EmailSend {

	private String userName = "";
	private String password = "";

	private String listaDestinatarios = "";
	private String nomeRemetente = "";
	private String assuntoEmail = "";
	private String textoEmail = "";

	public EmailSend(String listaDestinatarios, String nomeRemetente, String assuntoEmail, String textoEmail) {
		this.listaDestinatarios = listaDestinatarios;
		this.nomeRemetente = nomeRemetente;
		this.assuntoEmail = assuntoEmail;
		this.textoEmail = textoEmail;
	}

	public void EnviarEmail(boolean envioHtml) {
		
		try {
			
			Properties properties = new Properties();
			properties.put("mail.smtp.ssl.trust", "*");/* Autenticação SSL */
			properties.put("mail.smtp.auth", "true");/* Autorização */
			properties.put("mail.smtp.starttls", "true");/* Autenticação */
			properties.put("mail.smtp.host", "smtp.gmail.com");/* Servidor gmail Google */
			properties.put("mail.smtp.port", "465");/* Porta do servidor */
			properties.put("mail.smtp.socketFactory.port", "465");/* Especifica a porta a ser conectada pelo Socket */
			properties.put("mail.smtp.socketFactory.class",
					"javax.net.ssl.SSLSocketFactory");/* Clase socket de conexão ao SMTP */
	
			Session session = Session.getInstance(properties, new Authenticator() {
	
				protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(userName, password);
				};
	
			});
	
			Address[] toUser = InternetAddress.parse(listaDestinatarios);
	
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(userName, nomeRemetente));/* Quem está enviando */
			message.setRecipients(Message.RecipientType.TO, toUser);/* Email destinatario */
			message.setSubject(assuntoEmail);/* Assunto do e-mail */
	
			if (envioHtml) {
				message.setContent(textoEmail, "text/html; charset=UTF-8");
			} else {
				message.setText(textoEmail);
			}
	
			Transport.send(message);
		
		}catch(Exception e) {
			e.getStackTrace();
		}

		System.out.println("E-mail enviado!");

	}

	public void EnviarEmailAnexo(boolean envioHtml) {

		try {
			Properties properties = new Properties();
			properties.put("mail.smtp.ssl.trust", "*");/* Autenticação SSL */
			properties.put("mail.smtp.auth", "true");/* Autorização */
			properties.put("mail.smtp.starttls", "true");/* Autenticação */
			properties.put("mail.smtp.host", "smtp.gmail.com");/* Servidor gmail Google */
			properties.put("mail.smtp.port", "465");/* Porta do servidor */
			properties.put("mail.smtp.socketFactory.port", "465");/* Especifica a porta a ser conectada pelo Socket */
			properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");/* Clase socket de conexão ao SMTP */
	
			Session session = Session.getInstance(properties, new Authenticator() {
	
				protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(userName, password);
				};
	
			});
	
			Address[] toUser = InternetAddress.parse(listaDestinatarios);
	
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(userName, nomeRemetente));/* Quem está enviando */
			message.setRecipients(Message.RecipientType.TO, toUser);/* Email destinatario */
			message.setSubject(assuntoEmail);/* Assunto do e-mail */
	
			
			/*Parte 1 do e-mail que é texxto e a descrição do e-mail*/
			MimeBodyPart corpoEmail = new MimeBodyPart();
			
			if (envioHtml) {
				corpoEmail.setContent(textoEmail, "text/html; charset=UTF-8");
			} else {
				corpoEmail.setText(textoEmail);
			}
			
			/*Exemplo pegando um arquivo de uma pasta local e enviando*/
			//FileInputStream file = new FileInputStream(new File("C:\\Documentos\\Cesar_Augusto_Montaldi.pdf"));
			
			/*Parte 2 do e-mail que são os anexos em PDF*/
			MimeBodyPart anexoEmail = new MimeBodyPart();
			
			/*Onde é passado o simuladorDePDF você passa o seu arquivo gravado no banco de dados ou de alguma psta.*/
			anexoEmail.setDataHandler(new DataHandler(new ByteArrayDataSource(simuladorDePDF(), "aplication/pdf")));
			anexoEmail.setFileName("anexoemail.pdf");
			
			
			Multipart multipart = new MimeMultipart();/*Junta as 2 partes do e-mail corpo e o anexo*/
			multipart.addBodyPart(corpoEmail);
			multipart.addBodyPart(anexoEmail);
			
			
			message.setContent(multipart);
	
			Transport.send(message);
		
		}catch(Exception e) {
			e.getStackTrace();
		}

		System.out.println("E-mail enviado!");

	}

	/*
	 * Método simula o PDF ou qualquer arquivo que possa ser enviado por anexo no
	 * e-mail.
	 */
	/* Pode estar em um banco de dados, ou em uma pasta. */
	private FileInputStream simuladorDePDF() throws Exception {

		Document document = new Document();
		File file = new File("fileanexo.pdf");
		file.createNewFile();
		PdfWriter.getInstance(document, new FileOutputStream(file));
		document.open();
		document.add(new Paragraph("Conteudo do PDF anexo com Java Mail."));
		document.close();

		return new FileInputStream(file);
	}

}
