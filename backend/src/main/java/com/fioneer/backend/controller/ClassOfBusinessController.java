package com.fioneer.backend.controller;

import com.fioneer.backend.entity.ClassOfBusiness;
import com.fioneer.backend.repository.ClassOfBusinessRepository;
import com.fioneer.backend.service.ClassOfBusinessService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/classes-of-business")
public class ClassOfBusinessController {

    private ClassOfBusinessService classOfBusinessService;

    public ClassOfBusinessController(ClassOfBusinessService classOfBusinessService) {
        this.classOfBusinessService = classOfBusinessService;
    }

    @GetMapping("/{name}")
    public String getClassOfBusiness(@PathVariable String name) {
        return classOfBusinessService.getClassOfBusiness(name);
    }

    @PostMapping("/create")
    public String createClassOfBusiness(@RequestBody String name) {
        return classOfBusinessService.createClassOfBusiness(name);
    }

    @PatchMapping("/update/{oldName}")
    public String updateClassOfBusiness(@PathVariable String oldName, @RequestBody String newName) {
        return classOfBusinessService.updateClassOfBusiness(oldName, newName);
    }

    @DeleteMapping("/delete")
    public String deleteClassOfBusiness(@RequestParam String name) {
        return classOfBusinessService.deleteClassOfBusiness(name);
    }

}
