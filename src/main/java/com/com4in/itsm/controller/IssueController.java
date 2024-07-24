package com.com4in.itsm.controller;

import com.com4in.itsm.dto.IssueDto;
import com.com4in.itsm.dto.MailingDto;
import com.com4in.itsm.dto.ResultDto;
import com.com4in.itsm.mail.SendMail;
import com.com4in.itsm.service.IssueService;
import com.com4in.itsm.service.MailTemplateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.List;

@Api(value = "IssueController", tags = "이슈 관리")
@RestController
@RequestMapping("/api/issue")
@CrossOrigin("*")
public class IssueController {

    private final IssueService issueService;
    private final MailTemplateService mailTemplateService;

    private final SendMail sendmail;

//    private SendMail sendmail = new SendMail();

    public IssueController(IssueService issueService, SendMail sendmail, MailTemplateService mailTemplateService) {
        this.issueService = issueService;
        this.sendmail = sendmail;
        this.mailTemplateService = mailTemplateService;
    }
    @ApiOperation(value = "create 저장", notes ="이슈 등록 설정")
    @PostMapping("/create")
    public ResponseEntity<ResultDto> issuCreate(@RequestBody IssueDto issueDto) throws Exception {
        issueService.setIssueMain(issueDto);
        sendmail.sendEMail(issueDto);
        return new ResponseEntity<>(new ResultDto("200", "성공", null), HttpStatus.OK);
    }

    @GetMapping("/mainList/{company_account}")
    public ResponseEntity<ResultDto> getMainList(@PathVariable String company_account) {
        return new ResponseEntity<>(new ResultDto("200", "성공", issueService.getIssueList(company_account)), HttpStatus.OK);
    }

    @GetMapping("/managerList/{manager}")
    public ResponseEntity<ResultDto> getIssueManagerList(@PathVariable String manager) {
        return new ResponseEntity<>(new ResultDto("200", "성공", issueService.getIssueManagerList(manager)), HttpStatus.OK);
    }

    @GetMapping("/inquirerList/{session_id}")
    public ResponseEntity<ResultDto> getIssueInquirerList(@PathVariable String session_id) {
        return new ResponseEntity<>(new ResultDto("200", "성공", issueService.getIssueInquirerList(session_id)), HttpStatus.OK);
    }

    @GetMapping("/mainDetail/{id}")
    public ResponseEntity<ResultDto> getItems(@PathVariable String id) {
        return new ResponseEntity<>(new ResultDto("200", "성공", issueService.getItems(id)), HttpStatus.OK);
    }

    // 상태 변화 상관 없이 내용만 변경시
    @PatchMapping("/update")
    public ResponseEntity<ResultDto> issueUpdate(@RequestBody IssueDto issueDto) throws Exception {
        issueService.updateIssue(issueDto);
        return new ResponseEntity<>(new ResultDto("200", "성공", null), HttpStatus.OK);
    }

    //상태 변화에 따른 change는 호출
    @PostMapping("/updateStatus/{code}")
    public ResponseEntity<ResultDto> issueUpdateStatus(@RequestBody IssueDto issueDto, @PathVariable String code) throws Exception {
        issueService.updateIssue(issueDto);
        sendmail.sendEMail(issueDto);
        return new ResponseEntity<>(new ResultDto("200", "성공", null), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResultDto> issueDelete(@PathVariable String id) {
        issueService.deleteIssue(id);
        return new ResponseEntity<>(new ResultDto("200", "성공", null), HttpStatus.OK);
    }

    @PostMapping("/mailTest")
    public ResponseEntity<ResultDto> mailTest(@RequestBody MailingDto mailingDto) throws Exception {
        Document doc = Jsoup.parseBodyFragment(mailingDto.getTest_content());
//        Document doc1 = Jsoup.parseBodyFragment(mailingDto.getTest_name());

        Element body = doc.body();
//        Element body1 = doc1.body();
//        System.out.println("test11111  :  :   "+body1);
        System.out.println("test  :  :   "+body);

        List<Element> tr = doc.select("tr");

        String title = new String();
        String contentStr = new String();
        String imgStr = new String();

        // 결과 값 출력
        for(Element element : tr) {
            List<Element> td = element.select("td");

//            String str = element.getElementsByClass("MsoNormal").text();
            int i=0;
            for(Element testTd : td) {
                List<Element> p = testTd.select("p");
                System.out.println("size   :: ::  "+p.size());
                String tt = new String();
                if(p.size() > 1){
                    for(Element testp : p) {
                        tt += testp.text()+"</br>";
                    }
                }
                System.out.println("ttt :: ::  ::  "+ tt);
                i+=1;
                System.out.println("");
                System.out.println(i+ "  ::  original:::  "+testTd);



                System.out.println(i+ "  ::  text:::  "+testTd.text());
                System.out.println("");
            }

//            if(element.getElementsByTag("Img").size() > 0){
//                System.out.println("11111111111111111111111111111");
//                imgStr = element.getElementsByTag("Img").attr("src");
//            }

//            System.out.println("::::  :::   "+element.getElementsByTag("Img"));
//
//            System.out.println(":::::  ::::   "+element.getElementsByTag("Img").attr("src"));
//
//            element.getElementsByTag("Img").attr("src", "asdklfjaslkdfjalksjdflkajslkdfjlkasdfjalksdjf");
//
//            System.out.println(":::::::  ::::::   "+element.getElementsByTag("Img").attr("src"));
//            System.out.println(test);
        }

//        System.out.println("1::::        "+contentStr);
//        System.out.println("2::::        " + imgStr);


//        mailTemplateService.getMailToDb(mailingDto);
        return new ResponseEntity<>(new ResultDto("200", "성공", null), HttpStatus.OK);
    }
}
