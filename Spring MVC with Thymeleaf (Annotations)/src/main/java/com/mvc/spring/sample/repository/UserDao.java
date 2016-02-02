package com.mvc.spring.sample.repository;

import com.mvc.spring.sample.model.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by ozge on 02.02.2016.
 */
//
//@Repository
//public class UserDao {
//
//    @Autowired
//    protected SessionFactory sessionFactory;
//
//    public User findByUsername(String username){
//        final Session session = sessionFactory.getCurrentSession();
//        Criteria c = session.createCriteria(User.class);
//        c.add(Restrictions.eq("username", username));
//        return (User) c.uniqueResult();
//    }
//}
