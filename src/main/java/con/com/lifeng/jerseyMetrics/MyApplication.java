package con.com.lifeng.jerseyMetrics;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricFilter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.graphite.Graphite;
import com.codahale.metrics.graphite.GraphiteReporter;
import com.codahale.metrics.jersey.InstrumentedResourceMethodDispatchAdapter;
import com.sun.jersey.api.core.DefaultResourceConfig;
import con.com.lifeng.jerseyMetrics.rest.ExampleResource;

import javax.ws.rs.core.Application;
import java.net.InetSocketAddress;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by lifeng on 15-1-6.
 */
public class MyApplication extends Application {
    private final DefaultResourceConfig config = new DefaultResourceConfig();
    static MetricRegistry metrics;
//    private static ConsoleReporter reporter = ConsoleReporter.forRegistry(metrics).convertRatesTo(TimeUnit.SECONDS)
//                       .convertDurationsTo(TimeUnit.MILLISECONDS).build();
    static Graphite graphite;
    static GraphiteReporter reporter;

    public MyApplication() {
        metrics = new MetricRegistry();
        graphite = new Graphite(new InetSocketAddress("192.168.56.5", 2003));
        reporter = GraphiteReporter.forRegistry(metrics)
                .prefixedWith("nihao")
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .filter(MetricFilter.ALL)
                .build(graphite);
        config.getSingletons().add(new InstrumentedResourceMethodDispatchAdapter(metrics));
        config.getClasses().add(ExampleResource.class);
        reporter.start(30, TimeUnit.SECONDS);
    }

    public Set<Class<?>> getClasses() {
        return config.getClasses();
    }

    public Set<Object> getSingletons() {
        return config.getSingletons();
    }
}
