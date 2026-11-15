package org.example;

import entities.User;
import services.UserService;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        UserService userService = new UserService();
        int id;
        User user;
        String name;
        int age;
        String email;
        boolean end = false;
        while (!end) {
            System.out.println("1. добавить пользователя");
            System.out.println("2. удалить пользователя");
            System.out.println("3. заменить данные пользователя");
            System.out.println("4. найти пользователя по имени");
            System.out.println("5. вывести список всех пользователей");
            System.out.println("6. очистить список пользователей");
            System.out.println("0. выход");

            System.out.print("Введите команду: ");
            int command = scanner.nextInt();
            switch (command) {
                case (1):
                    System.out.print("Введите имя: ");
                    name = scanner.next();
                    System.out.print("Введите возраст: ");
                    age = scanner.nextInt();
                    System.out.print("Введите email: ");
                    email = scanner.next();
                    user = new User(name, email, age);
                    userService.saveUser(user);
                    break;
                case (2):
                    System.out.print("Введите Id: ");
                    id = scanner.nextInt();
                    userService.deleteUser(id);
                    break;
                case (3):
                    System.out.print("Введите id: ");
                    id = scanner.nextInt();
                    user = userService.findUserById(id);
                    while (user == null){
                        System.out.print("Такой Id не найден введите другой id: ");
                        id = scanner.nextInt();
                        user = userService.findUserById(id);
                    }
                    System.out.println("1. Изменить имя");
                    System.out.println("2. Изменить возраст");
                    System.out.println("3. Изменить email");
                    System.out.print("Введите команду: ");
                    command = scanner.nextInt();
                    switch (command) {
                        case (1):
                            System.out.print("Введите имя: ");
                            name = scanner.next();
                            user.setName(name);
                            userService.updateUser(user);
                            break;
                        case (2):
                            System.out.print("Введите возраст: ");
                            age = scanner.nextInt();
                            user.setAge(age);
                            userService.updateUser(user);
                            break;
                        case (3):
                            System.out.print("Введите email: ");
                            email = scanner.next();
                            user.setEmail(email);
                            userService.updateUser(user);
                            break;
                    }
                    break;
                case (4):
                    System.out.print("Введите имя: ");
                    name = scanner.next();
                    user = userService.findUserByName(name);
                    if (user != null) {
                        System.out.println(userService.findUserByName(name));
                    }else {
                        System.out.println("Пользователь с таким именем не найден");
                    }
                    break;
                case (5):
                    List<User> list = userService.findAllUsers();
                    for(User userFromList : list){
                        System.out.println(userFromList);
                    }
                    break;
                case (6):
                    userService.clear();
                    break;
                case (0):
                    end = true;
                    break;
            }
        }
    }
}
