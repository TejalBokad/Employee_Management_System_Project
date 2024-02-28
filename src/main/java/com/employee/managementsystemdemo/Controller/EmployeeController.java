package com.employee.managementsystemdemo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.employee.managementsystemdemo.Model.ContactForm;
import com.employee.managementsystemdemo.Model.Employee;
import com.employee.managementsystemdemo.Service.ContactService;
import com.employee.managementsystemdemo.Service.EmployeeService;

@Controller

public class EmployeeController {

	@GetMapping("/")
	public String home() {
		return "home";
	}

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private ContactService contactService;

	// display list of employees
	@GetMapping("/emp")
	public String viewHomePage(Model model) {
		return findPaginated(1, "firstName", "asc", model);

	}

	@GetMapping("/showNewEmployeeForm")
	public String showNewEmployeeForm(Model model) {
		// create model attribute to bind from data
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		return "new_employee";

	}

	@PostMapping("/saveEmployee")
	public String saveEmployee(@ModelAttribute("employee") Employee employee) {
		// save employee to database
		employeeService.saveEmployee(employee);
		return "redirect:/emp";

	}

	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {

		// get employee for the service
		Employee employee = employeeService.getEmployeeById(id);

		// set employee as a model attribute to pre-populate the form
		model.addAttribute("employee", employee);
		return "update_employee";

	}

	@GetMapping("/deleteEmployee/{id}")
	public String deleteEmployee(@PathVariable(value = "id") long id) {

		// call delete employee method

		this.employeeService.deleteEmployeeById(id);
		return "redirect:/emp";

	}

	

	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable(value = "pageNo") int pageNo, @RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir, Model model) {
		int pageSize = 5;

		Page<Employee> page = employeeService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Employee> listEmployees = page.getContent();

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());

		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

		model.addAttribute("listEmployees", listEmployees);

		return "index";

	}

	@PostMapping("/submitContactForm")
	public String submitContactForm(@ModelAttribute ContactForm contactForm, Model model) {
		ContactForm contactFormData = new ContactForm();
		contactFormData.setName(contactForm.getName());
		contactFormData.setEmail(contactForm.getEmail());
		contactFormData.setMessage(contactForm.getMessage());
		contactService.save(contactForm);

		return "home"; // Redirect to a thank you page
	}
}
