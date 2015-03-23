package con.com.lifeng.jerseyMetrics.metricsStudy.histograms;

import com.codahale.metrics.Histogram;
import com.codahale.metrics.MetricRegistry;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by lifeng on 15-3-23.
 */
public class QueueManager {
    private final Queue<String> queue;
    private final Histogram histogram;
    public QueueManager(MetricRegistry metrics, String name) {
        queue = new LinkedBlockingQueue<String>();
        histogram = metrics.histogram(MetricRegistry.name(QueueManager.class,"sizeStatic"));
    }
    public void addElement(String a){
        queue.offer(a);
        histogram.update(queue.size());
    }
    public void removeElement(){
        queue.poll();
        histogram.update(queue.size());
    }
}
