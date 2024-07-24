package com.com4in.itsm.service;

import com.com4in.itsm.dto.CommentDto;

import java.util.List;

public interface CommentService {

    public void createIssueComent(CommentDto commentDto);

    public List<CommentDto> getComment(String incident_uuid);

    public void updateComent(CommentDto commentDto);

    public void deleteComent(String uuid);
}
