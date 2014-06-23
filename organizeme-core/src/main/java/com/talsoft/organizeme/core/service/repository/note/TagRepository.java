package com.talsoft.organizeme.core.service.repository.note;

import org.springframework.data.jpa.repository.JpaRepository;

import com.talsoft.organizeme.core.domain.note.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {

}
