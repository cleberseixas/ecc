package br.com.ecc.repository;

import br.com.ecc.model.CirculoEccCasal;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class CirculoEccCasalRepository {

	private EntityManager manager;

	@Inject
	public CirculoEccCasalRepository(EntityManager manager) {
		this.manager = manager;
	}
	
	public void salvar(CirculoEccCasal circuloEccCasal) {
		manager.merge(circuloEccCasal);
	}

	public void excluir(CirculoEccCasal circuloEccCasal) {
		manager.remove(circuloEccCasal);
	}

	public CirculoEccCasal carregar(Long id) {
		return manager.find(CirculoEccCasal.class, id);
	}

	public List<CirculoEccCasal> listar() {
		TypedQuery<CirculoEccCasal> query = manager.createQuery("from CirculoEccCasal", CirculoEccCasal.class);
		return query.getResultList();
	}


	/**
	 * Método responsável por excluir os registros da tabela CIRCULOS_ECCS_ENCONTRISTAS, que estão no LIMBO,
	 * que são inseridos pelo mapeamento ManyToMany
	 * chamado ao abrir a tela Montagem dos Círculos e após salvar ou remover algum casal (Encontrista) no círculo;
	 */
	public void removeCasaisEncontristasLimbo() {
		manager.getTransaction().begin();
		Query query = manager.createNativeQuery("DELETE FROM CIRCULOS_ECCS_ENCONTRISTAS WHERE CIRCULO_ECC_ENCONTRISTA NOT IN (SELECT CIRCULO_ECC_ENCONTRISTA FROM CIRCULOS_ECCS_ENCONTRISTAS_ENCONTRISTAS)");

		query.executeUpdate();
		manager.getTransaction().commit();
	}

	/**
	 * Método que verifica se o Casal já está incluso em algum CIRCULO EM UM DETERMINADO ECC
	 * Caso exista retorna o id para a remoção
	 * @param ecc - ID do ECC
	 * @param casal - ID do Casal
	 * @return
	 */
	public Long casalEncontristaExistenteCircunoNoEcc(Long ecc, Long casal) {
		TypedQuery<Long> query = manager.createQuery("select max(cec.id) from CirculoEccCasal cec" +
				" where cec.ecc.id = :ECC" +
				" and cec.ficha.id = :CASAL", Long.class);
		query.setParameter("ECC", ecc);
		query.setParameter("CASAL", casal);
		Long resultado = query.getSingleResult();
		if (resultado == null)
			resultado = 0L;
		else resultado = query.getSingleResult();
		return resultado;
	}

	/**
	 * Método responsável por excluir o Casal quando se remove o mesmo da lista de ENCONTRISTAS,
	 * chamado ao REMOVER um casal da RELAÇÃO DE ENCONTRISTAS, página montagemEncontrista.xhtml;
	 * @param casal - ID do Casal
	 */
	public void removeCasalCirculoQuandoRemoveEncontristaLista(Long casal) {
		manager.getTransaction().begin();
		Query query = manager.createNativeQuery("DELETE FROM CIRCULOS_ECCS_ENCONTRISTAS_ENCONTRISTAS WHERE CIRCULO_ECC_ENCONTRISTA =:CASAL");
		query.setParameter("CASAL", casal);
		query.executeUpdate();
		manager.getTransaction().commit();
	}
}
