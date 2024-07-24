package com.com4in.itsm.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SapTokenInfoDto {
    private String tokenUrl;
    private String clientId;
    private String userId;
    private String userName;
    private String privateKey;
    private String expireInMinutes;
    private String companyId;
    private String grantType;
}
