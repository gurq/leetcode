package interview.thoughtworks2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author gurq
 * @date 2020/12/5 3:41 下午
 */
public class MarsAction {

    public static List<String> targetList;

    public MarsAction() {
        init();
    }

    public void init() {
        String[] targetArr = {"E", "S", "W", "N"};
        targetList = new ArrayList<>(Arrays.asList(targetArr));
    }

    public void action(MarsCar marsCar) throws Exception{
        String moveLine = marsCar.getActionString();
        if (moveLine == null || "".equals(moveLine)) {
            return;
        }
        String[] actionArr = moveLine.split("");
        for (String action : actionArr) {
            if ("M".equals(action)) {
                move(marsCar);
                continue;
            }
            if ("L".equals(action) || "R".equals(action)) {
                turnTarget(action, marsCar);
                continue;
            }
            throw new Exception(GlobalText.ERROR_MSG);
        }
    }

    private void move(MarsCar marsCar) {
        switch (marsCar.getTarget()) {
            case 0:
                marsCar.setX(marsCar.getX() - 1);
                break;
            case 1:
                marsCar.setY(marsCar.getY() + 1);
                break;
            case 2:
                marsCar.setX(marsCar.getX() + 1);
                break;
            case 3:
                marsCar.setY(marsCar.getY() - 1);
                break;
            default:
                return;
        }
    }

    private void turnTarget(String lOrR, MarsCar marsCar) {
        Integer target = marsCar.getTarget();
        if ("L".equals(lOrR)) {
            target--;
            if (target < 0) {
                target = targetList.size() - 1;
            }
        } else {
            target++;
            if (target >= targetList.size()){
                target = 0;
            }
        }
        marsCar.setTarget(target);
    }

    public MarsCar parseMarsCar(String inputLine) throws Exception {
        if (inputLine == null || "".equals(inputLine)) {
            throw new Exception(GlobalText.ERROR_MSG);
        }
        String[] arr = inputLine.split(" ");
        if (arr.length < 3) {
            throw new Exception(GlobalText.ERROR_MSG);
        }
        try {
            return new MarsCar(Integer.parseInt(arr[0]),
                    Integer.parseInt(arr[1]),
                    targetList.indexOf(arr[2]));
        } catch (Exception e) {
            throw new Exception(GlobalText.ERROR_MSG);
        }
    }

    public void printMarsCar(MarsCar marsCar, int maxX, int maxY){
        boolean isRip = marsCar.getX() < 0 || marsCar.getY() < 0
                || marsCar.getX() > maxX || marsCar.getY() > maxY;
        if (isRip) {
            System.out.println(GlobalText.RIP);
            return;
        }
        System.out.println(marsCar.toString(targetList));
    }

}
