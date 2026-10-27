package com.fioneer.backend.controller;

import com.fioneer.backend.dto.AccumulationCreateDto;
import com.fioneer.backend.dto.AccumulationDto;
import com.fioneer.backend.entity.Accumulation;
import com.fioneer.backend.entity.Coverage;
import com.fioneer.backend.service.AccumulationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{name}")
    public ResponseEntity<AccumulationDto> getAccumulationByName(@PathVariable String name) {

        return new ResponseEntity<>(accumulationService.getAccumulationByName(name), HttpStatus.OK);
    }

    @GetMapping("/{accumulationName}/coverages")
    public ResponseEntity<List<Coverage>> getCoverages(@PathVariable("accumulationName") String accumulationName) {

        return new ResponseEntity<>(accumulationService.getCoveragesByAccumulationName(accumulationName), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<AccumulationDto> create(@RequestBody AccumulationCreateDto accumulationCreateDto) {

        return new ResponseEntity<>(accumulationService.createAccumulation(accumulationCreateDto), HttpStatus.OK);
    }


}
