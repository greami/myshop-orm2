package it.myshop.web;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.FrameworkServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer
{

	
	@Override
	protected Class<?>[] getRootConfigClasses()
	{
		// TODO Auto-generated method stub
		return null;
	}

	//dice che classi utilizzare per le configurazioni della applicazinoe web
	@Override
	protected Class<?>[] getServletConfigClasses()
	{
		// TODO Auto-generated method stub
		return new Class<?>[] {MyshopConfig.class};
	}

	
	//dice qualì il path che deve intercettare il dispatcher all'interno
	@Override
	protected String[] getServletMappings()
	{
		// TODO Auto-generated method stub
		return new String [] {"/"}; // totto quello che arriva deve essere gestito da questa dispatcher servlet
	}

	
	@Override
	protected FrameworkServlet createDispatcherServlet(WebApplicationContext servletAppContext)
	{
		// TODO Auto-generated method stub
		
		DispatcherServlet ds = new DispatcherServlet(servletAppContext);
		ds.setThrowExceptionIfNoHandlerFound(true);
		return ds;
	}
	
	
	

}
