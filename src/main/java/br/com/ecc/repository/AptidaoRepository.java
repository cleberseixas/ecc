package br.com.ecc.repository;

import br.com.ecc.model.Aptidao;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class AptidaoRepository {

	private EntityManager manager;

	@Inject
	public AptidaoRepository(EntityManager manager) {
		this.manager = manager;
	}
	
	public void salvar(Aptidao aptidao) {
		manager.merge(aptidao);
	}

	public void excluir(Aptidao aptidao) {
		manager.remove(aptidao);
	}

	public Aptidao carregar(Long id) {
		return manager.find(Aptidao.class, id);
	}

	public List<Aptidao> listar() {
		TypedQuery<Aptidao> query = manager.createQuery("from Aptidao", Aptidao.class);
		return query.getResultList();
	}

	/**
	 * Método responsável por excluir os registros da tabela APTIDÕES, que estão no LIMBO,
	 * que são inseridos pelo mapeamento ManyToMany
	 * chamado ao abrir a tela Fichas e após salvar ou remover alguma aptidão;
	 */
	public void removeAptidaoLimbo() {
		manager.getTransaction().begin();
		Query query = manager.createNativeQuery("DELETE FROM APTIDOES WHERE APTIDAO NOT IN (SELECT APTIDAO FROM FICHAS_APTIDOES)");
		query.executeUpdate();
		manager.getTransaction().commit();
	}

	/**
	 * Método que lista as aptidões agrupadas
	 */

	@SuppressWarnings("rawtypes")
	public List<br.com.ecc.model.util.Aptidao> totalPorAptidao() {
		String sql = "";
		Query query = null;
		sql = "select eq.equipe, ec.numero, eq.descricao, count(ap.*) from aptidoes ap "
			+ " inner join equipes eq on eq.equipe = ap.equipe "
			+ " inner join fichas fa on fa.ficha = ap.ficha "
			+ " inner join eccs ec on ec.numero = fa.primeira_etapa "
			//+ " where ec.ecc = 2 "
			+ " group by 1,2,3 order by 3";
//		sql = "select eq.equipe, eq.descricao, count(ap.*) from aptidoes ap "
//				+ " inner join equipes eq on eq.equipe = ap.equipe group by 1,2 order by 2";
		query = manager.createNativeQuery(sql);
		List lst = query.getResultList();
		Iterator iter = lst.iterator();
		List<br.com.ecc.model.util.Aptidao> lista = new ArrayList<br.com.ecc.model.util.Aptidao>();
		br.com.ecc.model.util.Aptidao aptidao = null;
		while (iter.hasNext()) {
			aptidao = new br.com.ecc.model.util.Aptidao();
			Object[] obj = (Object[])iter.next();
			aptidao.setIdAptidao(Integer.valueOf(obj[0].toString()));
			aptidao.setEcc(obj[1].toString());
			aptidao.setAptidao(obj[2].toString());
			aptidao.setQuantidade(Integer.valueOf(obj[3].toString()));
			lista.add(aptidao);
		}
		return lista;
	}

}
