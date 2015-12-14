package com.business;

import com.business.resources.BusinessResource;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class BusinessApplication extends Application<Configuration> {
    public static void main(String[] args) throws Exception {
        new BusinessApplication().run(args);
    }

    @Override
    public String getName() {
        return "business";
    }

    @Override
    public void initialize(Bootstrap<Configuration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(Configuration configuration, Environment environment) throws ClassNotFoundException {
        final BusinessResource businessResource = new BusinessResource();

        environment.jersey().register(businessResource);
    }
}
