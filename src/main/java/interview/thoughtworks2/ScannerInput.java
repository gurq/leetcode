package interview.thoughtworks2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 *  * 5 5
 *  * 1 2 N
 *  * LMLMLMLMM
 *  * 3 3 E
 *  * MMRMMRMRRM
 * @author gurq
 * @date 2020/12/5 3:57 下午
 */
public class ScannerInput {
    public static void main(String[] args) {
        ScannerInput scannerInput = new ScannerInput();
        List<String> lineList = scannerInput.getInputLine();
        scannerInput.parseInput(lineList);

    }

    public List<String> getInputLine(){
        List<String> lineList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if ("".equals(line)) {
                break;
            }
            lineList.add(line);
        }
        return lineList;
    }

    public void parseInput(List<String> lineList){
        // 非空及大小判断
        String firstLine = lineList.get(0);
        String[] xyArr = firstLine.split(" ");
        int maxX = Integer.parseInt(xyArr[0]);
        int maxY = Integer.parseInt(xyArr[1]);

        MarsAction marsAction = new MarsAction();

        for (int i = 1; i < lineList.size(); i += 2) {
            try {
                String lineOne = lineList.get(i);
                String lineTwo = lineList.get(i + 1);
                MarsCar marsCar = marsAction.parseMarsCar(lineOne);
                marsCar.setActionString(lineTwo);
                marsAction.action(marsCar);
                marsAction.printMarsCar(marsCar, maxX, maxY);
            } catch (Exception e) {
                System.out.println(GlobalText.ERROR_MSG);
            }

        }
    }

    public void checkInputLint(List<String> lineList) throws Exception{
        // 校验
        for (int i = 1; i < lineList.size(); i += 2) {
            try {
                checkMarsCarLine(lineList.get(i));
                checkActionString(lineList.get(i + 1));
            } catch (Exception e) {
                System.out.println(GlobalText.ERROR_MSG);
            }

        }
    }

    private void checkXY(String line) throws Exception {
        // 空校验
        String[] arr = line.split(" ");
        // 长度2
    }

    private void checkMarsCarLine(String line) throws Exception{
        // 校验空
        String[] arr = line.split(" ");
        // 校验长度为3,不是就抛异常
    }

    private void checkActionString(String line) throws Exception{
        // 校验空
        long filterCount = Arrays.stream(line.split(""))
                .filter(i -> i.equals("R") || i.equals("L") || i.equals("M")).count();
        if (line.length() > filterCount) {
            throw new Exception(GlobalText.ERROR_MSG);
        }
    }
}
