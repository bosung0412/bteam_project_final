package kr.co.bteam.bteam_pro.controller;

import java.io.FileOutputStream;
import java.net.URLDecoder;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import kr.co.bteam.bteam_pro.dto.AndUploadDTO;
import kr.co.bteam.bteam_pro.service.AppService;
@RestController
public class UploadappController {
    @Autowired
    private AppService service;
    
    private final static String imageDirectory = Paths.get("").toAbsolutePath()
            .resolve("back/bteam_pro/src/main/resources/static/images").toString() + "/";

    @PostMapping(value = "/Andupload", produces = "application/json; charset=UTF-8")
    public ResponseEntity<?> handleFileUpload(@RequestParam("title") String title,
                                              @RequestParam("author") String author,
                                              @RequestParam("contents") String contents,
                                              @RequestParam("files") List<MultipartFile> files) {

        System.out.println("Code");
        String imgName = null;
        for (MultipartFile multipartFile : files) {
            String originalFileName = multipartFile.getOriginalFilename();
            String extension = getExtension(originalFileName);
            imgName = UUID.randomUUID().toString() + extension;
            String filePath = imageDirectory + imgName;
            try (FileOutputStream writer = new FileOutputStream(filePath)) {
                writer.write(multipartFile.getBytes());
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Fail to upload files.");
            }
        }

        // URL 디코딩 수행
        try {
            title = URLDecoder.decode(title, "UTF-8");
            author = URLDecoder.decode(author, "UTF-8");
            contents = URLDecoder.decode(contents, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Name: " + title);
        System.out.println("Author: " + author);
        System.out.println("Contents: " + contents);

        AndUploadDTO dto = new AndUploadDTO();
        dto.setTitle(title);
        dto.setAuthor(author);
        dto.setContents(contents);
        dto.setImgName(imgName);

        service.addappImg(dto);

        System.out.println("ImgName: " + imgName);
        return ResponseEntity.ok().body(dto);
    }

    private String getExtension(String fileName) {
        int index = fileName.lastIndexOf(".");
        if (index > -1) {
            return fileName.substring(index);
        }
        return "";
    }
}
