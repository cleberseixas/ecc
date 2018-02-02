package br.com.ecc.util;

import br.com.ecc.controller.ContextoBean;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 * Classe que captura o contexto da aplicação. 
 * Observação: Ainda é mantido por conta do Hibernate Envers que não aceita injeção de dependência.
 * A classe RevListener (do pacote util) precisa do contexto e como o CDI não funciona, necessitamos do ContextoUtil. 
 * 
 * @author Marcelo Douglas Silva dos Santos
 * @since 09/07/2013
 * */
public class ContextoUtil {
	public static ContextoBean getContextoBean() {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext external = context.getExternalContext();
		HttpSession session = (HttpSession)external.getSession(true);
		ContextoBean contextBean = (ContextoBean)session.getAttribute("contextoBean");
		return contextBean;
	}
	
}
