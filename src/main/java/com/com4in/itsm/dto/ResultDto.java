package com.com4in.itsm.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResultDto {
    private String resultCode;
    private String message;
    private Object detail;
}
