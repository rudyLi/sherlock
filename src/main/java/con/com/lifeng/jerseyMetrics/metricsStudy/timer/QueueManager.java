package con.com.lifeng.jerseyMetrics.metricsStudy.timer;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by lifeng on 15-3-23.
 */
public class QueueManager {
    private final Queue<String> queue;
    private final Timer timer;
    public QueueManager(MetricRegistry metrics, String name) {
        queue = new LinkedBlockingQueue<String>();
        timer = metrics.timer(MetricRegistry.name(QueueManager.class,"insert-speed"));
    }
    public void addElement(String a){
        queue.offer(a);
        timer.time();
    }
    public void removeElement(){
        queue.poll();
    }
}
