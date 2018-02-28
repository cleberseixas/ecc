package br.com.ecc.repository;

import br.com.ecc.model.CirculoEstudoEcc;
import br.com.ecc.util.Util;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class CirculoEstudoEccRepository {

	private EntityManager manager;

	@Inject
	public CirculoEstudoEccRepository(EntityManager manager) {
		this.manager = manager;
	}
	
	public void salvar(CirculoEstudoEcc circuloEstudoEcc) {
		manager.merge(circuloEstudoEcc);
	}

	public void excluir(CirculoEstudoEcc circuloEstudoEcc) {
		manager.remove(circuloEstudoEcc);
	}

	public CirculoEstudoEcc carregar(Long id) {
		return manager.find(CirculoEstudoEcc.class, id);
	}

	public List<CirculoEstudoEcc> listar() {
		TypedQuery<CirculoEstudoEcc> query = manager.createQuery("from CirculoEstudoEcc order by ecc.numero desc", CirculoEstudoEcc.class);
		return query.getResultList();
	}

	public List<CirculoEstudoEcc> listaUltimoCirculoEcc() {
		TypedQuery<CirculoEstudoEcc> query = manager.createQuery("from CirculoEstudoEcc ce where ce.ecc.id in (select ee.id from Ecc ee where extract(year from ee.dataFim) = :ANO_ANTERIOR)", CirculoEstudoEcc.class);
		query.setParameter("ANO_ANTERIOR", Util.retornaAnoAnterior());
		return query.getResultList();
	}


	/**
     * Método que verifica se já existe o Círculo de Estudo CADASTRADO NO MESMO ECC
	 * @param ecc - ID do ECC
	 * @param circulo - ID da CIRCULO
	 * @return
     */
	public boolean circuloEstudoJaExisteEccTema(Long ecc, Long circulo, String tema) {
		TypedQuery<Long> query = manager.createQuery("select count(id) from CirculoEstudoEcc  " +
				" where ecc.id = :ECC" +
				" and circulo.id = :CIRCULO and tema = :TEMA", Long.class);
		query.setParameter("ECC", ecc);
		query.setParameter("CIRCULO", circulo);
		query.setParameter("TEMA", tema);
		return (query.getSingleResult() > 0 ? true : false);
	}

	public List<CirculoEstudoEcc> listaCirculoEccPorFiltroEncerradoAndamento(String situacao) {
		TypedQuery<CirculoEstudoEcc> query = manager.createQuery("from CirculoEstudoEcc ce where ce.ecc.id in (select ee.id from Ecc ee where ee.ativo=true and ee.situacao=:SITUACAO) order by ce.ecc.numero desc)", CirculoEstudoEcc.class);
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
	public List<CirculoEstudoEcc> filtraCirculoPorEccStatus(Long ecc, String statusEcc) {
		TypedQuery<CirculoEstudoEcc> query = null;
		if (ecc > 0) {
			query = manager.createQuery("from CirculoEstudoEcc where ecc.id =:ID", CirculoEstudoEcc.class);
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
