package com.cupsoftware.tapestry;

import org.apache.tapestry5.ioc.Registry;
import org.apache.tapestry5.ioc.RegistryBuilder;
import org.apache.wicket.Application;
import org.apache.wicket.injection.web.InjectorHolder;
import org.apache.wicket.protocol.http.IDestroyableWebApplicationFactory;
import org.apache.wicket.protocol.http.IWebApplicationFactory;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.WicketFilter;

public class TapestryIOCWebApplicationFactory implements IDestroyableWebApplicationFactory {

    private Registry registry;

    /**
     * @see IWebApplicationFactory#createApplication(WicketFilter)
     */
    @Override
    public WebApplication createApplication(WicketFilter filter) {

        String module = filter.getFilterConfig().getInitParameter("module");

        final RegistryBuilder builder = new RegistryBuilder();
        builder.add(module);

        registry = builder.build();
        registry.performRegistryStartup();

        final WebApplication app = registry.getService(WebApplication.class);
        final TapestryIOCInjector injector = new TapestryIOCInjector(registry);
        app.addComponentInstantiationListener(injector);
        app.addComponentInitializationListener(injector);

        Application.set(app);

        InjectorHolder.setInjector(injector);

        return app;
    }

    @Override
    public void destroy() {

        registry.shutdown();
    }
}
