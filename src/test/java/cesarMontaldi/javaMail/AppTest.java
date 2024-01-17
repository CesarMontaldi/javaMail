package cesarMontaldi.javaMail;

import org.junit.Test;

public class AppTest {
	
	@Test
	public void emailTest() throws Exception {
		
		StringBuilder stringBuilder = new StringBuilder();
		
		stringBuilder.append("<h4>Bem vindo ao curso de Java web.</h4><br>");
		stringBuilder.append("Olá,<br><br>");
		stringBuilder.append("Você está recebendo o acesso ao curso de Java.<br><br>");
		stringBuilder.append("Para ter acesso clique no botão abaixo.<br><br><br>");
		
		stringBuilder.append("<b>Login: </b>guto_montaldi@yahoo.com.br<br>");
		stringBuilder.append("<b>Senha: </b>37R4KM#!<br><br><br>");
		
		stringBuilder.append("<a target=\"_blank\" href=\"http://google.com\"style=\"color: #2525A7; padding: 3px 8px; text-align:center; text-decoration: none; border-radius: 8px; font-size: 18px; font-family: courier; border: 1px solid green; background-color: #99DA39;\">Acessar<a/>");
		
		EmailSend emailSend = new EmailSend("guto_montaldi@yahoo.com.br",
											"Cesar Montaldi",
											"Acesso ao curso Java web",
											stringBuilder.toString());
		
		
		emailSend.EnviarEmail(true);
	} 
	
}

