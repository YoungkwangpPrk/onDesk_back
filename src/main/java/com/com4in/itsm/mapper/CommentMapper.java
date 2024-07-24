package com.com4in.itsm.mapper;

import com.com4in.itsm.dto.CommentDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentMapper {
    //코멘트 생성
    void createIssueComent(CommentDto commentDto);
    //코멘트 조회
    List<CommentDto> getComment(String incident_uuid);
    //코멘트 수정
    void updateComment(CommentDto commentDto);
    //코멘트 삭제
    void deleteComment(String uuid);
}
