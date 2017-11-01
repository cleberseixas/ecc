package br.com.ecc.repository;

import br.com.ecc.model.Equipe;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class EquipeRepository {

	private EntityManager manager;

	@Inject
	public EquipeRepository(EntityManager manager) {
		this.manager = manager;
	}
	
	public void salvar(Equipe equipe) {
		if (equipe.getId() == null)
			manager.persist(equipe);
		else
			manager.merge(equipe);
	}

	public void excluir(Equipe equipe) {
		manager.remove(equipe);
	}

	public Equipe carregar(Long id) {
		return manager.find(Equipe.class, id);
	}

	public List<Equipe> listar() {
		TypedQuery<Equipe> query = manager.createQuery("from Equipe order by descricao", Equipe.class);
		return query.getResultList();
	}

	public List<Equipe> listarEquipeDirigentes() {
		TypedQuery<Equipe> query = manager.createQuery("from Equipe where automatica=false and palestrante=false and ativo=true order by descricao", Equipe.class);
		return query.getResultList();
	}

	public List<Equipe> listarEquipeAutomatica() {
		TypedQuery<Equipe> query = manager.createQuery("from Equipe where automatica=true and palestrante=false and ativo=true order by descricao", Equipe.class);
		return query.getResultList();
	}

	public List<Equipe> listarAtividades() {
		TypedQuery<Equipe> query = manager.createQuery("from Equipe where ativo=true order by descricao", Equipe.class);
		return query.getResultList();
	}
}
