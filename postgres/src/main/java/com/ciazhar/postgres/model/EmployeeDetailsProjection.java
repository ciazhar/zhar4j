package com.ciazhar.postgres.model;

public interface EmployeeDetailsProjection {
    String getId();
    String getFirstName();
    String getLastName();
    String getCreatedBy();
    String getLastModifiedBy();
}
