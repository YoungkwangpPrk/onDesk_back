package com.com4in.itsm.mapper;

import com.com4in.itsm.dto.IssueDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface IssueMapper {

    List<IssueDto> getIssueList(String company_account);

    List<IssueDto> getIssueManagerList(String manager);

    List<IssueDto> getIssueInquirerList(String session_id);

    List<IssueDto> getItems(String pk);

    void insertIssue(IssueDto issueDto);

    void updateIssue(IssueDto issueDto);

    void deleteIssue(String pk);

}