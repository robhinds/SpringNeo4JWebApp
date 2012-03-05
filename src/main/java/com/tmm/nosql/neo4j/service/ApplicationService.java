/**
 * 
 */
package com.tmm.nosql.neo4j.service;

import java.util.UUID;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;


/**
 * @author robert.hinds
 *
 */
public class ApplicationService implements ApplicationContextAware
{
	private static ApplicationContext staticAppContext;
	private ApplicationContext applicationContext;

	public static final UUID saltID = UUID.randomUUID();


	public static ApplicationService getInstance()
	{
		return (ApplicationService) staticAppContext.getBean("applicationController");
	}
	@Override
	public void setApplicationContext(ApplicationContext ctx) throws BeansException
	{
		this.applicationContext = ctx;
		staticAppContext = ctx;
	}
	public ApplicationService()
	{
	}

	public ApplicationContext getApplicationContext()
	{
		return applicationContext;
	}


}
