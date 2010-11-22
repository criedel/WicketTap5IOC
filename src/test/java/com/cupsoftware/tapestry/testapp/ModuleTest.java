package com.cupsoftware.tapestry.testapp;

import org.apache.tapestry5.ioc.Registry;
import org.apache.tapestry5.ioc.RegistryBuilder;
import org.apache.wicket.protocol.http.WebApplication;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.cupsoftware.tapestry.TapestryIOCInjector;
import com.cupsoftware.tapestry.testapp.pages.TestHomePage;
import com.cupsoftware.tapestry.testapp.services.WicketModule;
import com.cupsoftware.tapestry.util.ApplicationTester;


public class ModuleTest {

    protected WebApplication app;
    
    @BeforeSuite
    public void setup() {
        
        final RegistryBuilder builder = new RegistryBuilder();
        builder.add(WicketModule.class);
        
        final Registry registry = builder.build();
        registry.performRegistryStartup();
        
        app = registry.getService(WebApplication.class);

        final TapestryIOCInjector injector = new TapestryIOCInjector(registry);
        app.addComponentInstantiationListener(injector);
        app.addComponentInitializationListener(injector);
        
    }
    
    @Test
    public void testModule() {

        final ApplicationTester applicationTester = new ApplicationTester(app);
        applicationTester.startPage(TestHomePage.class);
        applicationTester.assertContains("TEST HOME PAGE");
    }
    
}
