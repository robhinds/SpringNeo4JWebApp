package com.tmm.nosql.neo4j.batch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tmm.nosql.neo4j.domain.nodes.Actor;
import com.tmm.nosql.neo4j.domain.nodes.Award;
import com.tmm.nosql.neo4j.domain.nodes.Movie;
import com.tmm.nosql.neo4j.service.ActorService;
import com.tmm.nosql.neo4j.service.AwardService;
import com.tmm.nosql.neo4j.service.MovieService;

@Service
public class DataLoader {

	@Autowired
	MovieService movieService;
	@Autowired
	ActorService actorService;
	@Autowired
	AwardService awardService;

	public static void main(String args[]) throws IOException {
		DataLoader loader = new DataLoader();
		loader.loadMovieData();
	}

	@Transactional
	public void loadMovieData() throws IOException {
		System.out.println("loading movie data..");
		URL url = DataLoader.class.getResource("/oscars_data.csv");
		URLConnection connection = url.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(
				connection.getInputStream()));

		String inputLine;
		StringBuilder allLines = new StringBuilder();
		System.out.println("XXX About to create objects..");
		while ((inputLine = in.readLine()) != null) {
			String[] entry = inputLine.split(",");
			createObjects(entry);
		}
		System.out.println("Finished loading data");
	}

	@Transactional
	private void createObjects(String[] entry) {
		String award = entry[0];
		String nominee = entry[1];
		String movie = entry[2];
		String winner = entry[3];

		Movie m = movieService.retrieveMovie(movie);
		if (m == null) {
			m = movieService.createMovie(movie);
		}
		Actor a = actorService.retrieveActor(nominee);
		if (a == null) {
			a = actorService.createActor(nominee);
		}
	}

	@Transactional
	public void loadMovieRelationships() throws IOException {
		System.out.println("loading movie data edges..");
		URL url = DataLoader.class.getResource("/oscars_data.csv");
		URLConnection connection = url.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(
				connection.getInputStream()));

		String inputLine;
		StringBuilder allLines = new StringBuilder();
		while ((inputLine = in.readLine()) != null) {
			String[] entry = inputLine.split(",");
			createRelationships(entry);
		}
		System.out.println("Finished loading data");
	}

	@Transactional
	private void createRelationships(String[] entry) {
		String award = entry[0];
		String nominee = entry[1];
		String movie = entry[2];
		String winner = entry[3];

		Movie m = movieService.retrieveMovie(movie);
		Actor a = actorService.retrieveActor(nominee);
		System.out.println("Creating movie-actor link!");
		System.out.println(m.getName());
		System.out.println(a.getName());
		movieService.createRelationship(a, m);
	}

	@Transactional
	public void loadAwardData() throws IOException {
		System.out.println("loading movie data..");
		URL url = DataLoader.class.getResource("/oscars_data.csv");
		URLConnection connection = url.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(
				connection.getInputStream()));

		String inputLine;
		StringBuilder allLines = new StringBuilder();
		System.out.println("XXX About to create objects..");
		while ((inputLine = in.readLine()) != null) {
			String[] entry = inputLine.split(",");
			createAwardObjects(entry);
		}
		System.out.println("Finished loading data");
	}

	@Transactional
	private void createAwardObjects(String[] entry) {
		String award = entry[0];
		String nominee = entry[1];
		String movie = entry[2];
		String winner = entry[3];

		Award a = awardService.retrieveAward(award);
		if (a == null) {
			a = awardService.createAward(award);
		}
	}

	@Transactional
	public void loadAwardEdges() throws IOException {
		System.out.println("loading movie data..");
		URL url = DataLoader.class.getResource("/oscars_data.csv");
		URLConnection connection = url.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(
				connection.getInputStream()));

		String inputLine;
		StringBuilder allLines = new StringBuilder();
		System.out.println("XXX About to create objects..");
		while ((inputLine = in.readLine()) != null) {
			String[] entry = inputLine.split(",");
			createAwardEdges(entry);
		}
		System.out.println("Finished loading data");
	}

	@Transactional
	private void createAwardEdges(String[] entry) {
		String award = entry[0];
		String nominee = entry[1];
		String movie = entry[2];
		String winner = entry[3];

		System.out.println("CREATING AWARD RELATIONSHIP:");
		System.out.println("Actor: " + nominee);
		System.out.println("Award: " + award);
		awardService.createRelationship(nominee, award,
				winner.equalsIgnoreCase("true"));
	}
}