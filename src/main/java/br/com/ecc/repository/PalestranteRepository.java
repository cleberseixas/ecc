package br.com.ecc.repository;

import br.com.ecc.model.Palestrante;

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
}
