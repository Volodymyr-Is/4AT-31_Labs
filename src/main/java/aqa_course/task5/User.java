package aqa_course.task5;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {
    public String name;
    public Integer age;
    public boolean isMarried;
    public Address address;

    public User() {};

    public User(String name, Integer age, boolean isMarried, Address address) {
        this.name = name;
        this.age = age;
        this.isMarried = isMarried;
        this.address = address;
    }

    public boolean isMarried() {
        return isMarried;
    }

    public void setMarried(boolean married) {
        isMarried = married;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
