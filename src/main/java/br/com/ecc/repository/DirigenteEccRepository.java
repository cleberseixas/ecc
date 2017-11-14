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
	 * Método que verifica se o Casal/Equipe já faz parte da EQUIPE (DIRIGENTE)
	 * @param ecc - ID do ECC
	 * @param casal - ID do Casal
	 * @param equipe - ID da equipe
	 * @return
	 */
	public boolean casalJaExisteEccDirigente(Long ecc, Long casal, Long equipe) {
		TypedQuery<Long> query = manager.createQuery("select count(id) from DirigenteEcc " +
				" where ecc.id = :ECC" +
				" and (ficha.id = :CASAL or equipe.id = :EQUIPE)", Long.class);
		query.setParameter("ECC", ecc);
		query.setParameter("CASAL", casal);
		query.setParameter("EQUIPE", equipe);
		return (query.getSingleResult() > 0 ? true : false);
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
