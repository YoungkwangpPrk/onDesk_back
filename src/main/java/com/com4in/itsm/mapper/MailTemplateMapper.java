package com.com4in.itsm.mapper;

import com.com4in.itsm.dto.MailTemplateDto;
import com.com4in.itsm.dto.MailingDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MailTemplateMapper {

    void insertMailTemplate(MailTemplateDto mailTemplateDto);

    void updateMailTemplateDetail(MailTemplateDto mailTemplateDto);

    List<MailTemplateDto> getMailTemplateList();

    List<MailTemplateDto> getMailTemplateDetail(String uuid);

    MailTemplateDto getMailTemplateStatus(String status_code);

    void getMailToDB(MailingDto mailingDto);

}


