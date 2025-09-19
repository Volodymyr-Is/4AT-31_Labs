package aqa_course.task2;

public class Task2Classes {
    public int add(int a, int b){
        return a + b;
    }

    public int sub(int a, int b){
        return a - b;
    }

    public int mult(int a, int b){
        return a * b;
    }

    public int div(int a, int b){
        if (b == 0) throw new IllegalArgumentException("Division by zero");
        return a / b;
    }
}
