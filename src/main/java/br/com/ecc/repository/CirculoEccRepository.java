package br.com.ecc.repository;

import br.com.ecc.model.CirculoEcc;
import br.com.ecc.util.Util;

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

	public List<CirculoEcc> listaUltimoCirculoEcc() {
		TypedQuery<CirculoEcc> query = manager.createQuery("from CirculoEcc ce where ce.ecc.id in (select ee.id from Ecc ee where extract(year from ee.dataFim) = :ANO_ANTERIOR)", CirculoEcc.class);
		query.setParameter("ANO_ANTERIOR", Util.retornaAnoAnterior());
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

	/**
	 * Método que Percorre todos as CIRCULOS por ECC para atualizar na tabela ATIVIDADES
	 * @param ecc - ID do ECC
	 * @return
	 */
	public List<CirculoEcc> circulosPorEcc(Long ecc) {
		TypedQuery<CirculoEcc> query = manager.createQuery("from CirculoEcc where ecc.id =:ECC", CirculoEcc.class);
		query.setParameter("ECC", ecc);
		return query.getResultList();
	}

	public List<CirculoEcc> listaCirculoEccPorFiltroEncerradoAndamento(String situacao) {
		TypedQuery<CirculoEcc> query = manager.createQuery("from CirculoEcc ce where ce.ecc.id in (select ee.id from Ecc ee where ee.ativo=true and ee.situacao=:SITUACAO) order by ce.ecc.numero desc)", CirculoEcc.class);
		query.setParameter("SITUACAO", situacao);
		return query.getResultList();
	}


	/**
	 * Método utilizado para filtrar os CIRCULOS
	 * Por padrão tras todos os CIRCULOS do ÚLTIMO ECC que estão encerrados, com situação ENCERRADO
	 * @param ecc - Define qual ECC (31º, 30º, 29° ou TODOS).
	 * @param statusEcc - Define qual o Status do ECC (ANDAMENTO, ENCERRADO, TODOS), por padrão trás os ENCERRADO.
	 * @return List com os Circulos.
	 */
	public List<CirculoEcc> filtraCirculoPorEccStatus(Long ecc, String statusEcc) {
		TypedQuery<CirculoEcc> query = null;
		if (ecc > 0) {
			query = manager.createQuery("from CirculoEcc where ecc.id =:ID", CirculoEcc.class);
			query.setParameter("ID", ecc);
			return query.getResultList();
		} else {
			if (statusEcc.equals("TODOS")) {
				return listar();
			}
			else {
				return listaCirculoEccPorFiltroEncerradoAndamento(statusEcc);
			}
		}
	}

}
