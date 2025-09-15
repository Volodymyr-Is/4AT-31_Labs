package aqa_course.task3;

import java.util.*;

public class Task3 {
//    Array Reverse: Write a Java program that takes an array of integers and reverses its elements.
//    Your program should prompt the user to enter the array size and the elements of the array, and then output the reversed array.
//    Make HashSet of doubles from the result array and perform the following operations:
//    a) Add an element to the end of the list;
//    b) Remove an element from the list;
//    c) Replace an element in the list;
//    d) Sort the list in alphabetical order;
//    e) Print the elements of the list;
//    Make up the situation for ArrayIndexOutOfBoundsException. Catch it and display the explanation for your custom case.

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        int[] arr2 = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            arr2[arr.length - i - 1] = arr[i];
        }
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(arr2));
        System.out.println("----------------");


        Set<Double> doubles = new HashSet<>();
        for (int i : arr2){
            doubles.add(i + .0);
        }

        doubles.add(7.0);
        doubles.remove(2.0);
        List<Double> doubleList = new ArrayList<>(doubles);
        Collections.sort(doubleList);
        System.out.println(doubleList);
        System.out.println("----------------");


//        System.out.println(arr2[6]);
        try{
            System.out.println(arr2[6]);
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Invalid index " + e.getLocalizedMessage());
        }
    }
}
