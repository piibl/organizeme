package com.talsoft.organizeme.web.service;

import java.io.Serializable;

import com.talsoft.organizeme.web.dto.IDTO;

public interface DTOSupport<T, X extends Serializable, Y extends IDTO> {

	/**
	 * Crée et sauvegarde une entité à partir de son DTO
	 * 
	 * @param entityDTO
	 * @return
	 */
	public T createFromDTO(Y entityDTO);

	/**
	 * met à jour et sauvegarde une entité à partir de son DTO
	 * 
	 * @param entityDTO
	 * @return
	 */
	public T saveFromDTO(Y entityDTO, X id);

}
