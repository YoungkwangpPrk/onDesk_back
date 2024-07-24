package com.com4in.itsm.service;

import com.com4in.itsm.dto.BoardDto;
import com.com4in.itsm.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService  {

    @Autowired
    private final BoardMapper boardMapper;

    @Autowired
    private final FileService fileService;

    @Override
    public List<BoardDto> getBoardList() {
        return boardMapper.getBoardList();
    }

    @Override
    public List<BoardDto> getBoardListCom4in() {
        return boardMapper.getBoardListCom4in();
    }

    @Override
    public List<BoardDto> getBoardDetail(String uuid) {
        return boardMapper.getBoardDetail(uuid);
    }

    @Override
    @Transactional
    public void insertBoard(BoardDto boardDto) {
        try {
            fileService.updateFile(boardDto.getFile_info());
            boardMapper.insertBoard(boardDto);
        }catch (Exception e ){
            throw e;
        }
    }

    @Override
    @Transactional
    public void updateBoard(BoardDto boardDto) {
        try {
            fileService.updateFile(boardDto.getFile_info());
            boardMapper.updateBoard(boardDto);
        }catch (Exception e ){
            throw e;
        }
    }

    @Override
    public void deleteBoard(String uuid) {
        boardMapper.deleteBoard(uuid);
    }

}
