public class ExceptionLearn {
    public static void main(String[] args) { // 可以在main方法继续抛出异常 到虚拟机中 程序中无法处理
        E eee = new E();
        try {                   // 在调用时处理
//            eee.ageRange(-100);
            eee.test(-100);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}


class E{
    int age;
    public void ageRange (int age) throws Exception{ // 用throws在代码抛出异常
        if (age >= 0 && age <= 150){
            this.age = age;
            System.out.println("Age is: "+this.age);
        }else {
            throw new Exception("Age out of range!");
        }
    }

    public void test(int age) throws MyException{
        if (age >= 0 && age <= 150){
            this.age = age;
            System.out.println("Age is: "+this.age);
        }else {
            throw new MyException("Age out of range!");
        }
    }
}

class MyException extends Exception{
    public MyException(String msg){
        super(msg);
    }
}