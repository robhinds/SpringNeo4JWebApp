package com.tmm.nosql.neo4j.repo;

import org.springframework.data.neo4j.repository.GraphRepository;

import com.tmm.nosql.neo4j.domain.edges.Nomination;

/**
 * Repository class to persist Nomination (edge/relationship) objects - this is
 * the DAO object layer
 * 
 * @author robert.hinds
 * 
 */
public interface NominationRepository extends GraphRepository<Nomination> {

}