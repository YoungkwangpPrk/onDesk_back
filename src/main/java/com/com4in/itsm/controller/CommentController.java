package com.com4in.itsm.controller;

import com.com4in.itsm.dto.CommentDto;
import com.com4in.itsm.dto.ResultDto;
import com.com4in.itsm.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
@CrossOrigin("*")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService){
        this.commentService=commentService;
    }

    @PostMapping("/create")
    public ResponseEntity<ResultDto> issuCreate(@RequestBody CommentDto commentDto) {
        commentService.createIssueComent(commentDto);
        return new ResponseEntity<>(new ResultDto("200", "성공", null), HttpStatus.OK);
    }

    @GetMapping("/getComment/{incident_uuid}")
    public ResponseEntity<ResultDto> getComment(@PathVariable String incident_uuid) {

        return new ResponseEntity<>(new ResultDto("200", "성공", commentService.getComment(incident_uuid)), HttpStatus.OK);
    }

    @PatchMapping("/updateComment")
    public ResponseEntity<ResultDto> updateComment(@RequestBody CommentDto commentDto) {
        commentService.updateComent(commentDto);
        return new ResponseEntity<>(new ResultDto("200", "성공", null), HttpStatus.OK);
    }

    @DeleteMapping("/deleteComment/{uuid}")
    public ResponseEntity<ResultDto> deleteComment(@PathVariable String uuid) {
        commentService.deleteComent(uuid);
        return new ResponseEntity<>(new ResultDto("200", "성공", null), HttpStatus.OK);
    }
}
