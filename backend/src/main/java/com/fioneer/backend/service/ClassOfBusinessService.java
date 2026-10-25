package com.fioneer.backend.service;

import com.fioneer.backend.entity.ClassOfBusiness;

public interface ClassOfBusinessService {

    public ClassOfBusiness getClassOfBusiness(String name);

    public ClassOfBusiness createClassOfBusiness(String name);

    public ClassOfBusiness updateClassOfBusiness(String oldName, String newName);

    public String deleteClassOfBusiness(String name);
}
