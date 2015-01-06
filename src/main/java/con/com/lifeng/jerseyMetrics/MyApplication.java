package con.com.lifeng.jerseyMetrics;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.jersey.InstrumentedResourceMethodDispatchAdapter;
import com.sun.jersey.api.core.DefaultResourceConfig;
import con.com.lifeng.jerseyMetrics.rest.ExampleResource;

import javax.ws.rs.core.Application;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by lifeng on 15-1-6.
 */
public class MyApplication extends Application {
    private final DefaultResourceConfig config = new DefaultResourceConfig();
    static final MetricRegistry metrics = new MetricRegistry();
    private static ConsoleReporter reporter = ConsoleReporter.forRegistry(metrics).convertRatesTo(TimeUnit.SECONDS)
            .convertDurationsTo(TimeUnit.MILLISECONDS).build();

    public MyApplication(){
        config.getSingletons().add(new InstrumentedResourceMethodDispatchAdapter(metrics));
        config.getClasses().add(ExampleResource.class);
        reporter.start(30,TimeUnit.SECONDS);
    }
    public Set<Class<?>> getClasses() {
        return config.getClasses();
    }
    public Set<Object> getSingletons(){
        return config.getSingletons();
    }
}
