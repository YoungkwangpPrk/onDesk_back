package com.com4in.itsm.mapper;


import com.com4in.itsm.dto.DashBoardDto;
import com.com4in.itsm.dto.IssueDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DashBoardMapper {

    List<DashBoardDto> getModuleCount(String company_account);
    List<DashBoardDto> getStatusCount(String company_account);

}
