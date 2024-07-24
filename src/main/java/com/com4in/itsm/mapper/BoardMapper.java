package com.com4in.itsm.mapper;


import com.com4in.itsm.dto.BoardDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BoardMapper {

    List<BoardDto> getBoardList();

    List<BoardDto> getBoardListCom4in();

    List<BoardDto> getBoardDetail(String uuid);

    void insertBoard(BoardDto boardDto);

    void updateBoard(BoardDto boardDto);

    void deleteBoard(String uuid);

}