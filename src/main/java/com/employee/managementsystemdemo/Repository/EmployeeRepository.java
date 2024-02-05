package com.employee.managementsystemdemo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee.managementsystemdemo.Model.Employee;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
