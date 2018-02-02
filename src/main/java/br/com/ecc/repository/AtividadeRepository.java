package br.com.ecc.repository;

import br.com.ecc.model.Atividade;
import br.com.ecc.model.DirigenteEcc;
import br.com.ecc.model.util.Aptidao;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AtividadeRepository {

	private EntityManager manager;

	@Inject
	public AtividadeRepository(EntityManager manager) {
		this.manager = manager;
	}
	
	public void salvar(Atividade atividade) {
		manager.merge(atividade);
	}

	public void excluir(Atividade atividade) {
		manager.remove(atividade);
	}

	public Atividade carregar(Long id) {
		return manager.find(Atividade.class, id);
	}

	public List<Atividade> listar() {
		TypedQuery<Atividade> query = manager.createQuery("from Atividade", Atividade.class);
		return query.getResultList();
	}

	/**
	 * Método responsável por excluir os registros da tabela ATIVIDADES, que estão no LIMBO,
	 * que são inseridos pelo mapeamento ManyToMany
	 * chamado ao abrir a tela Fichas e após salvar ou remover alguma atividade;
	 */
	public void removeAtividadeLimbo() {
		manager.getTransaction().begin();
		Query query = manager.createNativeQuery("DELETE FROM ATIVIDADES WHERE ATIVIDADE NOT IN (SELECT ATIVIDADE FROM FICHAS_ATIVIDADES)");
		query.executeUpdate();
		manager.getTransaction().commit();
	}

	/**
	 * Método que lista as aptidões agrupadas
	 */

	@SuppressWarnings("rawtypes")
	public List<br.com.ecc.model.util.Atividade> totalPorAtividade() {
		String sql = "";
		Query query = null;
		sql = "select eq.descricao, count(at.*) from atividades at "
				+ " inner join equipes eq on eq.equipe = at.equipe group by 1 order by 1";
		query = manager.createNativeQuery(sql);
		List lst = query.getResultList();
		Iterator iter = lst.iterator();
		List<br.com.ecc.model.util.Atividade> lista = new ArrayList<br.com.ecc.model.util.Atividade>();
		br.com.ecc.model.util.Atividade atividade = null;
		while (iter.hasNext()) {
			atividade = new br.com.ecc.model.util.Atividade();
			Object[] obj = (Object[])iter.next();
			atividade.setAtividade(obj[0].toString());
			atividade.setQuantidade(Integer.valueOf(obj[1].toString()));
			lista.add(atividade);
		}
		return lista;
	}

	/**
	 * Método que Insere na tabela ATIVIDADES todas as atividades na hora de ENCERRAR O ECC
	 * @param ecc - ID do ECC
	 * @param ficha - ID da FICHA
	 * @param equipe - ID do EQUIPE
	 * @param coordenador - SE O CASAL FOI OU NÃO COORDENADOR na EQUIPE
	 * @param atualizaUltimoTrabalho - true - atualiza ULTIMO_TRABALHO
	 */
	public void insereAtividade(Long ecc, Long ficha, Long equipe, boolean coordenador, boolean atualizaUltimoTrabalho) {
		manager.getTransaction().begin();
		//Insere a Atividade
		Query query = manager.createNativeQuery("INSERT INTO ATIVIDADES "
				+" (ECC, FICHA, EQUIPE, COORDENADOR) "
				+" VALUES "
				+" (:ECC, :FICHA, :EQUIPE, :COOR)", DirigenteEcc.class);
		query.setParameter("ECC", ecc);
		query.setParameter("FICHA", ficha);
		query.setParameter("EQUIPE", equipe);
		query.setParameter("COOR", coordenador);
		query.executeUpdate();
		manager.getTransaction().commit();

		manager.getTransaction().begin();
		TypedQuery<Long> query1 = manager.createQuery("SELECT MAX(id) FROM Atividade "
				+" WHERE ecc.id =:ECC AND ficha.id =:FICHA AND equipe.id =:EQUIPE", Long.class);
		query1.setParameter("ECC", ecc);
		query1.setParameter("FICHA", ficha);
		query1.setParameter("EQUIPE", equipe);

		Long id = query1.getSingleResult();
		Query query2 = manager.createNativeQuery("INSERT INTO FICHAS_ATIVIDADES"
				+" (FICHA, ATIVIDADE) "
				+" VALUES "
				+" (:FICHA, :ATIV)");
		query2.setParameter("FICHA", ficha);
		query2.setParameter("ATIV", id);
		query2.executeUpdate();

		if (!atualizaUltimoTrabalho) {
			TypedQuery<Long> query4 = manager.createQuery("SELECT COUNT(id) FROM Atividade "
					+" WHERE ecc.id =:ECC AND ficha.id =:FICHA ", Long.class);
			query4.setParameter("ECC", ecc);
			query4.setParameter("FICHA", ficha);

			Long idEquipe = query4.getSingleResult();
			//Se inseriu PALESTRANTE, atualiza ULTIMO_TRABALHO
			if (idEquipe == 1) {
				Query query5 = manager.createNativeQuery("UPDATE FICHAS SET EQUIPE =:ULT WHERE FICHA =:FICHA");
				query5.setParameter("ULT", equipe);
				query5.setParameter("FICHA", ficha);
				query5.executeUpdate();
			}
		} else {
				Query query3 = manager.createNativeQuery("UPDATE FICHAS SET EQUIPE =:ULT WHERE FICHA =:FICHA");
				query3.setParameter("ULT", equipe);
				query3.setParameter("FICHA", ficha);
				query3.executeUpdate();
			}
		manager.getTransaction().commit();
	}
}
