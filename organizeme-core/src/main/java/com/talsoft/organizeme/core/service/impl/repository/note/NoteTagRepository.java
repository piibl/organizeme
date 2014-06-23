package com.talsoft.organizeme.core.service.impl.repository.note;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.talsoft.organizeme.core.domain.note.Note;
import com.talsoft.organizeme.core.domain.note.NoteTag;
import com.talsoft.organizeme.core.domain.note.Tag;

public interface NoteTagRepository extends JpaRepository<NoteTag, Long> {

	public List<NoteTag> findByNote(Note note);

	public List<NoteTag> findByTag(Tag tag);

}
