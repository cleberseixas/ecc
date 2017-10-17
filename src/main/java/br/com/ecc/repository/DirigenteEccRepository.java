package br.com.ecc.repository;

import br.com.ecc.model.Dirigente;
import br.com.ecc.model.Equipe;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class DirigenteEccRepository {

	private EntityManager manager;

	@Inject
	public DirigenteEccRepository(EntityManager manager) {
		this.manager = manager;
	}
	
	public void salvar(Dirigente dirigenteEcc) {
			manager.merge(dirigenteEcc);
	}

	public void excluir(Dirigente dirigenteEcc) {
		manager.remove(dirigenteEcc);
	}

	public Dirigente carregar(Long id) {
		return manager.find(Dirigente.class, id);
	}

	public List<Dirigente> listar() {
		TypedQuery<Dirigente> query = manager.createQuery("from Equipe order by descricao", Dirigente.class);
		return query.getResultList();
	}

	public List<Equipe> listarEquipeDirigentes() {
		TypedQuery<Equipe> query = manager.createQuery("from Equipe where automatica=false order by descricao", Equipe.class);
		return query.getResultList();
	}
}
