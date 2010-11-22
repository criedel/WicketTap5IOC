package com.cupsoftware.tapestry.testapp.services;

import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.wicket.protocol.http.WebApplication;

import com.cupsoftware.tapestry.util.TapestryTestApplication;


public class WicketModule {

    public static void bind(ServiceBinder binder) {

        binder.bind(WebApplication.class, TapestryTestApplication.class);
        
        binder.bind(MyServiceInterface.class, MyServiceTest.class).withId("MyServiceTest");
        
        binder.bind(MyServiceInterface.class, MyOtherService.class).withId("MyOtherService");

        binder.bind(InjectableService.class);
    }
}
