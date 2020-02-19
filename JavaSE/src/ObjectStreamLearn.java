import java.io.*;

public class ObjectStreamLearn {
    /**
     * 序列化（Serialize）：java对象写入IO流
     * 反序列化（Deserialize）：IO流中恢复java对象
     * 不能序列化statichetransient修饰的成员变量
     * 针对的是对象的各种属性不包括类的属性
     * 注意：对象的序列化与反序列化的类要严格一致 包名 类名 类结构
     */

    public static void main(String[] args){
        try{
//            ObjectStreamLearn.testSerialize();
//            ObjectStreamLearn.testDeserialize();
            ObjectStreamLearn.testRandomAccessFIleRead();
//            ObjectStreamLearn.testRandomAccessFIleWrite();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * 对象的序列化
     */
    public static void testSerialize() throws Exception{
        // 定义对象的输出流，把对象序列化之后的流放到指定的文件中
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("TTTT.txt"));

        IOPerson person = new IOPerson();
        person.name = "John";
        person.age = 10;

        objectOutputStream.writeObject(person);
        objectOutputStream.flush();
        objectOutputStream.close();
    }

    /**
     * 对象的反序列化
     */
    public static void testDeserialize() throws Exception{
        //创建对象输入流对象，从指定的文件中把对象的序列化后的流读取
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("TTTT.txt"));
        Object object = objectInputStream.readObject();
        IOPerson person = (IOPerson) object;
        System.out.println(person.name);
        System.out.println(person.age);
        objectInputStream.close();
    }

    /**
     * RandomAccessFile
     * 随机访问：可以直接跳到文件任意位置读写文件
     */
    public static void testRandomAccessFIleRead() throws Exception{
        /*
         * r：只读
         * rw：打开以便读取和写入
         * rwd：打开以便读取和写入；同步更新文件内容
         * rws：打开以便读取和写入；同步更新文件内容和元数据
         */
        RandomAccessFile randomAccessFile = new RandomAccessFile("TT.txt", "r");
        randomAccessFile.seek(0); // 读取文件的起始点
        byte[] bytes = new byte[1024];
//        char[] chars = new char[1024];
        int len = 0;
        while ((len = randomAccessFile.read(bytes)) != -1){
            System.out.println(new String(bytes, 0, len));
        }
        randomAccessFile.close();

    }

    public static void testRandomAccessFIleWrite() throws Exception{
        RandomAccessFile randomAccessFile = new RandomAccessFile("TT.txt", "rw");
        randomAccessFile.seek(0); //从开头写
        // 如在开头或中间某个位置开始写的时候，会用写的内容覆盖等长度的原内容
//        randomAccessFile.seek(randomAccessFile.length()); //从结尾写
        randomAccessFile.write("Hello".getBytes());
        randomAccessFile.close();
    }

}

class IOPerson implements Serializable{
    /**
     * 一个标示序列化版本标识符的静态变量
     * 用来表明类的不同版本间的兼容性
     */
    private static final long serialVersionUID = 1L;

    String name;
    int age;

}
