package com.sushmita.ork.service.sector;

import com.sushmita.ork.entity.Sector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Sushmita Budhathoki on 2024-10-02
 */

@Service
public class SectorService {

    private final SectorRepository sectorRepository;


    @Autowired
    public SectorService(SectorRepository sectorRepository) {
        this.sectorRepository = sectorRepository;
    }

    public Sector save(String sectorName) {
        if(sectorName == null) throw new NullPointerException("No data given");

        return sectorRepository.save(Sector.builder().name(sectorName).build());
    }

    public List<Sector> getAll() {
        return sectorRepository.findAll();
    }
}
