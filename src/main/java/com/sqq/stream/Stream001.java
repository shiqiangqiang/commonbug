package com.sqq.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Stream001 {

    /**
     * 由集合创建：
     * Java8 中的 Collection 接口被扩展，提供了两个获取流的方法,这两个方法是default方法，也就是说所有实现Collection接口的接口都不需要实现就可以直接使用：
     *
     * default Stream<E> stream() : 返回一个顺序流。
     * default Stream<E> parallelStream() : 返回一个并行流。
     * @param args
     */
    public static void createStreamByCollection(String[] args) {
        List<Integer> integerList = new ArrayList<>();
        integerList.add(1);
        integerList.add(2);
        Stream<Integer> stream = integerList.stream();
        Stream<Integer> stream1 = integerList.parallelStream();
    }

    /**
     * 由数组创建：
     * Java8 中的 Arrays 的静态方法 stream() 可以获取数组流:
     *
     * static <T> Stream<T> stream(T[] array): 返回一个流
     * 重载形式，能够处理对应基本类型的数组:
     * public static IntStream stream(int[] array)
     * public static LongStream stream(long[] array)
     * public static DoubleStream stream(double[] array)
     * @param args
     */
    public static void createStreamByArray(String[] args) {
        int[] intArray = {1,2,3};
        IntStream stream = Arrays.stream(intArray);

    }


    public static void createStreamByValue(String[] args) {
        // 可以使用静态方法 Stream.of(), 通过显示值 创建一个流。它可以接收任意数量的参数。
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5, 6, 7, 8);
    }

    /**
     * 由函数创建：创建无限流
     * 可以使用静态方法 Stream.iterate() 和 Stream.generate()创建无限流。
     *
     * 迭代
     * public static<T> Stream<T> iterate(final T seed, final UnaryOperator<T> f)
     * 生成
     * public static<T> Stream<T> generate(Supplier<T> s)
     */
    public static void createStreamByFunction() {
        // 注意：使用无限流一定要配合limit截断，不然会无限制创建下去。
        Stream.generate(Math::random).limit(5).forEach(System.out::print);
        List<Integer> collect = Stream.iterate(0,i -> i + 1).limit(5).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        createStreamByFunction();
    }

    }
