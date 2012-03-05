package com.tmm.nosql.neo4j.domain.nodes;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;
import org.springframework.data.neo4j.annotation.RelatedToVia;

import com.tmm.nosql.neo4j.domain.edges.Nomination;
import com.tmm.nosql.neo4j.domain.edges.Role;

@NodeEntity
public class Actor {

	@GraphId
	private Long id;

	@Indexed
	private String name;

	@RelatedTo(type = "ACTS_IN")
	@Fetch
	private Set<Movie> movies = new HashSet<Movie>();

	@RelatedToVia(type = "ACTS_IN")
	Iterable<Role> roles;

	@RelatedTo(type = "NOMINATED_FOR")
	@Fetch
	private Set<Award> nominatedFor = new HashSet<Award>();

	@RelatedToVia(type = "NOMINATED_FOR")
	Iterable<Nomination> nominations;

	public Iterable<Role> getRoles() {
		return roles;
	}

	public Set<Movie> getMovies() {
		return movies;
	}

	public void setMovies(Set<Movie> movie) {
		this.movies = movie;
	}

	public void addMovie(Movie m) {
		this.movies.add(m);
	}

	public void removeMovie(Movie m) {
		this.movies.remove(m);
	}

	public Iterable<Nomination> getNominations() {
		return nominations;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Award> getNominatedFor() {
		return nominatedFor;
	}

	public void setNominatedFor(Set<Award> nominatedFor) {
		this.nominatedFor = nominatedFor;
	}

	public void addNomindatedFor(Award award) {
		this.nominatedFor.add(award);
	}

	public void removeNomindatedFor(Award award) {
		this.nominatedFor.remove(award);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}