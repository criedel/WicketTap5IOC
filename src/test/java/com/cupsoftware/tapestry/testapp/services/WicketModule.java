package com.cupsoftware.tapestry.testapp.services;

import org.apache.tapestry5.ioc.ServiceBinder;

import com.cupsoftware.tapestry.TapestryApplication;
import com.cupsoftware.tapestry.util.TapestryTestApplication;


public class WicketModule {

    public static void bind(ServiceBinder binder) {

        binder.bind(TapestryApplication.class, TapestryTestApplication.class);
        
        binder.bind(MyServiceInterface.class, MyServiceTest.class).withId("MyServiceTest");
        
        binder.bind(MyServiceInterface.class, MyOtherService.class).withId("MyOtherService");
    }
}
