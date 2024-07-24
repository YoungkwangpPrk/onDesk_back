package com.com4in.itsm.service;

import com.com4in.itsm.dto.DashBoardDto;
import com.com4in.itsm.mapper.DashBoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DashBoardServiceImpl implements DashBoardService {
    private final DashBoardMapper dashBoardMapper;


    @Override
    public List<DashBoardDto> getModuleCount(String company_account) {
        return dashBoardMapper.getModuleCount(company_account);
    }

    @Override
    public List<DashBoardDto> getStatusCount(String company_account) {
        return dashBoardMapper.getStatusCount(company_account);
    }
}
