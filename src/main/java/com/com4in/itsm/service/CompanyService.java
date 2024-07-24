package com.com4in.itsm.service;

import com.com4in.itsm.dto.CompanyDto;

import java.util.List;

public interface CompanyService {
    void createCompany(CompanyDto companyDto);

    List<CompanyDto> getCompany(String company_account);

    List<CompanyDto> getCompanyList();

    void updateCompany(CompanyDto companyDto);

    void deleteCompany(String company_account);
}
