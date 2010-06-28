package com.kesstec;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class PersonDao {

    @Resource
    private SessionFactory sessionFactory;
    
    public void save(Person person) {
        sessionFactory.getCurrentSession().save(person);
    }

    public List<Person> findAll() {
        return sessionFactory.getCurrentSession().createQuery("from Person").list();
    }
}
