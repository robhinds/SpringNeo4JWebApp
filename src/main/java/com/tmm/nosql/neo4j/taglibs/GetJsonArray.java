/**
 * 
 */
package com.tmm.nosql.neo4j.taglibs;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * @author robert.hinds
 *
 * TagLib designed to get String from Google JSON Array
 */
public class GetJsonArray extends TagSupport{
	
	private static final long serialVersionUID = 2617352233757753085L;
	private JsonObject json = null;
	private String key;
	private String var;

	@Override
	public int doStartTag() throws JspException {
	      try {
	    	  if (json!=null && key!=null){
	    		  JsonElement j = json.get(key);
	    		  
	    		  JsonArray array;
	    		  if (j instanceof JsonArray){
	    			  array = (JsonArray) j;
	    		  }else{
	    			  array = new JsonArray();
	    		  }
	    		  
	    		  pageContext.setAttribute(var, (array), PageContext.PAGE_SCOPE);  
	    	  }
	      } catch (Exception e) {
	    	  e.printStackTrace();
	         throw new JspTagException("Error getting String From JSON: " + e.getMessage());
	      }
	      return SKIP_BODY;
	   }
	
	
	public JsonObject getJson() {
		return json;
	}
	public void setJson(JsonObject json) {
		this.json = json;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}


	public String getVar() {
		return var;
	}


	public void setVar(String var) {
		this.var = var;
	}	
}