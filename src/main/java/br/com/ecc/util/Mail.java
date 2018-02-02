package br.com.ecc.util;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;

public class Mail {
	public static void enviar(String assunto, String mensagem, String... destinatarios)  {
		try {
			MultiPartEmail email = new MultiPartEmail();        
			// configura email
			email.setHostName("smtp.mpro.mp.br"); // o servidor SMTP para envio do e-mail  
			email.addTo(destinatarios); //destinatário
			email.setFrom("nat@mpro.mp.br", "NAT | Núcleo de Análises Técnicas"); // remetente  
			email.setSubject(assunto); // assunto do e-mail  
			email.setMsg(mensagem); //conteudo do e-mail  
			email.setAuthentication("nat", "nat@mpro");  
			email.setSmtpPort(25);  
			email.send();
		} catch (EmailException e) {
			throw new NegocioException(e.getMessage());
		}
	}	
}