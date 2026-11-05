package com.fioneer.backend.controller;

import com.fioneer.backend.dto.*;
import com.fioneer.backend.entity.Accumulation;
import com.fioneer.backend.entity.Coverage;
import com.fioneer.backend.service.AccumulationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accumulations")
public class AccumulationController {

    private AccumulationService accumulationService;

    public AccumulationController(AccumulationService accumulationService) {
        this.accumulationService = accumulationService;
    }

    @GetMapping("")
    public ResponseEntity<List<AccumulationDto>> getAllAccumulations(){

        return new ResponseEntity<>(accumulationService.getAll(), HttpStatus.OK);
    }

//    @GetMapping("/{name}")
//    public ResponseEntity<AccumulationDto> getAccumulationByName(@PathVariable String name) {
//
//        return new ResponseEntity<>(accumulationService.getAccumulationByName(name), HttpStatus.OK);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<AccumulationDto> getAccumulationById(@PathVariable Long id) {

        return new ResponseEntity<>(accumulationService.getAccumulationById(id), HttpStatus.OK);
    }

    @GetMapping("/summary")
    public ResponseEntity<AccumulationsSummaryDto> getAccumulationSummary() {

        return new ResponseEntity<>(accumulationService.getAverageSumInsuredPerStatus(), HttpStatus.OK);
    }


    @GetMapping("/{accumulationName}/coverages")
    public ResponseEntity<List<CoverageDto>> getCoverages(@PathVariable("accumulationName") String accumulationName) {

        return new ResponseEntity<>(accumulationService.getCoveragesByAccumulationName(accumulationName), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<AccumulationDto> create(@RequestBody AccumulationCreateDto accumulationCreateDto) {

        return new ResponseEntity<>(accumulationService.createAccumulation(accumulationCreateDto), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
//    public ResponseEntity<String> delete(@RequestBody String accumulationName) {
    public ResponseEntity<String> delete(@RequestBody Map<String, String> accumulationName) {

        return new ResponseEntity<>(accumulationService.delete(accumulationName.get("name")), HttpStatus.OK);
    }


}
