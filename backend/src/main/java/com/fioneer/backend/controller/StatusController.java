package com.fioneer.backend.controller;

import com.fioneer.backend.service.StatusService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/statuses")
public class StatusController {

    private StatusService statusService;

    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @GetMapping("/{status}")
    public String getStatus(@PathVariable String status) {
        return statusService.getStatus(status);
    }

    @PostMapping("/create")
    public String createStatus(@RequestBody String status) {
        return statusService.createStatus(status);
    }

    @PatchMapping("/update/{oldStatus}")
    public String updateStatus(@PathVariable String oldStatus, @RequestBody String newStatus) {
        return statusService.updateStatus(oldStatus, newStatus);
    }

    @DeleteMapping("/delete")
    public String deleteStatus(@RequestParam String status) {
        return statusService.deleteStatus(status);
    }
}
