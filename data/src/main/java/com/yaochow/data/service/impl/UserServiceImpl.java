package com.yaochow.data.service.impl;

import com.yaochow.data.common.DBConstant;
import com.yaochow.data.entity.User;
import com.yaochow.data.repository.UserRepository;
import com.yaochow.data.service.UserService;
import com.yaochow.dto.UserDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO insert(UserDTO userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        user.setModifier(DBConstant.SYSTEM);
        user.setGmtModified(Calendar.getInstance().getTime());
        user.setCreator(DBConstant.SYSTEM);
        user.setGmtCreated(Calendar.getInstance().getTime());
        user.setDelete(DBConstant.UN_DELETE);
        user = userRepository.insert(user);
        userDTO.setId(user.getId());
        return userDTO;
    }

    @Override
    public UserDTO update(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        Optional<User> optional = userRepository.findOne(Example.of(user));
        if (null != userDTO.getAge())
            optional.get().setAge(userDTO.getAge());
        if (null != userDTO.getBirthday())
            optional.get().setBirthday(userDTO.getBirthday());
        if (null != userDTO.getCertificateCode())
            optional.get().setCertificateCode(userDTO.getCertificateCode());
        if (null != userDTO.getCertificateType())
            optional.get().setCertificateType(userDTO.getCertificateType());
        if (null != userDTO.getGender())
            optional.get().setGender(userDTO.getGender());
        optional.get().setModifier(DBConstant.SYSTEM);
        optional.get().setGmtModified(Calendar.getInstance().getTime());
        user = userRepository.save(optional.get());
        UserDTO userDTORes = new UserDTO();
        BeanUtils.copyProperties(user, userDTORes);
        return userDTORes;
    }

    @Override
    public UserDTO getUserByAccountId(String accountId) {
        User user = new User();
        user.setAccountId(accountId);
        user.setDelete(DBConstant.UN_DELETE);
        Optional<User> optional = userRepository.findOne(Example.of(user));
        UserDTO userDTO = null;
        if (optional.isPresent()) {
            userDTO = new UserDTO();
            BeanUtils.copyProperties(optional.get(), userDTO);
        }
        return userDTO;
    }
}
