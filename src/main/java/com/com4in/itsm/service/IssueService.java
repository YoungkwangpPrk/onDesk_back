package com.com4in.itsm.service;

import com.com4in.itsm.dto.IssueDto;

import java.util.List;

public interface IssueService {
    void setIssueMain(IssueDto issueDto) throws Exception;
    List<IssueDto> getIssueList(String company_account);
    List<IssueDto> getIssueManagerList(String manager);
    List<IssueDto> getIssueInquirerList(String session_id);
    List<IssueDto> getItems(String pk);
    void updateIssue(IssueDto issueDto) throws Exception;
    void deleteIssue(String pk);
}