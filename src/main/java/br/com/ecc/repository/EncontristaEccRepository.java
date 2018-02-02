package br.com.ecc.repository;

import br.com.ecc.model.EncontristaEcc;
import br.com.ecc.model.EquipeEcc;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class EncontristaEccRepository {

	private EntityManager manager;

	@Inject
	public EncontristaEccRepository(EntityManager manager) {
		this.manager = manager;
	}
	
	public void salvar(EncontristaEcc encontristaEcc) {
		manager.merge(encontristaEcc);
	}

	public void excluir(EncontristaEcc encontristaEcc) {
		manager.remove(encontristaEcc);
	}

	public EncontristaEcc carregar(Long id) {
		return manager.find(EncontristaEcc.class, id);
	}

	public List<EncontristaEcc> listar() {
		TypedQuery<EncontristaEcc> query = manager.createQuery("from EncontristaEcc order by ecc.numero desc", EncontristaEcc.class);
		return query.getResultList();
	}

	/**
     * Método que verifica se já existe EncontristaECC cadastrado
	 * @param ecc - ID do ECC
	 * @return
     */
	public boolean jaExisteEcc(Long ecc) {
		TypedQuery<Long> query = manager.createQuery("select count(id) from EncontristaEcc  " +
				" where ecc.id = :ECC", Long.class);
		query.setParameter("ECC", ecc);
		return (query.getSingleResult() > 0 ? true : false);
	}

	/**
	 * Método que verifica se o Casal já está presente como ENCONTRISTA NO MESMO ECC
	 * @param ecc - ID do ECC
	 * @param casal - ID do Casal
	 * @return
	 */
	public boolean casalEncontristaJaExisteNoEcc(Long ecc, Long casal) {
		TypedQuery<Long> query = manager.createQuery("select count(ec.id) from EncontristaEcc ec " +
				" left join ec.encontristasEccCasais ee" +
				" where ec.ecc.id = :ECC" +
				" and ee.ficha.id = :CASAL", Long.class);
		query.setParameter("ECC", ecc);
		query.setParameter("CASAL", casal);
		return (query.getSingleResult() > 0 ? true : false);
	}

}
