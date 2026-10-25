package com.fioneer.backend.service;

public interface ClassOfBusinessService {

    public String getClassOfBusiness(String name);

    public String createClassOfBusiness(String name);

    public String updateClassOfBusiness(String oldName, String newName);

    public String deleteClassOfBusiness(String name);
}
