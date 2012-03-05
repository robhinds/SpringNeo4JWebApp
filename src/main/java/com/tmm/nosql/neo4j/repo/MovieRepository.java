package com.tmm.nosql.neo4j.repo;

import org.springframework.data.neo4j.repository.GraphRepository;

import com.tmm.nosql.neo4j.domain.nodes.Movie;

/**
 * Repository class to persist Movie objects - this is the DAO object layer
 * 
 * @author robert.hinds
 * 
 */
public interface MovieRepository extends GraphRepository<Movie> {

	public Movie getMovieByName(String name);

}