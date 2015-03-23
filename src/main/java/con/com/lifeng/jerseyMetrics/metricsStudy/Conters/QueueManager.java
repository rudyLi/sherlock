package con.com.lifeng.jerseyMetrics.metricsStudy.Conters;

import com.codahale.metrics.Counter;
import com.codahale.metrics.Gauge;
import com.codahale.metrics.MetricRegistry;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by lifeng on 15-3-23.
 */
public class QueueManager {
    private final Queue<String> queue;
    private final Counter counter;
    public QueueManager(MetricRegistry metrics, String name) {
        queue = new LinkedBlockingQueue<String>();
        counter = metrics.counter(MetricRegistry.name(QueueManager.class,"pending-jobs"));
    }
    public void addElement(String a){
        counter.inc();
        queue.offer(a);
    }
    public void removeElement(){
        queue.poll();
        counter.dec();
    }
}
