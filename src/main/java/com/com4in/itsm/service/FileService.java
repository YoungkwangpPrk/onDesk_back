package com.com4in.itsm.service;

import com.com4in.itsm.dto.FileDto;

import java.util.List;

public interface FileService {

    void insertFile(FileDto fileDto);
    void updateFile(List<FileDto> fileDto);
    void deleteFile(String uuid);
    List<FileDto> getBoardFile(String uuid);
    List<FileDto> getIncidentFile(String uuid);
    FileDto getFileDownload(String uuid);
}
