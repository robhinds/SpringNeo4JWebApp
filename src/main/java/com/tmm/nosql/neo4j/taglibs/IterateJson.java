/**
 * 
 */
package com.tmm.nosql.neo4j.taglibs;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import com.google.gson.JsonArray;

/**
 * @author robert.hinds
 *
 * TagLib designed to iterate through Google JSON Array
 */
public class IterateJson extends TagSupport{
	
	private static final long serialVersionUID = -102924264339712780L;
	private JsonArray items = null;
	private int position=0;
	private String var;
	private String counter;


	@Override
	public int doStartTag() throws JspException{
		return processIteration();
	}

	private int processIteration() {
		if (items!=null && items.size()>position){
			pageContext.setAttribute(var, items.get(position), PageContext.PAGE_SCOPE);
			if (counter!=null){
				pageContext.setAttribute(counter, position, PageContext.PAGE_SCOPE);
			}
			position ++;
			return EVAL_BODY_AGAIN;
		}
		else{
			position=0;
			return SKIP_BODY;
		}
	}
	
	@Override
	public int doAfterBody() throws JspException {
		return processIteration();
	}


	/**
	 * @param items the items to set
	 */
	public void setItems(JsonArray items) {
		this.items = items;
	}


	/**
	 * @return the items
	 */
	public JsonArray getItems() {
		return items;
	}


	/**
	 * @param var the var to set
	 */
	public void setVar(String var) {
		this.var = var;
	}


	/**
	 * @return the var
	 */
	public String getVar() {
		return var;
	}
	

	public String getCounter() {
		return counter;
	}

	public void setCounter(String counter) {
		this.counter = counter;
	}
}