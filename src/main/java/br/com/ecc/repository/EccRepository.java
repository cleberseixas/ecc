package br.com.ecc.repository;

import br.com.ecc.model.Ecc;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class EccRepository {

	private EntityManager manager;

	@Inject
	public EccRepository(EntityManager manager) {
		this.manager = manager;
	}
	
	public void salvar(Ecc ecc) {
		if (ecc.getId() == null)
			manager.persist(ecc);
		else
			manager.merge(ecc);
	}

	public void excluir(Ecc ecc) {
		manager.remove(ecc);
	}

	public Ecc carregar(Long id) {
		return manager.find(Ecc.class, id);
	}

	public List<Ecc> listar() {
		TypedQuery<Ecc> query = manager.createQuery("from Ecc order by numero desc", Ecc.class);
		return query.getResultList();
	}
}
