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

import org.junit.Test;


public class AppTest {
	
	private String userName = "";
	private String password = "";
	
	@Test
	public void testEmail() {
		
			try {
				Properties properties = new Properties();
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
				
				Address[] toUser = InternetAddress.parse("guto_montaldi@yahoo.com.br, montaldi35@gmail.com");
				
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress(userName));/*Quem está enviando*/
				message.setRecipients(Message.RecipientType.TO, toUser);/*Email destinatario*/
				message.setSubject("Chegou um e-mail enviado com Java");/*Assunto do e-mail*/
				message.setText("Olá programador, você acaba de receber um e-mail enviado com Java do curso Formação Java Web do Jdev Treinamentos.");
				
				Transport.send(message);
				
				System.out.println("E-mail enviado!");
				
			}catch (Exception e) {
				e.printStackTrace();
		}
	}

}

