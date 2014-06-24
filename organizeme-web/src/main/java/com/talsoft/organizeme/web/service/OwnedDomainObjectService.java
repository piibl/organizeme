package com.talsoft.organizeme.web.service;

import java.io.Serializable;
import java.util.List;

import com.talsoft.organizeme.core.service.CrudService;

public interface OwnedDomainObjectService<T, X extends Serializable, Y> extends CrudService<T, X> {

	public List<T> findByOwner(Y owner);

}
