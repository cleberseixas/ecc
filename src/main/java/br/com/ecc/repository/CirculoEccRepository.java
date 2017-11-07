package br.com.ecc.repository;

import br.com.ecc.model.CirculoEcc;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class CirculoEccRepository {

	private EntityManager manager;

	@Inject
	public CirculoEccRepository(EntityManager manager) {
		this.manager = manager;
	}
	
	public void salvar(CirculoEcc circuloEcc) {
		manager.merge(circuloEcc);
	}

	public void excluir(CirculoEcc circuloEcc) {
		manager.remove(circuloEcc);
	}

	public CirculoEcc carregar(Long id) {
		return manager.find(CirculoEcc.class, id);
	}

	public List<CirculoEcc> listar() {
		TypedQuery<CirculoEcc> query = manager.createQuery("from CirculoEcc order by ecc.numero desc", CirculoEcc.class);
		return query.getResultList();
	}

	/**
     * Método que verifica se já existe o Círculo CADASTRADO NO MESMO ECC
	 * @param ecc - ID do ECC
	 * @param circulo - ID da CIRCULO
	 * @return
     */
	public boolean circuloJaExisteEcc(Long ecc, Long circulo) {
		TypedQuery<Long> query = manager.createQuery("select count(id) from CirculoEcc  " +
				" where ecc.id = :ECC" +
				" and circulo.id = :CIRCULO", Long.class);
		query.setParameter("ECC", ecc);
		query.setParameter("CIRCULO", circulo);
		return (query.getSingleResult() > 0 ? true : false);
	}

	/**
	 * Método que verifica se o Casal já é COORDENADOR de OUTRO CIRCULO NO MESMO ECC
	 * @param ecc - ID do ECC
	 * @param casal - ID do Casal
	 * @return
	 */
	public boolean casalJaExisteEccCoordenadorCirculo(Long ecc, Long casal) {
		TypedQuery<Long> query = manager.createQuery("select count(id) from CirculoEcc " +
				" where ecc.id = :ECC" +
				" and casalCoordenador.id = :COOR", Long.class);
		query.setParameter("ECC", ecc);
		query.setParameter("COOR", casal);
		return (query.getSingleResult() > 0 ? true : false);
	}

	/**
	 * Método que verifica se o Casal já está presente em CIRCULO NO MESMO ECC
	 * @param ecc - ID do ECC
	 * @param casal - ID do Casal
	 * @return
	 */
	public boolean casalJaExisteEccOutroCirculo(Long ecc, Long casal) {
		TypedQuery<Long> query = manager.createQuery("select count(ce.id) from CirculoEcc ce " +
				" left join ce.circulosEccCasais cc" +
				" where ce.ecc.id = :ECC" +
				" and cc.ficha.id = :CASAL", Long.class);
		query.setParameter("ECC", ecc);
		query.setParameter("CASAL", casal);
		return (query.getSingleResult() > 0 ? true : false);
	}

}
