package interview.aila;

/**
 * @author gurq
 * @date 2020/12/3 7:45 下午
 */
public class Test {
    private static class InnerTest {
        private static Test test = new Test();
    }

    private Test() {
    }

    public static Test get() {
        return InnerTest.test;
    }
}
