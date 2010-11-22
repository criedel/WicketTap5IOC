# Wicket + Tapestry IOC

## How to use

Build wicket-tapestry5 from the sources.

Add the dependency to your project:

        <dependency>
            <groupId>com.cupsoftware</groupId>
            <artifactId>wicket-tapestry5</artifactId>
            <version>1.0.0-SNAPSHOT</version>
            <type>jar</type>
            <scope>compile</scope>
        </dependency>

Adjust your web.xml filter config like this (you need a module!):

    <filter>
        <filter-name>test</filter-name>
        <filter-class>org.apache.wicket.protocol.http.WicketFilter</filter-class>
        <init-param>
            <param-name>module</param-name>
            <param-value>com.example.services.TestModule</param-value>
        </init-param>
        <init-param>
            <param-name>applicationFactoryClassName</param-name>
            <param-value>com.cupsoftware.tapestry.TapestryIOCWebApplicationFactory</param-value>
        </init-param>
    </filter>

and bind your Implementation of Wicket's WebApplication:

public class TestModule {

    public static void bind(ServiceBinder binder) {
        binder.bind(WebApplication.class, TestApplication.class);
    }
}
        
Now you're done :-)

## Motivation
Recently I started to work with Apache Wicket in a client project. They chose 
Spring for DI, which I was quite comfortable with, since I worked with Spring 
for a couple of years already.

After some weeks of working with that combination I came across some kind of
limitation of this architecture (don't want become too detailed on this one).
I wasn't satisfied with the integration, it just didn't make fun to code.

## Ok, but why Tapestry IOC?
Having used Tapestry 5 for about two years now I am used to its way of coding.
Live-Class-Reloading and other things make development so much easier and 
Tapestry 5.2 introduced Live-Class-Reloading for its IOC services!

Actually, I thought it would be a good joke to mix Wicket and Tapestry and make
some die-hard-Wicket user use Tapestry to fill the IOC gap - instead of Spring
and Guice.


    
