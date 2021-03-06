package br.com.ecc.controller;


import br.com.ecc.model.Usuario;
import br.com.ecc.service.UsuarioService;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Classe destinada à capturar o usuário logado, mantendo-o conhecido durante toda a sessão
 * 
 * @author Cleber Seixas
 * @since 08/10/2018
 * */

@Named
@SessionScoped
public class ContextoBean implements Serializable {
	private static final long serialVersionUID = 12L;

	@Inject
	private Usuario usuarioLogado = null;

	@Inject
	private UsuarioService usuarioService;
	
	public Usuario getUsuarioLogado() {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext external =  context.getExternalContext();
		String login = external.getRemoteUser();
		if (this.usuarioLogado == null || !login.equals(this.usuarioLogado.getLogin())) {
			if (login != null) {
				this.usuarioLogado = usuarioService.buscaPorLogin(login);
			}
		}
		return usuarioLogado;
	}
}