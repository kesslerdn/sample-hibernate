package com.kesstec;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

import static junit.framework.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/context.xml"})
@TransactionConfiguration
@Transactional
public class PersonDaoTest{

    
    @Resource  
    private PersonDao personDao;

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Before
    public void setUp(){
        jdbcTemplate.execute("drop table Person if exists;");
        jdbcTemplate.execute("create table Person (id bigint identity, FirstName varchar, LastName varchar);");
    }
      
    @Test
    public void testSave(){
        Person firstPerson = new Person();
        firstPerson.setFirstName("One");
        firstPerson.setLastName("First");

        Person secondPerson = new Person();
        secondPerson.setFirstName("Two");
        secondPerson.setLastName("Second");

        personDao.save(firstPerson);
        personDao.save(secondPerson);

        List<Person> people = personDao.findAll();

        assertEquals(2, people.size());

        Person firstActual = people.get(0);
        Person secondActual = people.get(1);

        assertEquals(firstPerson.getFirstName(), firstActual.getFirstName());
        assertEquals(firstPerson.getLastName(), firstActual.getLastName());

        assertEquals(secondPerson.getFirstName(), secondActual.getFirstName());
        assertEquals(secondPerson.getLastName(), secondActual.getLastName());
    }


}
