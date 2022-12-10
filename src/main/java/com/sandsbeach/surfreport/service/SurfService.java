package com.sandsbeach.surfreport.service;


import com.sandsbeach.surfreport.model.SurfLocationReport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SurfService {
    @Autowired
    private SurfliesService surfliesService;

    public SurfLocationReport getReport(String locationId) {
        try {
            return surfliesService.getReport(locationId);
        } catch (Exception e) {
            log.error("Failed to call surflies, going with default", e);
            return new SurfLocationReport();
        }
    }
}
