package com.tmm.nosql.neo4j.domain.nodes;

import java.util.HashSet;
import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;
import org.springframework.data.neo4j.annotation.RelatedToVia;

import com.tmm.nosql.neo4j.domain.edges.Directed;
import com.tmm.nosql.neo4j.domain.edges.Role;

@NodeEntity
public class Movie {

	@GraphId
	private Long id;

	@Indexed
	private String name;

	@RelatedTo(type = "ACTS_IN", direction = Direction.INCOMING)
	private Set<Actor> cast = new HashSet<Actor>();

	@RelatedTo(type = "DIRECTED", direction = Direction.INCOMING)
	private Director director;

	@RelatedToVia(type = "ACTS_IN", direction = Direction.INCOMING)
	Iterable<Role> roles;

	@RelatedToVia(type = "DIRECTED", direction = Direction.INCOMING)
	Iterable<Directed> directed;

	public Set<Actor> getCast() {
		return cast;
	}

	public void setCast(Set<Actor> cast) {
		this.cast = cast;
	}

	public void addCast(Actor actor) {
		this.cast.add(actor);
	}

	public void removeCast(Actor actor) {
		this.cast.remove(actor);
	}

	public Director getDirector() {
		return director;
	}

	public void setDirector(Director director) {
		this.director = director;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Iterable<Role> getRoles() {
		return roles;
	}

	public Iterable<Directed> getDirected() {
		return directed;
	}

}