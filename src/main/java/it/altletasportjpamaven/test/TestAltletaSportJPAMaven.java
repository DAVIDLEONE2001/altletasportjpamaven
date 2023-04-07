package it.altletasportjpamaven.test;

import java.time.LocalDate;
import java.util.List;

import it.altletasportjpamaven.dao.EntityManagerUtil;
import it.altletasportjpamaven.model.Atleta;
import it.altletasportjpamaven.model.Sport;
import it.altletasportjpamaven.service.AtletaService;
import it.altletasportjpamaven.service.MyServiceFactory;
import it.altletasportjpamaven.service.SportService;

public class TestAltletaSportJPAMaven {

	public static void main(String[] args) throws Exception {

//		SportDAO sportDAOIstance= MyDAOFactory.getSportDAOInstance();
//		AtletaDAO atletaDAOIstance = MyDAOFactory.getAtletaDAOInstance();
		SportService sportServiceIstance = MyServiceFactory.getSportServiceInstance();
		AtletaService atletaServiceIstance = MyServiceFactory.getAtletaServiceInstance();

		try {

			initSport(sportServiceIstance);

//*****************INIZIO TEST SPORT****************************			

			System.out.println("Nella tabella sport ci sono " + sportServiceIstance.listAll().size() + " elementi");

//			testInsertSport(sportServiceIstance);
//			testDeleteSport(sportServiceIstance);
//			testUpdateSport(sportServiceIstance);

			System.out.println("Nella tabella sport ci sono " + sportServiceIstance.listAll().size() + " elementi");

//*****************FINE TEST SPORT****************************	

//-------------------------------------------------------------------------------------------------------------------------------------

//*****************INIZIO TEST ATLETA**************************

			System.out.println("Nella tabella atleta ci sono " + atletaServiceIstance.listAll().size() + " elementi");

//			testInsertAtleta(atletaServiceIstance);
			testUpdateAtleta(atletaServiceIstance);

			System.out.println("Nella tabella atleta ci sono " + atletaServiceIstance.listAll().size() + " elementi");

//*****************FINE TEST ATLETA****************************

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			EntityManagerUtil.shutdown();
		}

	}

	private static void initSport(SportService sportServiceIstance) throws Exception {

		if (sportServiceIstance.cercaPerDescrizione("Calcio") == null) {

			sportServiceIstance.inserisciNuovo(new Sport("Calcio", LocalDate.now(), null));
		}

		if (sportServiceIstance.cercaPerDescrizione("Nuoto") == null) {

			sportServiceIstance.inserisciNuovo(new Sport("Nuoto", LocalDate.now(), null));
		}

		if (sportServiceIstance.cercaPerDescrizione("Corsa") == null) {

			sportServiceIstance.inserisciNuovo(new Sport("Corsa", LocalDate.now(), null));
		}

		if (sportServiceIstance.cercaPerDescrizione("Pallavolo") == null) {

			sportServiceIstance.inserisciNuovo(new Sport("Pallavolo", LocalDate.now(), null));
		}
	}

//*******************************INIZIO TEST SPORT**********************************************	

	private static void testInsertSport(SportService sportServiceIstance) throws Exception {

		System.out.println("***********INIZIO TEST testUpdateSport************");

		List<Sport> presentiSulDb = sportServiceIstance.listAll();

		Sport sport = new Sport("Pallavolo", LocalDate.now(), null);

		sportServiceIstance.inserisciNuovo(sport);

		List<Sport> presentiSulDbDopoInsert = sportServiceIstance.listAll();

		if (presentiSulDbDopoInsert.size() <= presentiSulDb.size()) {

			throw new RuntimeException("****************TEST FAILED testUpdateSport: Sport non aggiunto**********");

		}
		sportServiceIstance.rimuovi(sport.getId());

		System.out.println("***********FINE TEST testUpdateSport: PASSED************");

	}

	private static void testUpdateSport(SportService sportServiceIstance) throws Exception {

		System.out.println("***********INIZIO TEST testUpdateSport************");

		Sport sportUpdate = new Sport();

		sportServiceIstance.inserisciNuovo(sportUpdate);

		List<Sport> presentiSulDb = sportServiceIstance.listAll();

		Sport sport = presentiSulDb.get(presentiSulDb.size() - 1);

		sport.setDefinizione("Sport modificato");

		sportServiceIstance.aggiorna(sport);

		Sport sportModificato = presentiSulDb.get(presentiSulDb.size() - 1);

		if (!sportModificato.getDefinizione().equals(sportModificato.getDefinizione())) {

			throw new RuntimeException("****************TEST FAILED testUpdateSport: Sport non modificato**********");

		}

		sportServiceIstance.rimuovi(sport.getId());

		System.out.println("***********FINE TEST testUpdateSport: PASSED************");

	}

//	private static void testDeleteSport(SportService sportServiceIstance) throws Exception {} TESTATO IN testInsertSport

//*********************************FINE TEST SPORT**************************************	
//-------------------------------------------------------------------------------------------------
//*********************************INIZIO TEST ATELTA**************************************	

	private static void testInsertAtleta(AtletaService atletaServiceIstance) throws Exception {

		System.out.println("***********INIZIO TEST testUpdateSport************");

		List<Atleta> presentiSulDb = atletaServiceIstance.listAll();

		Atleta atleta = new Atleta("Mario", "Toscano", "MRTSCN01", 0, LocalDate.parse("1992-05-06"));

		atletaServiceIstance.inserisciNuovo(atleta);

		List<Atleta> presentiSulDbDopoInsert = atletaServiceIstance.listAll();

		if (presentiSulDbDopoInsert.size() <= presentiSulDb.size()) {

			throw new RuntimeException("****************TEST FAILED testUpdateSport: Sport non aggiunto**********");

		}
		atletaServiceIstance.rimuovi(atleta.getId());

		System.out.println("***********FINE TEST testUpdateSport: PASSED************");

	}

	private static void testUpdateAtleta(AtletaService atletaServiceIstance) throws Exception {

		System.out.println("***********INIZIO TEST testUpdateAtleta************");

		atletaServiceIstance
				.inserisciNuovo(new Atleta("Mario", "Toscano", "MRTSCN01", 0, LocalDate.parse("1992-05-06")));

		List<Atleta> presentiSulDb = atletaServiceIstance.listAll();

		Atleta atleta = presentiSulDb.get(presentiSulDb.size() - 1);
		
		String vecchioNome = atleta.getNome();

		atleta.setNome("Mimmo");
		
		atletaServiceIstance.aggiorna(atleta);

//		List<Atleta> presentiSulDbDopoUpdate = atletaServiceIstance.listAll();


		if (atleta.getNome().equals(vecchioNome)) {

			throw new RuntimeException("****************TEST FAILED testUpdateAtleta: Atleta non aggiornato**********");

		}
		atletaServiceIstance.rimuovi(atleta.getId());

		System.out.println("***********FINE TEST testUpdateAtleta: PASSED************");

	}

//*********************************FINE TEST ATELTA**************************************	
}
