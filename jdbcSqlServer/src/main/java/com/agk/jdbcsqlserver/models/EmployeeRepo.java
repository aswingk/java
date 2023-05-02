package com.agk.jdbcsqlserver.models;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<Employee, Long> { //}, EmpRepoCustom {
}
