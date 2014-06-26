package com.talsoft.organizeme.core.service.impl.repository.note;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.talsoft.organizeme.core.domain.note.Note;
import com.talsoft.organizeme.core.domain.user.EndUser;

public interface NoteRepository extends JpaRepository<Note, Long> {

	List<Note> findByOwnerAndArchivedOrderByCreatedAtAsc(EndUser owner, boolean b);

	List<Note> findByOwnerAndArchivedOrderByCreatedAtDesc(EndUser owner, boolean b, Pageable pageable);

	List<Note> findByOwnerOrderByCreatedAtAsc(EndUser owner);

}
