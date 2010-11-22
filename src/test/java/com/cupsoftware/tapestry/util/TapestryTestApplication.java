package com.cupsoftware.tapestry.util;

import javax.servlet.http.HttpServletResponse;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.HttpSessionStore;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.WebResponse;
import org.apache.wicket.session.ISessionStore;

import com.cupsoftware.tapestry.testapp.pages.TestHomePage;

/**
 * Dummy-Test-Application
 * 
 * @author criedel
 */
public class TapestryTestApplication extends WebApplication {

    private Class<? extends Page> homePage;

    public TapestryTestApplication() {

        this(TestHomePage.class);
    }

    /**
     * @param homePage
     */
    public TapestryTestApplication(Class<? extends Page> homePage) {

        super();
        this.homePage = homePage;
    }

    @Override
    public Class<? extends Page> getHomePage() {

        return this.homePage;
    }

    @Override
    protected ISessionStore newSessionStore() {

        // Don't use a filestore, or we spawn lots of threads, which
        // makes things slow.
        return new HttpSessionStore(this);
    }

    @Override
    protected WebResponse newWebResponse(final HttpServletResponse servletResponse) {

        return new WebResponse(servletResponse);
    }

    @Override
    protected void outputDevelopmentModeWarning() {

        // Do nothing.
    }

    @Override
    public void init() {

    }
    
}