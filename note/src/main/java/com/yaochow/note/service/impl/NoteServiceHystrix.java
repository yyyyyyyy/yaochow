package com.yaochow.note.service.impl;

import com.yaochow.note.service.NoteService;
import com.yaochow.parent.common.*;
import com.yaochow.parent.dto.NoteDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NoteServiceHystrix implements NoteService {

    @Override
    public ResultBase<NoteDTO> insert(NoteDTO noteDTO) {
        return HystrixEffectiveEnum.NORMAL_EFFECTIVE.getResultBase();
    }

    @Override
    public ResultBase<NoteDTO> getNoteById(String id) {
        return HystrixEffectiveEnum.NORMAL_EFFECTIVE.getResultBase();
    }

    @Override
    public ResultBase<NoteDTO> update(NoteDTO noteDTO) {
        return HystrixEffectiveEnum.NORMAL_EFFECTIVE.getResultBase();
    }

    @Override
    public ResultBase<NoteDTO> delete(String id) {
        return HystrixEffectiveEnum.NORMAL_EFFECTIVE.getResultBase();
    }

    @Override
    public ResultBase<NoteDTO> resume(String id) {
        return HystrixEffectiveEnum.NORMAL_EFFECTIVE.getResultBase();
    }

    @Override
    public ResultBase<List<NoteDTO>> listDeletedNotes(String accountId) {
        return HystrixEffectiveEnum.NORMAL_EFFECTIVE.getResultBase();
    }

    @Override
    public PageResult<NoteDTO> listNotes(PageParam<NoteDTO> pageParam) {
        PageResult result = new PageResult();
        result.setSuccess(false);
        result.setErrorMsg(ErrorMsgEnum.HYSTRIX_ERROR.getErrorMsg());
        result.setErrorCode(ErrorMsgEnum.HYSTRIX_ERROR.getErrorCode());
        return result;
    }

    @Override
    public ResultBase<List<NoteDTO>> listNotes(NoteDTO noteDTO) {
        return HystrixEffectiveEnum.NORMAL_EFFECTIVE.getResultBase();
    }

}
