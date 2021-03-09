package com.sqq.stream;

import com.sqq.entity.User;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class StreamDemo2 {
    public static void main(String[] args) {
        test01();
    }

    private static void test01(){
        List<User> userList = new ArrayList<>();
        userList.add(new User(10,"A"));
        userList.add(new User(12,"B"));
        userList.add(new User(9,"C"));
        userList.add(new User(15,"G"));
        userList.add(new User(10,"A"));
        // 去重
        userList.stream().distinct().forEach(user -> System.out.println(user.getStudentName()));
        // limit
        userList.stream().distinct().limit(2).forEach(user -> System.out.println(user.toString()));
        System.out.println();
        // userList.add(new User(10,"A"));
        userList.stream().filter(user -> user.getClassId() > 13).forEach(user -> System.out.println(user.toString()));
        // 按classId进行排序
        System.out.println("按classId进行排序");
        userList.stream().sorted(Comparator.comparing(User :: getClassId)).forEach(user -> System.out.println(user.toString()));
        // Stream<T> skip(long n):  跳过元素，返回一个扔掉了前 n 个元素的流。若流中元素不足 n 个，则返回一个空流。与 limit(n) 互补。
        System.out.println("按照classId进行排序，倒排需要用到reversed方法，然后用skip跳过前面年龄大的，剩下classId最小的。");
        userList.stream().sorted(Comparator.comparing(User::getClassId).reversed()).skip(userList.size() -1).forEach(user -> System.out.println(user.toString()));

        System.out.println("-----");
        userList.stream().sorted(Comparator.comparing(User::getClassId).reversed()).skip(userList.size() -1).forEach(System.out::println);

        // Stream<R> map(Function<? super T, ? extends R> mapper) 接收一个Function函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。也就是转换操作，
        // map还有三个应用于具体类型方法，
        // 分别是：mapToInt，mapToLong和mapToDouble。这三个方法也比较好理解，比如mapToInt就是把原始Stream转换成一个新的Stream，这个新生成的Stream中的元素都是int类型。
        // 这三个方法可以免除自动装箱/拆箱的额外消耗。
        System.out.println("map接收功能函数方式");
        userList.stream().map(user -> "欢迎" + user.getStudentName()).forEach(System.out::println);
        userList.stream().map(user -> "欢迎222___" + user.getStudentName()).forEach(user2 -> System.out.printf(user2));

        // Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper)：
        // 接收一个Function函数作为参数，将流中的每个值都转换成另一个流，然后把所有流连接成一个流。flatMap也有三个应用于具体类型的方法，
        // 分别是：flatMapToInt、flatMapToLong、flatMapToDouble，其作用于map的三个衍生方法相同。
        // 举例: 3个年轻人身上有若干钞票，我们看一下他们每个人都带了多少钱，map和flatMap方式如下：
        System.out.println("map和flatMap方式区别————————");
        List<User> userList2 = new ArrayList<>();
        List<String> money2 = new ArrayList<String>();
        money2.add("2元");
        money2.add("5元");
        List<String> money3 = new ArrayList<String>();
        money3.add("10元");
        money3.add("50元");
        List<String> money4 = new ArrayList<String>();
        money4.add("100元");
        userList2.add(new User(20, "王二",money2));
        userList2.add(new User(30, "张三",money3));
        userList2.add(new User(40, "李四",money4));
        // 使用map方式输出
        System.out.println("使用map方式输出,钱。。。");
        userList2.stream().map(user -> user.getMoney()).forEach(System.out::println);
        // 使用flatMap方式输出
        System.out.println("使用flatMap方式输出,钱。。。。。。");
        userList2.stream().flatMap(user -> user.getMoney().stream()).forEach(System.out::println);
        // 可以看出，map会将每个值返回一个流然后输出，flatMap会将每个值返回的流合并成一个流输出

        System.out.println("ceshi ,,,");
        userList2.stream().map(user -> test002(user.getClassId())).forEach(System.out::println);

    }

    private static int test002(int classId){
        return  classId + 100;
    }
}
