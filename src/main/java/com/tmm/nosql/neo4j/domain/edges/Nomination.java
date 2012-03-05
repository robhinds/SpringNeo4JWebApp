package com.tmm.nosql.neo4j.domain.edges;

import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

import com.tmm.nosql.neo4j.domain.nodes.Actor;
import com.tmm.nosql.neo4j.domain.nodes.Award;

@RelationshipEntity(type = "NOMINATED_FOR")
public class Nomination {

	@GraphId
	private Long id;
	@StartNode
	@Fetch
	private Actor nominee;
	@EndNode
	@Fetch
	private Award award;

	public Nomination(Actor nom, Award a, boolean winner) {
		this.nominee = nom;
		this.award = a;
		this.winner = winner;
	}

	public Nomination() {
	}

	private boolean winner;

	public Actor getNominee() {
		return nominee;
	}

	public void setNominee(Actor nominee) {
		this.nominee = nominee;
	}

	public Award getAward() {
		return award;
	}

	public void setAward(Award award) {
		this.award = award;
	}

	public boolean isWinner() {
		return winner;
	}

	public void setWinner(boolean winner) {
		this.winner = winner;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
