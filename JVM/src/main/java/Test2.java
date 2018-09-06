import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucky on 2018/9/5.
 */
public class Test2 {
    /**
     * 方法区和常量池溢出
     * -XX:MetaspaceSize=10m -XX:MaxMetaspaceSize=10m
     * @param args
     */
    public static void main(String[] args) {
        List<Object> list = new ArrayList<Object>();
        int i = 0;
        while(true){
            list.add(String.valueOf(i++).intern());
        }
    }
}
