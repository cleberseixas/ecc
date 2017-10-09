package br.com.ecc.repository;

import br.com.ecc.model.Circulo;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class CirculoRepository {

	private EntityManager manager;

	@Inject
	public CirculoRepository(EntityManager manager) {
		this.manager = manager;
	}
	
	public void salvar(Circulo circulo) {
		if (circulo.getId() == null)
			manager.persist(circulo);
		else
			manager.merge(circulo);
	}

	public void excluir(Circulo circulo) {
		manager.remove(circulo);
	}

	public Circulo carregar(Long id) {
		return manager.find(Circulo.class, id);
	}

	public List<Circulo> listar() {
		TypedQuery<Circulo> query = manager.createQuery("from Circulo order by descricao", Circulo.class);
		return query.getResultList();
	}
}
