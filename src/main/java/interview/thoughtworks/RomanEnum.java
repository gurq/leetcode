package interview.thoughtworks;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author gurq
 * @date 2020/12/2 2:29 下午
 */
public enum RomanEnum {


    /**
     * I
     */
    I("I", 1),
    /**
     * V
     */
    V("V", 5),
    /**
     * X
     */
    X("X", 10),
    /**
     * L
     */
    L("L", 50),
    /**
     * C
     */
    C("C", 100),
    /**
     * D
     */
    D("D", 500),
    /**
     * M
     */
    M("M", 1000);

    RomanEnum(String symbol, Integer value) {
        this.symbol = symbol;
        this.value = value;
    }

    private final String symbol;

    private final Integer value;

    /**
     * 判断是否已经有该symbol的枚举
     * @param symbol symbol
     * @return boolean
     */
    public static boolean containsSymbol(String symbol){
        return Arrays.stream(RomanEnum.values()).anyMatch(i->i.symbol.equals(symbol));
    }

    /**
     * 根据输入的symbol返回新建的Roman对象
     * @param symbol symbol
     * @return Roman
     */
    public static Roman getRomanBySymbol(String symbol){
        List<RomanEnum> list = Arrays.stream(RomanEnum.values())
                .filter(i -> i.symbol.equals(symbol)).collect(Collectors.toList());
        return list.size() < 1 ? null : new Roman(list.get(0).symbol,list.get(0).value);
    }

    /**
     * 返回所有的symbol
     * @return symbolList
     */
    public static List<String> symbolList(){
        return Arrays.stream(RomanEnum.values()).map(i->i.symbol).collect(Collectors.toList());
    }

}
