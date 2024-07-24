package com.com4in.itsm.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenDto {

    private String token;
    private Object userInfo;
}