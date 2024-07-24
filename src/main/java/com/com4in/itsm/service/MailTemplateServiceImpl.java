package com.com4in.itsm.service;

import com.com4in.itsm.dto.MailTemplateDto;
import com.com4in.itsm.dto.MailingDto;
import com.com4in.itsm.mapper.MailTemplateMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MailTemplateServiceImpl implements MailTemplateService{

    @Autowired
    private final MailTemplateMapper mailTemplateMapper;

    @Override
    public void insertMailTemplate(MailTemplateDto mailTemplateDto) {
        mailTemplateMapper.insertMailTemplate(mailTemplateDto);
    }

    @Override
    public void updateMailTemplateDetail(MailTemplateDto mailTemplateDto) {
        mailTemplateMapper.updateMailTemplateDetail(mailTemplateDto);
    }

    @Override
    public List<MailTemplateDto> getMailTemplateList() {
        return mailTemplateMapper.getMailTemplateList();
    }

    @Override
    public List<MailTemplateDto> getMailTemplateDetail(String uuid) {
        return mailTemplateMapper.getMailTemplateDetail(uuid);
    }

    @Override
    public MailTemplateDto getMailTemplateStatus(String status_code) {
        return mailTemplateMapper.getMailTemplateStatus(status_code);
    }

    @Override
    public void getMailToDb(MailingDto mailingDto) {
        String s = mailingDto.getTest_content();

        System.out.println(s.split("image001"));


        mailTemplateMapper.getMailToDB(mailingDto);
    }
}
