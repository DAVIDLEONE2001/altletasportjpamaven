package it.altletasportjpamaven.service;

import it.altletasportjpamaven.dao.SportDAO;
import it.altletasportjpamaven.model.Sport;

public interface SportService extends IBaseService<Sport> {

	public Sport cercaPerDescrizione(String descrizione) throws Exception;

	public void setSportDAO(SportDAO sportDAO);

}
