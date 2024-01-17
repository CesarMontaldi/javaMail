package cesarMontaldi.javaMail;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

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
				properties.put("mail.smtp.socketDactory.port", "465");/* Especifica a porta a ser conectada pelo Socket */
				properties.put("mail.smtp.socketDactory.class", "javax.net.ssl.SSLSocketFactory");/* Clase socket de conexão ao SMTP */
				
				Session session = Session.getInstance(properties, new Authenticator() {
					
					protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(userName, password);
					};
	
				});
				
				System.out.println(session);
				
			}catch (Exception e) {
				e.printStackTrace();
		}
	}

}

