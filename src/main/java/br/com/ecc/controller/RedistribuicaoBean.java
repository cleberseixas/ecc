package br.com.ecc.controller;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Classe responsável por exibir a página adequada ao perfil que está sendo logado.
 * @author 44455
 * @since 02/09/2016
 */
@Named
@SessionScoped
public class RedistribuicaoBean implements Serializable {
	
	@Inject
	private ContextoBean contextoBean;

	private static final long serialVersionUID = 1L;

	public String distribui() {
		//pega o perfil do usuário logado e direciona para a página correta
		String perfil = this.contextoBean.getUsuarioLogado().getPerfil();
		if (perfil.equals("Dirigente"))
			return "/dirigente/menuDirigente";
		else if (perfil.equals("Administrador")) 
			return "/administrador/menuAdministrador";
//		else if (perfil.equals("Analista"))
//			return "/analista/parecerAnalise";
//		else if (perfil.equals("Usuário"))
//			return "/usuario/solicitaParecer";
//		else if (perfil.equals("Gestor"))
//			return "/gestao/relatorio";
		return "";
	}
}
