package org.example;

public class Main {
    public static void main(String[] args) {
//        V 1: Basic Arithmetic Operations
//        Objective: To understand and perform basic arithmetic operations in Java using primitive types.
//        Instructions:
//        Declare two int variables.
//        Assign values to the variables and perform basic arithmetic operations (e.g., addition, subtraction, multiplication, division).
//        Print the results of each operation to the console.

        int a = 6;
        int b = 3;

        var sum = a + b;
        var sub = a - b;
        var mult = a * b;
        var div = a / b;

        System.out.println("Sum: " + sum);
        System.out.println("Sub: " + sub);
        System.out.println("Mult: " + mult);
        System.out.println("Div: " + div);
    }
}