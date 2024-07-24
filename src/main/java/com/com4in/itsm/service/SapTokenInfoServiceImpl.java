package com.com4in.itsm.service;

import com.com4in.itsm.dto.SapTokenInfoDto;
import com.com4in.itsm.mapper.SapTokenInfoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SapTokenInfoServiceImpl implements SapTokenInfoService{

    private final SapTokenInfoMapper sapTokenInfoMapper;

    @Override
    public SapTokenInfoDto getSapTokenInfo(String companyId) {
        return sapTokenInfoMapper.getSapTokenInfo(companyId);
    }
}
