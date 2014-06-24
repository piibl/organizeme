package com.talsoft.organizeme.web.dto;

import com.talsoft.organizeme.core.service.CrudService;

/**
 * Interface pour les DTO des entités avec propriétaire <br/>
 * Bidon, ne sert que pour tromper les services indeed ! {@link CrudService}
 */
public interface OwnedDTO<X> extends IDTO {

	public X getOwnerId();

}
