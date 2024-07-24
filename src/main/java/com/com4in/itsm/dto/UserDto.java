package com.com4in.itsm.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String uuid; //고유키
    private String status;//crud 상태
    private String name; //성명
    private String email; //이메일
    private String password; //비밀번호
    private String leader; //리더 여부
    private String company_account; //회사계정
    private String company_name; //회사계정
    private String role; //역할
    private String create_id; //생성자
    private String create_date; //생성일
    private String update_id; //수정자
    private String update_date; //수정일
    private String user_id;
    private String session_id;
    private String comforin_manager;
}
