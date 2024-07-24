package com.com4in.itsm.service;

import com.com4in.itsm.dto.DashBoardDto;

import java.util.List;

public interface DashBoardService {

    public List<DashBoardDto> getModuleCount(String company_account);

    public List<DashBoardDto> getStatusCount(String company_account);

}
