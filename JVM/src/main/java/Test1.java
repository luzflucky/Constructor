import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Created by lucky on 2018/9/5.
 */
public class Test1 {
    /**
     * java DirectMemory 内存溢出
     * 本机直接内存溢出
     * @param args
     * @throws IllegalAccessException
     */
    public static void main(String[] args) throws IllegalAccessException {
        Field field = Unsafe.class.getDeclaredFields()[0];
        //允许赋值
        field.setAccessible(true);
        Unsafe o = (Unsafe) field.get(null);
        while (true){
            o.allocateMemory(1024*1024);
        }
    }
}
