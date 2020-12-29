package interview.thoughtworks;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author gurq
 * @date 2020/12/1 11:47 下午
 */
public class RomanToNumber {

    public static void main(String[] args) throws Exception {
        // 1944
        System.out.println(change("MCMXLIV"));
        // error
        System.out.println(change("IIV"));
    }

    /**
     * 将形如MCMXLIV的字符转成数字
     * @param romanString romanString
     * @return 转换后的数字
     * @throws Exception Exception
     */
    public static int change(String romanString) throws Exception {
        if (romanString == null || "".equals(romanString)) {
            return 0;
        }
        // 先切割成一个一个的
        String[] romanArr = romanString.split("");
        // 再转换成list
        List<Roman> romanList = arrToList(romanArr);
        // 将每一位上的数字用正或者负来表示
        signList(romanList);
        // 不能连续出现负数
        checkContinuousNegative(romanList);
        // DLV不能重复出现,DLV不能是负数
        checkDLV(romanList);
        // 从后向前遍历，有负的就求和，得出求和后的结果
        List<Integer> mergeList = mergeList(romanList);
        // IXC可以重复三次,只能IV或者IX,只能XL或者XC
        checkMergeList(mergeList);
        return mergeList.stream().mapToInt(Integer::intValue).sum();
    }

    /**
     * arr 转 list， 做了异常处理
     *
     * @param romanArr arr
     * @return list
     * @throws Exception 异常
     */
    private static List<Roman> arrToList(String[] romanArr) throws Exception {
        List<Roman> list = new ArrayList<>();
        for (String item : romanArr) {
            if (!RomanEnum.containsSymbol(item)) {
                throw new Exception("I have no idea what you are talking about");
            }
            Roman roman = RomanEnum.getRomanBySymbol(item);

            list.add(roman);
        }
        return list;
    }

    /**
     * DLV不能重复出现，DLV不能为负数
     *
     * @param list list
     */
    private static void checkDLV(List<Roman> list) throws Exception {
        // 不能重复
        Map<String, Long> timeMap = list.stream().collect(Collectors.groupingBy(Roman::getSymbol, Collectors.counting()));
        boolean repeat = timeMap.getOrDefault("D", 0L) > 1
                || timeMap.getOrDefault("L", 0L) > 1
                || timeMap.getOrDefault("V", 0L) > 1;
        if (repeat) {
            throw new Exception("I have no idea what you are talking about");
        }
        // 不能为负
        for (Roman roman : list) {
            boolean negative = ("D".equals(roman.getSymbol())
                    || "L".equals(roman.getSymbol())
                    || "V".equals(roman.getSymbol()))
                    && roman.getValue() < 0;
            if (negative) {
                throw new Exception("I have no idea what you are talking about");
            }
        }
    }

    /**
     * 将每一位上的数字用正或者负来表示
     *
     * @param list list
     */
    private static void signList(List<Roman> list) {
        Roman next;
        Roman thisRoman;
        int index = list.size() - 1;
        while (index >= 1) {
            thisRoman = list.get(index);
            while (index >= 1) {
                next = list.get(index - 1);
                if (!next.absSmaller(thisRoman)) {
                    index--;
                    break;
                }
                next.setValue(next.getValue() * -1);
                index--;
            }
        }
    }

    /**
     * 不能连续出现负数
     *
     * @param list list
     * @throws Exception 异常
     */
    private static void checkContinuousNegative(List<Roman> list) throws Exception {
        Roman next;
        for (int index = list.size() - 1; index > 0; index--) {
            next = list.get(index - 1);
            if (next.getValue() < 1 && list.get(index).getValue() < 1) {
                throw new Exception("I have no idea what you are talking about");
            }
        }
    }

    /**
     * 从后向前遍历，有负的就求和，得出求和后的结果
     *
     * @param list list
     * @return mergeList
     */
    private static List<Integer> mergeList(List<Roman> list) {
        List<Integer> mergeList = new ArrayList<>();
        Roman next;
        int num;
        for (int index = list.size() - 1; index > 0; index--) {
            next = list.get(index - 1);
            num = list.get(index).getValue();

            if (next.getValue() < 0) {
                num += next.getValue();
                index--;
            }
            mergeList.add(num);
        }
        if (list.get(0).getValue() > 0) {
            mergeList.add(list.get(0).getValue());
        }
        return mergeList;
    }

    /**
     * IXC可以重复三次,只能IV或者IX,只能XL或者XC
     *
     * @param mergeList mergeList
     * @throws Exception Exception
     */
    private static void checkMergeList(List<Integer> mergeList) throws Exception {
        // IXC可以重复三次
        Map<Integer, Long> timeMap = mergeList.stream().collect(Collectors.groupingBy(i -> i, Collectors.counting()));
        boolean repeat = timeMap.getOrDefault(1, 0L) > 3
                || timeMap.getOrDefault(10, 0L) > 3
                || timeMap.getOrDefault(100, 0L) > 3;
        if (repeat) {
            throw new Exception("I have no idea what you are talking about");
        }
        List<Integer> errorNumberList = new ArrayList<>();
        // 只能IV或者IX
        errorNumberList.add(49);
        errorNumberList.add(99);
        errorNumberList.add(499);
        errorNumberList.add(999);
        // 只能XL或者XC
        errorNumberList.add(490);
        errorNumberList.add(990);
        // 取交集
        errorNumberList.retainAll(mergeList);
        if (errorNumberList.size() > 0) {
            throw new Exception("I have no idea what you are talking about");
        }
    }
}
