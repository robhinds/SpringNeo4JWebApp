package com.tmm.nosql.neo4j.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.neo4j.helpers.collection.ClosableIterable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tmm.nosql.neo4j.domain.edges.Nomination;
import com.tmm.nosql.neo4j.domain.edges.Role;
import com.tmm.nosql.neo4j.domain.nodes.Actor;
import com.tmm.nosql.neo4j.domain.nodes.Award;
import com.tmm.nosql.neo4j.domain.nodes.Movie;
import com.tmm.nosql.neo4j.repo.MovieRepository;
import com.tmm.nosql.neo4j.repo.NominationRepository;
import com.tmm.nosql.neo4j.repo.RoleRepository;

@Repository("movieService")
@Transactional
public class MovieService {

	@Autowired
	MovieRepository movieRepo;
	@Autowired
	RoleRepository roleRepo;
	@Autowired
	NominationRepository nominationRepository;
	@Autowired
	ActorService actorService;
	@Autowired
	AwardService awardService;

	public void setRoleRepo(RoleRepository roleRepo) {
		this.roleRepo = roleRepo;
	}

	public void setMovieRepo(MovieRepository movieRepo) {
		this.movieRepo = movieRepo;
	}

	public void setActorService(ActorService actorService) {
		this.actorService = actorService;
	}

	public void setAwardService(AwardService awardService) {
		this.awardService = awardService;
	}

	public void setNominationRepository(
			NominationRepository nominationRepository) {
		this.nominationRepository = nominationRepository;
	}

	@Transactional
	public ClosableIterable<Movie> findAllMovies() {
		return movieRepo.findAll();
	}

	@Transactional
	public void createMovie(Movie movie) {
		movieRepo.save(movie);
	}

	@Transactional
	public Movie createMovie(String title) {
		Movie m = retrieveMovie(title);
		if (m == null) {
			m = new Movie();
			m.setName(title);
			createMovie(m);
		}
		return m;
	}

	@Transactional
	public void createRelationship(Actor a, Movie m) {
		Role r = new Role(m, a);
		a.addMovie(m);
		m.addCast(a);
		roleRepo.save(r);
	}

	@Transactional
	public void createRelationship(Actor a, Award aw, boolean winner) {
		Nomination n = new Nomination(a, aw, winner);
		a.addNomindatedFor(aw);
		aw.addNominee(a);
		nominationRepository.save(n);
	}

	public Movie retrieveMovie(String name) {
		return movieRepo.findByPropertyValue("name", name);
	}

	@Transactional
	public void createAllData(List<String> importRow) {
		Map<String, Movie> allMovies = new HashMap<String, Movie>();
		Map<String, Actor> allActors = new HashMap<String, Actor>();
		Map<String, Award> allAwards = new HashMap<String, Award>();

		// create all node objects
		for (String row : importRow) {
			String[] entry = row.split(",");
			String award = entry[0];
			String nominee = entry[1];
			String movie = entry[2];

			if (allActors.get(nominee) == null) {
				allActors.put(nominee, actorService.createActor(nominee));
			}
			if (allMovies.get(movie) == null) {
				allMovies.put(movie, createMovie(movie));
			}
			if (allAwards.get(award) == null) {
				allAwards.put(award, awardService.createAward(award));
			}
		}

		// create all relationships
		for (String row : importRow) {
			String[] entry = row.split(",");
			String award = entry[0];
			String nominee = entry[1];
			String movie = entry[2];
			String winner = entry[3];

			Actor a = allActors.get(nominee);
			Movie m = allMovies.get(movie);
			Award aw = allAwards.get(award);

			// link actor and award
			createRelationship(a, aw, "true".equalsIgnoreCase(winner));

			// link actor and movie
			createRelationship(a, m);
		}

		for (Nomination n : nominationRepository.findAll()) {
			System.out.println("Nomination found: " + n.getNominee().getName()
					+ " >> " + n.getAward().getCategory());
		}
	}

}
