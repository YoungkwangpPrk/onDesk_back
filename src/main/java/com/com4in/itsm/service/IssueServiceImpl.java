package com.com4in.itsm.service;

import com.com4in.itsm.dto.IssueDto;
import com.com4in.itsm.mapper.IssueMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IssueServiceImpl implements IssueService {

    @Autowired
    private final IssueMapper issueMapper;

    @Autowired
    private final FileService fileService;

    @Override
    @Transactional
    public void setIssueMain(IssueDto issueDto)throws Exception {
        try {
            fileService.updateFile(issueDto.getFile_info());
            issueMapper.insertIssue(issueDto);
        }catch (Exception e ){
            throw e;
        }
    }

    public List<IssueDto> getIssueList(String company_account) {
        return issueMapper.getIssueList(company_account);
    }

    @Override
    public List<IssueDto> getIssueManagerList(String manager) {
        return issueMapper.getIssueManagerList(manager);
    }

    @Override
    public List<IssueDto> getIssueInquirerList(String session_id) {
        return issueMapper.getIssueInquirerList(session_id);
    }


    public List<IssueDto> getItems(String pk) {
        return issueMapper.getItems(pk);
    }

    @Override
    @Transactional
    public void updateIssue(IssueDto issueDto) throws Exception {
        try {
            fileService.updateFile(issueDto.getFile_info());
            issueMapper.updateIssue(issueDto);
        }catch (Exception e ){
            throw e;
        }
    }

    public void deleteIssue(String pk) {
        issueMapper.deleteIssue(pk);
    }
}