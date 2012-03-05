package com.tmm.nosql.neo4j.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class HomeController extends MultiActionController implements
		InitializingBean {

	public void afterPropertiesSet() throws Exception {
	}

	public ModelAndView anonhome(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ModelAndView("homepage");
	}
}
