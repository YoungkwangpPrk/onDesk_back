package com.com4in.itsm.controller;

import com.com4in.itsm.dto.ResultDto;
import com.com4in.itsm.dto.TokenDto;
import com.com4in.itsm.dto.UserDto;
import com.com4in.itsm.jwt.JwtFilter;
import com.com4in.itsm.jwt.TokenProvider;
import com.com4in.itsm.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin("*")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider tokenProvider;

    public UserController(UserService userService, PasswordEncoder passwordEncoder, AuthenticationManagerBuilder authenticationManagerBuilder, TokenProvider tokenProvider) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.tokenProvider = tokenProvider;
    }

    @PostMapping("/create")
    public ResponseEntity<ResultDto> createUser(@RequestBody List<UserDto> userDto) {
//        PersonsDto personsDto = new PersonsDto();
        List<String> errUser = null;

        for(int i=0; i < userDto.size(); i++){

            if(userDto.get(i).getStatus().equals("create")){
                if(userDto.get(i).getPassword().equals("com4in!!")){
                    userDto.get(i).setPassword(passwordEncoder.encode(userDto.get(i).getPassword()));
                }
                userService.createUser(userDto.get(i));
                // create service 호출
            } else if(userDto.get(i).getStatus().equals("edit")){
                // update service 호출
                userService.updateUser(userDto.get(i));
            } else if(userDto.get(i).getStatus().equals("delete")){
                // delete service 호출
                userService.deleteUser(userDto.get(i));
            }
        }
        return new ResponseEntity<>(new ResultDto("200", "성공", null), HttpStatus.OK);

    }

    @GetMapping("/read/{company_account}")
    public ResponseEntity<ResultDto> readUser(@PathVariable String company_account) {
        return new ResponseEntity<>(new ResultDto("200", "성공", userService.getUserList(company_account)), HttpStatus.OK);
    }

    @PostMapping("/changePassword")
    public ResponseEntity<ResultDto> changePassword(@RequestBody UserDto userDto) {
        if(!userDto.getPassword().equals("Init@1234")){
            userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        }
        userService.updateUser(userDto);
        return new ResponseEntity<>(new ResultDto("200", "성공", null), HttpStatus.OK);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<ResultDto> authorize(@RequestBody HashMap<String, Object> loginDto) throws Exception {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.get("email"), loginDto.get("password"));
        UserDto selectUser = new UserDto();

        selectUser = userService.getPersons(loginDto.get("email").toString());
        if(loginDto.get("password").equals("Init@1234")){
            // 서비스에서 초기 패스워드 같으면
            String userPassword = selectUser.getPassword();
            if(userPassword.equals(loginDto.get("password"))){
                return new ResponseEntity<>(new ResultDto("310", "초기비밀번호 입니다. 변경 요청드립니다.", selectUser), HttpStatus.OK);
            }else {
                return new ResponseEntity<>(new ResultDto("320", "비밀 번호를 확인하세요.", null), HttpStatus.OK);
            }

        }else{

            try {

                Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
                SecurityContextHolder.getContext().setAuthentication(authentication);

                String jwt = tokenProvider.createToken(authentication);

                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

//                List<CompanyDto> selectCompany = userService.getCompany(selectUser.getCompany_account().toString());
//                String companyName = selectCompany.get(0).getCompany_account();

                // 회사명 조회 후 추가 회사명 select
                return new ResponseEntity<>(new ResultDto("200", "성공", new TokenDto(jwt, selectUser)), httpHeaders, HttpStatus.OK);
            } catch(AuthenticationException e) {
                System.out.println("Authentication failed: " + e.getMessage());
                return new ResponseEntity<>(new ResultDto("330", "로그인 정보가 불일치 합니다.", null), HttpStatus.BAD_REQUEST);
            }
        }
    }
}
