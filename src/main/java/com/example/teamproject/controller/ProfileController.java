package com.example.teamproject.controller;

import com.example.teamproject.message.ResponseFile;
import com.example.teamproject.message.ResponseMessage;
import com.example.teamproject.model.Profile;
import com.example.teamproject.paging.Criteria;
import com.example.teamproject.service.ProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@CrossOrigin("http://localhost:8080")
//@CrossOrigin("http://192.168.0.166:8080")
// TODO : @CrossOrigin("http://192.168.0.166:8080")
@RequestMapping("/api")
public class ProfileController {

  @Autowired
  private ProfileService profileService;

  Logger logger = LoggerFactory.getLogger(this.getClass());




  @PostMapping("/uploadpf")
  public ResponseEntity<ResponseMessage> uploadFile(
          @RequestParam("username") String username,
          @RequestParam("usernick") String usernick,
          @RequestParam("file") MultipartFile file
  ) {
    String message = "";

    logger.info("username {} : ", username);
    logger.info("usernick {} : ", usernick);
    logger.info("file {} : ", file);

    try {
      profileService.store(username, usernick, file);

      message = "업로드 완료";
      return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
    } catch (Exception e) {
      message = "업로드 실패: " + file.getOriginalFilename() + "!";
      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
    }
  }




//  Todo:
  @GetMapping("/filespf")
  public ResponseEntity<List<ResponseFile>> getListFiles(Criteria criteria) {

    List<ResponseFile> files = profileService.getAllFiles(criteria).map(dbFile -> {
      // uri 만들기 : http://localhost:8080/files/1 => 1 은 db sequence 번호
      String fileDownloadUri = ServletUriComponentsBuilder
          .fromCurrentContextPath()
          .path("/api/filespf/")
          .path(dbFile.getId())
          .toUriString();

//      collect : stream 을 다시 다른 자료형으로 변환
//        아래는 stream => List 변환
      return new ResponseFile(
//        todo: dbFile getTitle(), getContent(), criteria.getPage(),criteria.getTotalItems(),
//               criteria.getTotalPages(),
              dbFile.getId(),
              dbFile.getName(),
              criteria.getPage(),
              criteria.getTotalItems(),
              criteria.getTotalPages(),
              fileDownloadUri,
              dbFile.getType(),
              dbFile.getData().length);
    }).collect(Collectors.toList());

    return ResponseEntity.status(HttpStatus.OK).body(files);
  }




  @GetMapping("/filespf/{id}")
  public ResponseEntity<byte[]> getFile(@PathVariable String id) {
    Profile profile = profileService.getFile(id);

    return ResponseEntity.ok()
//           Todo : attachment: => attachment;
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + profile.getName() + "\"")
        .body(profile.getData());
  }




  @PutMapping("/filespf/deletion/{id}")
  public ResponseEntity<HttpStatus>
  deleteFile(@PathVariable("id") Long id) {

    boolean bSuccess = profileService.deleteById(id);

    logger.info("bSuccess {}", bSuccess);

    try {
//			boolean bSuccess = tutorialService.deleteById(id);

      if(bSuccess == true) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }


}
