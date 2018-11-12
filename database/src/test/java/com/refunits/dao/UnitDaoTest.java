package com.refunits.dao;

import com.refunits.model.Unit;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

public class UnitDaoTest {

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
            session.createQuery("delete from Unit ").executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Test
    public void checkSaveUnit() {
        Unit unit = new Unit("AKM.N10-0048-1xZB21K-K45", 4.8);
        UnitDao.getInstance().save(unit);
    }

    @Test
    public void checkUpdateUnit() {
        Unit unit = new Unit("AKM.N10-0048-1xZB21K-K45", 4.8);
        UnitDao.getInstance().save(unit);

        unit.setPrice(1000);
        UnitDao.getInstance().update(unit);
    }

    @Test
    public void checkDeleteUnit() {
        Unit unit = new Unit("AKM.N10-0048-1xZB21K-K45", 4.8);
        UnitDao.getInstance().save(unit);

        UnitDao.getInstance().delete(unit);
    }

    @Test
    public void checkGetUnitById() {
        Unit unit = new Unit("AKM.N10-0048-1xZB21K-K45", 4.8);
        UnitDao.getInstance().save(unit);

        UnitDao.getInstance().getById(1);
    }

    @Test
    public void checkGetAllUnits() {
        Unit unit = new Unit("AKM.N10-0048-1xZB21K-K45", 4.8);
        UnitDao.getInstance().save(unit);

        UnitDao.getInstance().getAll();
    }
}
