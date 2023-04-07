package it.altletasportjpamaven.service;

import it.altletasportjpamaven.dao.AtletaDAO;
import it.altletasportjpamaven.model.Atleta;

public interface AtletaService extends IBaseService<Atleta> {

	public void setAtletaDAO(AtletaDAO atletaDAO);

	
}
