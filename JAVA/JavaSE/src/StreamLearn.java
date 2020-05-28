import java.io.*;

public class StreamLearn {
    public static void main(String[] args){
//        StreamLearn.testFileInputStream();
//        StreamLearn.testFileOutputStream();
//        StreamLearn.copyFile("TT.txt","src/cc/TT1.txt");
//        StreamLearn.testFileReader("TT.txt");
//        StreamLearn.testFileWrite("ajeiuamk", "src/cc/TT1.txt");
//        StreamLearn.testInputStreamReader();
//        StreamLearn.testOnputStreamWrite();
//        StreamLearn.testSystemIn();
        StreamLearn.writeToTXT("TTT.txt");
    }


    /**
     * 文件字节的输入流
     */
    public static void testFileInputStream(){
        try  {
            FileInputStream inputStream = new FileInputStream("TT.txt");
            byte[] bytes = new byte[10];

            int len = 0; // 设置一个读取数据的长度
            while((len = inputStream.read(bytes)) != -1){
                System.out.println(new String(bytes, 0, len)); // 哪个数组 开始位置 读取个数
            }

//            inputStream.read(bytes); // inputStream.read 有一个返回值： 读取的数据的长度 读完后返回值为-1


            inputStream.close(); // 流在使用完毕后一定要关闭
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 文件字节输出流
     */
    public static void testFileOutputStream() {
        try {
            FileOutputStream outputStream = new FileOutputStream("TT.txt");
            String string = "Asfasrjkewahkdjbfsao";
            outputStream.write(string.getBytes());  // 把数据写到内存中
            outputStream.flush(); // 把内存中的数据写到硬盘
            outputStream.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 复制文件到指定位置
     * @param inPath 源文件路径
     * @param outPath 目标位置路径
     * 文件字节流非常通用，可以用来操作文档和任何其他类型文件（图片，压缩包，，）
     * 引用字节流直接使用二进制
     */
    public static void copyFile(String inPath, String outPath){
        try  {
            FileInputStream fileInputStream = new FileInputStream(inPath);
            FileOutputStream fileOutputStream = new FileOutputStream(outPath);
            byte[] bytes = new byte[100];
            int len = 0;
            while((len = fileInputStream.read(bytes)) != -1){
                fileOutputStream.write(bytes, 0,len);
            }
            fileOutputStream.flush();

            fileInputStream.close();
            fileOutputStream.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 文件字符输入流
     * @param inPath
     */
    public static void testFileReader(String inPath){
        try{
            FileReader fileReader = new FileReader(inPath);
            char[] chars = new char[10];
            int len = 0;
            while((len = fileReader.read(chars)) != -1){
                System.out.println(new String(chars, 0, len));
            }
            fileReader.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     * 文件字符输出流
     */
    public static void testFileWrite(String text, String outPath) {
        try {
            FileWriter fileWriter = new FileWriter(outPath);
            fileWriter.write(text);
            fileWriter.flush();
            fileWriter.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 复制文件到指定位置
     * @param inPath 源文件路径
     * @param outPath 目标位置路径
     * 字符流 只能是文本文档
     */
    public static void copyFileStram(String inPath, String outPath){
        try  {
            FileReader fileReader = new FileReader(inPath);
            FileWriter fileWriter = new FileWriter(outPath);
            char[] chars = new char[100];
            int len = 0;
            while((len = fileReader.read(chars)) != -1){
                fileWriter.write(chars, 0,len);
            }
            fileWriter.flush();

            fileReader.close();
            fileWriter.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /*
     * 所有文件都有编码格式
     * TXT 和 JAVA 文件一般由三种：
     * ISO8859-1 西欧编码： 纯英文 不适应汉字
     * GBK UTF-8 都适用
     * 一般使用UTF-8
     */

    /**
     * 转换输入流 字节->字符
     * 转换时，设置的字符集编码要与读取文件的编码一致
     */
    public static void testInputStreamReader() {
        try {
            FileInputStream fileInputStream = new FileInputStream("TTT.txt");
            // 把字节流转换为字符流
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8"); // 字节流 编码
            char[] chars = new char[10];
            int len = 0;

            while((len = inputStreamReader.read(chars))!= -1){
                System.out.println(new String(chars, 0, len));
            }

            inputStreamReader.close();
            fileInputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 转换输出流 字节->字符
     * 转换时，设置的字符集编码要与读取文件的编码一致
     */
    public static void testOnputStreamWrite() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("TTT.txt");
            // 把字节流转换为字符流
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, "UTF-8"); // 字节流 编码
            outputStreamWriter.write("测试加一");
            outputStreamWriter.flush();

            outputStreamWriter.close();
            fileOutputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 标准输入流
     */
    public static void testSystemIn(){
        try {
            // 创建一个接受键盘输入数据的输入流
            InputStreamReader inputStreamReader = new InputStreamReader(System.in);
            // 把输入流放到缓冲流
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String string = ""; // 临时接受数据的字符串
            while ((string = bufferedReader.readLine()) != null) {
                System.out.println(string);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 把控制台输入的内容写到指定文件夹中，当接收到字符串over结束运行
     */
    public static void writeToTXT(String outPath){
        try {

            InputStreamReader inputStreamReader = new InputStreamReader(System.in);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outPath));
            String string = "";
            while((string = bufferedReader.readLine()) != null) {
                if (string.equals("over")){
                    break;
                }
                bufferedWriter.write(string);
            }
            bufferedWriter.flush();
            bufferedWriter.close();
            bufferedReader.close();
            inputStreamReader.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}

