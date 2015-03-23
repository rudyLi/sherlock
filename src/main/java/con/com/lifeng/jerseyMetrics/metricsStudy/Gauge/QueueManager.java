package con.com.lifeng.jerseyMetrics.metricsStudy.Gauge;

import com.codahale.metrics.Gauge;
import com.codahale.metrics.MetricRegistry;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by lifeng on 15-3-23.
 */
public class QueueManager {
    private final Queue<String> queue;
    private final Gauge<Integer> gaugeStatic;
    public QueueManager(MetricRegistry metrics, String name ) {
        queue = new LinkedBlockingQueue<String>();
        gaugeStatic = metrics.register(MetricRegistry.name(QueueManager.class,name,"size"),new Gauge<Integer>() {
            @Override
            public Integer getValue() {
                return queue.size();
            }
        });
    }
    public void addElement(String a){
        queue.offer(a);
        gaugeStatic.getValue();
    }
    public void removeElement(){
        queue.poll();
        gaugeStatic.getValue();
    }
}
