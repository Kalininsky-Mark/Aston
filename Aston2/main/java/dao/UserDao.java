package dao;

import entities.User;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import java.util.List;

public class UserDao {

    public User findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().find(User.class, id);
    }

    public User findByName(String name) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            String queryText = "From User where name = :name";
            Query query = session.createQuery(queryText);
            query.setParameter("name", name);

            return (User) query.getSingleResult();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void save(User user) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx1 = session.beginTransaction();
            session.persist(user);
            tx1.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void update(User user) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx1 = session.beginTransaction();
            session.merge(user);
            tx1.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(int id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx1 = session.beginTransaction();
            session.remove(session.find(User.class, id));
            tx1.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void clear() {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx1 = session.beginTransaction();
            String queryText = "Delete From User";
            session.createQuery(queryText).executeUpdate();
            tx1.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<User> findAll() {
        return (List<User>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From User").list();
    }
}
