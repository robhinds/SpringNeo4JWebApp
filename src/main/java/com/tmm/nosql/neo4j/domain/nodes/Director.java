package com.tmm.nosql.neo4j.domain.nodes;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;
import org.springframework.data.neo4j.annotation.RelatedToVia;

import com.tmm.nosql.neo4j.domain.edges.Directed;

@NodeEntity
public class Director {

	@GraphId
	private Long id;

	@Indexed
	private String name;

	@RelatedTo(type = "DIRECTED")
	private Set<Movie> movies = new HashSet<Movie>();

	@RelatedToVia(type = "DIRECTED")
	Iterable<Directed> directed;

	public Iterable<Directed> getDirected() {
		return directed;
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
}