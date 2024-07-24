package com.com4in.itsm.service;


import com.com4in.itsm.dto.CompanyDto;
import com.com4in.itsm.dto.UserDto;

import java.util.List;

public interface UserService {

    List<UserDto> getUserList(String account);

    UserDto getPersons(String email);

    void createUser(UserDto userDto);

    void updateUser(UserDto userDto);

    void deleteUser(UserDto userDto);

    List<CompanyDto> getCompany(String account);

}
