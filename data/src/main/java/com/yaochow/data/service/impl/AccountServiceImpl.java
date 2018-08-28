package com.yaochow.data.service.impl;

import com.yaochow.data.common.DBConstant;
import com.yaochow.data.entity.Account;
import com.yaochow.data.entity.PageEntity;
import com.yaochow.data.repository.AccountRepository;
import com.yaochow.data.service.AccountService;
import com.yaochow.common.AccountStatusEnum;
import com.yaochow.dto.AccountDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public AccountDTO insert(AccountDTO accountDTO) {
        Account account = new Account();
        BeanUtils.copyProperties(accountDTO, account);
        account.setModifier(DBConstant.SYSTEM);
        account.setGmtModified(Calendar.getInstance().getTime());
        account.setCreator(DBConstant.SYSTEM);
        account.setGmtCreated(Calendar.getInstance().getTime());
        account.setDelete(DBConstant.UN_DELETE);
        account = accountRepository.insert(account);
        accountDTO.setId(account.getId());
        return accountDTO;
    }

    @Override
    public AccountDTO getAccountByUsername(String username) {
        Account account = new Account();
        account.setUsername(username);
        account.setStatus(AccountStatusEnum.CONFIRM.getCode());
        account.setDelete(DBConstant.UN_DELETE);
        Optional<Account> optional = accountRepository.findOne(Example.of(account));
        AccountDTO accountDTO = null;
        if (optional.isPresent()) {
            accountDTO = new AccountDTO();
            BeanUtils.copyProperties(optional.get(), accountDTO);
        }
        return accountDTO;
    }

    @Override
    public AccountDTO updateAccountById(AccountDTO accountDTO) {
        Account account = new Account();
        account.setId(accountDTO.getId());
        Optional<Account> optional = accountRepository.findOne(Example.of(account));
        if (accountDTO.getStatus() != null) {
            optional.get().setStatus(accountDTO.getStatus());
        }
        if (accountDTO.getPassword() != null) {
            optional.get().setPassword(accountDTO.getPassword());
        }
        account.setModifier(DBConstant.SYSTEM);
        account.setGmtModified(Calendar.getInstance().getTime());
        account = accountRepository.save(optional.get());
        AccountDTO accountDTORes = new AccountDTO();
        BeanUtils.copyProperties(account, accountDTORes);
        return accountDTORes;
    }

    @Override
    public AccountDTO getAccountByEmail(String email) {
        Account account = new Account();
        account.setEmail(email);
        account.setStatus(AccountStatusEnum.CONFIRM.getCode());
        account.setDelete(DBConstant.UN_DELETE);
        Optional<Account> optional = accountRepository.findOne(Example.of(account));
        AccountDTO accountDTO = null;
        if (optional.isPresent()) {
            accountDTO = new AccountDTO();
            BeanUtils.copyProperties(optional.get(), accountDTO);
        }
        return accountDTO;
    }

    @Override
    public AccountDTO getAccount(AccountDTO accountDTO) {
        Account account = new Account();
        BeanUtils.copyProperties(accountDTO, account);
        List<Account> accounts = accountRepository.findAll(Example.of(account), new PageEntity().getSort());
        if (!CollectionUtils.isEmpty(accounts) && accounts.size() > 0){
            AccountDTO accountDTORes = new AccountDTO();
            BeanUtils.copyProperties(accounts.get(0), accountDTORes);
            return accountDTORes;
        }
        return null;
    }
}
