public class MyDate {
    int year;
    int month;
    int day;

    public MyDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    @Override
    public boolean equals(Object obj) {
        int flag = 1;
        if (obj instanceof MyDate){
            MyDate md = (MyDate) obj;
            flag = 0;

            if (this.year != md.year){
                flag += 1;
            }
            if (this.month != md.month){
                flag += 1;
            }
            if (this.day != md.day){
                flag += 1;
            }
        }

        if (flag == 0){
            return true;
        }
        else {
            return false;
        }
    }

    // Object 中的 toString 输出当先对象的内存地址
    @Override
    public String toString() {
        String str = this.year + "-" + this.month + "-" + this.day;
        return str;
    }

//    @Override
//    public String toString() {
//        return "MyDate{" +
//                "year=" + year +
//                ", month=" + month +
//                ", day=" + day +
//                '}';
//    }
}
