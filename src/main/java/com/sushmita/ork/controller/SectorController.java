package com.sushmita.ork.controller;

import com.sushmita.ork.base.CustomResponse;
import com.sushmita.ork.entity.Sector;
import com.sushmita.ork.service.sector.SectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.sushmita.ork.constants.APIConstants.SECTOR_API;

/**
 * @author Sushmita Budhathoki on 2024-10-02
 */

@RestController
@RequestMapping(value = SECTOR_API)
public class SectorController {

   private final SectorService sectorService;

   @Autowired
    public SectorController(SectorService sectorService) {
        this.sectorService = sectorService;
    }

    @PostMapping()
   public ResponseEntity<?> saveSector(@RequestParam("orkFile") MultipartFile orkFile,
                                       @RequestParam("name") String name) {
       try {
          Sector sector = sectorService.save(orkFile, name);
          return CustomResponse.getSuccessResponse("Saved successfully", sector);
       } catch (Exception e) {
          return CustomResponse.getErrorResponse(e.getMessage(), "", HttpStatus.BAD_REQUEST);
       }
    }

    @GetMapping("/list")
    public ResponseEntity<?> getAllSectors() {
        try {
            List<Sector> sectorList = sectorService.getAll();
            return CustomResponse.getSuccessResponse("Fetched successfully", sectorList);
        } catch (Exception e) {
            return CustomResponse.getErrorResponse(e.getMessage(), "", HttpStatus.BAD_REQUEST);
        }
    }
}
