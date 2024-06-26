package com.hib.tut.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.hib.tut.entities.Students;
import com.hib.tut.helper.SessionFactoryProvider;

public class StudentsDAO {

	public static void save(Students s) {

		Session sf = SessionFactoryProvider.getSession().openSession();
    	Transaction tx =  sf.beginTransaction();
    	
    	sf.save(s);	
    	tx.commit();
    	
    	sf.close();
    	//SessionFactoryProvider.closeSession();
    
    	if(tx!=null) {
            tx.rollback();
        }
	}
	
	public static Students fetch(int roll) {

		Session sf = SessionFactoryProvider.getSession().openSession();
    	Transaction tx =  sf.beginTransaction();
    	
    	Students s = (Students)sf.get(Students.class, roll);	
    	tx.commit();
    	
    	sf.close();
    	//SessionFactoryProvider.closeSession();
    
    	return s;
	}
	
	public static boolean delete(Students s) {

		Session sf = SessionFactoryProvider.getSession().openSession();
    	Transaction tx =  sf.beginTransaction();
    	
    	boolean f = false;
    	sf.delete(s);
    	f = true;
    	tx.commit();
    	
    	sf.close();
    	//SessionFactoryProvider.closeSession();
    
    	return f;
	}

	public static boolean update(Students s) {

		Session sf = SessionFactoryProvider.getSession().openSession();
    	Transaction tx =  sf.beginTransaction();
    	
    	boolean f = false;
    	sf.saveOrUpdate(s);
    	f = true;
    	tx.commit();
    	
    	sf.close();
    	//SessionFactoryProvider.closeSession();
    
    	return f;
	}

	
	
	
	/*
	 * For custom query========================
	 * SQL => session.createNativeQuery()
	 * HQL => session.createQuery()
	 * HCQL =>session.createCriteria()
	 * */
	public static List<Students> fetchAll() {

		Session sf = SessionFactoryProvider.getSession().openSession();
    	Transaction tx =  sf.beginTransaction();
    	
    	Query<Students> q = sf.createNativeQuery("SELECT * FROM students", Students.class);
    	List al = q.getResultList();
    	
    	tx.commit();
    	sf.close();
    
    	return al;
	}
	
	public static List<Students> fetchAllRollNoGTThan104() {

		Session sf = SessionFactoryProvider.getSession().openSession();
    	Transaction tx =  sf.beginTransaction();
    	
    	Criteria c = sf.createCriteria(Students.class);
    	c.add(Restrictions.gt("rollNo", 104));
    	c.addOrder(Order.desc("rollNo"));
    	List al = c.list();
    	
    	tx.commit();
    	sf.close();
    
    	return al;
	}
	
	public static List<Students> namedQueryExample() {

		Session s = SessionFactoryProvider.getSession().openSession();
    	Transaction tx =  s.beginTransaction();
    	
    	//Query nq = s.createNamedQuery("FindByCollege");
    	TypedQuery nq = s.getNamedQuery("FindByCollege");
    	nq.setParameter("cn", "MU");
    	List<Students> al = nq.getResultList();
    	
    	tx.commit();
    	s.close();
    
    	return al;
	}
}
