package br.com.ecc.repository;

import br.com.ecc.model.Ficha;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

public class FichaRepository {

	private EntityManager manager;

	@Inject
	public FichaRepository(EntityManager manager) {
		this.manager = manager;
	}
	
	public void salvar(Ficha ficha) {
		if (ficha.getId() == null)
			manager.persist(ficha);
		else manager.merge(ficha);
	}

	public void excluir(Ficha ficha) {
		//manager.remove(manager.getReference(Objeto.class, objeto.getId()));
		manager.remove(ficha);
	}

	public Ficha carregar(Long id) {
		return manager.find(Ficha.class, id);
	}

	public List<Ficha> listar() {
		TypedQuery<Ficha> query = manager.createQuery("from Ficha order by primeiraEtapa desc", Ficha.class);
		return query.getResultList();
	}

	public List<Ficha> listarEncontreiroEncontrista(String parametro) {
		TypedQuery<Ficha> query = manager.createQuery("from Ficha where situacao= :SITUACAO and ativo=true order by nomeUsual", Ficha.class);
		query.setParameter("SITUACAO", parametro);
		return query.getResultList();
	}

	public List<Ficha> listarEncontristaEcc(Long ecc) {
		TypedQuery<Ficha> query = manager.createQuery("from Ficha fi "+
//				" inner join ec.ficha fi "+
				" where fi.ativo=true "+
				" and fi.situacao = 'ENCONTRISTA'"+
				" and fi.id in ("+
				" select ec.ficha.id from EncontristaEccCasal ec "+
				" where ec.ecc.id =:ECC) order by fi.primeiraEtapa desc, fi.nomeUsual", Ficha.class);
		query.setParameter("ECC", ecc);
		return query.getResultList();
	}

	/**
	 * Método utilizado para filtrar as fichas
	 * Por padrão tras todas as fichas (ativas/inativas) com situação TODAS
	 * @param situacao - Define a situação da FICHA (ENCONTREIRO, ENCONTRISTA, PENDENTE OU AUSÊNCIA JUSTIFICADA).
	 * @param nomeUsualCasal - Nome usual do casal.
	 * @return List com as Fichas.
	 */
	public List<Ficha> filtraFichaSituacaoeNomeUsual(String situacao, String nomeUsualCasal) {
		TypedQuery<Ficha> query = null;
		if (situacao.equals("TODAS")) {
			if (nomeUsualCasal.trim().length() > 0) {
				query = manager.createQuery("from Ficha "
						+" where nomeUsual like :NU order by primeiraEtapa desc", Ficha.class);
				query.setParameter("NU", "%"+nomeUsualCasal+"%");
			} else {
				query = manager.createQuery("from Ficha order by primeiraEtapa desc", Ficha.class);
			}
		} else {
			if (nomeUsualCasal.trim().length() > 0) {
				query = manager.createQuery("from Ficha "
						+ " where situacao = :SI "
						+ " and nomeUsual like :NU order by nomeUsual", Ficha.class);
				query.setParameter("SI", situacao);
				query.setParameter("NU", "%" + nomeUsualCasal + "%");
			} else {
				query = manager.createQuery("from Ficha "
						+" where situacao = :SI order by primeiraEtapa desc", Ficha.class);
				query.setParameter("SI", situacao);
			}
		}
		return query.getResultList();
	}

	/**
	 * Método que busca os casais que podem ser COORDENADOR de EQUIPE.
	 * Utilizado no caso de uso MONTAGEM DAS EQUIPES (LITURGIA E VIGILIA, VISITAÇÃO, ACOLHIDA, ETC
	 * CASAL_COORDENADOR : Casal que já foi membro de equipe.
	 * @return List com as Fichas.
	 */

	public List<Ficha> listarCasaisCoordenadores(String equipe) {
		TypedQuery<Ficha> query = manager.createQuery("from Ficha where situacao='ENCONTREIRO' and ativo=true order by primeiraEtapa desc, nomeUsual", Ficha.class);
		//query.setParameter("SITUACAO", equipe);
		return query.getResultList();
	}

}
