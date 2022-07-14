package com.ace.web.pf.repository;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ace.web.pf.datamodel.User;

public interface UserRepository extends JpaRepository<User, Long>, DataTablesRepository<User, Long>, BaseRepository {
	@Query
	User findByUserName(@Param("userName") String userName);

	@Query
	String findPasswordById(Long id);
}
