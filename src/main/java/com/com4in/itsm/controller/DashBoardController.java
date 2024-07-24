package com.com4in.itsm.controller;

import com.com4in.itsm.dto.ResultDto;
import com.com4in.itsm.service.DashBoardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin("*")
public class DashBoardController {
    private final DashBoardService dashBoardService;

    public DashBoardController(DashBoardService dashBoardService) {
        this.dashBoardService = dashBoardService;
    }

    @GetMapping("/status_count/{company_account}")
    public ResponseEntity<ResultDto> statusCount(@PathVariable String company_account) {

        return new ResponseEntity<>(new ResultDto("200", "성공", dashBoardService.getStatusCount(company_account)), HttpStatus.OK);
    }
    @GetMapping("/module_count/{company_account}")
    public ResponseEntity<ResultDto> moduleCount(@PathVariable String company_account) {

        return new ResponseEntity<>(new ResultDto("200", "성공", dashBoardService.getModuleCount(company_account)), HttpStatus.OK);
    }
}

