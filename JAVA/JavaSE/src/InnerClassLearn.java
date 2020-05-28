public class InnerClassLearn {
    int i;
    public int z;
    private int k;

    class A{ // 可为final private protected static（此时不能使用外部非static的变量）abstract 可被其他内部类继承
        int i;
        public void setFileds(){
            InnerClassLearn.this.i = 1;
            InnerClassLearn.this.z = 2;
            InnerClassLearn.this.k = 3;
        }
        public void setIinA(){
            this.i = 10;
        }
    }
    final class B{}
    private class C{}
    protected class D{}
    static  class E{}
    abstract class F{}  // 内部类用来解决java不能多重继承的问题

    public void setInfo(){
        new A().setFileds(); // 外部的类要用自己的内部类需先new内部类的对象
    }

    public void showINfo(){
        System.out.println(this.i);
        System.out.println(this.z);
        System.out.println(this.k);
    }

    public static void main(String[] args){
        InnerClassLearn innerClassLearn = new InnerClassLearn();
        innerClassLearn.setInfo();
        innerClassLearn.showINfo();
    }
}
