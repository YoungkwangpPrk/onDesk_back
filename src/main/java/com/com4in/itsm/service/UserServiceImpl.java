package com.com4in.itsm.service;

import com.com4in.itsm.dto.CompanyDto;
import com.com4in.itsm.dto.UserDto;
import com.com4in.itsm.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Override
    public List<UserDto> getUserList(String account) {
        return userMapper.getUserList(account);
    }

    public void createUser(UserDto userDto) {
        userMapper.createUser(userDto);
    }

    public void updateUser(UserDto userDto) {
        userMapper.updateUser(userDto);
    }

    public void deleteUser(UserDto userDto) {
        userMapper.deleteUser(userDto);
    }

    public List<CompanyDto> getCompany(String account) {
        return userMapper.getCompany(account);
    }

    public UserDto getPersons(String email) {
        return userMapper.getPersonID(email);
    }
}
