package com.cupsoftware.tapestry;

import org.apache.tapestry5.ioc.Registry;
import org.apache.tapestry5.ioc.RegistryBuilder;
import org.apache.wicket.Application;
import org.apache.wicket.injection.web.InjectorHolder;
import org.apache.wicket.protocol.http.IWebApplicationFactory;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.WicketFilter;

public class TapestryIOCWebApplicationFactory implements IWebApplicationFactory {

    /**
     * @see IWebApplicationFactory#createApplication(WicketFilter)
     */
    @Override
    public WebApplication createApplication(WicketFilter filter) {
        
        String module = filter.getFilterConfig().getInitParameter("module");

        final RegistryBuilder builder = new RegistryBuilder();
        builder.add(module);
        
        final Registry registry = builder.build();
        registry.performRegistryStartup();

        final WebApplication app = registry.getService(TapestryApplication.class).getWebApplication();
        final TapestryIOCInjector injector = new TapestryIOCInjector(registry);
        app.addComponentInstantiationListener(injector);
        app.addComponentInitializationListener(injector);

        Application.set(app);
        
        InjectorHolder.setInjector(injector);
        
        return app;
    }
}
