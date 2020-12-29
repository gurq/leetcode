package interview;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * 题目来自【号称】阿里子公司：熙牛医疗
 *
 * 设计一个算法，随机生成n个不重复的长度为36的字符串
 *
 * @author gurq
 * @date 2020/11/29 2:01 上午
 */
public class Demo02 {
    public static void main(String[] args) {
        Set set = new HashSet();
        putInSet(set, 10);
        System.out.println(set);
    }

    private static void putInSet(Set set, int count){
        if (set != null && set.size() >= count){
            return;
        }
        String[] base = "abcdefghigklmnopqrstuvwxyz".split("");
        while(set.size() < count){
            set.add(getUuid(base));
        }
    }

    private static String getUuid(String[] base){
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        while(sb.length() < 32){
            sb.append(base[random.nextInt(base.length)]);
        }
        return sb.toString();
    }
}
