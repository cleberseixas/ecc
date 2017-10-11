package br.com.ecc.repository;

import br.com.ecc.model.DirigenteEcc;
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
	
	public void salvar(DirigenteEcc dirigenteEcc) {
		if (dirigenteEcc.getId() == null)
			manager.persist(dirigenteEcc);
		else
			manager.merge(dirigenteEcc);
	}

	public void excluir(DirigenteEcc dirigenteEcc) {
		manager.remove(dirigenteEcc);
	}

	public DirigenteEcc carregar(Long id) {
		return manager.find(DirigenteEcc.class, id);
	}

	public List<DirigenteEcc> listar() {
		TypedQuery<DirigenteEcc> query = manager.createQuery("from Equipe order by descricao", DirigenteEcc.class);
		return query.getResultList();
	}

	public List<Equipe> listarEquipeDirigentes() {
		TypedQuery<Equipe> query = manager.createQuery("from Equipe where automatica=false order by descricao", Equipe.class);
		return query.getResultList();
	}
}
