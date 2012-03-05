package com.tmm.nosql.neo4j.service;

import org.neo4j.helpers.collection.ClosableIterable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tmm.nosql.neo4j.domain.edges.Role;
import com.tmm.nosql.neo4j.repo.RoleRepository;

@Repository("roleService")
@Transactional
public class RoleService {

	@Autowired
	RoleRepository roleRepo;
	@Autowired
	Neo4jOperations template;

	public void setRoleRepo(RoleRepository roleRepo) {
		this.roleRepo = roleRepo;
	}

	public void setTemplate(Neo4jOperations template) {
		this.template = template;
	}

	@Transactional
	public ClosableIterable<Role> findAllRoles() {
		return roleRepo.findAll();
	}

}
