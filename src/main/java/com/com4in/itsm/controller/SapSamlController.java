package com.com4in.itsm.controller;

import com.com4in.itsm.dto.ResultDto;
import com.com4in.itsm.dto.SapTokenInfoDto;
import com.com4in.itsm.saptoken.SAMLAssertionGen;
import com.com4in.itsm.service.SapTokenInfoService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/saml")
@CrossOrigin("*")
public class SapSamlController {

    private final SapTokenInfoService sapTokenInfoService;

    private SAMLAssertionGen gen= new SAMLAssertionGen();

    public SapSamlController(SapTokenInfoService sapTokenInfoService) {
        this.sapTokenInfoService = sapTokenInfoService;
    }

    @RequestMapping(value = "/getSapUserAll", method = RequestMethod.GET)
    public ResponseEntity<ResultDto> getSapUserAll() throws Exception{
        String Company=null;

        Object getsaptoken=getToken(Company);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Object> response = null;
        String formattedToken = getsaptoken.toString().replace("\"", " ").replaceAll("\\s+","");
        System.out.println(formattedToken);

        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            httpHeaders.add("Authorization", "Bearer " + formattedToken);

            HttpEntity<String> request = new HttpEntity<>(null, httpHeaders);
//            String access_token_url = "https://api44preview.sapsf.com/odata/v2/User('"+userId+"')?$select=email,userId,displayName&$format=json";
            String access_token_url = "https://api44preview.sapsf.com/odata/v2/User?$select=email,userId,displayName,lastName,firstName&$format=json";
//            access_token_url += "?$format=json";
//            access_token_url += "?$top=5";
//            access_token_url += "&$format=json";

            response = restTemplate.exchange(access_token_url, HttpMethod.GET, request, Object.class);

        }catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(new ResultDto("200", "성공", response.getBody()), HttpStatus.OK);
    }

    @RequestMapping(value = "/getSapUser/{userId}/{companyId}", method = RequestMethod.GET)
    public ResponseEntity<ResultDto> getSapUser(@PathVariable String userId, @PathVariable String companyId) throws Exception{

        Object getsaptoken=getToken(companyId);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Object> response = null;
        String formattedToken = getsaptoken.toString().replace("\"", " ").replaceAll("\\s+","");

        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            httpHeaders.add("Authorization", "Bearer " + formattedToken);

            HttpEntity<String> request = new HttpEntity<>(null, httpHeaders);
            SapTokenInfoDto companyTokenInfo = getCompanyInfo(companyId);

            String odata_url=companyTokenInfo.getTokenUrl().split("/oauth")[0];
            System.out.println("access_token_url   : ;: :    "+ odata_url);

            String access_token_url = odata_url+"/odata/v2/User('"+userId+"')?$select=email,userId,displayName&$format=json";

            response = restTemplate.exchange(access_token_url, HttpMethod.GET, request, Object.class);

        }catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(new ResultDto("200", "성공", response.getBody()), HttpStatus.OK);
    }

    public Object getToken(String companyId) {
        SapTokenInfoDto companyTokenInfo = getCompanyInfo(companyId);

        String tokenUrl = companyTokenInfo.getTokenUrl();
        String clientId = companyTokenInfo.getClientId();
        String userId = companyTokenInfo.getUserId();
        String username = companyTokenInfo.getUserName();
        String privateKey = companyTokenInfo.getPrivateKey();
        String grantType = companyTokenInfo.getGrantType();
        Integer expireInMinutes = Integer.parseInt(companyTokenInfo.getExpireInMinutes());

        String assertion = gen.getGenStart(tokenUrl, clientId, userId, username, privateKey, expireInMinutes);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("company_id", companyId);
        map.add("client_id", clientId);
        map.add("grant_type", grantType);
        map.add("assertion", assertion);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> requestBodyFormUrlEncoded = new HttpEntity<>(map, headers);
        ResponseEntity<JsonNode> responseEntity = null;
        RestTemplate restTemplate = new RestTemplate();
        Object tokenInfo = null;
        try {
            responseEntity = restTemplate.postForEntity(tokenUrl, requestBodyFormUrlEncoded, JsonNode.class);
            JsonNode json = responseEntity.getBody();
            tokenInfo = json.findValue("access_token");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tokenInfo;
    }

    public SapTokenInfoDto getCompanyInfo(String companyId) {
        SapTokenInfoDto companyTokenInfo = sapTokenInfoService.getSapTokenInfo(companyId);

        return companyTokenInfo;

    }

}












