package com.com4in.itsm.mapper;

import com.com4in.itsm.dto.CompanyDto;
import com.com4in.itsm.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {

    List<UserDto> getUserList(String account);

    void createUser(UserDto userDto);

    void updateUser(UserDto userDto);

    void deleteUser(UserDto userDto);

    List<CompanyDto> getCompany(String account);

    UserDto getPersonID(String email);

    void deleteCompanyUser(String deleteCompanyUser);
}
