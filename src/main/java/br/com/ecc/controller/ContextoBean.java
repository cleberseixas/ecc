package br.com.ecc.controller;


import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Classe destinada à capturar o usuário logado, mantendo-o conhecido durante toda a sessão
 * 
 * @author Marcelo Douglas Silva dos Santos
 * @since 08/07/2013
 * @version Atualizada em 03/06/2015
 * */

@Named
@SessionScoped
public class ContextoBean implements Serializable {
	private static final long serialVersionUID = 1L;
//	private UsuarioAstec usuarioLogado = null;
//
//	@Inject
//	private UsuarioAstecRN usuarioRN;
	
	//public UsuarioAstec getUsuarioLogado() {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext external =  context.getExternalContext();
		String login = external.getRemoteUser();
//		if (this.usuarioLogado == null || !login.equals(this.usuarioLogado.getLogin())) {
//			if (login != null) {
//				this.usuarioLogado = usuarioRN.buscaPorLogin(login);
//			}
//		}
//		return usuarioLogado;
//		return null;
//	}
}