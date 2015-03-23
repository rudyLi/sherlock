package con.com.lifeng.jerseyMetrics.metricsStudy.histograms;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricRegistry;

import java.util.concurrent.TimeUnit;

/**
 * Created by lifeng on 15-3-23.
 */
public class HistogramStudy {
    static final MetricRegistry metrics = new MetricRegistry();

    public static void main(String[] args) {
        final QueueManager queueManager = new QueueManager(metrics,"test");
        startReport();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<5;i++){
                    queueManager.addElement("la");
                    waitSeconds(1);
                }
            }
        }).run();
        waitSeconds(2);
        queueManager.removeElement();
        waitSeconds(10);
    }
    static void startReport() {
        ConsoleReporter reporter = ConsoleReporter.forRegistry(metrics).convertRatesTo(TimeUnit.SECONDS).convertDurationsTo(TimeUnit.MILLISECONDS).build();
        reporter.start(1,TimeUnit.SECONDS);
    }
    static void waitSeconds(int i) {
        try {
            Thread.sleep(i*1000);
        }
        catch(InterruptedException e) {}
    }
}
