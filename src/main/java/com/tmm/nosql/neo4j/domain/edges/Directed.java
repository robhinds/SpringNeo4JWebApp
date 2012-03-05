package com.tmm.nosql.neo4j.domain.edges;

import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

import com.tmm.nosql.neo4j.domain.nodes.Director;
import com.tmm.nosql.neo4j.domain.nodes.Movie;

@RelationshipEntity(type = "DIRECTED")
public class Directed {

	@GraphId
	private Long id;
	@StartNode
	private Director director;
	@EndNode
	private Movie movie;

	public Directed(Director d, Movie m) {
		this.director = d;
		this.movie = m;
	}

	public Directed() {
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Director getDirector() {
		return director;
	}

	public void setDirector(Director director) {
		this.director = director;
	}

}
