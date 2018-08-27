package com.yaochow.note.service;

import com.yaochow.note.service.impl.NoteServiceHystrix;
import com.yaochow.parent.common.PageParam;
import com.yaochow.parent.common.PageResult;
import com.yaochow.parent.common.ResultBase;
import com.yaochow.parent.dto.NoteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "service-data", fallback = NoteServiceHystrix.class)
public interface NoteService {

    @RequestMapping(value = "/note/insert", method = RequestMethod.POST)
    ResultBase<NoteDTO> insert(@RequestBody NoteDTO noteDTO);

    @RequestMapping(value = "/note/getNoteById/{id}", method = RequestMethod.GET)
    ResultBase<NoteDTO> getNoteById(@PathVariable("id") String id);

    @RequestMapping(value = "/note/update", method = RequestMethod.POST)
    ResultBase<NoteDTO> update(@RequestBody NoteDTO noteDTO);

    @RequestMapping(value = "/note/delete/{id}", method = RequestMethod.GET)
    ResultBase<NoteDTO> delete(@PathVariable("id") String id);

    @RequestMapping(value = "/note/resume/{id}", method = RequestMethod.GET)
    ResultBase<NoteDTO> resume(@PathVariable("id") String id);

    @RequestMapping(value = "/note/listDeletedNotes/{accountId}", method = RequestMethod.GET)
    ResultBase<List<NoteDTO>> listDeletedNotes(@PathVariable("accountId") String accountId);

    @RequestMapping(value = "/note/listNotesPages", method = RequestMethod.POST)
    PageResult<NoteDTO> listNotes(@RequestBody PageParam<NoteDTO> pageParam);

    @RequestMapping(value = "/note/listNotes", method = RequestMethod.POST)
    ResultBase<List<NoteDTO>> listNotes(@RequestBody NoteDTO noteDTO);

}
