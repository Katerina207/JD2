package dao;

import lombok.AccessLevel;
import lombok.Cleanup;
import lombok.NoArgsConstructor;
import model.Unit;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UnitDao {

    private static final UnitDao INSTANCE = new UnitDao();

    public static UnitDao getInstance() {
        return INSTANCE;
    }

    public List<Unit> getAll() {
        List<Unit> list;
        @Cleanup
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        try (
                Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            list = session
                    .createQuery("select u from Unit u", Unit.class).list();
            System.out.println();

            session.getTransaction().commit();
        }
        return list;
    }
}