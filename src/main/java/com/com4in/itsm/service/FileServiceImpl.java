package com.com4in.itsm.service;

import com.com4in.itsm.dto.FileDto;
import com.com4in.itsm.mapper.FileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    @Autowired
    private final FileMapper fileMapper;

    @Override
    public void insertFile(FileDto fileDto) {
        fileMapper.insertFile(fileDto);
    }

    @Override
    public void updateFile(List<FileDto> fileDto) {
        for (FileDto updateFileDto: fileDto) {
            fileMapper.updateFile(updateFileDto);
        }
    }

    @Override
    public void deleteFile(String uuid) {
        fileMapper.deleteFile(uuid);
    }

    @Override
    public List<FileDto> getBoardFile(String uuid) {
        return fileMapper.getBoardFile(uuid);
    }

    @Override
    public List<FileDto> getIncidentFile(String uuid) {
        return fileMapper.getIncidentFile(uuid);
    }

    @Override
    public FileDto getFileDownload(String uuid) {
        return fileMapper.getFileDownload(uuid);
    }
}
