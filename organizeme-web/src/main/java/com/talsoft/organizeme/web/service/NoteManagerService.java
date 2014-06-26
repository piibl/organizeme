package com.talsoft.organizeme.web.service;

import java.util.List;

import com.talsoft.organizeme.core.domain.note.Note;
import com.talsoft.organizeme.core.domain.user.EndUser;
import com.talsoft.organizeme.web.dto.impl.NoteDTO;

public interface NoteManagerService extends OwnedDomainObjectService<Note, Long, EndUser, NoteDTO> {

	List<Note> findByOwner(EndUser owner);

	List<Note> findNotArchivedByOwner(EndUser owner);

	List<Note> findByOwnerAndArchivedStatus(EndUser owner, boolean archived);

	List<Note> findLastestByOwner(EndUser owner, int limit);

}
