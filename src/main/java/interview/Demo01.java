package interview;

import java.util.*;

/**
 * 题目来自：古希
 *
 * 给一个n位数组，如{"b","a","c","b"}
 * 需求：控制台打印所有可能的排列，并按照字典顺序换行，且去掉重复的打印行
 * 如{"b","a","c","b"}控制台应打印
 * abbc abcb acbb
 * babc bbac bbca
 * cabb cbab cbba
 *
 * @author gurq
 * @date 2020/11/16 1:15 上午
 */
public class Demo01 {

    public static void main(String[] args) {
        String[] arr = new String[]{"b", "a", "c", "b"};
        List<String> list = getList(arr);
        printList(list);
    }

    private static void printList(List<String> list) {
        if (list == null) {
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + "\t");
            if (i + 1 < list.size() && list.get(i).charAt(0) != list.get(i + 1).charAt(0)) {
                System.out.println();
            }
        }
    }

    private static List<String> getList(String[] arr) {
        Set<String> set = new HashSet<>();
        addSet(arr, new ArrayList<Integer>(), set, 4);
        List<String> list = new ArrayList<>();
        list.addAll(set);
        Collections.sort(list);
        return list;
    }

    private static void addSet(String[] arr, List<Integer> indexList, Set<String> set, int maxLength) {
        if (indexList != null && indexList.size() == maxLength) {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < indexList.size(); i++) {
                sb.append(arr[indexList.get(i)]);
            }
            set.add(sb.toString());
            indexList.clear();
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            if (indexList != null && indexList.contains(i)) {
                continue;
            }
            List<Integer> newIndexList = new ArrayList<>();
            newIndexList.addAll(indexList);
            newIndexList.add(i);
            addSet(arr, newIndexList, set, maxLength);
        }
    }

}
