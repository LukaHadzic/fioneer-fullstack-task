package com.fioneer.backend.service;

import com.fioneer.backend.entity.Status;

public interface StatusService {

    public Status getStatus(String status);

    public Status createStatus(String status);

    public Status updateStatus(String oldStatus, String newStatus);

    public String deleteStatus(String status);
}
