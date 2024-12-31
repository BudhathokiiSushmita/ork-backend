package com.sushmita.ork.service.sector;

import com.sushmita.ork.constants.OrkConstants;
import com.sushmita.ork.entity.OrkFile;
import com.sushmita.ork.entity.Sector;
import com.sushmita.ork.enums.Image;
import com.sushmita.ork.service.orkImage.OrkFileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author Sushmita Budhathoki on 2024-10-02
 */

@Service
public class SectorService {

    private final SectorRepository sectorRepository;
    private final OrkFileService orkFileService;

    public SectorService(SectorRepository sectorRepository, OrkFileService orkFileService) {
        this.sectorRepository = sectorRepository;
        this.orkFileService = orkFileService;
    }

    public Sector save(MultipartFile file, String sectorName) throws IOException {
        if(sectorName == null) throw new NullPointerException("No data given");


        //save sector image
        String uniqueKey = sectorName + "-";
        String folder = OrkConstants.FOLDER_PATH + Image.SECTOR + "/" + uniqueKey;
        OrkFile orkFile = orkFileService.uploadFile(file, Image.SECTOR, folder, uniqueKey);

        //save sector
        return sectorRepository.save(Sector.builder().name(sectorName).orkFile(orkFile).build());
    }

    public List<Sector> getAll() {
        return sectorRepository.findAll().stream().peek(f -> f.setFile(orkFileService.fetchImage(f.getOrkFile().getName()))).toList();
    }
}
