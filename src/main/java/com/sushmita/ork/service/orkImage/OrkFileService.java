package com.sushmita.ork.service.orkImage;

import com.sushmita.ork.constants.OrkConstants;
import com.sushmita.ork.entity.OrkFile;
import com.sushmita.ork.enums.Image;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * @author Sushmita Budhathoki on 2024-12-31
 */

@Service
public class OrkFileService {

    private OrkFileRepository orkFileRepository;

    public OrkFileService(OrkFileRepository orkFileRepository) {
        this.orkFileRepository = orkFileRepository;
    }

    public OrkFile uploadFile(MultipartFile multipartFile, Image imageType, String folder, String uniqueKey) throws IOException {

        // TODO
//        //application
//        folder = FOLDER_PATH + imageType + orkUserId + applicationId;

       String filePath = folder + multipartFile.getOriginalFilename();

        //creating OrkFile object with required data that corresponds to file that will be saved
       OrkFile fileToSave = OrkFile.builder()
               .name(uniqueKey + multipartFile.getOriginalFilename())
               .type(multipartFile.getContentType())
               .fileType(imageType)
               .filePath(filePath)
               .build();

       OrkFile savedFile = orkFileRepository.save(fileToSave);

       try {
           //Saving file in the device
           multipartFile.transferTo(new File(filePath));
       } catch (Exception e) {
           throw new IOException("Cannot save file");
       }

        return savedFile;

    }

    public byte[] fetchImage(String fileName) {

        OrkFile orkFile = orkFileRepository.findByName(fileName);
        String filePath = orkFile.getFilePath();

        byte[] fileBytes;

        try {
            //Reading file through file system > converting file to byte array
            fileBytes = Files.readAllBytes(new File(filePath).toPath());
        } catch (Exception e) {
            throw new RuntimeException("File Not Found");
        }

        return fileBytes;
    }
}
