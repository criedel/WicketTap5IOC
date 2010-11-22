package com.cupsoftware.tapestry.testapp.pages;

import java.text.SimpleDateFormat;

import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.InjectService;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;

import com.cupsoftware.tapestry.testapp.services.InjectableService;
import com.cupsoftware.tapestry.testapp.services.MyServiceInterface;

public class TestHomePage extends WebPage {

    @InjectService(value = "MyOtherService")
    private MyServiceInterface other;

    @InjectService(value = "MyServiceTest")
    private MyServiceInterface test;
    
    @Inject
    private InjectableService injectableService;

    /**
     * Constructor that is invoked when page is invoked without a session.
     * 
     * @param parameters
     *            Page parameters
     */
    public TestHomePage(final PageParameters parameters) {

        getSession().bind();
        // Add the simplest type of label

        add(new Label("otherOutput", other.getString()));

        add(new Label("testOutput", test.getString()));

        add(new Label("injectTest", new SimpleDateFormat().format(injectableService.getCurrentDate())));
    }
}
