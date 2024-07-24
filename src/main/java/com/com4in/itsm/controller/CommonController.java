package com.com4in.itsm.controller;

import com.com4in.itsm.dto.ResultDto;
import com.com4in.itsm.service.CommonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/common")
@CrossOrigin("*")
public class CommonController {
    private final CommonService commonService;

    public CommonController(CommonService commonService){
        this.commonService=commonService;
    }

    @GetMapping("/getcode")
    public ResponseEntity<ResultDto> getCommonCode() {

        return new ResponseEntity<>(new ResultDto("200", "성공", commonService.getCommonCode()), HttpStatus.OK);
    }
}
