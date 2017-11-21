package br.com.ecc.repository;

import br.com.ecc.model.Ecc;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

public class EccRepository {

	private EntityManager manager;

	@Inject
	public EccRepository(EntityManager manager) {
		this.manager = manager;
	}
	
	public void salvar(Ecc ecc) {
		manager.merge(ecc);
	}

	public void excluir(Ecc ecc) {
		manager.remove(ecc);
	}

	public Ecc carregar(Long id) {
		return manager.find(Ecc.class, id);
	}

	public List<Ecc> listar() {
		TypedQuery<Ecc> query = manager.createQuery("from Ecc order by numero desc", Ecc.class);
		return query.getResultList();
	}

	public List<Ecc> listarEccAtivoEncerradoNao() {
		TypedQuery<Ecc> query = manager.createQuery("from Ecc where ativo=true and situacao='ANDAMENTO' order by numero desc", Ecc.class);
		return query.getResultList();
	}

	public List<Ecc> listarEccAtividade() {
		TypedQuery<Ecc> query = manager.createQuery("from Ecc where ativo=true and automatico=false and situacao='ANDAMENTO' order by numero", Ecc.class);
		return query.getResultList();
	}

	/**
	 * Método que atualiza O ECC para ENCERRADO, DATA_ENCERRAMENTO = DATE, USUARIO_ENCERROU
	 * @param ecc - ID do ECC
	 * @param usuario - usuário que está encerrando o ECC
	 */	public void atualizaSituacaoEcc(Long ecc, String usuario) {
		manager.getTransaction().begin();

		Query query = manager.createNativeQuery("UPDATE ECCS SET SITUACAO = 'ENCERRADO', DATA_ENCERRAMENTO =:DATA, USUARIO_ENCERROU =:USUARIO WHERE ECC =:ECC");
		query.setParameter("ECC", ecc);
		query.setParameter("DATA", new Date());
		query.setParameter("USUARIO", usuario);
		query.executeUpdate();
		manager.getTransaction().commit();
	}

}
