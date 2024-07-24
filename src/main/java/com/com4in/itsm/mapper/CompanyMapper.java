package com.com4in.itsm.mapper;

import com.com4in.itsm.dto.CommentDto;
import com.com4in.itsm.dto.CompanyDto;
import com.com4in.itsm.dto.IssueDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CompanyMapper {
    //회사 생성
    void createCompany(CompanyDto companyDto);

    //회사 조회
    List<CompanyDto> getCompany(String company_account);

    //회사List 조회
    List<CompanyDto> getCompanyList();

    void updateCompany(CompanyDto companyDto);

    void deleteCompany(String company_account);
}
