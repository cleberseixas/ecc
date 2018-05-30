package br.com.ecc.controller;

import br.com.ecc.model.Usuario;
import br.com.ecc.service.UsuarioService;
import br.com.ecc.util.FacesMessages;
import br.com.ecc.util.Mail;
import br.com.ecc.util.Util;
import org.primefaces.context.RequestContext;
import org.springframework.util.DigestUtils;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * Classe responsável por interligar a view à regra de negócio - Usuários do sistema
 * @author Cleber Seixas
 * @since 28/02/2018
 */
@Named
@RequestScoped
public class UsuarioBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Usuario usuario = new Usuario();

	private List<Usuario> lista;
	private List<Usuario> listaQuemPodeVer;

	private String destinoSalvar;
	private String senhaCriptografada;
	private String pesquisaUsuario;

	private String email;

	@Inject
	UsuarioService usuarioService;
	
	@Inject
	ContextoBean contextoBean;
	
	public String novo() {
		this.destinoSalvar = "usuarioSucesso";
		this.usuario = new Usuario();
		this.usuario.setAtivo(true);
		return "usuarioEcc";
	}

	public String editar() {
		return "/administrador/usuario";
	}

	public void pesquisar() {
		lista = usuarioService.listarUsuarios(pesquisaUsuario);
	}	
	
	public String salvar() {
		if (this.usuario.getId() == null) {
			if  (verificaUsuarioLoginSenha(this.usuario)) {
				FacesMessages.info("Já existe um usuário cadastrado com esse Login!");
				//FacesMessages.info("Já existe um usuário cadastrado com esse e-mail e login!");
			} else {
				this.usuario.setAtivo(true);
				if (this.usuario.getId() == null) {
					//this.usuario.setSenha("123");
					String senha = this.usuario.getSenha();
					String senhaCripto = DigestUtils.md5DigestAsHex(senha.getBytes());
					this.usuario.setSenha(senhaCripto);
				}
				usuarioService.salvar(this.usuario, true);
				this.usuario = new Usuario();
				pesquisaUsuario = "";
			}
		}
		return null;
	}

	public void recuperaSenha() {
		try {
			if (this.email.isEmpty()) {
				FacesMessages.error("Favor informar o e-mail!");
				RequestContext.getCurrentInstance().addCallbackParam("validationFailed", true);
				return;
			} else {
				if (!Util.validaEnderecoDeEmail(this.email)) {
					FacesMessages.error("Endereço de e-mail inválido!");
					RequestContext.getCurrentInstance().addCallbackParam("validationFailed", true);
				} else {
					Usuario usu = usuarioService.buscaPorEmail(this.email);
					if (usu == null) {
						FacesMessages.error("Usuário não cadastrado!");
						RequestContext.getCurrentInstance().addCallbackParam("validationFailed", true);
					} else {
						String novaSenha = usu.getLogin()+"123";
						usu.setSenha(novaSenha);
						String senhaCripto = DigestUtils.md5DigestAsHex(novaSenha.getBytes());
						usu.setSenha(senhaCripto);
						usuarioService.salvar(usu, false);

						StringBuffer msg = new StringBuffer("Sua Senha é "+novaSenha);
						Mail.enviar("Recuperação de Senha ", msg, usu.getEmail());
						FacesMessages.info("Sua senha foi enviada para o e-mail");
					}
				}
			}
		} catch (Exception e) {
			FacesMessages.error(e.getMessage());
			RequestContext.getCurrentInstance().addCallbackParam("validationFailed", true);
		}
	}

	public boolean verificaUsuarioLoginSenha(Usuario usuario) {
		return usuarioService.verificaUsuarioJaCadastrado(this.usuario.getLogin(), this.usuario.getEmail());
	}

	public void excluir() {
		usuarioService.excluir(this.usuario);
		this.lista = null;
	}

	public void ativar() {
		this.usuario.setAtivo(!this.usuario.isAtivo());
		usuarioService.salvar(this.usuario, true);
		this.usuario = new Usuario();
	}	
	
	public List<Usuario> getLista() {
		if (this.lista == null) {
			this.lista = usuarioService.listar();
		}
		return this.lista;
	}

	public List<Usuario> getListaQuemPodeVer() {
		if (this.listaQuemPodeVer == null) {
			//this.listaQuemPodeVer = usuarioAstecRepository.listarUsuariosPorComarca(contextoBean.getUsuarioLogado());
		}
		return this.listaQuemPodeVer;
	}	
	
	public List<Usuario> getListaQuemPodeVerChefe() {
		if (this.listaQuemPodeVer == null) {
			this.listaQuemPodeVer = usuarioService.listarUsuarios();
		}
		return this.listaQuemPodeVer;
	}	
	
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getDestinoSalvar() {
		return destinoSalvar;
	}

	public void setDestinoSalvar(String destinoSalvar) {
		this.destinoSalvar = destinoSalvar;
	}

	public String getSenhaCriptografada() {
		return senhaCriptografada;
	}

	public void setSenhaCriptografada(String senhaCriptografada) {
		this.senhaCriptografada = senhaCriptografada;
	}

	public String getPesquisaUsuario() {
		return pesquisaUsuario;
	}

	public void setPesquisaUsuario(String pesquisaUsuario) {
		this.pesquisaUsuario = pesquisaUsuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}