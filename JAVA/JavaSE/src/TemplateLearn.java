
public abstract class TemplateLearn {
    // 模版方法设计模式 TemplateMethod
    // 抽象类作为多个子类的通用模版
    public abstract void code();

    public final void getTime(){
        long start = System.currentTimeMillis();
        code();
        long end = System.currentTimeMillis();

        System.out.println("code 执行时间："+ (end - start));

    }
}

class TestTmp extends TemplateLearn{
    @Override
    public void code() {
        int k = 0;
        for (int i = 0; i<5000000;i++){
            k += 1;
        }
        System.out.println(k);
    }
}