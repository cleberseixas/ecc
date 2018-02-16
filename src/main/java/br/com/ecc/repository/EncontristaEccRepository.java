package br.com.ecc.repository;

import br.com.ecc.model.EncontristaEcc;
import br.com.ecc.util.Util;

import javax.inject.Inject;
import javax.persistence.EntityManager;
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

	public List<EncontristaEcc> listarUltimoEncontristaEcc() {
		TypedQuery<EncontristaEcc> query = manager.createQuery("from EncontristaEcc ec where ec.ecc.id in (select ee.id from Ecc ee where extract(year from ee.dataFim) = :ANO_ANTERIOR)", EncontristaEcc.class);
		query.setParameter("ANO_ANTERIOR", Util.retornaAnoAnterior());
		//TypedQuery<EquipeEcc> query = manager.createQuery("from EquipeEcc ee where ee.ecc.id in (select e.id from Ecc e where date_trunc('Year', e.dataFim) = '2017-01-01')", EquipeEcc.class);
		return query.getResultList();
	}

	public List<EncontristaEcc> listaEncontristaEccPorFiltroEncerradoAndamento(String situacao) {
		TypedQuery<EncontristaEcc> query = manager.createQuery("from EncontristaEcc ec where ec.ecc.id in (select ee.id from Ecc ee where ee.ativo=true and ee.situacao=:SITUACAO) order by ec.ecc.numero desc)", EncontristaEcc.class);
		query.setParameter("SITUACAO", situacao);
		return query.getResultList();
	}
	/**
	 * Método utilizado para filtrar os ENCONTRISTAS
	 * Por padrão tras todos os ENCONTRISTAS do ÚLTIMO ECC que estão encerrados, com situação ENCERRADO
	 * @param ecc - Define qual ECC (31º, 30º, 29° ou TODOS).
	 * @param statusEcc - Define qual o Status do ECC (ANDAMENTO, ENCERRADO, TODOS), por padrão trás os ENCERRADO.
	 * @return List com os Encontristas.
	 */
	public List<EncontristaEcc> filtraEncontristaPorEccStatus(Long ecc, String statusEcc) {
		TypedQuery<EncontristaEcc> query = null;
		if (ecc > 0) {
			query = manager.createQuery("from EncontristaEcc where ecc.id =:ID", EncontristaEcc.class);
			query.setParameter("ID", ecc);
			return query.getResultList();
		} else {
			if (statusEcc.equals("TODOS")) {
				return listar();
			}
			else {
				return listaEncontristaEccPorFiltroEncerradoAndamento(statusEcc);
			}
		}
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
