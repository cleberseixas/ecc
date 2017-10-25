package br.com.ecc.repository;

import br.com.ecc.model.Atividade;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class AtividadeRepository {

	private EntityManager manager;

	@Inject
	public AtividadeRepository(EntityManager manager) {
		this.manager = manager;
	}
	
	public void salvar(Atividade atividade) {
		manager.merge(atividade);
	}

	public void excluir(Atividade atividade) {
		manager.remove(atividade);
	}

	public Atividade carregar(Long id) {
		return manager.find(Atividade.class, id);
	}

	public List<Atividade> listar() {
		TypedQuery<Atividade> query = manager.createQuery("from Atividade", Atividade.class);
		return query.getResultList();
	}

	/**
	 * Método responsável por excluir os registros da tabela ATIVIDADES, que estão no LIMBO,
	 * que são inseridos pelo mapeamento ManyToMany
	 * chamado ao abrir a tela Fichas e após salvar ou remover alguma atividade;
	 */
	public void removeAtividadeLimbo() {
		manager.getTransaction().begin();
		Query query = manager.createNativeQuery("DELETE FROM ATIVIDADES WHERE ATIVIDADE NOT IN (SELECT ATIVIDADE FROM FICHAS_ATIVIDADES)");
		query.executeUpdate();
		manager.getTransaction().commit();
	}
}
