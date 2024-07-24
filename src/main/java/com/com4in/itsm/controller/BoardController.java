package com.com4in.itsm.controller;

import com.com4in.itsm.dto.BoardDto;
import com.com4in.itsm.dto.ResultDto;
import com.com4in.itsm.service.BoardService;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.LinkedHashMap;

@Api(value = "BoardController", tags = "게시판 - 게시판 정보 조회, 수정 설정")
@RestController
@RequestMapping("/api/board")
@CrossOrigin("*")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping("/create")
    public ResponseEntity<ResultDto> boradCreate(@RequestBody BoardDto boardDto) {
        boardService.insertBoard(boardDto);
        return new ResponseEntity<>(new ResultDto("200", "성공", null), HttpStatus.OK);
    }

    @GetMapping("/selectList")
    public ResponseEntity<ResultDto> getMainList() {
        return new ResponseEntity<>(new ResultDto("200", "성공", boardService.getBoardList()), HttpStatus.OK);
    }

    @GetMapping("/selectListCom4in")
    public ResponseEntity<ResultDto> getMainListCom4in() {
        return new ResponseEntity<>(new ResultDto("200", "성공", boardService.getBoardListCom4in()), HttpStatus.OK);
    }

    @GetMapping("/selectDetail/{uuid}")
    public ResponseEntity<ResultDto> getItems(@PathVariable String uuid) {
        return new ResponseEntity<>(new ResultDto("200", "성공", boardService.getBoardDetail(uuid)), HttpStatus.OK);
    }

    @PatchMapping("/update/{uuid}")
    public ResponseEntity<ResultDto> issueUpdate(@RequestBody BoardDto boardDto) {
        boardService.updateBoard(boardDto);
        return new ResponseEntity<>(new ResultDto("200", "성공", null), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{uuid}")
    public ResponseEntity<ResultDto> issueDelete(@PathVariable String uuid) {
        boardService.deleteBoard(uuid);
        return new ResponseEntity<>(new ResultDto("200", "성공", null), HttpStatus.OK);
    }
}
