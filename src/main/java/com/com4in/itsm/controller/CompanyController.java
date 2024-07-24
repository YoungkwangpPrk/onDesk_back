package com.com4in.itsm.controller;

import com.com4in.itsm.dto.CompanyDto;
import com.com4in.itsm.dto.ResultDto;
import com.com4in.itsm.service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/company")
@CrossOrigin("*")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping("/create")
    public ResponseEntity<ResultDto> companyCreate(@RequestBody CompanyDto companyDto) {
        companyService.createCompany(companyDto);
        return new ResponseEntity<>(new ResultDto("200", "성공", null), HttpStatus.OK);
    }

    @GetMapping("/getCompanyList")
    public ResponseEntity<ResultDto> getComment() {
        return new ResponseEntity<>(new ResultDto("200", "성공", companyService.getCompanyList()), HttpStatus.OK);
    }

    @GetMapping("/getCompany/{company_account}")
    public ResponseEntity<ResultDto> getComment(@PathVariable String company_account) {

        return new ResponseEntity<>(new ResultDto("200", "성공", companyService.getCompany(company_account)), HttpStatus.OK);
    }

    @PatchMapping("/updateCompany")
    public ResponseEntity<ResultDto> updateCompany(@RequestBody CompanyDto companyDto) {
        companyService.updateCompany(companyDto);
        return new ResponseEntity<>(new ResultDto("200", "성공", null), HttpStatus.OK);
    }

    @DeleteMapping("/deleteCompany/{company_account}")
    public ResponseEntity<ResultDto> deleteCompany(@PathVariable String company_account) {
        companyService.deleteCompany(company_account);
        return new ResponseEntity<>(new ResultDto("200", "성공", null), HttpStatus.OK);
    }
}
