-- Alter employee table to add department_id
ALTER TABLE employees
    ADD COLUMN department_id BIGINT;

-- Add foreign key constraint
ALTER TABLE employees
    ADD CONSTRAINT fk_employee_department
        FOREIGN KEY (department_id) REFERENCES departments (id);