package br.com.ecc.repository;

import br.com.ecc.model.DirigenteEcc;
import br.com.ecc.model.Equipe;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class DirigenteEccRepository {

	private EntityManager manager;

	@Inject
	public DirigenteEccRepository(EntityManager manager) {
		this.manager = manager;
	}
	
	public void salvar(DirigenteEcc dirigenteEcc) {
		manager.merge(dirigenteEcc);
	}

	public void excluir(DirigenteEcc dirigenteEcc) {
//		Object c= manager.merge(dirigenteEcc);
//		manager.remove(c);
//		manager.remove(manager.getReference(Dirigente.class, dirigenteEcc.getId()));
//		//manager.remove(manager.getReference(Dirigente.class, dirigenteEcc.getId()));
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

	/**
	 * Método responsável por excluir os registros da tabela DIRIGENTES, que estão no LIMBO,
	 * que são inseridos pelo mapeamento ManyToMany
	 * chamado ao abrir a tela ECCs e após salvar ou remover algum dirigente;
	 */

	public void removeDirigenteLimbo() {
		manager.getTransaction().begin();
		Query query = manager.createNativeQuery("DELETE FROM DIRIGENTES WHERE DIRIGENTE NOT IN (SELECT DIRIGENTE FROM ECCS_DIRIGENTES)");
		query.executeUpdate();
		manager.getTransaction().commit();
	}
}
