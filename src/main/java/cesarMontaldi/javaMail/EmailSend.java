package cesarMontaldi.javaMail;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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
			}else {
				message.setText(textoEmail);
			}
			
			Transport.send(message);

			System.out.println("E-mail enviado!");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
