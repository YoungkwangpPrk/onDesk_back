package com.com4in.itsm.service;

import com.com4in.itsm.dto.BoardDto;
import com.com4in.itsm.dto.FileDto;

import java.util.List;

public interface BoardService {

    public List<BoardDto> getBoardList();

    public List<BoardDto> getBoardListCom4in();

    public List<BoardDto> getBoardDetail(String uuid);

    public void insertBoard(BoardDto boardDto);

    public void updateBoard(BoardDto boardDto);

    public void deleteBoard(String uuid);
}
