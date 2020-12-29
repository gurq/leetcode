package interview.thoughtworks2;

import java.util.List;

/**
 * @author gurq
 * @date 2020/12/5 3:34 下午
 */
public class MarsCar {
    private Integer x;
    private Integer y;
    private Integer target;
    private String actionString;


    public MarsCar() {
    }

    public MarsCar(Integer x, Integer y, Integer target) {
        this.x = x;
        this.y = y;
        this.target = target;
    }

    public String toString(List<String> targetList) {
        return this.x + " " + this.y + targetList.get(target);
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getTarget() {
        return target;
    }

    public void setTarget(Integer target) {
        this.target = target;
    }

    public String getActionString() {
        return actionString;
    }

    public void setActionString(String actionString) {
        this.actionString = actionString;
    }
}
