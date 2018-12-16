package au.com.infomedia;

import au.com.infomedia.health.TemplateHealthCheck;
import au.com.infomedia.resources.HelloWorldResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class SettingsEntryPoint extends Application<DropwizardConfiguration> {

    public static void main(String[] args) {
        try {
            new SettingsEntryPoint().run(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<DropwizardConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(DropwizardConfiguration configuration,
                    Environment environment) {
        final HelloWorldResource resource = new HelloWorldResource(
                configuration.getTemplate(),
                configuration.getDefaultName()
        );
        final TemplateHealthCheck healthCheck =
                new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);
        environment.jersey().register(resource);
    }
}
