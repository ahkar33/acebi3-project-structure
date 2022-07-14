package com.ace.web.pf.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

import com.ace.web.pf.datamodel.Student;
import com.ace.web.pf.enums.ErrorMessage;
import com.ace.web.pf.exception.ACEException;
import com.ace.web.pf.repository.StudentRepository;
import com.ace.web.pf.service.interfaces.IGeneralSettingService;
import com.ace.web.pf.util.DataTableColumns;
import com.ace.web.pf.util.MessagesUtil;

@Service("studentService")
public class StudentServiceImpl implements IGeneralSettingService<Student, Long> {

	@Autowired
	private StudentRepository repo;

	@Autowired
	protected MessagesUtil messagesUtils;

	@Override
	public Student save(Student entity) throws ACEException {
		Student vessel = null;
		try {
			vessel = repo.save(entity);
		} catch (Exception e) {
			throw new ACEException(messagesUtils.getMessage(ErrorMessage.Unexpected_Error.code(), e.getMessage()),
					e.getCause(), ErrorMessage.Unexpected_Error);
		}

		return vessel;
	}

	@Override
	public void delete(Long id) {
		repo.deleteById(id);
	}

	@Override
	public DataTablesOutput<Student> findAll(DataTablesInput dataTableInput) {
		return repo.findAll(repo, dataTableInput, DataTableColumns.STUDENT);
	}

	@Override
	public Student findById(Long id) {
		return repo.findById(id).orElse(null);
	}

	public List<Student> findAll() {
		return repo.findAll();
	}

	public Student findByName(String name) {

		return repo.findByName(name);
	}
}
