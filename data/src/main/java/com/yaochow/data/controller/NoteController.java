package com.yaochow.data.controller;

import com.alibaba.fastjson.JSONObject;
import com.yaochow.data.entity.PageEntity;
import com.yaochow.data.service.NoteService;
import com.yaochow.common.ErrorMsgEnum;
import com.yaochow.common.PageParam;
import com.yaochow.common.PageResult;
import com.yaochow.common.ResultBase;
import com.yaochow.dto.NoteDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/note")
@Slf4j
public class NoteController {

    @Autowired
    private NoteService noteServiceImpl;

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public ResultBase<NoteDTO> insert(@RequestBody NoteDTO noteDTO) {
        long start = System.currentTimeMillis();
        ResultBase<NoteDTO> result = new ResultBase<>();
        log.info("insert, param : {}", JSONObject.toJSONString(noteDTO));
        try {
            if (noteDTO.getAccountId() == null) {
                result.setSuccess(false);
                result.setErrorCode(ErrorMsgEnum.ACCOUNT_ID_NOT_EXIST_ERROR.getErrorCode());
                result.setErrorMsg(ErrorMsgEnum.ACCOUNT_ID_NOT_EXIST_ERROR.getErrorMsg());
                return result;
            }
            NoteDTO noteDTORes = noteServiceImpl.insert(noteDTO);
            result.setSuccess(true);
            result.setResult(noteDTORes);
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
        log.info("update, param : {}", JSONObject.toJSONString(noteDTO));
        try {
            NoteDTO noteDTORes = noteServiceImpl.update(noteDTO);
            result.setSuccess(true);
            result.setResult(noteDTORes);
        } catch (Exception e) {
            log.info(e.getMessage(), e);
            result.setSuccess(false);
            result.setErrorCode(ErrorMsgEnum.SYSTEM_ERROR.getErrorCode());
            result.setErrorMsg(ErrorMsgEnum.SYSTEM_ERROR.getErrorMsg());
        }

        log.info("update, result : {}, cost : {}ms", JSONObject.toJSONString(result), System.currentTimeMillis() - start);
        return result;
    }

    @RequestMapping(value = "/getNoteById/{id}", method = RequestMethod.GET)
    public ResultBase<NoteDTO> getNoteById(@PathVariable String id) {
        long start = System.currentTimeMillis();
        ResultBase<NoteDTO> result = new ResultBase<>();
        log.info("get note by id : {}", id);
        try {
            NoteDTO noteDTORes = noteServiceImpl.getNoteById(id);
            result.setSuccess(true);
            result.setResult(noteDTORes);
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
        log.info("delete : {}", id);
        try {
            NoteDTO noteDTORes = noteServiceImpl.delete(id);
            result.setSuccess(true);
            result.setResult(noteDTORes);
        } catch (Exception e) {
            log.info(e.getMessage(), e);
            result.setSuccess(false);
            result.setErrorCode(ErrorMsgEnum.SYSTEM_ERROR.getErrorCode());
            result.setErrorMsg(ErrorMsgEnum.SYSTEM_ERROR.getErrorMsg());
        }
        log.info("delete, result : {}, cost : {}ms", JSONObject.toJSONString(result), System.currentTimeMillis() - start);
        return result;
    }

    @RequestMapping(value = "/resume/{id}", method = RequestMethod.GET)
    public ResultBase<NoteDTO> resume(@PathVariable String id) {
        long start = System.currentTimeMillis();
        ResultBase<NoteDTO> result = new ResultBase<>();
        log.info("resume : {}", id);
        try {
            NoteDTO noteDTORes = noteServiceImpl.resume(id);
            result.setSuccess(true);
            result.setResult(noteDTORes);
        } catch (Exception e) {
            log.info(e.getMessage(), e);
            result.setSuccess(false);
            result.setErrorCode(ErrorMsgEnum.SYSTEM_ERROR.getErrorCode());
            result.setErrorMsg(ErrorMsgEnum.SYSTEM_ERROR.getErrorMsg());
        }
        log.info("resume, result : {}, cost : {}ms", JSONObject.toJSONString(result), System.currentTimeMillis() - start);
        return result;
    }

    @RequestMapping(value = "/listDeletedNotes/{accountId}", method = RequestMethod.GET)
    public ResultBase<List<NoteDTO>> listDeletedNotes(@PathVariable String accountId) {
        long start = System.currentTimeMillis();
        ResultBase<List<NoteDTO>> result = new ResultBase<>();
        try {
            log.info("list deleted noteName by accountId : {}", accountId);
            List<NoteDTO> noteDTORes = noteServiceImpl.listDeletedNoteNameByAccountId(accountId);
            result.setSuccess(true);
            result.setResult(noteDTORes);
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
        try {
            log.info("list note, param : {}", JSONObject.toJSONString(pageParam));
            PageEntity page = new PageEntity();
            page.setPageNumber(pageParam.getPageNumber());
            page.setPageSize(pageParam.getPageSize());
            NoteDTO noteDTO = pageParam.getParam();
            if (noteDTO == null || noteDTO.getAccountId() == null) {
                result.setSuccess(false);
                result.setErrorCode(ErrorMsgEnum.ACCOUNT_ID_NOT_EXIST_ERROR.getErrorCode());
                result.setErrorMsg(ErrorMsgEnum.ACCOUNT_ID_NOT_EXIST_ERROR.getErrorMsg());
                return result;
            }
            Page<NoteDTO> pageDTO = noteServiceImpl.listNote(noteDTO, page);
            result.setSuccess(true);
            if (pageDTO == null) {
                result.setTotalPages(0);
                result.setTotalCount(0);
            } else {
                result.setTotalCount(pageDTO.getTotalElements());
                result.setTotalPages(pageDTO.getTotalPages());
                result.setResult(pageDTO.getContent());
            }
        } catch (Exception e) {
            log.info(e.getMessage(), e);
            result.setSuccess(false);
            result.setErrorCode(ErrorMsgEnum.SYSTEM_ERROR.getErrorCode());
            result.setErrorMsg(ErrorMsgEnum.SYSTEM_ERROR.getErrorMsg());
        }
        log.info("list noteName, result : {}, cost : {}ms", JSONObject.toJSONString(result), System.currentTimeMillis() - start);
        return result;
    }

    @RequestMapping(value = "/listNotes", method = RequestMethod.POST)
    public ResultBase<List<NoteDTO>> listNotes(@RequestBody NoteDTO noteDTO) {
        long start = System.currentTimeMillis();
        ResultBase<List<NoteDTO>> result = new ResultBase<>();
        try {
            log.info("list note, param : {}", JSONObject.toJSONString(noteDTO));

            if (noteDTO.getAccountId() == null) {
                result.setSuccess(false);
                result.setErrorCode(ErrorMsgEnum.ACCOUNT_ID_NOT_EXIST_ERROR.getErrorCode());
                result.setErrorMsg(ErrorMsgEnum.ACCOUNT_ID_NOT_EXIST_ERROR.getErrorMsg());
                return result;
            }
            List<NoteDTO> noteDTOS = noteServiceImpl.listNote(noteDTO);
            result.setSuccess(true);
            result.setResult(noteDTOS);
        } catch (Exception e) {
            log.info(e.getMessage(), e);
            result.setSuccess(false);
            result.setErrorCode(ErrorMsgEnum.SYSTEM_ERROR.getErrorCode());
            result.setErrorMsg(ErrorMsgEnum.SYSTEM_ERROR.getErrorMsg());
        }
        log.info("list noteName, result : {}, cost : {}ms", JSONObject.toJSONString(result), System.currentTimeMillis() - start);
        return result;
    }
}
