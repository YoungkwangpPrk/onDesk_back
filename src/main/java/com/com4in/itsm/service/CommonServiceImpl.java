package com.com4in.itsm.service;

import com.com4in.itsm.dto.CommonCodeDto;
import com.com4in.itsm.mapper.CommonMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommonServiceImpl implements CommonService {
    private final CommonMapper commonMapper;

    @Override
    public List<CommonCodeDto> getCommonCode() {
        return commonMapper.getCommonCode();
    }
}
