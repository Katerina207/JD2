package com.refunits.database.repository;

import com.refunits.database.configuration.TestConfiguration;
import com.refunits.database.model.Admin;
import com.refunits.database.model.PreOrder;
import com.refunits.database.model.RegisteredUser;
import com.refunits.database.util.DatabaseHelper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@Transactional
public class PreOrderRepositoryTest {

    @Autowired
    private DatabaseHelper databaseHelper;

    @Autowired
    private PreOrderRepository preOrderRepository;

    @Autowired
    private RegisteredUserRepository registeredUserRepository;

    @Before
    public void init() {
        databaseHelper.cleanDatabase();
        databaseHelper.prepareData();
    }

    @Test
    public void checkFindAll() {
        preOrderRepository.findAll();
    }

    @Test
    public void checkFindById() {
        preOrderRepository.findById(1);
    }

    @Test
    public void checkFindAllByPreOrder(){
        RegisteredUser registeredUser = new Admin();

        if (registeredUserRepository.findById(1).isPresent()){
            registeredUser = registeredUserRepository.findById(1).get();
        }
     preOrderRepository.findAllByRegisteredUser(registeredUser);
    }

    @Test
    public void checkSave(){
        RegisteredUser registeredUser = new Admin("Admin1", "123", LocalDate.now());

        if (registeredUserRepository.findById(1).isPresent()){
            registeredUser = registeredUserRepository.findById(1).get();
        }

        PreOrder preOrder = new PreOrder(LocalDate.now(), registeredUser);
        preOrderRepository.save(preOrder);
        Assert.assertNotNull(preOrder);
    }
}
