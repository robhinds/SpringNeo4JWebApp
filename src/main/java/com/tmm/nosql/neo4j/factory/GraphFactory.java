package com.tmm.nosql.neo4j.factory;

import java.util.List;

import com.google.gson.JsonObject;

public class GraphFactory {

	public enum NodeType {
		AWARD, MOVIE, ACTOR, DIRECTOR, OSCARS
	};

	public static JsonObject createNode(NodeType type) {
		JsonObject node = new JsonObject();
		switch (type) {
		case ACTOR:
			node.addProperty("color", "rgb(255,81,61)");
			node.addProperty("shape", "rect");
			node.addProperty("alpha", "1");
			break;
		case MOVIE:
			node.addProperty("color", "rgb(81,61,255)");
			node.addProperty("shape", "rect");
			node.addProperty("alpha", "1");
			break;
		case AWARD:
			node.addProperty("color", "rgb(255,177,61)");
			node.addProperty("shape", "rect");
			node.addProperty("alpha", "1");
			break;
		case DIRECTOR:
			node.addProperty("color", "rgb(61,255,81)");
			node.addProperty("shape", "rect");
			node.addProperty("alpha", "1");
			break;
		case OSCARS:
			node.addProperty("color", "rgb(255,177,61)");
			node.addProperty("shape", "circle");
			node.addProperty("alpha", "1");
			break;
		}

		return node;
	}

	public static JsonObject createEdge(List<String> targets) {
		JsonObject edge = new JsonObject();
		for (String trg : targets) {
			edge.add(trg, new JsonObject());
		}

		return edge;
	}
}
