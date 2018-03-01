package br.com.ecc.util;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;

public class Mail {
	public static void enviar(String assunto, StringBuffer mensagem, String... destinatarios)  {
		try {
			MultiPartEmail email = new MultiPartEmail();        
			// configura email
//			email.setHostName("smtp.gmail.com"); // o servidor SMTP para envio do e-mail
//			email.addTo(destinatarios); //destinatário
//			email.setSubject(assunto); // assunto do e-mail
//			email.setFrom("cleber.malu@gmail.com", "Cleber de Oliveira Seixas"); // remetente
//			email.setMsg(mensagem.toString()); //conteudo do e-mail
//			mensagem.append("\n\n=======================================================\n");
//			mensagem.append("Paróquia São Luiz Gonzaga");
//			mensagem.append("\n");
//			mensagem.append("ECC-SLG");
//			mensagem.append("\nMensagem enviada em ");
//			mensagem.append(Util. formataDataHoraAtualComPonto());
//			mensagem.append("\nNão é necessário responder esta mensagem. ");
//			mensagem.append("Em caso de dúvidas, contacte:\n");
//			mensagem.append("Cleber de Oliveira Seixas\n");
//			mensagem.append("e-mail: cleber.malu@gmail.com\n");
//			mensagem.append("Celular: (69) 98104-3796\n");
//			mensagem.append("\n========================================================");
//			email.setMsg(mensagem.toString()); //conteudo do e-mail
//			email.setAuthentication("cleber.malu", "parquetweb");
//			email.setSmtpPort(465);
//			email.send();

			email.setHostName("smtp.mpro.mp.br"); // o servidor SMTP para envio do e-mail
			email.addTo(destinatarios); //destinatário
			email.setSubject(assunto); // assunto do e-mail
			email.setFrom("mp@mpro.mp.br", "MP"); // remetente

			mensagem.append("\n\n=======================================================\n");
			mensagem.append("Paróquia São Luiz Gonzaga");
			mensagem.append("\n");
			mensagem.append("ECC-SLG");
			mensagem.append("\nMensagem enviada em ");
			mensagem.append(Util. formataDataHoraAtualComPonto());
			mensagem.append("\nNão é necessário responder esta mensagem. ");
			mensagem.append("Em caso de dúvidas, contacte:\n");
			mensagem.append("Cleber de Oliveira Seixas\n");
			mensagem.append("e-mail: cleber.malu@gmail.com\n");
			mensagem.append("Celular: (69) 98104-3796\n");
			mensagem.append("\n========================================================");
			email.setMsg(mensagem.toString()); //conteudo do e-mail
			//Util.incluiAssinaturaNaMensagem(mensagem);
			email.setAuthentication("mp", "12345");
			email.setSmtpPort(25);
			email.send();

		} catch (EmailException e) {
			throw new NegocioException(e.getMessage());
		}
	}
}