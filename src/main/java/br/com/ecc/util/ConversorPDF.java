package br.com.ecc.util;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.ByteArrayRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;

public class ConversorPDF {
	private static final String PDF = "application/pdf";
	public static String CONVERSOR_WS_URL = "http://10.20.0.78:8080/jodconverter/service";
	
	public  static byte[] converte(byte[] arquivoFonteBytes, String contentType) throws Exception {
		
		PostMethod post = new PostMethod(CONVERSOR_WS_URL);
		try {
			post.addRequestHeader("Content-type", contentType);
			post.addRequestHeader("Accept", PDF);
			RequestEntity entity = new ByteArrayRequestEntity(arquivoFonteBytes, contentType);
			post.setRequestEntity(entity);
			
			HttpClient httpCliente = new HttpClient();
			int codigoHTTP = httpCliente.executeMethod(post);
			
			if (codigoHTTP != 200) {
				throw new Exception("Erro não esperado: " + codigoHTTP);
			}
			return post.getResponseBody();
		} catch (Exception e) {
			throw new Exception("Erro ao converter para tipo " + PDF + ", " + e.getMessage());
		} finally {
			post.releaseConnection();
		}
	}
	
}
