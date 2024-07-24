package com.com4in.itsm.controller;

import com.com4in.itsm.dto.MailTemplateDto;
import com.com4in.itsm.dto.ResultDto;
import com.com4in.itsm.service.MailTemplateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mail")
@CrossOrigin("*")
public class MailTemplateController {
    private final MailTemplateService mailTemplateService;

    public MailTemplateController(MailTemplateService mailTemplateService) {
        this.mailTemplateService = mailTemplateService;
    }

    @PostMapping("/createMailTemplate")
    public ResponseEntity<ResultDto> mailTemplate(@RequestBody MailTemplateDto mailTemplateDto) {
        mailTemplateService.insertMailTemplate(mailTemplateDto);
        return new ResponseEntity<>(new ResultDto("200", "성공", null), HttpStatus.OK);
    }

    @PatchMapping("/updateMailTemplate")
    public ResponseEntity<ResultDto> updateCompany(@RequestBody MailTemplateDto mailTemplateDto) {
        mailTemplateService.updateMailTemplateDetail(mailTemplateDto);
        return new ResponseEntity<>(new ResultDto("200", "성공", null), HttpStatus.OK);
    }

    @GetMapping("/getMailTemplateDetail/{uuid}")
    public ResponseEntity<ResultDto> getMailTemplateDetail(@PathVariable String uuid) {

        return new ResponseEntity<>(new ResultDto("200", "성공", mailTemplateService.getMailTemplateDetail(uuid)), HttpStatus.OK);
    }

    @GetMapping("/getMailTemplateList")
    public ResponseEntity<ResultDto> getMailTemplateDetail() {

        return new ResponseEntity<>(new ResultDto("200", "성공", mailTemplateService.getMailTemplateList()), HttpStatus.OK);
    }
}
