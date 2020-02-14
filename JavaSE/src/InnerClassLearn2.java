public class InnerClassLearn2 {

    public static void main(String[] args) {
        /**
         * 使用内部类变相实现类的多重继承
         */
        A a = new A();
        a.testB();
        a.testC();
    }
}
    // A 想同时获得并重写B C的方法
    class A{

        private class InnerB extends B{
            @Override
            public void testB() {
                System.out.println("A中重写的testB方法");
            }
        }
        public void testB(){
            new InnerB().testB();
        }

        private class InnerC extends C{
            @Override
            public void testC() {
                System.out.println("A中重写的testC方法");
            }
        }
        public void testC(){
            new InnerC().testC();
        }
    }
    class B{
        public void testB(){

        }
    }
    class C{
        public void testC(){

        }
    }


