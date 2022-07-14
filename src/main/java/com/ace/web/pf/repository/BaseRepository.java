package com.ace.web.pf.repository;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.data.jpa.domain.Specification;

public interface BaseRepository {
	EntityManager getEntityManager();

	<T> DataTablesOutput<T> findAll(BaseRepository repo, DataTablesInput dataTablesInput, String[] tableColumns);

	<T> DataTablesOutput<T> findAll(BaseRepository repo, DataTablesInput dataTablesInput, String[] tableColumns,
			Specification<T> spec);

}
