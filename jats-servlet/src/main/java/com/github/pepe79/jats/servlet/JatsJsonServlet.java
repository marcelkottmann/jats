package com.github.pepe79.jats.servlet;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.pepe79.jats.json.JastJsonViewGenerator;
import com.github.pepe79.jats.json.idextractor.BeanPropertyIdExtractor;
import com.github.pepe79.jats.json.idextractor.IdExtractor;
import com.github.pepe79.jats.repository.Repository;
import com.github.pepe79.jats.repository.RepositoryFactory;
import com.github.pepe79.jats.view.ViewFactory;

public class JatsJsonServlet extends HttpServlet
{

	private static final Pattern URL_PATTERN = Pattern.compile("\\/([a-zA-Z0-9]*)\\/?([a-zA-Z0-9]*)\\/?([a-zA-Z0-9]*)");

	private static final String REPOSITORY_FACTORY_CLASS_INIT_PARAM = "repositoryFactoryClass";

	private static final String ID_EXTRACTOR_INIT_PARAM = "idExtractorClass";

	private static final String VIEW_FACTORY_CLASS_INIT_PARAM = "viewFactoryClass";

	private Repository<?> respository;

	private IdExtractor idExtractor;

	private Map<String, Set<String>> views;

	@Override
	public void init(ServletConfig config) throws ServletException
	{
		super.init(config);

		createRepository(config);
		createIdExtractor(config);
		createViews(config);
	}

	private void createViews(ServletConfig config) throws ServletException
	{
		String viewFactoryClass = config.getInitParameter(VIEW_FACTORY_CLASS_INIT_PARAM);
		if (viewFactoryClass != null)
		{
			ViewFactory factory;
			try
			{
				factory = (ViewFactory) Class.forName(viewFactoryClass).newInstance();
				views = factory.createViews();
			}
			catch (Exception e)
			{
				throw new ServletException("An error occured while instantiating view factory class '"
						+ viewFactoryClass + "'.", e);
			}
		}
		else
		{
			// using default view
			views = null;
		}
	}

	private void createIdExtractor(ServletConfig config) throws ServletException
	{
		String idExtractorClass = config.getInitParameter(ID_EXTRACTOR_INIT_PARAM);
		if (idExtractorClass != null)
		{
			try
			{
				idExtractor = (IdExtractor) Class.forName(idExtractorClass).newInstance();
			}
			catch (Exception e)
			{
				throw new ServletException("An error occured while instantiating idExtractor class '"
						+ idExtractorClass + "'.", e);
			}
		}
		else
		{
			// using default id extractor
			idExtractor = new BeanPropertyIdExtractor("id", true);
		}
	}

	private void createRepository(ServletConfig config) throws ServletException
	{
		String repositoryFactoryClass = config.getInitParameter(REPOSITORY_FACTORY_CLASS_INIT_PARAM);
		if (repositoryFactoryClass != null)
		{
			RepositoryFactory<?> factory;
			try
			{
				factory = (RepositoryFactory<?>) Class.forName(repositoryFactoryClass).newInstance();
			}
			catch (Exception e)
			{
				throw new ServletException("An error occured while instantiating repository factory class '"
						+ repositoryFactoryClass + "'.", e);
			}

			respository = factory.createRepository();
			if (respository == null)
			{
				throw new ServletException("Repository factory returns null when calling createRepository().");
			}
		}
		else
		{
			throw new ServletException(
					"Repository factory is not correctly configured. Please specify a init parameter for '"
							+ REPOSITORY_FACTORY_CLASS_INIT_PARAM + "' in servlet configuration.");
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		resp.setContentType("application/json");

		Object object = null;
		String viewId = null;

		Matcher m = URL_PATTERN.matcher(req.getPathInfo());
		if (m.matches())
		{
			String type = m.group(1);
			String id = m.group(2);
			viewId = m.group(3);

			object = respository.find(type, id);
		}

		if (object != null)
		{
			JastJsonViewGenerator generator = new JastJsonViewGenerator(idExtractor, views);
			generator.toJson(resp.getWriter(), object, viewId, new Boolean(req.getParameter("pretty")));
		}
		else
		{
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}

	public void setRespository(Repository<?> respository)
	{
		this.respository = respository;
	}

	public void setIdExtractor(IdExtractor idExtractor)
	{
		this.idExtractor = idExtractor;
	}

	public void setViews(Map<String, Set<String>> views)
	{
		this.views = views;
	}

}
