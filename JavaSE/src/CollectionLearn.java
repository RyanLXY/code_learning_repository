import java.util.*;

public class CollectionLearn {
    /**
     * 集合只能存放对象
     * int -》 Integer 后存放
     * 集合存放对象的引用 对象本身放在堆中
     * 可存不同类型不限数量的数据类型
     * Set：无序 不可重复
     * List：有序 可重复
     * Map：具有映射关系
     * JDK5 后增加了范行
     */

    // HashSet 不是线性安全 可存null
    // 存元素时 调用该元素的hashCode() 根据hashCode值决定存储位置
    // 如两元素equals返回true但hashCode返回值不同 可以添加成功
    public static void main(String[] args) {
        // HashSet
        Set set = new HashSet();
        set.add(1);
        set.add("a");
        System.out.println(set);

        set.remove(1);
        System.out.println(set);

        System.out.println(set.contains(1));

        set.clear();

        set.add("a");
        set.add("b");
        set.add("c");
        set.add("d");
        System.out.println(set);

        // 使用迭代器遍历
        Iterator iterator = set.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

        //for each
        for (Object obj : set) {
            System.out.println(obj);
        }

        System.out.println(set.size());

        Set<String> set1 = new HashSet<>();

        /**
         * TreeSet 确保元素处于排序状态 默认自然排序
         * 调用集合元素的compareTo方法比较
         * this > obj 1
         * this = obj 0
         * this < obj -1
         * */

        Set<Integer> set2 = new TreeSet<>();
        set2.add(5);
        set2.add(2);
        set2.add(4);
        set2.add(1);
        set2.add(3);
        System.out.println(set2);

        Person p1 = new Person("A",1,12);
        Person p2 = new Person("B",2,65);
        Person p3 = new Person("C",1,6);
        Person p4 = new Person("D",2,36);
        Person p5 = new Person("E",1,24);
        Set<Person> set3 = new TreeSet<>(new Person());
        set3.add(p1);
        set3.add(p2);
        set3.add(p3);
        set3.add(p4);
        set3.add(p5);

        for (Person p : set3) {
            System.out.println(p.getName() + "  "+p.getAge());
        }

        /**
         * List 是接口 ArrayList 使用 List接口 线程不安全
         * 默认按元素添加顺序设置元素的索引
         * Vector 老集合 虽然线程安全 不推荐使用
         */

        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("d");
        list.add("c");
        list.add("f");
        list.add("b");
        System.out.println(list);
        System.out.println(list.get(2));

        list.add(1,"e");
        System.out.println(list.indexOf("f"));
        System.out.println(list.lastIndexOf("f"));

        list.remove(2);
        list.set(4, "a");

        List<String> subList = list.subList(2, 4);
        System.out.println(subList);


        /**
         * Map： Key--Value 单向一对一 线程不安全 不能用null
         * key不可重复
         * Map 接口 HashMap实现
         * Hashtable 线程安全 不可用null
         * TreeMap 自然排序是字典排序
         */
        Map<String,Integer> map = new HashMap<>();
        map.put("b", 2);
        map.put("a", 1);
        map.put("e", 2);
        map.put("c", 5);
        System.out.println(map);

        System.out.println(map.get("b"));
        map.remove("c");
        System.out.println(map.size());

        System.out.println(map.containsKey("a"));
        System.out.println(map.containsValue(1));
        //map.clear();

        Set<String> keys = map.keySet(); //获取所有key
        map.values(); //获取所有value

        // 遍历map集合
        for (String key :keys) {
            System.out.println("Key: "+ key + ",value: " + map.get(key));
        }

        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        for (Map.Entry<String, Integer> en: entries) {
            System.out.println("Key: "+ en.getKey() + ",value: " + en.getValue());
        }

        /**
         * Collection 工具类
         */

        List<Person> personSet = new ArrayList<Person>();
        set3.add(p1);
        set3.add(p2);
        set3.add(p3);
        set3.add(p4);
        set3.add(p5);

        Collections.reverse(list); //反转
        Collections.shuffle(list); //随机排序
        Collections.sort(list); //升序排列
        Collections.sort(personSet, new Person()); //自定排序

        Collections.swap(list, 2, 4); //元素交换
        Collections.max(list);
        Collections.min(list);

        Collections.max(personSet, new Person());
        Collections.min(personSet, new Person());

        Collections.frequency(list, "a"); // 出现次数
        Collections.replaceAll(list, "a", "A"); // 替换
    }
}
