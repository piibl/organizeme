package com.talsoft.organizeme.web.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.talsoft.organizeme.core.domain.note.Note;
import com.talsoft.organizeme.core.domain.user.EndUser;
import com.talsoft.organizeme.core.service.AbstractCrudService;
import com.talsoft.organizeme.core.service.impl.repository.note.NoteRepository;
import com.talsoft.organizeme.web.dto.impl.NoteDTO;
import com.talsoft.organizeme.web.service.NoteManagerService;

@Named
public class NoteManagerServiceImpl extends AbstractCrudService<Note, Long> implements NoteManagerService {

	@Inject
	private NoteRepository noteRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Note> findByOwnerAndArchivedStatus(EndUser owner, boolean archived) {
		return noteRepository.findByOwnerAndArchivedOrderByCreatedAtAsc(owner, archived);
	}

	@Override
	protected JpaRepository<Note, Long> getRepository() {
		return noteRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Note> findNotArchivedByOwner(EndUser owner) {
		// recherche des notes actives, triées par date de création
		return noteRepository.findByOwnerAndArchivedOrderByCreatedAtAsc(owner, false);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Note> findLastestByOwner(EndUser owner, int limit) {
		Pageable resultLimit = new PageRequest(0, limit);
		// recherche des notes actives, triées par date de création
		return noteRepository.findByOwnerAndArchivedOrderByCreatedAtDesc(owner, false, resultLimit);
	}

	@Override
	public Note createFromDTO(NoteDTO entityDTO, EndUser owner) {
		return noteRepository.save(new Note(entityDTO.getTitle(), entityDTO.getContent(), owner));
	}

	@Override
	public Note saveFromDTO(NoteDTO entityDTO, Long id) {
		Note note = noteRepository.findOne(id);
		if (note != null) {
			// transfert
			// TODO check des valeurs plus implé partielle
			note.setTitle(entityDTO.getTitle());
			note.setContent(entityDTO.getContent());
			return noteRepository.save(note);
		}
		// TODO cas erreur !!!
		return null;
	}

	@Override
	public List<Note> findByOwner(EndUser owner) {
		// On retourne tous les notes actives
		return findNotArchivedByOwner(owner);
	}

}
