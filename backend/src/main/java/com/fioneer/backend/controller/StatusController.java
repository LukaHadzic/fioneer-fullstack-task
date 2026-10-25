package com.fioneer.backend.controller;

import com.fioneer.backend.entity.Status;
import com.fioneer.backend.service.StatusService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/statuses")
public class StatusController {

    private StatusService statusService;

    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @GetMapping("/{status}")
    public ResponseEntity<Status> getStatus(@PathVariable String status) {
        return new ResponseEntity<>(statusService.getStatus(status), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Status> createStatus(@RequestBody String status) {
        return new ResponseEntity<>(statusService.createStatus(status), HttpStatus.CREATED);
    }

    @PatchMapping("/update/{oldStatus}")
    public ResponseEntity<Status> updateStatus(@PathVariable String oldStatus, @RequestBody String newStatus) {
        return new ResponseEntity<>(statusService.updateStatus(oldStatus, newStatus), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteStatus(@RequestParam String status) {
        return new ResponseEntity<>(statusService.deleteStatus(status), HttpStatus.OK);
    }
}
