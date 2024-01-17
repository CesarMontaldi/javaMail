package cesarMontaldi.javaMail;

import org.junit.Test;

public class AppTest {
	
	@Test
	public void emailTest() {
		
		EmailSend emailSend = new EmailSend("guto_montaldi@yahoo.com.br",
											"Cesar Montaldi",
											"Testanto e-mail com Java",
											"Olá parábens você está indo super bem, continue assim vai longe.");
		emailSend.EnviarEmail();
	} 
	
}

