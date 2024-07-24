package com.com4in.itsm.service;

import com.com4in.itsm.dto.MailTemplateDto;
import com.com4in.itsm.dto.MailingDto;

import java.util.List;

public interface MailTemplateService {
    void insertMailTemplate(MailTemplateDto mailTemplateDto);
    void updateMailTemplateDetail(MailTemplateDto mailTemplateDto);
    List<MailTemplateDto> getMailTemplateList();
    List<MailTemplateDto> getMailTemplateDetail(String uuid);

    MailTemplateDto getMailTemplateStatus(String status_code);

    void getMailToDb(MailingDto mailingDto);
}
