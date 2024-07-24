package com.com4in.itsm.service;

import com.com4in.itsm.dto.CommentDto;
import com.com4in.itsm.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService  {

    @Autowired
    private final CommentMapper commentMapper;


    public void createIssueComent(CommentDto commentDto) {
        commentMapper.createIssueComent(commentDto);
    }

    @Override
    public List<CommentDto> getComment(String incident_uuid) {
        return commentMapper.getComment(incident_uuid);
    }

    public void updateComent(CommentDto commentDto) {
        commentMapper.updateComment(commentDto);
    }

    public void deleteComent(String uuid) {
        commentMapper.deleteComment(uuid);
    }
}
