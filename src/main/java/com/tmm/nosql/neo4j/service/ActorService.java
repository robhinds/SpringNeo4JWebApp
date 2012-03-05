package com.tmm.nosql.neo4j.service;

import org.neo4j.helpers.collection.ClosableIterable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tmm.nosql.neo4j.domain.nodes.Actor;
import com.tmm.nosql.neo4j.repo.ActorRepository;

@Repository("actorService")
@Transactional
public class ActorService {

	@Autowired
	ActorRepository actorRepo;
	@Autowired
	Neo4jOperations template;

	public void setActorRepo(ActorRepository actorRepo) {
		this.actorRepo = actorRepo;
	}

	public void setTemplate(Neo4jOperations template) {
		this.template = template;
	}

	@Transactional
	public ClosableIterable<Actor> findAllActors() {
		return actorRepo.findAll();
	}

	@Transactional
	public void createActor(Actor a) {
		actorRepo.save(a);
	}

	@Transactional
	public Actor createActor(String name) {
		Actor a = retrieveActor(name);
		if (a == null) {
			System.out.println("Actor is null, creating new: " + name);
			a = new Actor();
			a.setName(name);
			createActor(a);
		}
		return a;
	}

	public Actor retrieveActor(String name) {
		// return actorRepo.findByName(name);
		return actorRepo.findByPropertyValue("name", name);
	}
}
