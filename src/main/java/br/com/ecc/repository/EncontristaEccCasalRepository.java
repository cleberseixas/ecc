package br.com.ecc.repository;

import br.com.ecc.model.EncontristaEccCasal;
import br.com.ecc.model.EquipeEccCasal;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class EncontristaEccCasalRepository {

	private EntityManager manager;

	@Inject
	public EncontristaEccCasalRepository(EntityManager manager) {
		this.manager = manager;
	}
	
	public void salvar(EncontristaEccCasal encontristaEccCasal) {
		manager.merge(encontristaEccCasal);
	}

	public void excluir(EncontristaEccCasal encontristaEccCasal) {
		manager.remove(encontristaEccCasal);
	}

	public EncontristaEccCasal carregar(Long id) {
		return manager.find(EncontristaEccCasal.class, id);
	}

	public List<EncontristaEccCasal> listar() {
		TypedQuery<EncontristaEccCasal> query = manager.createQuery("from EncontristaEccCasal", EncontristaEccCasal.class);
		return query.getResultList();
	}


	/**
	 * Método responsável por excluir os registros da tabela ENCONTRISTAS_ECCS_CASAIS, que estão no LIMBO,
	 * que são inseridos pelo mapeamento ManyToMany
	 * chamado ao abrir a tela Montagem dos Encontristas e após salvar ou remover algum casal no ECC;
	 */
	public void removeCasaisEncontristasLimbo() {
		manager.getTransaction().begin();
		Query query = manager.createNativeQuery("DELETE FROM ENCONTRISTAS_ECCS_CASAIS WHERE ENCONTRISTA_ECC_CASAL NOT IN (SELECT ENCONTRISTA_ECC_CASAL FROM ENCONTRISTAS_ECCS_CASAIS_CASAIS)");


		query.executeUpdate();
		manager.getTransaction().commit();
	}
}
