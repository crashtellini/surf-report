package com.sandsbeach.surfreport.controller;

import com.sandsbeach.surfreport.model.SurfLocationReport;
import com.sandsbeach.surfreport.service.SurflineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/surf")
public class SurfController {

    @Autowired
    private SurflineService surflineService;


    @CrossOrigin
    @GetMapping("/{locationId}")
    public ResponseEntity<SurfLocationReport> getSurfLocationReport(
            @PathVariable("locationId") String locationId) {
        return new ResponseEntity<>(surflineService.getReport(locationId), HttpStatus.OK);
    }
}
