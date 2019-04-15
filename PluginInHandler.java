package pluginconfig;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;

import org.apache.commons.io.FilenameUtils;
import org.plugface.core.PluginManager;
import org.plugface.core.factory.PluginManagers;
import org.plugface.core.factory.PluginSources;

import controllers.plugininit.CustomPluginManager;
import controllers.plugininit.CustomPluginSource;
import ie.ul.interfaces.Chart;


public class PluginInHandler
{
	private static PluginInHandler initializer;
	private static PluginManager manager;
	private static ServletContext context;
	
	private PluginInHandler()
	{
		
	}
	
	public static PluginInHandler getInstance(ServletContext context, HttpServlet servlet)
	{
		if(initializer == null)
		{
			initializer = new PluginInHandler();
			PluginInHandler.context = context;
			loadPluginLibrary(servlet);
			
		}
		return initializer;
	}
	
	private static void loadPluginLibrary(HttpServlet servlet)
	{
		manager = CustomPluginManager.defaultPluginManager();
		try
		{
			manager.loadPlugins(CustomPluginSource.jarSource("path/to/jarfolder",servlet));
                        // you can give the path like this "file:///E:/EE/UL/PluginJars/"
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
		
	}
	
	public PluginManager getPluginManager()
	{
		return manager;
	}
	
}
