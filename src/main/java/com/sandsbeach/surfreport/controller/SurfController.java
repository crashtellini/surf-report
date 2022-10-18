package com.sandsbeach.surfreport.controller;

import com.sandsbeach.surfreport.model.SurfLocationReport;
import com.sandsbeach.surfreport.service.SurflineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/surf")
public class SurfController {

    @Autowired
    private SurflineService surflineService;

    @GetMapping("/{locationId}")
    public ResponseEntity<SurfLocationReport> getSurfLocationReport(
            @PathVariable("locationId") String locationId) {
        return new ResponseEntity<>(surflineService.getReport(locationId), HttpStatus.OK);
    }
}
