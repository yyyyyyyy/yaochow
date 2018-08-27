package com.yaochow.data.service;

import com.yaochow.data.entity.PageEntity;
import com.yaochow.parent.dto.NoteDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface NoteService {

    NoteDTO insert(NoteDTO noteDTO);

    NoteDTO getNoteById(String id);

    NoteDTO update(NoteDTO noteDTO);

    NoteDTO delete(String id);

    NoteDTO resume(String id);

    List<NoteDTO> listDeletedNoteNameByAccountId(String accountId);

    Page<NoteDTO> listNote(NoteDTO noteDTO, PageEntity pageEntity);

    List<NoteDTO> listNote(NoteDTO noteDTO);

}
