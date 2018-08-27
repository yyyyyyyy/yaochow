package com.yaochow.note.controller;

import com.alibaba.fastjson.JSONObject;
import com.yaochow.note.service.NoteService;
import com.yaochow.parent.common.ErrorMsgEnum;
import com.yaochow.parent.common.PageParam;
import com.yaochow.parent.common.PageResult;
import com.yaochow.parent.common.ResultBase;
import com.yaochow.parent.dto.NoteDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/note")
public class NoteController {

    @Autowired
    private NoteService noteService;
    @Autowired
    private HttpServletRequest request;

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public ResultBase<NoteDTO> insert(@RequestBody NoteDTO noteDTO) {
        long start = System.currentTimeMillis();
        ResultBase<NoteDTO> result = new ResultBase<>();
        log.info("insert, param : {}", JSONObject.toJSONString(noteDTO));
        try {
            if (noteDTO.getName() == null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                noteDTO.setName(sdf.format(Calendar.getInstance().getTime()));
            }
            noteDTO.setAccountId((String) request.getSession().getAttribute("uid"));
            result = noteService.insert(noteDTO);
        } catch (Exception e) {
            log.info(e.getMessage(), e);
            result.setSuccess(false);
            result.setErrorCode(ErrorMsgEnum.SYSTEM_ERROR.getErrorCode());
            result.setErrorMsg(ErrorMsgEnum.SYSTEM_ERROR.getErrorMsg());
        }
        log.info("insert, result : {}, cost : {}ms", JSONObject.toJSONString(result), System.currentTimeMillis() - start);
        return result;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResultBase<NoteDTO> update(@RequestBody NoteDTO noteDTO) {
        long start = System.currentTimeMillis();
        ResultBase<NoteDTO> result = new ResultBase<>();
        log.info("update by accountId, param : {}", JSONObject.toJSONString(noteDTO));
        try {
            result = noteService.update(noteDTO);
        } catch (Exception e) {
            log.info(e.getMessage(), e);
            result.setSuccess(false);
            result.setErrorCode(ErrorMsgEnum.SYSTEM_ERROR.getErrorCode());
            result.setErrorMsg(ErrorMsgEnum.SYSTEM_ERROR.getErrorMsg());
        }

        log.info("update by accountId, result : {}, cost : {}ms", JSONObject.toJSONString(result), System.currentTimeMillis() - start);
        return result;
    }

    @RequestMapping(value = "/getNoteById/{id}", method = RequestMethod.GET)
    public ResultBase<NoteDTO> getNoteById(@PathVariable String id) {
        long start = System.currentTimeMillis();
        ResultBase<NoteDTO> result = new ResultBase<>();
        log.info("get note by id : {}", id);
        try {
            result = noteService.getNoteById(id);
        } catch (Exception e) {
            log.info(e.getMessage(), e);
            result.setSuccess(false);
            result.setErrorCode(ErrorMsgEnum.SYSTEM_ERROR.getErrorCode());
            result.setErrorMsg(ErrorMsgEnum.SYSTEM_ERROR.getErrorMsg());
        }
        log.info("get note by id, result : {}, cost : {}ms", JSONObject.toJSONString(result), System.currentTimeMillis() - start);
        return result;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ResultBase<NoteDTO> delete(@PathVariable String id) {
        long start = System.currentTimeMillis();
        ResultBase<NoteDTO> result = new ResultBase<>();
        log.info("delete note by id : {}", id);
        try {
            result = noteService.delete(id);
        } catch (Exception e) {
            log.info(e.getMessage(), e);
            result.setSuccess(false);
            result.setErrorCode(ErrorMsgEnum.SYSTEM_ERROR.getErrorCode());
            result.setErrorMsg(ErrorMsgEnum.SYSTEM_ERROR.getErrorMsg());
        }
        log.info("delete note by id, result : {}, cost : {}ms", JSONObject.toJSONString(result), System.currentTimeMillis() - start);
        return result;
    }

    @RequestMapping(value = "/resume/{id}", method = RequestMethod.GET)
    public ResultBase<NoteDTO> resume(@PathVariable String id) {
        long start = System.currentTimeMillis();
        ResultBase<NoteDTO> result = new ResultBase<>();
        log.info("resume note by id : {}", id);
        try {
            result = noteService.resume(id);
        } catch (Exception e) {
            log.info(e.getMessage(), e);
            result.setSuccess(false);
            result.setErrorCode(ErrorMsgEnum.SYSTEM_ERROR.getErrorCode());
            result.setErrorMsg(ErrorMsgEnum.SYSTEM_ERROR.getErrorMsg());
        }
        log.info("resume note by id, result : {}, cost : {}ms", JSONObject.toJSONString(result), System.currentTimeMillis() - start);
        return result;
    }

    @RequestMapping(value = "/listDeletedNotes", method = RequestMethod.GET)
    public ResultBase<List<NoteDTO>> listDeletedNotes() {
        long start = System.currentTimeMillis();
        ResultBase<List<NoteDTO>> result = new ResultBase<>();
        log.info("list deleted noteName by accountId begin");
        try {
            result = noteService.listDeletedNotes((String) request.getSession().getAttribute("uid"));
        } catch (Exception e) {
            log.info(e.getMessage(), e);
            result.setSuccess(false);
            result.setErrorCode(ErrorMsgEnum.SYSTEM_ERROR.getErrorCode());
            result.setErrorMsg(ErrorMsgEnum.SYSTEM_ERROR.getErrorMsg());
        }
        log.info("list deleted noteName, result : {}, cost : {}ms", JSONObject.toJSONString(result), System.currentTimeMillis() - start);
        return result;
    }

    @RequestMapping(value = "/listNotesPages", method = RequestMethod.POST)
    public PageResult<NoteDTO> listNotes(@RequestBody PageParam<NoteDTO> pageParam) {
        long start = System.currentTimeMillis();
        PageResult<NoteDTO> result = new PageResult<>();
        log.info("list note begin");
        try {
            if (pageParam.getParam() == null) {
                NoteDTO noteDTO = new NoteDTO();
                noteDTO.setAccountId((String) request.getSession().getAttribute("uid"));
                pageParam.setParam(noteDTO);
            } else {
                pageParam.getParam().setAccountId((String) request.getSession().getAttribute("uid"));
            }
            result = noteService.listNotes(pageParam);
        } catch (Exception e) {
            log.info(e.getMessage(), e);
            result.setSuccess(false);
            result.setErrorCode(ErrorMsgEnum.SYSTEM_ERROR.getErrorCode());
            result.setErrorMsg(ErrorMsgEnum.SYSTEM_ERROR.getErrorMsg());
        }
        log.info("list note, result : {}, cost : {}ms", JSONObject.toJSONString(result), System.currentTimeMillis() - start);
        return result;
    }

    @RequestMapping(value = "/listNotes", method = RequestMethod.POST)
    public ResultBase<List<NoteDTO>> listNotes(@RequestBody NoteDTO noteDTO) {
        long start = System.currentTimeMillis();
        ResultBase<List<NoteDTO>> result = new ResultBase<>();
        log.info("list note begin, param : {}", JSONObject.toJSONString(noteDTO));
        try {
            if (noteDTO == null) {
                noteDTO = new NoteDTO();
            }
            noteDTO.setAccountId((String) request.getSession().getAttribute("uid"));
            result = noteService.listNotes(noteDTO);
        } catch (Exception e) {
            log.info(e.getMessage(), e);
            result.setSuccess(false);
            result.setErrorCode(ErrorMsgEnum.SYSTEM_ERROR.getErrorCode());
            result.setErrorMsg(ErrorMsgEnum.SYSTEM_ERROR.getErrorMsg());
        }
        log.info("list note, result : {}, cost : {}ms", JSONObject.toJSONString(result), System.currentTimeMillis() - start);
        return result;
    }
}
