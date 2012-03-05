package com.tmm.nosql.neo4j.repo;

import org.springframework.data.neo4j.repository.GraphRepository;

import com.tmm.nosql.neo4j.domain.nodes.Actor;

/**
 * Repository class to persist Actor objects - this is the DAO object layer
 * 
 * @author robert.hinds
 * 
 */
public interface ActorRepository extends GraphRepository<Actor> {

	public Actor findByName(String name);
}