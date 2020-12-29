package interview.ant;

import interview.ant.GlobalVariable;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RecursiveAction;

/**
 * 生产者
 *
 * @author gurq
 * @date 2020/11/30 11:28 下午
 */
public class Producer extends RecursiveAction {
    /**
     * 每个线程，最多处理的文件数
     */
    private final int threshold;

    /**
     * 线程安全的待排序队列
     */
    private LinkedBlockingQueue<Data> dataQueue;
    /**
     * countDownLatch
     */
    private CountDownLatch countDownLatch;
    /**
     * 待处理文件list
     */
    private List<File> fileList;


    public Producer() {
        // 默认一次处理5个吧
        this.threshold = 5;
    }

    public Producer(int threshold,
                    LinkedBlockingQueue<Data> dataQueue,
                    CountDownLatch countDownLatch,
                    List<File> fileList) {
        this.threshold = threshold;
        this.dataQueue = dataQueue;
        this.countDownLatch = countDownLatch;
        this.fileList = fileList;
    }

    @Override
    protected void compute() {
        if (fileList == null || fileList.size() < 1) {
            return;
        }

        if (fileList.size() > threshold) {
            // 将大任务分解成2个小任务
            int middle = fileList.size() / 2;

            Producer left = new Producer(threshold, dataQueue, countDownLatch, new ArrayList<>(fileList.subList(0, middle)));
            Producer right = new Producer(threshold, dataQueue, countDownLatch, new ArrayList<>(fileList.subList(middle, fileList.size())));

            // 并行执行
            invokeAll(left, right);
            return;
        }

        // 去掉这里的注释运行可以看到，虽然工作队列数很多，但是实际上同时就10个线程在运行，这个工作队列数和老的线程池那个不是一个概念
//        try {
//            System.out.println(Thread.currentThread().getName());
//            Thread.sleep(10000000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        // 开始执行任务
        fileList.forEach(file -> {
            try (InputStreamReader read = new InputStreamReader(new FileInputStream(file));
                 BufferedReader br = new BufferedReader(read)) {
                String line;
                String[] arr;
                while ((line = br.readLine()) != null) {
                    if ("".equals(line)) {
                        continue;
                    }
                    arr = line.split(GlobalVariable.SPLIT);
                    Data data = new Data();
                    data.setId(arr[0]);
                    data.setGroupId(arr[1]);
                    data.setQuota(new Float(arr[2]));
                    dataQueue.add(data);
                    System.out.println("生产一条数据:" + data.toString());
                    countDownLatch.countDown();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        // 清空list，方便回收
        fileList.clear();
    }
}
