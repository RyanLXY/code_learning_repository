public class Utils {
    public static boolean isEmpty(String string){
        // 判断string是否为字符串
        boolean flag = false;
        if(string != null && !string.equals("")){
            flag = true;
        }
        return flag;
    }
}
