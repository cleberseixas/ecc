package br.com.ecc.repository;

import br.com.ecc.model.Palestra;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class PalestraRepository {

	private EntityManager manager;

	@Inject
	public PalestraRepository(EntityManager manager) {
		this.manager = manager;
	}
	
	public void salvar(Palestra palestra) {
		if (palestra.getId() == null)
			manager.persist(palestra);
		else
			manager.merge(palestra);
	}

	public void excluir(Palestra palestra) {
		manager.remove(palestra);
	}

	public Palestra carregar(Long id) {
		return manager.find(Palestra.class, id);
	}

	public List<Palestra> listar() {
		TypedQuery<Palestra> query = manager.createQuery("from Palestra order by ordemImpressao", Palestra.class);
		return query.getResultList();
	}

	public List<Palestra> listarAtivas() {
		TypedQuery<Palestra> query = manager.createQuery("from Palestra where ativo=true order by descricao", Palestra.class);
		return query.getResultList();
	}
}
