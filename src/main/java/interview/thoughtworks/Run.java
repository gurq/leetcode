package interview.thoughtworks;

import interview.thoughtworks.InputParser;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author gurq
 * @date 2020/12/2 11:28 下午
 */
public class Run {

    public static void main(String[] args) {
        // 两种方式都可以运行
        // 不加参数，即从控制台读取，任何时候不输入直接回车即开始计算
        run();
        // 加参数，即直接读取参数计算
//        run("glob is I\n" +
//                "prok is V\n" +
//                "pish is X\n" +
//                "tegj is L\n" +
//                "glob glob Silver is 34 Credits\n" +
//                "glob prok Gold is 57800 Credits\n" +
//                "pish pish Iron is 3910 Credits\n" +
//                "how much is pish tegj glob glob ?\n" +
//                "how many Credits is glob prok Silver ?\n" +
//                "how many Credits is glob prok Gold ?\n" +
//                "how many Credits is glob prok Iron ?\n" +
//                "how much wood could a woodchuck chuck if a woodchuck could chuck wood ?");
    }

    /**
     * 调用时直接粘贴
     * @param input input
     */
    private static void run(String input) {
        parseInput(input);
    }

    /**
     * 从控制台读取，任何时候不输入直接回车即开始计算
     */
    private static void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("please input something");
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            if ("".equals(line)){
                break;
            }
            stringBuilder.append(line).append("\n");
        }
        parseInput(stringBuilder.toString());
    }

    /**
     * 将获取到的输入值计算并打印
     * @param input input
     */
    private static void parseInput(String input) {
        if ("".equals(input)) {
            System.out.println("please input anything");
            return;
        }
        List<String> lineList = Arrays.asList(input.split("\n"));
        List<String> answerList = InputParser.parse(lineList);
        for (String answer : answerList) {
            System.out.println(answer);
        }
    }

}
