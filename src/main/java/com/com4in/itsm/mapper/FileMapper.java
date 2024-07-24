package com.com4in.itsm.mapper;

import com.com4in.itsm.dto.FileDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface FileMapper {
    void insertFile(FileDto fileDto);
    void updateFile(FileDto fileDto);
    List<FileDto> getIncidentFile(String uuid);
    List<FileDto> getBoardFile(String uuid);
    FileDto getFileDownload(String uuid);
    void deleteFile(String uuid);
}
