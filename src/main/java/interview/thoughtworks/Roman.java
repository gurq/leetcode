package interview.thoughtworks;

/**
 * @author gurq
 * @date 2020/12/1 11:09 下午
 */
public class Roman {

    private final String symbol;

    private Integer value;

    public Roman(String symbol, Integer value) {
        this.symbol = symbol;
        this.value = value;
    }

    /**
     * 判断绝对值的大小，用来在添加负号以后仍能方便的比较大小
     * @param input input
     * @return boolean
     */
    public boolean absSmaller(Roman input) {
        return Math.abs(this.value) < Math.abs(input.getValue());
    }



    public String getSymbol() {
        return symbol;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
