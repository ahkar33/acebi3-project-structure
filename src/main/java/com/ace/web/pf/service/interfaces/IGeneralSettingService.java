package com.ace.web.pf.service.interfaces;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import com.ace.web.pf.exception.ACEException;

public interface IGeneralSettingService<T, S> {

	T save(T entity) throws ACEException;

	T findById(S id) throws ACEException;

	void delete(S id) throws ACEException;

	DataTablesOutput<T> findAll(DataTablesInput dataTableInput) throws ACEException;
}
