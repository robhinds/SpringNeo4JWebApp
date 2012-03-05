package com.tmm.nosql.neo4j.service;

import org.neo4j.helpers.collection.ClosableIterable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tmm.nosql.neo4j.domain.edges.Nomination;
import com.tmm.nosql.neo4j.repo.NominationRepository;

@Repository("nominationService")
@Transactional
public class NominationService {

	@Autowired
	NominationRepository nominationRepo;
	@Autowired
	Neo4jOperations template;

	public void setNominationRepo(NominationRepository nominationRepo) {
		this.nominationRepo = nominationRepo;
	}

	public void setTemplate(Neo4jOperations template) {
		this.template = template;
	}

	@Transactional
	public ClosableIterable<Nomination> findAllNominations() {
		return nominationRepo.findAll();
	}

}
