package com.com4in.itsm.mapper;

import com.com4in.itsm.dto.SapTokenInfoDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SapTokenInfoMapper {
    SapTokenInfoDto getSapTokenInfo(String companyId);
}
