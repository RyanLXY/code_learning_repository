import java.util.Comparator;

public class Person implements Comparator<Person> {

    private String name;
    private int sex;
    private int age;
    public static int count;

    public Person() {
        Person.count += 1;
    }

    public Person(String name, int sex, int age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        Person.count += 1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void printInfo(){
        System.out.println(this.name);
        System.out.println(this.sex);
        System.out.println(this.age);

    }

    // 传递参数数量不定 多个参数在最后

    /**
     * 数组方式
     * 如无参数 则需定义空数组
     * @param args
     */
    public void printInfo1(String[] args){
        for(int i = 0; i < args.length; i++){
            System.out.println();
        }
    }

    /**
     * java特有的 ... 方式,使用时与数组使用方式相同
     * 如无参数 则可不填
     * @param args
     */
    public void printInfo2(String... args) {
        for (int i = 0; i < args.length; i++) {
            System.out.println();
        }
    }

    public void intTest(int... i){

    }

    // static 内不能使用this 和 super
    public static int showCount(){
        return count;
    }

    @Override
    public int compare(Person o1, Person o2) {  // 集合定制排序 Comparator接口
        if (o1.age > o2.age){
            return 1;
        }else if(o1.age < o2.age){
            return -1;
        }else {
            return 0;
        }

    }

}

