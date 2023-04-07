package it.altletasportjpamaven.dao;

import it.altletasportjpamaven.model.Sport;

public interface SportDAO extends IBaseDAO<Sport> {

	public Sport cercaPerDescrizione(String descrizione) throws Exception;

}
