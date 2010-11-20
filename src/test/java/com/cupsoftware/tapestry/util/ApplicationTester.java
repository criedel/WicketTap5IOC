package com.cupsoftware.tapestry.util;

import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.util.tester.WicketTester;

public class ApplicationTester extends WicketTester {

    /**
     * @param application
     */
    public ApplicationTester(WebApplication application) {

        this(application, null);
    }
    
    /**
     * @param application
     * @param path
     */
    public ApplicationTester(WebApplication application, String path) {
        
        super(application, path);
        
    }

}
