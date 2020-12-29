package interview.ant;

import java.util.TreeMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 消费者
 *
 * @author gurq
 * @date 2020/11/30 11:59 下午
 */
public class Consumer implements Runnable {
    /**
     * 线程安全的待排序队列
     */
    private LinkedBlockingQueue<Data> dataQueue;
    /**
     * countDownLatch
     */
    private CountDownLatch countDownLatch;
    /**
     * 要分组，那肯定得是map型的，又要key有序，那就只能treeMap了，
     * 这里只有一个线程在写，所以没有线程安全问题
     */
    private TreeMap<String, Data> treeMap;

    public Consumer() {
    }

    public Consumer(LinkedBlockingQueue<Data> dataQueue, CountDownLatch countDownLatch, TreeMap<String, Data> treeMap) {
        this.dataQueue = dataQueue;
        this.countDownLatch = countDownLatch;
        this.treeMap = treeMap;
    }

    @Override
    public void run() {
        try {
            while (true) {
                if (!dataQueue.isEmpty()) {
                    Data queueData = dataQueue.take();
                    countDownLatch.countDown();
                    System.out.println("消费一条数据:" + queueData.toString());
                    Data mapData = treeMap.get(queueData.getGroupId());
                    if (mapData == null || queueData.getQuota() < mapData.getQuota()) {
                        treeMap.put(queueData.getGroupId(), queueData);
                    }
                    continue;
                }

                if (countDownLatch.getCount() <= 1) {
                    countDownLatch.countDown();
                    break;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
