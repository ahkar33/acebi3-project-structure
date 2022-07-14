package com.ace.web.pf.repository;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ace.web.pf.datamodel.Authority;

@Repository
public interface UserRoleRepository extends CrudRepository<Authority, Long>, JpaRepository<Authority, Long>,
		DataTablesRepository<Authority, Long>, BaseRepository {

}
