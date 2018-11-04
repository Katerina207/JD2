package model;

import enumeration.OrderStatus;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RefUnitsDaoTest {

    private static final SessionFactory FACTORY = new Configuration()
            .configure()
            .buildSessionFactory();

    @AfterClass
    public static void closeFactory() {
        FACTORY.close();
    }

    @Before
    public void clean() {
        try (Session session = FACTORY.openSession()) {
            session.beginTransaction();
            session.createQuery("delete from RegisteredUser ").executeUpdate();
            session.createQuery("delete from Unit ").executeUpdate();
            session.createQuery("delete from Option ").executeUpdate();
            session.createQuery("delete from Product ").executeUpdate();
            session.createQuery("delete from PreOrder ").executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Test
    public void checkSaveEntity() {
        try (Session session = FACTORY.openSession()) {
            Admin admin = new Admin("Admin", "1111", LocalDate.now());
            Consumer consumer = new Consumer("Client", "1111", LocalDate.now(), "Company");
            Manager manager = new Manager("Manager", "1111", LocalDate.now(), "RefUnits");

            Unit unit = new Unit("AKM.N10-0048-1xZB21K-K45", 4.8);
            Option option = new Option("A1", 20);

            Set<Option> options = new HashSet<>();
            options.add(option);
            Product product = new Product(1, 800, unit, options);

            Set<Product> products = new HashSet<>();
            products.add(product);
            PreOrder preOrder = new PreOrder(LocalDate.now(), OrderStatus.ACCEPTED, products, consumer);

            session.save(admin);
            session.save(consumer);
            session.save(manager);
            session.save(unit);
            session.save(option);
            session.save(product);
            session.save(preOrder);
        }
    }

    @Test
    public void checkGetAll() {
        try (Session session = FACTORY.openSession()) {
            List<RegisteredUser> listRegisteredUsers =
                    session.createQuery("select r from RegisteredUser r", RegisteredUser.class).list();
            System.out.println(listRegisteredUsers);
            List<Unit> listUnits =
                    session.createQuery("select u from Unit u", Unit.class).list();
            System.out.println(listUnits);
            List<Option> listOptions =
                    session.createQuery("select o from Option o", Option.class).list();
            System.out.println(listOptions);
            List<Product> listProducts =
                    session.createQuery("select p from Product p", Product.class).list();
            System.out.println(listProducts);
            List<PreOrder> listPreOrders =
                    session.createQuery("select pr from PreOrder pr", PreOrder.class).list();
            System.out.println(listPreOrders);
        }
    }
}
