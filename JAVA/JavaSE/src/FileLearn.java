import java.io.File;
import java.io.IOException;

public class FileLearn {
    public static void main(String[] args){
        /*
        File 类只能操作文件本身 不能操作文件内容
         */
        File file = new File("/Users/L/Documents/GitHub/code_learning_repository/JavaSE/src/test.txt");
        File file1 = new File("/Users/L"+File.separator+"Documents/GitHub/code_learning_repository/JavaSE/src/test.txt");
        File file2 = new File("text.txt");
        System.out.println(file.getName());
        System.out.println(file.getPath());
        System.out.println(file2.getAbsolutePath());
        System.out.println(file2.getAbsoluteFile()); // 返回一个当前文件的绝对路径构建的File对象
        System.out.println(file.getParent());

//        file.renameTo(new File("TT.txt")); // 重命名
//        file.renameTo(new File("test.txt"));

        System.out.println(file2.exists()); // 判断文件或文件夹是否存在
        System.out.println(file2.canRead());
        System.out.println(file2.canWrite());

        System.out.println(file2.isFile());
        System.out.println(file2.isDirectory());

        System.out.println(file2.lastModified());
        System.out.println(file2.length()); // 返回文件长队 单位是字节数

        File file3 = new File("test2.txt");
        System.out.println(file3.exists());
//        if (!file3.exists()){
//            try {
//                file3.createNewFile();
//            } catch (IOException e){
//                e.printStackTrace();
//            }
//        }
        file3.delete();


        File file4 = new File("/Users/L/Documents/GitHub/code_learning_repository/JavaSE/src/cc");
        System.out.println(file4.exists());
        file4.mkdir(); // 创建单层目录
        System.out.println(file4.exists());

        File file5 = new File("/Users/L/Documents/GitHub/code_learning_repository/JavaSE/src/cc/bb/aa");
        System.out.println(file5.exists());
        file5.mkdirs(); // 创建多层层目录
        System.out.println(file5.exists());

        File file6 = new File("/Users/L/Documents/GitHub/code_learning_repository/JavaSE/src/cc");
        System.out.println(file6.list());
        String fl[] = file6.list(); // 返回当前文件夹的子集的名称：所有目录和文件
        for ( String s : fl){
            System.out.println(s);
        }

        File[] fs = file6.listFiles(); // 返回当前文件夹的子集的File对象：所有目录和文件
        for ( File ff : fs){
            System.out.println(ff);
        }
        File file7 = new File("/Users/L/Documents/GitHub/code_learning_repository/JavaSE/src/cc");
        new FileLearn().getAllFile(file7);

    }

    /**
     * 递归遍历文件
     * @param file
     */
    public void getAllFile(File file){
        if (file.isFile()){
            System.out.println(file.getAbsolutePath() + "   is FILE!");
        } else {
            System.out.println(file.getAbsolutePath() + "   is DIRECTORY!");
            File[] files = file.listFiles();
            if (files != null && files.length > 0) {
                for (File ff : files) {
                    getAllFile(ff);
                }
            }
        }
    }
}
