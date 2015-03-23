package con.com.lifeng.jerseyMetrics.metricsStudy.meter;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;

import java.util.concurrent.TimeUnit;

/**
 * Created by lifeng on 15-3-23.
 */
public class MetersStudy {
    static final MetricRegistry metrics = new MetricRegistry();
    static final Meter request = metrics.meter("request");

    public static void main(String[] args) {
        startReport();
        for(int i=0;i<5;i++) {
            handleRequest();
        }
    }
    static void startReport() {
        ConsoleReporter reporter = ConsoleReporter.forRegistry(metrics).convertRatesTo(TimeUnit.SECONDS).convertDurationsTo(TimeUnit.MILLISECONDS).build();
        reporter.start(1,TimeUnit.SECONDS);
    }
    static void handleRequest(){
        request.mark();
        wait5Seconds();
    }
    static void wait5Seconds() {
        try {
            Thread.sleep(5*1000);
        }
        catch(InterruptedException e) {}
    }
}
