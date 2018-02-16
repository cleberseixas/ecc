package br.com.ecc.repository;

import br.com.ecc.model.EquipeEcc;
import br.com.ecc.util.Util;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

public class EquipeEccRepository {

	private EntityManager manager;

	@Inject
	public EquipeEccRepository(EntityManager manager) {
		this.manager = manager;
	}
	
	public void salvar(EquipeEcc equipeEcc) {
		manager.merge(equipeEcc);
	}

	public void excluir(EquipeEcc equipeEcc) {
		manager.remove(equipeEcc);
	}

	public EquipeEcc carregar(Long id) {
		return manager.find(EquipeEcc.class, id);
	}

	public List<EquipeEcc> listar() {
		TypedQuery<EquipeEcc> query = manager.createQuery("from EquipeEcc order by ecc.numero desc, equipe.descricao ", EquipeEcc.class);
		return query.getResultList();
	}

	public List<EquipeEcc> listarUltimaEquipeEcc() {
		TypedQuery<EquipeEcc> query = manager.createQuery("from EquipeEcc ec where ec.ecc.id in (select ee.id from Ecc ee where extract(year from ee.dataFim) = :ANO_ANTERIOR)", EquipeEcc.class);
		query.setParameter("ANO_ANTERIOR", Util.retornaAnoAnterior());
		//TypedQuery<EquipeEcc> query = manager.createQuery("from EquipeEcc ee where ee.ecc.id in (select e.id from Ecc e where date_trunc('Year', e.dataFim) = '2017-01-01')", EquipeEcc.class);
		return query.getResultList();
	}

	/**
	 * Método que verifica se já existe a Equipe CADASTRADA NO MESMO ECC
	 * @param ecc - ID do ECC
	 * @param equipe - ID da EQUIPE
	 * @return
	 */
	public boolean equipeJaExisteEcc(Long ecc, Long equipe) {
		TypedQuery<Long> query = manager.createQuery("select count(id) from EquipeEcc  " +
				" where ecc.id = :ECC" +
				" and equipe.id = :EQUIPE", Long.class);
		query.setParameter("ECC", ecc);
		query.setParameter("EQUIPE", equipe);
		return (query.getSingleResult() > 0 ? true : false);
	}

	/**
	 * Método que verifica se o Casal já está presente em OUTRA EQUIPE NO MESMO ECC
	 * Tanto como coordenador ou como membro da Equipe
	 * @param ecc - ID do ECC
	 * @param casal - ID do Casal
	 * @return
	 */
	public boolean casalJaExisteEccCoordenadorOuEquipe(Long ecc, Long casal) {
		TypedQuery<Long> query = manager.createQuery("select count(ec.id) from EquipeEcc ec " +
				" left join ec.equipesEccCasais ee" +
				" where ec.ecc.id = :ECC" +
				" and (ee.ficha.id = :CASAL" +
				" or ec.casalCoordenador.id = :COOR)", Long.class);
		query.setParameter("ECC", ecc);
		query.setParameter("CASAL", casal);
		query.setParameter("COOR", casal);
		return (query.getSingleResult() > 0 ? true : false);
	}

	/**
	 * Método que Percorre todos as EQUIPES por ECC para atualizar na tabela ATIVIDADES
	 * @param ecc - ID do ECC
	 * @return
	 */
	public List<EquipeEcc> equipesPorEcc(Long ecc) {
		TypedQuery<EquipeEcc> query = manager.createQuery("from EquipeEcc where ecc.id =:ECC", EquipeEcc.class);
		query.setParameter("ECC", ecc);
		return query.getResultList();
	}

	public List<EquipeEcc> listaEccPorFiltroEncerradoAndamento(String situacao) {
		TypedQuery<EquipeEcc> query = manager.createQuery("from EquipeEcc ec where ec.ecc.id in (select ee.id from Ecc ee where ee.ativo=true and ee.situacao=:SITUACAO) order by ec.ecc.numero desc)", EquipeEcc.class);
		//TypedQuery<EquipeEcc> query = manager.createQuery("from EquipeEcc ec where ec.ecc.id in (select ee.id from Ecc ee where extract(year from e.dataFim) = :ANO_ANTERIOR)", EquipeEcc.class);
		query.setParameter("SITUACAO", situacao);
		return query.getResultList();
	}

	/**
	 * Método utilizado para filtrar as Equipes por ECCs
	 * Por padrão tras todas as EQUIPES do Ultimo ECC ENCERRADO, último ANO
	 * @param ecc - Define qual ECC (31º, 30º, 29° ou TODOS).
	 * @param statusEcc - Define qual o Status do ECC (ANDAMENTO, ENCERRADO, TODOS), por padrão trás os em ANDAMENTO.
	 * @return List com as EQUIPES.
	 */
	public List<EquipeEcc> filtraEquipePorEccStatus(Long ecc, String statusEcc) {
		TypedQuery<EquipeEcc> query = null;
		if (ecc > 0) {
			query = manager.createQuery("from EquipeEcc where ecc.id =:ID", EquipeEcc.class);
			query.setParameter("ID", ecc);
			return query.getResultList();
		} else {
			if (statusEcc.equals("TODOS")) {
				return listar();
			}
			else {
				return listaEccPorFiltroEncerradoAndamento(statusEcc);
			}
		}
	}

}


