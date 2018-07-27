package br.com.ecc.repository;

import br.com.ecc.model.DirigenteNacionalEcc;
import br.com.ecc.util.Util;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class DirigenteNacionalEccRepository {

	private EntityManager manager;

	@Inject
	public DirigenteNacionalEccRepository(EntityManager manager) {
		this.manager = manager;
	}
	
	public void salvar(DirigenteNacionalEcc dirigenteNacionalEcc) {
		manager.merge(dirigenteNacionalEcc);
	}

	public void excluir(DirigenteNacionalEcc dirigenteNacionalEcc) {
		manager.remove(dirigenteNacionalEcc);
	}

	public DirigenteNacionalEcc carregar(Long id) {
		return manager.find(DirigenteNacionalEcc.class, id);
	}

	public List<DirigenteNacionalEcc> listar() {
		TypedQuery<DirigenteNacionalEcc> query = manager.createQuery("from DirigenteNacionalEcc order by ecc.numero desc, ce.ordemExibicao", DirigenteNacionalEcc.class);
		return query.getResultList();
	}

	public List<DirigenteNacionalEcc> listaUltimoDirigenteNacionalEcc() {
		TypedQuery<DirigenteNacionalEcc> query = manager.createQuery("from DirigenteNacionalEcc ce where ce.ecc.id in (select ee.id from Ecc ee where extract(year from ee.dataFim) = :ANO_ANTERIOR order by ce.ordemExibicao )", DirigenteNacionalEcc.class);
		query.setParameter("ANO_ANTERIOR", Util.retornaAnoAnterior());
		return query.getResultList();
	}


//	/**
//     * Método que verifica se já existe o Círculo de Estudo CADASTRADO NO MESMO ECC
//	 * @param ecc - ID do ECC
//	 * @param circulo - ID da CIRCULO
//	 * @return
//     */
//	public boolean circuloEstudoJaExisteEccTema(Long ecc, Long circulo, String tema) {
//		TypedQuery<Long> query = manager.createQuery("select count(id) from CirculoEstudoEcc  " +
//				" where ecc.id = :ECC" +
//				" and circulo.id = :CIRCULO and tema = :TEMA", Long.class);
//		query.setParameter("ECC", ecc);
//		query.setParameter("CIRCULO", circulo);
//		query.setParameter("TEMA", tema);
//		return (query.getSingleResult() > 0 ? true : false);
//	}

	public List<DirigenteNacionalEcc> listaDirigenteNacionalEccPorFiltroEncerradoAndamento(String situacao) {
		TypedQuery<DirigenteNacionalEcc> query = manager.createQuery("from DirigenteNacionalEcc ce where ce.ecc.id in (select ee.id from Ecc ee where ee.ativo=true and ee.situacao=:SITUACAO) order by ce.ecc.numero desc)", DirigenteNacionalEcc.class);
		query.setParameter("SITUACAO", situacao);
		return query.getResultList();
	}


	/**
	 * Método utilizado para filtrar os DIRIGENTE NACIONAIS
	 * Por padrão tras todos os DIRIGENTES NACIONAIS do ÚLTIMO ECC que estão encerrados, com situação ENCERRADO
	 * @param ecc - Define qual ECC (31º, 30º, 29° ou TODOS).
	 * @param statusEcc - Define qual o Status do ECC (ANDAMENTO, ENCERRADO, TODOS), por padrão trás os ENCERRADO.
	 * @return List com os Dirigentes Nacionais.
	 */
	public List<DirigenteNacionalEcc> filtraDirigenteNacionalPorEccStatus(Long ecc, String statusEcc) {
		TypedQuery<DirigenteNacionalEcc> query = null;
		if (ecc > 0) {
			query = manager.createQuery("from DirigenteNacionalEcc where ecc.id =:ID", DirigenteNacionalEcc.class);
			query.setParameter("ID", ecc);
			return query.getResultList();
		} else {
			if (statusEcc.equals("TODOS")) {
				return listar();
			}
			else {
				return listaDirigenteNacionalEccPorFiltroEncerradoAndamento(statusEcc);
			}
		}
	}

}
