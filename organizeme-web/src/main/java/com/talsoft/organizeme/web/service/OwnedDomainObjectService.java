package com.talsoft.organizeme.web.service;

import java.io.Serializable;
import java.util.List;

import com.talsoft.organizeme.core.domain.user.EndUser;
import com.talsoft.organizeme.core.service.CrudService;
import com.talsoft.organizeme.web.dto.IDTO;

public interface OwnedDomainObjectService<T, X extends Serializable, Y, Z extends IDTO> extends CrudService<T, X> {

	public List<T> findByOwner(Y owner);

	/**
	 * Crée et sauvegarde une entité à partir de son DTO
	 * 
	 * @param entityDTO
	 * @return
	 */
	public T createFromDTO(Z entityDTO, EndUser user);

	/**
	 * met à jour et sauvegarde une entité à partir de son DTO
	 * 
	 * @param entityDTO
	 * @return
	 */
	public T saveFromDTO(Z entityDTO, X entityId);

}
