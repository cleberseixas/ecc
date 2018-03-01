package br.com.ecc.repository;


import br.com.ecc.model.Usuario;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UsuarioRepository {

	private EntityManager manager;

	@Inject
	public UsuarioRepository(EntityManager manager) {
		this.manager = manager;
	}
	
	public void salvar(Usuario usuario) {
//		if (usuario.getId() == null)
//			manager.persist(usuario);
//		else
		manager.merge(usuario);
	}

	public void excluir(Usuario usuario) {
		manager.remove(usuario);
	}

	public Usuario carregar(Long id) {
		return manager.find(Usuario.class, id);
	}

	public List<Usuario> listar() {
		TypedQuery<Usuario> query = manager.createQuery("from Usuario order by nome", Usuario.class);
		return query.getResultList();
	}

	public Usuario buscarPorLogin(String login) {
		TypedQuery<Usuario> query = manager.createQuery("from Usuario where login = :login", Usuario.class);
		query.setParameter("login", login);
		if (query.getResultList().size() > 0)
			return query.getSingleResult();
		return null;
	}

	public Usuario buscarPorEmail(String email) {
		TypedQuery<Usuario> query = manager.createQuery("from Usuario where email = :email", Usuario.class);
		query.setParameter("email", email);
		if (query.getResultList().size() > 0)
			return query.getSingleResult();
		return null;
	}

	@SuppressWarnings("rawtypes")
	public List<Usuario> listarUsuarios(String nome) {
		List<Usuario> listAux = new ArrayList<Usuario>();
		Query query = null;
		query = manager.createNativeQuery("select usuario, ativo, email, login, nome, perfil, senha, permissao "
				+ "from usuarios "
				+ "where nome like  :nome");
		query.setParameter("nome", "%"+nome+"%");
		List lst = query.getResultList();
		Iterator iter = lst.iterator();
		Usuario usuario = null;
		while (iter.hasNext()) {
			usuario = new Usuario();
			Object[] obj = (Object[])iter.next();
			usuario.setId(Long.parseLong(obj[0].toString()));
			usuario.setAtivo(Boolean.parseBoolean(obj[1].toString()));
			usuario.setEmail(obj[2].toString());
			usuario.setLogin(obj[3].toString());
			usuario.setNome(obj[4].toString());
			usuario.setPerfil(obj[5].toString());
			usuario.setSenha(obj[6].toString());
			usuario.setPermissao(obj[7].toString());
			listAux.add(usuario);
		}
		return listAux;
	}

	/**
	 * Método que verifica se já existe usuário Cadastrado com esse login e e-mail
	 * @param login - login do usuário
	 * @param email - email do usuário
	 * @return
	 */
	public boolean verificaUsuarioJaCadastrado(String login, String email) {
		TypedQuery<Long> query = manager.createQuery("select count(id) from Usuario  " +
				" where " +
				" login = :LOGIN ", Long.class);
		//" login = :LOGIN and email =:EMAIL", Long.class);
		query.setParameter("LOGIN", login);
//		query.setParameter("EMAIL", email);
		return (query.getSingleResult() > 0 ? true : false);
	}
}
