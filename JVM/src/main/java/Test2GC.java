/**
 * Created by lucky on 2018/9/5.
 */
public class Test2GC {

    public Object instance = null;

    private static final int _1MB = 1024*1024;

    private byte[] bytes = new byte[100*_1MB];

    public static void main(String[] args) {
        Test2GC instance1 = new Test2GC();
        Test2GC instance2 = new Test2GC();
        instance1.instance = instance2;
        instance2.instance = instance1;

        instance1 = null;
        instance2 = null;

        System.gc();
    }
}
