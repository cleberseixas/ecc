package br.com.ecc.repository;

import br.com.ecc.model.EquipeEccCasal;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class EquipeEccCasalRepository {

	private EntityManager manager;

	@Inject
	public EquipeEccCasalRepository(EntityManager manager) {
		this.manager = manager;
	}
	
	public void salvar(EquipeEccCasal equipeEccCasal) {
		manager.merge(equipeEccCasal);
	}

	public void excluir(EquipeEccCasal equipeEccCasal) {
		manager.remove(equipeEccCasal);
	}

	public EquipeEccCasal carregar(Long id) {
		return manager.find(EquipeEccCasal.class, id);
	}

	/**
	 * Método que verifica se o Casal já está presente em OUTRA EQUIPE NO MESMO ECC
	 * Tanto como coordenador ou como membro da Equipe
	 * @param ecc - ID do ECC
	 * @param casal - ID do Casal
	 * @return
	 */
	public boolean casalJaExisteEccCoordenadorOuEquipe(Long ecc, Long casal) {
		TypedQuery<Long> query = manager.createQuery("select count(ee.id) from EquipeEcc ee " +
				" inner join EquipeEccCasal ec on ee.id = ec.equipeEcc.id " +
				" where ee.ecc.id = :ECC" +
				" and (ec.ficha.id = :CASAL" +
				" or ec.ficha.id = :CASAL)", Long.class);
		query.setParameter("ECC", ecc);
		query.setParameter("CASAL", casal);
		return (query.getSingleResult() > 0 ? true : false);
	}

	public List<EquipeEccCasal> listar() {
		TypedQuery<EquipeEccCasal> query = manager.createQuery("from EquipeEccCasal", EquipeEccCasal.class);
		return query.getResultList();
	}


	/**
	 * Método responsável por excluir os registros da tabela EQUIPES_ECCS_CASAIS, que estão no LIMBO,
	 * que são inseridos pelo mapeamento ManyToMany
	 * chamado ao abrir a tela Montagem Equipe e após salvar ou remover algum casal na equipe;
	 */
	public void removeCasaisLimbo() {
		manager.getTransaction().begin();
		Query query = manager.createNativeQuery("DELETE FROM EQUIPES_ECCS_CASAIS WHERE EQUIPE_ECC_CASAL NOT IN (SELECT EQUIPE_ECC_CASAL FROM EQUIPES_ECCS_CASAIS_CASAIS)");

		query.executeUpdate();
		manager.getTransaction().commit();
	}
}
