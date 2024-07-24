package com.com4in.itsm.controller;

import com.com4in.itsm.dto.FileDto;
import com.com4in.itsm.dto.IssueDto;
import com.com4in.itsm.dto.ResultDto;
import com.com4in.itsm.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/file")
@CrossOrigin("*")
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }


    @PostMapping("/upload")
    @ResponseBody
    public void FileUpload(MultipartFile uploadfile, Model model) {
        try {
            if(!uploadfile.isEmpty()) {
                uploadfile.transferTo(new File(uploadfile.getOriginalFilename()));
            }
        }
        catch (Exception e ) {
            e.printStackTrace();
        }
    }

    @PostMapping("/upload2")
    @ResponseBody
    public void FileUpload2(MultipartHttpServletRequest request) {
        try {
            log.info("/upload2");
            //request의 file이름을 가져온다.
            String fileName = "Mollang_"+request.getParameter("file");
            String test = request.getParameter("test");
            log.info(test);

            MultipartFile file = request.getFile("file");

            log.info(file.getOriginalFilename());
            file.transferTo(new File(file.getOriginalFilename()));
        }
        catch (Exception e) {

        }
    }

    @PostMapping("/upload3")
    @ResponseBody
    public ResponseEntity<ResultDto> FileUpload3(MultipartHttpServletRequest request) {
        FileDto fileDto = new FileDto();
        try {
            log.info("/upload3");
            //File 경로 임의 지정 192.168.0.65
//            String uploadPath = "http://211.171.152.242:8000/itsm_files/test";
            String uploadPath = "C:\\Shared\\"+request.getParameter("path");


            MultipartFile file = request.getFile("file");
            String saveTime = System.currentTimeMillis()+"_";
            File fileUpload = new File(uploadPath,saveTime+file.getOriginalFilename().replaceAll(" ", ""));
            //저장시 파일에 권한 설정
            Runtime.getRuntime().exec("icacls" + " " + fileUpload + " " + "/grant Everyone:F");

            //생성 될 경로가 없을 경우, 파일을 생성한다.
            if(!fileUpload.exists()) {
                log.info(uploadPath+" : 파일 경로 생성완료");
                fileUpload.mkdirs();
            }
            file.transferTo(fileUpload);
            fileDto.setFile_type(request.getMultiFileMap().getFirst("file").getContentType());
            fileDto.setFile_full_name(saveTime+request.getMultiFileMap().getFirst("file").getOriginalFilename().replaceAll(" ", ""));
            fileDto.setFile_size(Integer.toString((int) request.getMultiFileMap().getFirst("file").getSize()));
            fileDto.setFile_name((String) request.getMultiFileMap().getFirst("file").getOriginalFilename().replaceAll(" ", ""));
            fileDto.setFile_path(uploadPath);
            fileDto.setUuid(request.getParameter("uuid"));

            fileService.insertFile(fileDto);

//            ImageIO.read(new File("C:/Desktop/picture.jpeg"));
//            ImageIO.read(new File("D:/const/test/KakaoTalk_20230406_150808065.jpg"));
//            Image picture = ImageIO.read(new File("D:/const/test/KakaoTalk_20230406_150808065.jpg"));
//            System.out.println(picture);
            //insert service 호출
            //파일 정보 저장
            return new ResponseEntity<>(new ResultDto("200", "성공", fileDto.getFile_full_name()), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(new ResultDto("5000", e.getMessage(), null), HttpStatus.BAD_GATEWAY);
        }
    }

    @GetMapping("/download/{uuid}")
    public void download(HttpServletResponse response, @PathVariable String uuid) throws IOException {
        FileDto fileDto = new FileDto();
        fileDto = fileService.getFileDownload(uuid);
        String filePath = fileDto.getFile_path()+"\\"+fileDto.getFile_full_name();
//        String path = file;
        byte[] fileByte = FileUtils.readFileToByteArray(new File(filePath));

        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode("tistory.png", "UTF-8")+"\";");
        response.setHeader("Content-Transfer-Encoding", "binary");

        response.getOutputStream().write(fileByte);
        response.getOutputStream().flush();
        response.getOutputStream().close();
    }

    @GetMapping("/downloadInfoIncident/{uuid}")
    public ResponseEntity<ResultDto> downloadInfoIncident(@PathVariable String uuid) {
        return new ResponseEntity<>(new ResultDto("200", "성공", fileService.getIncidentFile(uuid)), HttpStatus.OK);
    }

    @GetMapping("/downloadInfoBoard/{uuid}")
    public ResponseEntity<ResultDto> downloadInfoBoard(@PathVariable String uuid) {
        return new ResponseEntity<>(new ResultDto("200", "성공", fileService.getBoardFile(uuid)), HttpStatus.OK);
    }

    @DeleteMapping("/deleteFile/{uuid}")
    public ResponseEntity<ResultDto> deleteFile(@PathVariable String uuid) {
        fileService.deleteFile(uuid);
        return new ResponseEntity<>(new ResultDto("200", "성공", null), HttpStatus.OK);
    }

}
