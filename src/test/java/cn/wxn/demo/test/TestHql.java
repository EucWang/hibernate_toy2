package cn.wxn.demo.test;

import java.util.List;

import javax.persistence.OrderBy;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;

import cn.wxn.demo.entity.Special;
import cn.wxn.demo.entity.Student;

public class TestHql {

	private Session session;

	@Before
	public void init(){
		Configuration configure = new Configuration().configure();
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configure.getProperties())
				.buildServiceRegistry();
		SessionFactory sessionFactory = configure.buildSessionFactory(serviceRegistry);

		session = sessionFactory.openSession();
	}
	
	@After
	public void tearDown(){
		if (session != null) {
			session.close();
		}
	}
	
	@org.junit.Test
	public void test(){
		
		List<Special> specials = session.createQuery("from Special").list();
		for (int i = 0; i < specials.size(); i++) {
			System.out.println(specials.get(i).getName());
		}
		
	}
	
	
	@org.junit.Test
	public void test2(){
		
		List<Student> students = session.createQuery("from Student s where s.name like :name and gender = :gender ")
				.setParameter("name", "%老八%")
				.setParameter("gender", "女")
				.list();
		for (int i = 0; i < students.size(); i++) {
			System.out.println(students.get(i));
		}
		
	}
	
	
}
