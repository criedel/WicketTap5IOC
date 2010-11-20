package com.cupsoftware.tapestry.testapp.pages;

import org.apache.tapestry5.ioc.annotations.InjectService;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;

import com.cupsoftware.tapestry.testapp.services.MyServiceInterface;

public class TestHomePage extends WebPage {

    @InjectService(value = "MyOtherService")
    private MyServiceInterface other;

    @InjectService(value = "MyServiceTest")
    private MyServiceInterface test;

    /**
     * Constructor that is invoked when page is invoked without a session.
     * 
     * @param parameters
     *            Page parameters
     */
    public TestHomePage(final PageParameters parameters) {

        getSession().bind();
        // Add the simplest type of label

        System.out.println(other.getString());

        System.out.println(test.getString());

    }
}
