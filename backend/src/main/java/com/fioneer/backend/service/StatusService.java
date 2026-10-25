package com.fioneer.backend.service;

public interface StatusService {

    public String getStatus(String status);

    public String createStatus(String status);

    public String updateStatus(String oldStatus, String newStatus);

    public String deleteStatus(String status);
}
