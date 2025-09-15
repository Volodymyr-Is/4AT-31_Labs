package aqa_course.task6;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table
public class User implements Serializable {
    public String name;
    public Integer age;
    public boolean isMarried;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "addressId") // Foreign key column in the User table
    public Address address;
    @Id
    public Integer id;

    public User() {};

    public User(Integer id, String name, Integer age, boolean isMarried, Address address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.isMarried = isMarried;
        this.address = address;
    }

    public User(Integer id, String name, Integer age, boolean isMarried) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.isMarried = isMarried;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
