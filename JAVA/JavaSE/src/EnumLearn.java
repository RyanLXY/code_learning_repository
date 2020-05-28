import java.lang.annotation.*;

public class EnumLearn {
    public static void main(String[] args) {
        Season spring = Season.SPRING;
        spring.showInfo();
        Season spring2 = Season.SPRING; // 每次调用都是同一个对象 枚举是单例模式
        spring.showInfo();
        System.out.println(spring.equals(spring2));
    }
}

enum Season{
    SPRING("春","花"), // 此处相当于调用有参的私有构造
    SUMMER("夏","热"),
    AUTUMN("秋","谷"),
    WINTER("冬","冷");

    private final String NAME;
    private final String DESC;

    private Season(String name, String desc) {
        this.NAME = name;
        this.DESC = desc;
    }

    public void showInfo(){
        System.out.println(this.NAME + ": " + this.DESC);
    }

}

/*
 *annotation
 * @Overwrite 重写
 * @Depressed 过时
 * @SuppressWarnings 抑制警告
 * 使用@interface 自定义注解
 */

@Target(ElementType.FIELD) // <-属性
@Retention(RetentionPolicy.RUNTIME) // 生命周期
@Documented
@interface TestAnn{
    public int id() default 0;
    public String desc() default "";
}

class AnnTest {

    @TestAnn(id = 100, desc = "姓名")
    String name;
}