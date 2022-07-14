package com.ace.web.pf.repository;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ace.web.pf.datamodel.Student;

@Repository
public interface StudentRepository
		extends CrudRepository<Student, Long>, JpaRepository<Student, Long>, DataTablesRepository<Student, Long>, BaseRepository {
	
	@Query
	Student findByName(@Param("name") String name);

}
