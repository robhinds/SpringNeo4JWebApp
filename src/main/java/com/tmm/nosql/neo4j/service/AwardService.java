package com.tmm.nosql.neo4j.service;

import org.neo4j.helpers.collection.ClosableIterable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tmm.nosql.neo4j.domain.edges.Nomination;
import com.tmm.nosql.neo4j.domain.nodes.Actor;
import com.tmm.nosql.neo4j.domain.nodes.Award;
import com.tmm.nosql.neo4j.repo.ActorRepository;
import com.tmm.nosql.neo4j.repo.AwardRepository;
import com.tmm.nosql.neo4j.repo.NominationRepository;

@Repository("awardService")
@Transactional
public class AwardService {

	@Autowired
	AwardRepository awardRepo;
	@Autowired
	Neo4jOperations template;
	@Autowired
	NominationRepository nominationRepo;
	@Autowired
	ActorService actorService;
	@Autowired
	ActorRepository actorRepo;

	public void setAwardRepo(AwardRepository awardRepo) {
		this.awardRepo = awardRepo;
	}

	public void setTemplate(Neo4jOperations template) {
		this.template = template;
	}

	public void setNominationRepo(NominationRepository nominationRepo) {
		this.nominationRepo = nominationRepo;
	}

	public void setActorService(ActorService actorService) {
		this.actorService = actorService;
	}

	public void setActorRepo(ActorRepository actorRepo) {
		this.actorRepo = actorRepo;
	}

	@Transactional
	public ClosableIterable<Award> findAllAwards() {
		return awardRepo.findAll();
	}

	@Transactional
	public void createAward(Award a) {
		awardRepo.save(a);
	}

	@Transactional
	public Award createAward(String name) {
		Award a = new Award();
		a.setCategory(name);
		createAward(a);
		return a;
	}

	public Award retrieveAward(String name) {
		return awardRepo.findByPropertyValue("category", name);
	}

	@Transactional
	public void createRelationship(String nominee, String award, boolean winner) {
		Award aw = awardRepo.findByPropertyValue("category", award);
		Actor nom = actorRepo.findByPropertyValue("name", nominee);
		System.out.println("Award: " + aw.getCategory());
		System.out.println("Actor: " + nom.getName());
		Nomination n = new Nomination();
		n.setNominee(nom);
		n.setAward(aw);
		nom.addNomindatedFor(aw);
		aw.addNominee(nom);
		n.setWinner(winner);
		nominationRepo.save(n);
	}
}
