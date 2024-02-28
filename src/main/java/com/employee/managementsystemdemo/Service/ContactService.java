package com.employee.managementsystemdemo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.managementsystemdemo.Model.ContactForm;
import com.employee.managementsystemdemo.Repository.ContactRepository;

@Service
public class ContactService {

	@Autowired
	private ContactRepository CRepo;

	public void save(ContactForm cf) {

		CRepo.save(cf);
	}

}


