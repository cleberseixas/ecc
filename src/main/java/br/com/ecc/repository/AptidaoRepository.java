package br.com.ecc.repository;

import br.com.ecc.model.Aptidao;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class AptidaoRepository {

	private EntityManager manager;

	@Inject
	public AptidaoRepository(EntityManager manager) {
		this.manager = manager;
	}
	
	public void salvar(Aptidao aptidao) {
		manager.merge(aptidao);
	}

	public void excluir(Aptidao aptidao) {
		manager.remove(aptidao);
	}

	public Aptidao carregar(Long id) {
		return manager.find(Aptidao.class, id);
	}

	public List<Aptidao> listar() {
		TypedQuery<Aptidao> query = manager.createQuery("from Aptidao", Aptidao.class);
		return query.getResultList();
	}

	/**
	 * Método responsável por excluir os registros da tabela APTIDÕES, que estão no LIMBO,
	 * que são inseridos pelo mapeamento ManyToMany
	 * chamado ao abrir a tela Fichas e após salvar ou remover alguma aptidão;
	 */
	public void removeAptidaoLimbo() {
		manager.getTransaction().begin();
		Query query = manager.createNativeQuery("DELETE FROM APTIDOES WHERE APTIDAO NOT IN (SELECT APTIDAO FROM FICHAS_APTIDOES)");
		query.executeUpdate();
		manager.getTransaction().commit();
	}
}
