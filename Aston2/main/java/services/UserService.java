package services;

import dao.UserDao;
import entities.User;

import java.util.List;

public class UserService {

    private UserDao usersDao = new UserDao();

    public UserService() {}

    public User findUserByName(String name) {
        return usersDao.findByName(name);
    }

    public User findUserById(int id) {
        return usersDao.findById(id);
    }

    public void saveUser(User user) {
        usersDao.save(user);
    }

    public void deleteUser(int id) {
        usersDao.delete(id);
    }

    public void updateUser(User user) {
        usersDao.update(user);
    }

    public List<User> findAllUsers() {
        return usersDao.findAll();
    }

    public void clear() {
        usersDao.clear();
    }
}
