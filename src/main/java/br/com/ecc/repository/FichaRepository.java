package br.com.ecc.repository;

import br.com.ecc.model.Ficha;
import br.com.ecc.util.Constantes;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class FichaRepository {

	private EntityManager manager;

	@Inject
	public FichaRepository(EntityManager manager) {
		this.manager = manager;
	}
	
	public void salvar(Ficha ficha) {
		if (ficha.getId() == null)
			manager.persist(ficha);
		else
			manager.merge(ficha);
	}

	public void excluir(Ficha ficha) {
		manager.remove(ficha);
	}

	public Ficha carregar(Long id) {
		return manager.find(Ficha.class, id);
	}

	public List<Ficha> listar() {
		TypedQuery<Ficha> query = manager.createQuery("from Ficha order by nomeUsual", Ficha.class);
		return query.getResultList();
	}

	public List<Ficha> listarEncontreiroEncontrista(String parametro) {
		TypedQuery<Ficha> query = manager.createQuery("from Ficha where situacao= :SITUACAO order by nomeUsual", Ficha.class);
		query.setParameter("SITUACAO", parametro);
		return query.getResultList();
	}
}
