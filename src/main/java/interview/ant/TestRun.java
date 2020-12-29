package interview.ant;

import java.io.File;
import java.util.Arrays;
import java.util.TreeMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 测试运行类
 *
 * @author gurq
 * @date 2020/11/30 10:20 下午
 */
public class TestRun {
    public static void main(String[] args) {
        run();
    }

    private static void run() {
        // 初始化测试文件
        System.out.println(CreateTestFile.init());

        // 准备文件
        File folder = new File(GlobalVariable.FOLDER_PATH);
        // 这里不需要线程安全，因为每被forkJoin切割一次，就会生成新的list，每个worker都持有他自己线程的list
        // 当然，这里可能会导致内存泄漏，所以原来用的是array，后来改成list了，每次用完手动释放，所以下面传给producer的时候转成list了
        File[] fileArr = folder.listFiles(file -> file.getName().endsWith(GlobalVariable.FILE_SUFFIX));
        // 准备所有要计数的行数，生产一遍，消费一遍，所以是2*
        CountDownLatch countDownLatch = new CountDownLatch(2 * GlobalVariable.TOTAL_FILE_COUNT * GlobalVariable.DATA_COUNT_PER_FILE);
        // 准备多线程下要并发写的线程安全的队列
        LinkedBlockingQueue<Data> dataQueue = new LinkedBlockingQueue<>();
        TreeMap<String, Data> treeMap = new TreeMap<>();

        // 先启动消费者线程，这样刚一读就开始消费
        Consumer consumer = new Consumer(dataQueue, countDownLatch, treeMap);
        new Thread(consumer).start();

        // 初始化forkJoinPool，指定线程数是10
        ForkJoinPool forkJoinPool = new ForkJoinPool(10);
        // 传入一个完整的任务，指定一次切片的最大大小是5，这里其实切割成1就跟普通线程池差不多了
        forkJoinPool.invoke(new Producer(5, dataQueue, countDownLatch, Arrays.asList(fileArr)));

        try {
            //生产者线程和消费者线程执行完成，关闭线程池，输出结果
            countDownLatch.await();
            forkJoinPool.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 这里2000个值分布在0到200，最小值小于1的概率真的很大。。。不是数据出问题了
        treeMap.forEach((key, value) -> System.out.println(value.toString()));

    }
}
