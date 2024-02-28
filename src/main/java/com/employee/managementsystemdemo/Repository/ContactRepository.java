package com.employee.managementsystemdemo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee.managementsystemdemo.Model.ContactForm;

@Repository

public interface ContactRepository extends JpaRepository<ContactForm, Long> {

}
