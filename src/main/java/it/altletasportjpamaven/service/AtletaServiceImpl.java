package it.altletasportjpamaven.service;

import java.util.List;

import javax.persistence.EntityManager;

import it.altletasportjpamaven.dao.AtletaDAO;
import it.altletasportjpamaven.dao.EntityManagerUtil;
import it.altletasportjpamaven.model.Atleta;

public class AtletaServiceImpl implements AtletaService {

	private AtletaDAO atletaDAO;

	@Override
	public List<Atleta> listAll() throws Exception {

		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {

			atletaDAO.setEntityManager(entityManager);

			return atletaDAO.list();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);

		}

	}

	@Override
	public Atleta caricaSingoloElemento(Long id) throws Exception {

		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {

			atletaDAO.setEntityManager(entityManager);

			return atletaDAO.get(id);

		} catch (Exception e) {

			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public void aggiorna(Atleta o) throws Exception {

		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {

			atletaDAO.setEntityManager(entityManager);
			entityManager.getTransaction().begin();
			atletaDAO.update(o);
			entityManager.getTransaction().commit();

		} catch (Exception e) {

			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;

		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}

	}

	@Override
	public void inserisciNuovo(Atleta o) throws Exception {

		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {

			atletaDAO.setEntityManager(entityManager);
			entityManager.getTransaction().begin();
			atletaDAO.insert(o);
			entityManager.getTransaction().commit();

		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);

		}

	}

	@Override
	public void rimuovi(Long id) throws Exception {

		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {

			atletaDAO.setEntityManager(entityManager);
			entityManager.getTransaction().begin();
			atletaDAO.delete(atletaDAO.get(id));
			entityManager.getTransaction().commit();

		} catch (Exception e) {

			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);

		}

	}

	@Override
	public void setAtletaDAO(AtletaDAO atletaDAO) {

		this.atletaDAO = atletaDAO;

	}

}
