package com.tmm.nosql.neo4j.domain.nodes;

import java.util.HashSet;
import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;
import org.springframework.data.neo4j.annotation.RelatedToVia;

import com.tmm.nosql.neo4j.domain.edges.Nomination;

@NodeEntity
public class Award {

	@GraphId
	private Long id;

	@Indexed
	private String category;

	private String organisation;

	@RelatedTo(type = "NOMINATED_FOR", direction = Direction.INCOMING)
	private Set<Actor> nominees = new HashSet<Actor>();

	@RelatedToVia(type = "NOMINATED_FOR", direction = Direction.INCOMING)
	Iterable<Nomination> nominations;

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getOrganisation() {
		return organisation;
	}

	public void setOrganisation(String organisation) {
		this.organisation = organisation;
	}

	public Set<Actor> getNominees() {
		return nominees;
	}

	public void setNominees(Set<Actor> nominees) {
		this.nominees = nominees;
	}

	public void addNominee(Actor nominee) {
		this.nominees.add(nominee);
	}

	public void removeNominee(Actor nominee) {
		this.nominees.remove(nominee);
	}

	public Iterable<Nomination> getNominations() {
		return nominations;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
