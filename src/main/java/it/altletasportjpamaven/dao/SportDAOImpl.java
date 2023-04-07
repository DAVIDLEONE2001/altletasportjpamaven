package it.altletasportjpamaven.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.altletasportjpamaven.model.Sport;

public class SportDAOImpl implements SportDAO {

	EntityManager entityManager;

	@Override
	public List<Sport> list() throws Exception {

		return entityManager.createQuery("from Sport", Sport.class).getResultList();

	}

	@Override
	public Sport get(Long id) throws Exception {

		return entityManager.find(Sport.class, id);

	}

	@Override
	public void update(Sport o) throws Exception {

		if (o == null) {
			throw new Exception("Problema valore in input");
		}

		entityManager.merge(o);

	}

	@Override
	public void insert(Sport o) throws Exception {

		if (o == null) {
			throw new Exception("Problema valore in input");
		}
		
		entityManager.persist(o);
		
	}

	@Override
	public void delete(Sport o) throws Exception {

		if (o == null) {
			throw new Exception("Problema valore in input");
		}
		
		entityManager.remove(entityManager.merge(o));
		
		
	}

	@Override
	public void setEntityManager(EntityManager entityManager) {

		this.entityManager = entityManager;

	}

	@Override
	public Sport cercaPerDescrizione(String descrizione) throws Exception {
		
		TypedQuery<Sport> query = entityManager
				.createQuery("select s from Sport s where s.definizione=?1", Sport.class)
				.setParameter(1, descrizione);
		

		return query.getResultStream().findFirst().orElse(null);
		
	}

}
