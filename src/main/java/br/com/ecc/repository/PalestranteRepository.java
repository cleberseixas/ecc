package br.com.ecc.repository;

import br.com.ecc.model.Palestrante;
import br.com.ecc.util.Util;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class PalestranteRepository {

	private EntityManager manager;

	@Inject
	public PalestranteRepository(EntityManager manager) {
		this.manager = manager;
	}
	
	public void salvar(Palestrante palestrante) {
		if (palestrante.getId() == null)
			manager.persist(palestrante);
		else
			manager.merge(palestrante);
	}

	public void excluir(Palestrante palestrante) {
		manager.remove(palestrante);
	}

	public Palestrante carregar(Long id) {
		return manager.find(Palestrante.class, id);
	}

	public List<Palestrante> listar() {
		TypedQuery<Palestrante> query = manager.createQuery("from Palestrante order by ecc.numero desc, dataPalestra", Palestrante.class);
		return query.getResultList();
	}

	public List<Palestrante> listarUltimoPalestranteEcc() {
		TypedQuery<Palestrante> query = manager.createQuery("from Palestrante pa where pa.ecc.id in (select ee.id from Ecc ee where extract(year from ee.dataFim) = :ANO_ANTERIOR)", Palestrante.class);
		query.setParameter("ANO_ANTERIOR", Util.retornaAnoAnterior());
		return query.getResultList();
	}

	/**
	 * Método que Percorre todos os PALESTRANTES por ECC para atualizar na tabela ATIVIDADES
	 * @param ecc - ID do ECC
	 * @return
	 */
	public List<Palestrante> palestrantesPorEcc(Long ecc) {
		TypedQuery<Palestrante> query = manager.createQuery("from Palestrante where ficha.id > 0 and ecc.id =:ECC", Palestrante.class);
		query.setParameter("ECC", ecc);
		return query.getResultList();
	}

	public List<Palestrante> listaPalestranteEccPorFiltroEncerradoAndamento(String situacao) {
		TypedQuery<Palestrante> query = manager.createQuery("from Palestrante pa where pa.ecc.id in (select ee.id from Ecc ee where ee.ativo=true and ee.situacao=:SITUACAO) order by pa.ecc.numero desc)", Palestrante.class);
		query.setParameter("SITUACAO", situacao);
		return query.getResultList();
	}

	/**
	 * Método utilizado para filtrar os PALESTRANTES
	 * Por padrão tras todos os PALESTRANTES do ÚLTIMO ECC que estão encerrados, com situação ENCERRADO
	 * @param ecc - Define qual ECC (31º, 30º, 29° ou TODOS).
	 * @param statusEcc - Define qual o Status do ECC (ANDAMENTO, ENCERRADO, TODOS), por padrão trás os ENCERRADO.
	 * @return List com os Palestrantes.
	 */
	public List<Palestrante> filtraPalestrantePorEccStatus(Long ecc, String statusEcc) {
		TypedQuery<Palestrante> query = null;
		if (ecc > 0) {
			query = manager.createQuery("from Palestrante where ecc.id =:ID", Palestrante.class);
			query.setParameter("ID", ecc);
			return query.getResultList();
		} else {
			if (statusEcc.equals("TODOS")) {
				return listar();
			}
			else {
				return listaPalestranteEccPorFiltroEncerradoAndamento(statusEcc);
			}
		}
	}
}
