package aqa_course.task5;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Task5 {
//    Serialization-Deserialization:
//    a) Make some complex models using your variant.
//    b) Make it serializable.
//    c) Read JSON from “input.json”
//    d) and deserialize it to POJO.
//    e) Then change a few fields and save it to “output.json”.
//    f) Do the same for XML.
//
//    Stream:
//    a) Generate 10 random objects using a class from a previous task
//    b) Sort it using any two fields using stream.
//    c) Filter it by any two fields custom filter.
//    d) Collect it to List with *main field(s).
//
//    Write a maven command for executing one of your tasks with arguments (number of elements).

    public static void main(String[] args) throws IOException {
        String inputJson = "";

        try {
            File file = new File("src\\main\\resources\\input.json");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                inputJson += line;
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ObjectMapper objectMapper = new ObjectMapper();
        User user = new User("User1", 21, true, new Address("City1", "State1"));
        objectMapper.writeValue(new File("target/user.json"), user);

        System.out.println("-------------------------");

        Random random = new Random();
        String[] cities = {"New York", "Boston", "Chicago", "Miami"};
        String[] states = {"NY", "MA", "IL", "FL"};
        String[] names = {"John", "Jane", "Alice", "Bob", "Carol"};

        List<User> users = Arrays.stream(new User[10])
                .map(i -> new User(
                        names[random.nextInt(names.length)],
                        random.nextInt(20) + 20,
                        random.nextBoolean(),
                        new Address(cities[random.nextInt(cities.length)], states[random.nextInt(states.length)])))
                .toList();

        List<User> sortedUsers = users.stream().sorted(Comparator.comparingInt(User::getAge).thenComparing(User::getName)).toList();

        List<User> filteredUsers = sortedUsers.stream().filter(u -> u.getAge() > 25 && !u.getAddress().getCity().equals("Miami")).toList();

        List<String> mainFields = filteredUsers.stream()
                .map(u -> u.getName() + " (age " + u.getAge() + ") from " + u.getAddress().getCity() + ", " + u.getAddress().getState())
                .toList();

        for (String i : mainFields){
            System.out.println(i);
        }
    }
}
