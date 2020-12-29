package interview.thoughtworks;

import interview.thoughtworks.RomanToNumber;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author gurq
 * @date 2020/12/2 1:01 下午
 */
public class InputParser {

    /**
     * 存放输入有哪些symbols
     */
    public static Map<String, Roman> inputRomanMap = new HashMap<>();

    /**
     * 存放输入的银金铁的单价
     */
    public static Map<String, BigDecimal> inputThingValueMap = new HashMap<>();

    /**
     * 提示信息
     */
    private static final String ERROR_MSG = "I have no idea what you are talking about";

    public static void main(String[] args) {
        String input = "glob is I\n" +
                "prok is V\n" +
                "pish is X\n" +
                "tegj is L\n" +
                "glob glob Silver is 34 Credits\n" +
                "glob prok Gold is 57800 Credits\n" +
                "pish pish Iron is 3910 Credits\n" +
                "how much is pish tegj glob glob ?\n" +
                "how many Credits is glob prok Silver ?\n" +
                "how many Credits is glob prok Gold ?\n" +
                "how many Credits is glob prok Iron ?\n" +
                "how much wood could a woodchuck chuck if a woodchuck could chuck wood ?";
        List<String> lineList = Arrays.asList(input.split("\n"));
        List<String> answerList = parse(lineList);
        for (String answer : answerList) {
            System.out.println(answer);
        }
    }


    /**
     * 根据输入的行数据，解析并返回最终输出语句
     * @param lineList lineList
     * @return lineList
     */
    public static List<String> parse(List<String> lineList) {
        // 初始化装答案的list
        List<String> answerList = new ArrayList<>();

        if (lineList == null || lineList.size() < 1) {
            answerList.add("please input something");
            return lineList;
        }
        // 一行一行解析输入语句
        parseLine(lineList, answerList);
        return answerList;
    }

    /**
     * 一行一行解析输入语句
     * @param lineList lineList
     * @param answerList answerList
     */
    private static void parseLine(List<String> lineList, List<String> answerList) {
        for (String line : lineList) {
            // 1. 解析录入的数据里面的 单词 -> roman 映射
            if (endWithRoman(line)) {
                parseRomanMap(line, answerList);
                continue;
            }
            // 2. 解析录入的数据里面的 银/金/铁 -> 价值 的映射
            if (line.endsWith("Credits")) {
                parseThingValue(line, answerList);
                continue;
            }
            // 3. 解析 how much 并求值
            if (line.startsWith("how much is")) {
                parseHowMuch(line, answerList);
                continue;
            }
            // 4. 解析 how many 并求值
            if (line.startsWith("how many Credits is")) {
                parseHowMany(line, answerList);
                continue;
            }
            // 如果走到这一步，肯定是异常
            answerList.add(ERROR_MSG);
        }
    }

    /**
     * 判断是否以 roman 结尾
     *
     * @param line line
     * @return boolean
     */
    private static boolean endWithRoman(String line) {
        List<String> endWithRomanList = RomanEnum.symbolList().stream()
                .map(i -> "is " + i).collect(Collectors.toList());
        for (String endWith : endWithRomanList) {
            if (line.endsWith(endWith)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 解析录入的数据里面的 单词 -> roman 映射
     *
     * @param line line
     * @param answerList answerList
     */
    private static void parseRomanMap(String line, List<String> answerList) {
        String[] arr = line.split(" ");
        if (!RomanEnum.containsSymbol(arr[2])) {
            answerList.add(ERROR_MSG);
        }
        Roman roman = RomanEnum.getRomanBySymbol(arr[2]);
        inputRomanMap.put(arr[0], roman);
    }

    /**
     * 解析录入的数据里面的 银/金/铁 -> 价值 的映射
     *
     * @param line line
     * @param answerList answerList
     */
    private static void parseThingValue(String line, List<String> answerList) {
        StringBuilder romanString = new StringBuilder();
        String thing = null;
        BigDecimal value = BigDecimal.ZERO;
        for (String word : line.split(" ")) {
            if (inputRomanMap.get(word) != null) {
                romanString.append(inputRomanMap.get(word).getSymbol());
                continue;
            }
            if ("Silver".equals(word) || "Gold".equals(word) || "Iron".equals(word)) {
                thing = word;
                continue;
            }
            if ("is".equals(word) || "Credits".equals(word)) {
                continue;
            }
            try {
                value = new BigDecimal(word);
            } catch (Exception e) {
                answerList.add(ERROR_MSG);
            }
        }
        int count = 0;
        try {
            count = RomanToNumber.change(romanString.toString());
        } catch (Exception e) {
            answerList.add(ERROR_MSG);
        }
        inputThingValueMap.put(thing, value.divide(new BigDecimal(count),5, RoundingMode.CEILING));
    }

    /**
     * 解析 how much 并求值
     * @param line line
     * @param answerList answerList
     */
    private static void parseHowMuch(String line, List<String> answerList){
        StringBuilder romanString = new StringBuilder();
        StringBuilder wordString = new StringBuilder();
        for (String word : line.split(" ")) {
            if ("how".equals(word) || "much".equals(word) || "is".equals(word) || "?".equals(word)) {
                continue;
            }
            if (inputRomanMap.get(word) != null) {
                romanString.append(inputRomanMap.get(word).getSymbol());
                wordString.append(word).append(" ");
                continue;
            }
            answerList.add(ERROR_MSG);
            return;
        }
        int count;
        try {
            count = RomanToNumber.change(romanString.toString());
            answerList.add(wordString.toString() + "is " + count);
        } catch (Exception e) {
            answerList.add(ERROR_MSG);
        }
    }

    /**
     * 解析 how many 并求值
     * @param line line
     * @param answerList answerList
     */
    private static void parseHowMany(String line, List<String> answerList){
        StringBuilder romanString = new StringBuilder();
        StringBuilder wordString = new StringBuilder();
        BigDecimal value = BigDecimal.ZERO;
        for (String word : line.split(" ")) {
            if ("how".equals(word) || "many".equals(word) || "Credits".equals(word)
                    || "is".equals(word) || "?".equals(word)) {
                continue;
            }
            if (inputRomanMap.get(word) != null) {
                romanString.append(inputRomanMap.get(word).getSymbol());
                wordString.append(word).append(" ");
                continue;
            }
            if (inputThingValueMap.get(word) != null) {
                value = inputThingValueMap.get(word);
                wordString.append(word).append(" ");
                continue;
            }
            answerList.add(ERROR_MSG);
            return;
        }
        int count;
        try {
            count = RomanToNumber.change(romanString.toString());
            BigDecimal result = value.multiply(new BigDecimal(count));
            answerList.add(wordString.toString() + "is " + result.stripTrailingZeros().toPlainString() + " Credits");
        } catch (Exception e) {
            answerList.add(ERROR_MSG);
        }
    }

}
