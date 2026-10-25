package com.fioneer.backend.controller;

import com.fioneer.backend.entity.ClassOfBusiness;
import com.fioneer.backend.repository.ClassOfBusinessRepository;
import com.fioneer.backend.service.ClassOfBusinessService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/classes-of-business")
public class ClassOfBusinessController {

    private ClassOfBusinessService classOfBusinessService;

    public ClassOfBusinessController(ClassOfBusinessService classOfBusinessService) {
        this.classOfBusinessService = classOfBusinessService;
    }

    @GetMapping("/{name}")
    public ResponseEntity<ClassOfBusiness> getClassOfBusiness(@PathVariable String name) {
        return new ResponseEntity<>(classOfBusinessService.getClassOfBusiness(name), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ClassOfBusiness> createClassOfBusiness(@RequestBody String name) {
        return new ResponseEntity<>(classOfBusinessService.createClassOfBusiness(name), HttpStatus.OK);
    }

    @PatchMapping("/update/{oldName}")
    public ResponseEntity<ClassOfBusiness> updateClassOfBusiness(@PathVariable String oldName, @RequestBody String newName) {
        return new ResponseEntity<>(classOfBusinessService.updateClassOfBusiness(oldName, newName), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteClassOfBusiness(@RequestParam String name) {
        return new ResponseEntity<>(classOfBusinessService.deleteClassOfBusiness(name), HttpStatus.OK);
    }

}
