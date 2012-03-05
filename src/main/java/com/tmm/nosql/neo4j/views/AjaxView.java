package com.tmm.nosql.neo4j.views;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;

import com.google.gson.JsonObject;

public class AjaxView extends AbstractView {

	@Override
	protected void renderMergedOutputModel(Map map, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JsonObject jo = (JsonObject) map.get("graph");
		response.setContentType("text/plain; charset=UTF-8");
		response.getOutputStream().write(jo.toString().getBytes());
	}
}