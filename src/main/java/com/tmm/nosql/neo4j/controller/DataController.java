package com.tmm.nosql.neo4j.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.google.common.collect.Maps;
import com.google.gson.JsonObject;
import com.tmm.nosql.neo4j.batch.DataLoader;
import com.tmm.nosql.neo4j.domain.nodes.Actor;
import com.tmm.nosql.neo4j.domain.nodes.Award;
import com.tmm.nosql.neo4j.domain.nodes.Movie;
import com.tmm.nosql.neo4j.factory.GraphFactory;
import com.tmm.nosql.neo4j.factory.GraphFactory.NodeType;
import com.tmm.nosql.neo4j.service.ActorService;
import com.tmm.nosql.neo4j.service.AwardService;
import com.tmm.nosql.neo4j.service.MovieService;

import edu.emory.mathcs.backport.java.util.Arrays;

public class DataController extends MultiActionController implements
		InitializingBean {

	@Autowired
	private ActorService actorService;
	@Autowired
	private AwardService awardService;
	@Autowired
	private MovieService movieService;

	public void setActorService(ActorService actorService) {
		this.actorService = actorService;
	}

	public void setAwardService(AwardService awardService) {
		this.awardService = awardService;
	}

	public void setMovieService(MovieService movieService) {
		this.movieService = movieService;
	}

	public void afterPropertiesSet() throws Exception {
	}

	public ModelAndView loadMovieData(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		JsonObject graph = new JsonObject();
		JsonObject nodes = new JsonObject();
		JsonObject edges = new JsonObject();

		for (Actor a : actorService.findAllActors()) {
			nodes.add(a.getName(), GraphFactory.createNode(NodeType.ACTOR));
			List<String> targets = new ArrayList<String>();
			for (Movie m : a.getMovies()) {
				targets.add(m.getName());
			}
			for (Award award : a.getNominatedFor()) {
				targets.add(award.getCategory());
			}
			edges.add(a.getName(), GraphFactory.createEdge(targets));
		}
		for (Movie m : movieService.findAllMovies()) {
			nodes.add(m.getName(), GraphFactory.createNode(NodeType.MOVIE));
		}
		for (Award a : awardService.findAllAwards()) {
			nodes.add(a.getCategory(), GraphFactory.createNode(NodeType.AWARD));
			edges.add(a.getCategory(), GraphFactory.createEdge(Arrays
					.asList(new String[] { "Oscars 2012" })));
		}

		nodes.add("Oscars 2012", GraphFactory.createNode(NodeType.OSCARS));

		graph.add("nodes", nodes);
		graph.add("edges", edges);
		Map<String, Object> model = Maps.newHashMap();
		model.put("graph", graph);
		return new ModelAndView("ajax_graph", model);
	}

	public ModelAndView initData(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("loading movie data..");
		URL url = DataLoader.class.getResource("/oscars_data.csv");
		URLConnection connection = url.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(
				connection.getInputStream()));

		String inputLine;
		List<String> entries = new ArrayList<String>();
		while ((inputLine = in.readLine()) != null) {
			entries.add(inputLine);
		}
		movieService.createAllData(entries);
		System.out.println("Finished loading data");
		return null;
	}
}
