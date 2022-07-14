package com.ace.web.pf.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ace.web.pf.datamodel.User;
import com.ace.web.pf.datamodel.Student;
import com.ace.web.pf.enums.ErrorMessage;
import com.ace.web.pf.enums.InfoMessages;
import com.ace.web.pf.enums.PageUrls;
import com.ace.web.pf.service.impl.StudentServiceImpl;

@Controller
@RequestMapping("/generalSetting/student")
public class StudentController extends BaseController {

	@Autowired
	@Qualifier("studentService")
	private StudentServiceImpl studentService;

	@GetMapping
	public String vesselPage() {
		return PageUrls.Student.value();
	}

	@PostMapping("/dataTable")
	public @ResponseBody DataTablesOutput<Student> vesselData(@RequestBody DataTablesInput dataTablesInput) {
		return studentService.findAll(dataTablesInput);
	}

	@PostMapping("/save")
	public @ResponseBody String addVessel(Student vessel) {
		User userName = commonUtils.loadLoginUserName();

		String name = vessel.getName();
		Student oldVessel = studentService.findByName(name);
		if (vessel.getId() == null) {
			if (oldVessel != null) {
				return messagesUtils.getMessage(ErrorMessage.Duplicate_Error.code());
			}
			vessel.setCreatedDateTime(new Date());
			vessel.setCreatedUserId(userName.getId());
		} else {

			Student updatedvessel = studentService.findById(vessel.getId());

			if (!updatedvessel.getName().equals(vessel.getName())) {
				if (oldVessel != null) {
					return messagesUtils.getMessage(ErrorMessage.Duplicate_Error.code());
				}
			}
			vessel.setCreatedDateTime(updatedvessel.getCreatedDateTime());
			vessel.setCreatedUserId(updatedvessel.getCreatedUserId());
			vessel.setUpdatedDateTime(new Date());
			vessel.setUpdatedUserId(userName.getId());
		}

		try {
			studentService.save(vessel);
		}catch (Exception e) {
			return messagesUtils.getMessage(e.getMessage());
		}

		return messagesUtils.getMessage(InfoMessages.Successfully_Save.code());
	}

	@PostMapping("/findById")
	public @ResponseBody Student findById(long id) {
		return studentService.findById(id);
	}

	@PostMapping("/delete")
	public @ResponseBody String deleteVessel(long id) {
		
		studentService.delete(id);
		return messagesUtils.getMessage(InfoMessages.Successfully_delete.code());
	}

}
