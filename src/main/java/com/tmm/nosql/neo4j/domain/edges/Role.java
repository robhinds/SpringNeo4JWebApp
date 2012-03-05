package com.tmm.nosql.neo4j.domain.edges;

import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

import com.tmm.nosql.neo4j.domain.nodes.Actor;
import com.tmm.nosql.neo4j.domain.nodes.Movie;

@RelationshipEntity(type = "ACTS_IN")
public class Role {

	@GraphId
	private Long id;
	@StartNode
	@Fetch
	private Actor actor;
	@EndNode
	@Fetch
	private Movie movie;

	public Role(Movie m, Actor a) {
		this.movie = m;
		this.actor = a;
	}

	public Role() {
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Actor getActor() {
		return actor;
	}

	public void setActor(Actor actor) {
		this.actor = actor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
