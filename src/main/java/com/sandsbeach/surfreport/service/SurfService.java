package com.sandsbeach.surfreport.service;


import com.sandsbeach.surfreport.model.SurfLocationReport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SurfService {
    @Autowired
    private SurflineService surflineService;

    public SurfLocationReport getReport(String locationId) {
       //When the ec2 server calls surfline
        // there is a 401 error
        // this will catch the exception and give a response


        try {
            return surflineService.getReport(locationId);

        } catch (Exception e) {
            log.error("Failed to call surfline, going with default", e);
            return new SurfLocationReport();
        }
    }
}
