package com.com4in.itsm.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IssueDto {
    private String uuid;
    private String title;
    private String company_account;
    private String company_name;
    private String req_level;
    private String inquirer;
    private String manager;
    private String manager_name;
    private String status;
    private String comp_req_date;
    private String comp_date;
    private String module;
    private String create_id;
    private String create_date;
    private String update_id;
    private String req_type;
    private String main_content;
    private String status_code;
    private String inquirer_email;
    private String time_taken;
    private String session_id;
    private String result_content;
    private String sap_incident_number;
    private List<FileDto> file_info;

}

