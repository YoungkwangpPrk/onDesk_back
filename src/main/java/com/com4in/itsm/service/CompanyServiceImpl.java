package com.com4in.itsm.service;

import com.com4in.itsm.dto.CompanyDto;
import com.com4in.itsm.mapper.CompanyMapper;
import com.com4in.itsm.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService  {

    @Autowired
    private final CompanyMapper companyMapper;
    private final UserMapper userMapper;

    @Override
    public void createCompany(CompanyDto companyDto) {
        companyMapper.createCompany(companyDto);
    }

    @Override
    public List<CompanyDto> getCompany(String company_account) {
        return companyMapper.getCompany(company_account);
    }

    @Override
    public List<CompanyDto> getCompanyList() {
        return companyMapper.getCompanyList();
    }

    @Override
    public void updateCompany(CompanyDto companyDto) {
        companyMapper.updateCompany(companyDto);
    }

    @Override
    public void deleteCompany(String company_account) {
        userMapper.deleteCompanyUser(company_account);
        companyMapper.deleteCompany(company_account);
    }


}
