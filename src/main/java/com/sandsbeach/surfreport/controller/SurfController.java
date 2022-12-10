package com.sandsbeach.surfreport.controller;

import com.sandsbeach.surfreport.model.SurfLocationReport;
import com.sandsbeach.surfreport.service.SurfService;
import com.sandsbeach.surfreport.service.SurfliesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/surf")
public class SurfController {

    @Autowired
    private SurfliesService surfliesService;

    @Autowired
    private SurfService surfService;





    @CrossOrigin
    @GetMapping("/{locationId}")
    public ResponseEntity<SurfLocationReport> getSurfLocationReport(
            @PathVariable("locationId") String locationId) {
        return new ResponseEntity<>(surfService.getReport(locationId), HttpStatus.OK);
    }
}
