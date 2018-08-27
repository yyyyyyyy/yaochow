package com.yaochow.data.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yaochow.data.common.DBConstant;
import com.yaochow.data.entity.Note;
import com.yaochow.data.entity.PageEntity;
import com.yaochow.data.repository.NoteRepository;
import com.yaochow.data.service.NoteService;
import com.yaochow.parent.dto.NoteDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Override
    public NoteDTO insert(NoteDTO noteDTO) {
        Note note = new Note();
        BeanUtils.copyProperties(noteDTO, note);
        note.setModifier(DBConstant.SYSTEM);
        note.setGmtModified(Calendar.getInstance().getTime());
        note.setCreator(DBConstant.SYSTEM);
        note.setGmtCreated(Calendar.getInstance().getTime());
        note.setDelete(DBConstant.UN_DELETE);
        note = noteRepository.insert(note);
        noteDTO.setId(note.getId());
        return noteDTO;
    }

    @Override
    public NoteDTO getNoteById(String id) {
        Optional<Note> optional = noteRepository.findById(id);
        NoteDTO noteDTO = null;
        if (optional.isPresent()) {
            noteDTO = new NoteDTO();
            BeanUtils.copyProperties(optional.get(), noteDTO);
        }
        return noteDTO;
    }

    @Override
    public NoteDTO update(NoteDTO noteDTO) {
        Note note = new Note();
        note.setId(noteDTO.getId());
        Optional<Note> optional = noteRepository.findOne(Example.of(note));
        optional.get().setContent(noteDTO.getContent());
        optional.get().setModifier(DBConstant.SYSTEM);
        optional.get().setGmtModified(Calendar.getInstance().getTime());
        note = noteRepository.save(optional.get());
        NoteDTO noteDTORes = new NoteDTO();
        BeanUtils.copyProperties(note, noteDTORes);
        return noteDTORes;
    }

    @Override
    public NoteDTO delete(String id) {
        Note note = new Note();
        note.setId(id);
        Optional<Note> optional = noteRepository.findOne(Example.of(note));
        optional.get().setModifier(DBConstant.SYSTEM);
        optional.get().setGmtModified(Calendar.getInstance().getTime());
        optional.get().setDelete(DBConstant.DELETE);
        note = noteRepository.save(optional.get());
        NoteDTO noteDTO = new NoteDTO();
        BeanUtils.copyProperties(note, noteDTO);
        return noteDTO;
    }

    @Override
    public NoteDTO resume(String id) {
        Note note = new Note();
        note.setId(id);
        Optional<Note> optional = noteRepository.findOne(Example.of(note));
        optional.get().setModifier(DBConstant.SYSTEM);
        optional.get().setGmtModified(Calendar.getInstance().getTime());
        optional.get().setDelete(DBConstant.UN_DELETE);
        note = noteRepository.save(optional.get());
        NoteDTO noteDTO = new NoteDTO();
        BeanUtils.copyProperties(note, noteDTO);
        return noteDTO;
    }

    @Override
    public List<NoteDTO> listDeletedNoteNameByAccountId(String accountId) {
        Note note = new Note();
        note.setAccountId(accountId);
        note.setDelete(DBConstant.DELETE);
        List<Note> notes = noteRepository.findAll(Example.of(note));
        List<NoteDTO> noteDTOs = null;
        if (!CollectionUtils.isEmpty(notes)) {
            noteDTOs = JSONArray.parseArray(JSONObject.toJSONString(notes), NoteDTO.class);
        }
        return noteDTOs;
    }

    @Override
    public Page<NoteDTO> listNote(NoteDTO noteDTO, PageEntity pageEntity) {
        Note note = new Note();
        BeanUtils.copyProperties(noteDTO, note);
        note.setDelete(DBConstant.UN_DELETE);
        Page<Note> page = noteRepository.findAll(Example.of(note), pageEntity);
        Page<NoteDTO> pageDTO = null;
        if (!CollectionUtils.isEmpty(page.getContent())) {
            List<NoteDTO> noteDTOs = JSONArray.parseArray(JSONObject.toJSONString(page.getContent()), NoteDTO.class);
            pageDTO = new PageImpl(noteDTOs, pageEntity, page.getTotalElements());
        }
        return pageDTO;
    }

    @Override
    public List<NoteDTO> listNote(NoteDTO noteDTO) {
        Note note = new Note();
        BeanUtils.copyProperties(noteDTO, note);
        note.setDelete(DBConstant.UN_DELETE);
        List<Note> notes = noteRepository.findAll(Example.of(note), new PageEntity().getSort());
        List<NoteDTO> noteDTOs = null;
        if (!CollectionUtils.isEmpty(notes)) {
            noteDTOs = JSONArray.parseArray(JSONObject.toJSONString(notes), NoteDTO.class);
        }
        return noteDTOs;
    }
}
