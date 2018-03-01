package br.com.ecc.service;

import br.com.ecc.model.Usuario;
import br.com.ecc.repository.UsuarioRepository;
import br.com.ecc.util.FacesMessages;
import br.com.ecc.util.NegocioException;
import br.com.ecc.util.Transactional;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

/**
 * Classe responsável por gerenciar as regras de negócio 
 * relacionadas as usuários do programa. 
 * </br>-----------------------------------------------------------------------</br>
 *                      <b>Regras de negócio</b>
 * </br>-----------------------------------------------------------------------</br>
 * Gerencia o perfil dos usuários. O sistema tem comportamento diferente 
 * para cada perfil permitido. 
 * </br>-----------------------------------------------------------------------</br>
 * @author Cleber Seixas
 * @since 08/10/2017
 */
public class UsuarioService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioRepository usuarioRepository;

	@Transactional
	public void salvar(Usuario usuario, boolean flag){
		try{
			if (usuario.getPerfil().equals("Administrador")) {
				usuario.setPermissao("ROLE_ADMINISTRADOR");
			} else if (usuario.getPerfil().equals("Usuário")) {
				usuario.setPermissao("ROLE_USUARIO");
			} else if (usuario.getPerfil().equals("Dirigente")) {
				usuario.setPermissao("ROLE_DIRIGENTE");
			} else if (usuario.getPerfil().equals("Secretária")) {
				usuario.setPermissao("ROLE_SECRETARIA");
			}
  			this.usuarioRepository.salvar(usuario);
			if (flag) {
				FacesMessages.info("Usuário cadastrado");
			}
		}catch(NegocioException e){
			FacesMessages.error(e.getMessage());
		}	
	}

	@Transactional
	public void excluir(Usuario usuario){
		try{
			usuario = usuarioRepository.carregar(usuario.getId());
			this.usuarioRepository.excluir(usuario);
			FacesMessages.info("Registro excluído!");
		}catch(NegocioException e){
			FacesMessages.error(e.getMessage());
		}	
	}	

	public Usuario carregar(Long id){
		try {
			return usuarioRepository.carregar(id);
		} catch(NegocioException e){
			FacesMessages.error(e.getMessage());FacesMessages.error(e.getMessage());
			return null;
		}
	}

	public List<Usuario> listar(){
		try {
			return usuarioRepository.listar();
		} catch(NegocioException e){
			FacesMessages.error(e.getMessage());
			return null;
		}
	}

	public List<Usuario> listarUsuarios(){
		try {
			return usuarioRepository.listar();
		} catch(NegocioException e){
			FacesMessages.error(e.getMessage());
			return null;
		}
	}
	
	public List<Usuario> listarUsuarios(String nome){
		try {
			return usuarioRepository.listarUsuarios(nome);
		} catch(NegocioException e){
			FacesMessages.error(e.getMessage());
			return null;
		}
	}	
	
	public Usuario buscaPorLogin(String login) {
		try {
			return usuarioRepository.buscarPorLogin(login);
		} catch(NegocioException e){
			FacesMessages.error(e.getMessage());
			return null;
		}
	}

	public Usuario buscaPorEmail(String email) {
		try {
			return usuarioRepository.buscarPorEmail(email);
		} catch(NegocioException e){
			FacesMessages.error(e.getMessage());
			return null;
		}
	}

	public boolean verificaUsuarioJaCadastrado(String login, String email) {
		try {
			return usuarioRepository.verificaUsuarioJaCadastrado(login, email);
		} catch (NegocioException e) {
			FacesMessages.error(e.getMessage());
			return true;
		}
	}

}