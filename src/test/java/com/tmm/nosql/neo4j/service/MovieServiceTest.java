package com.tmm.nosql.neo4j.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.tmm.nosql.neo4j.domain.nodes.Actor;
import com.tmm.nosql.neo4j.domain.nodes.Movie;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:META-INF/spring/applicationContext.xml" })
@Transactional
public class MovieServiceTest {

	@Autowired
	MovieService movieService;
	@Autowired
	ActorService actorService;
	@Autowired
	Neo4jOperations template;

	public void setMovieService(MovieService movieService) {
		this.movieService = movieService;
	}

	public void setActorService(ActorService actorService) {
		this.actorService = actorService;
	}

	@Test
	public void createMovie() {
		Movie m = movieService.createMovie("Hugo");
		Movie m2 = movieService.retrieveMovie("Hugo");
		assertEquals("Persist and load Movie", m.getId(), m2.getId());
	}

	@Test
	public void createRelationship() {
		Actor a = actorService.createActor("Brad Pitt");
		Movie m = movieService.createMovie("Hugo");
		movieService.createRelationship(a, m);
		Movie m2 = movieService.retrieveMovie("Hugo");
		assertEquals("Check Movie has been persisted", m.getId(), m2.getId());
		Actor a2 = actorService.retrieveActor("Brad Pitt");
		assertEquals("Check Actor has been persisted", a.getId(), a2.getId());

		assertNotNull("Check relationship has been created properly",
				a.getMovies());
		assertTrue("Check relationship has been created properly", a
				.getMovies().size() > 0);

		assertTrue("Check Movie Relationship is correct", a.getMovies()
				.contains(m));
	}

}
