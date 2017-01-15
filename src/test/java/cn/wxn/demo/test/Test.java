package cn.wxn.demo.test;

import java.util.Random;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;

import cn.wxn.demo.entity.Classroom;
import cn.wxn.demo.entity.Special;
import cn.wxn.demo.entity.Student;

public class Test {

	private Session session;

	@Before
	public void init() {
		Configuration configure = new Configuration().configure();
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configure.getProperties())
				.buildServiceRegistry();
		SessionFactory sessionFactory = configure.buildSessionFactory(serviceRegistry);

		session = sessionFactory.openSession();
	}

	@After
	public void tearDown() {
		if (session != null) {
			session.close();
		}
	}

	@org.junit.Test
	public void test() {
		try {
			session.beginTransaction();

			Special special = new Special();
			special.setType("电气工程及其自动化");
			special.setName("电气工程系");
			session.save(special);

			Classroom classroom1 = new Classroom();
			classroom1.setName("电气工程及其自动化一班");
			classroom1.setGrade(2009);
			classroom1.setSpecial(special);

			Classroom classroom2 = new Classroom();
			classroom2.setName("电气工程及其自动化三班");
			classroom2.setGrade(2009);
			classroom2.setSpecial(special);

			session.save(classroom1);
			session.save(classroom2);

			Student s1 = new Student();
			s1.setName("张飞");
			s1.setGender("男");
			s1.setClassroom(classroom1);

			Student s2 = new Student();
			s2.setName("刘备");
			s2.setGender("男");
			s2.setClassroom(classroom1);

			Student s3 = new Student();
			s3.setName("张辽");
			s3.setGender("男");
			s3.setClassroom(classroom2);

			session.save(s1);
			session.save(s2);
			session.save(s3);

			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			if (session != null) {
				session.getTransaction().rollback();
			}
		}
	}

	@org.junit.Test
	public void test2() {

		try {
			session.beginTransaction();

			Classroom classroom = (Classroom) session.load(Classroom.class, 1);
			System.out.println(classroom.getSpecial().getName());

			System.out.println(classroom.getStudents().size());

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@org.junit.Test
	public void testAdd() {
		
		try {
			session.beginTransaction();
			
			
			Special special1 = new Special("计算机教育", "教育类");
			Special special2 = new Special("计算机应用技术", "高职类");
			Special special3 = new Special("计算机网络技术", "高职类");
			Special special4 = new Special("计算机信息管理", "高职类");
			Special special5 = new Special("数学教育", "教育类");
			Special special6 = new Special("物理教育", "教育类");
			Special special7 = new Special("化学教育", "教育类");
			Special special8 = new Special("会计", "高职类");
			Special special9 = new Special("英语教育", "教育类");
			
			session.save(special1);
			session.save(special2);
			session.save(special3);
			session.save(special4);
			session.save(special5);
			session.save(special6);
			session.save(special7);
			session.save(special8);
			session.save(special9);

			Classroom classroom1 = new Classroom("计算机教育1班", 2009, new Special(1));
			Classroom classroom2 = new Classroom("计算机教育2班", 2009, new Special(1));
			Classroom classroom3 = new Classroom("计算机教育班", 2010, new Special(1));
			Classroom classroom4 = new Classroom("计算机教育班", 2011, new Special(1));
			Classroom classroom5 = new Classroom("计算机应用技术", 2009, new Special(2));
			Classroom classroom6 = new Classroom("计算机应用技术", 2010, new Special(2));
			Classroom classroom7 = new Classroom("计算机应用技术", 2011, new Special(2));
			Classroom classroom8 = new Classroom("计算机网络技术", 2009, new Special(3));
			Classroom classroom9 = new Classroom("计算机网络技术", 2010, new Special(3));
			Classroom classroom10 = new Classroom("计算机网络技术", 2011, new Special(3));
			Classroom classroom11 = new Classroom("计算机信息管理", 2009, new Special(4));
			Classroom classroom12 = new Classroom("计算机信息管理", 2010, new Special(4));
			Classroom classroom13 = new Classroom("计算机信息管理", 2011, new Special(4));
			Classroom classroom14 = new Classroom("计算机信息管理", 2009, new Special(1));
			Classroom classroom15 = new Classroom("计算机信息管理", 2010, new Special(1));
			Classroom classroom16 = new Classroom("计算机信息管理", 2011, new Special(1));
			Classroom classroom17 = new Classroom("数学教育3班", 2009, new Special(5));
			Classroom classroom18 = new Classroom("数学教育1班", 2010, new Special(5));
			Classroom classroom19= new Classroom("数学教育2班", 2010, new Special(5));
			Classroom classroom20 = new Classroom("数学教育1班", 2011, new Special(5));
			Classroom classroom21 = new Classroom("数学教育2班", 2011, new Special(5));
			Classroom classroom22 = new Classroom("物理教育", 2009, new Special(6));
			Classroom classroom23 = new Classroom("物理教育", 2010, new Special(6));
			Classroom classroom24 = new Classroom("物理教育", 2011, new Special(6));
			Classroom classroom25 = new Classroom("化学教育", 2009, new Special(7));
			Classroom classroom26 = new Classroom("化学教育", 2010, new Special(7));
			Classroom classroom27 = new Classroom("化学教育", 2011, new Special(7));
			Classroom classroom28 = new Classroom("会计", 2009, new Special(8));
			Classroom classroom29 = new Classroom("会计", 2010, new Special(8));
			Classroom classroom30 = new Classroom("会计", 2011, new Special(8));
			Classroom classroom31 = new Classroom("英语教育A班", 2009, new Special(9));
			Classroom classroom32 = new Classroom("英语教育B班", 2009, new Special(9));
			Classroom classroom33 = new Classroom("英语教育A班", 2010, new Special(9));
			Classroom classroom34 = new Classroom("英语教育B班", 2010, new Special(9));
			Classroom classroom35 = new Classroom("英语教育A班", 2011, new Special(9));
			Classroom classroom36 = new Classroom("英语教育B班", 2011, new Special(9));
			Classroom classroom37 = new Classroom("选修课班A班", 2011, new Special(9));
			Classroom classroom38 = new Classroom("选修课班B班", 2011, new Special(9));

			session.save(classroom1);
			session.save(classroom2);
			session.save(classroom3);
			session.save(classroom4);
			session.save(classroom5);
			session.save(classroom6);
			session.save(classroom7);
			session.save(classroom8);
			session.save(classroom9);
			session.save(classroom10);
			session.save(classroom11);
			session.save(classroom12);
			session.save(classroom13);
			session.save(classroom14);
			session.save(classroom15);
			session.save(classroom16);
			session.save(classroom17);
			session.save(classroom18);
			session.save(classroom19);
			session.save(classroom20);
			session.save(classroom21);
			session.save(classroom22);
			session.save(classroom23);
			session.save(classroom24);
			session.save(classroom25);
			session.save(classroom26);
			session.save(classroom27);
			session.save(classroom28);
			session.save(classroom29);
			session.save(classroom30);
			session.save(classroom31);
			session.save(classroom32);
			session.save(classroom33);
			session.save(classroom34);
			session.save(classroom35);
			session.save(classroom36);
			session.save(classroom37);
			session.save(classroom38);
			
			for(int i = 0; i<100;i++){
				int a = (int)(Math.random()*first_name.length);
				int b = (int)(Math.random()*last_name.length);
				int c = (int)(Math.random()*38) + 1;
				
				String name = first_name[a] + last_name[b];
				String gender = Math.random() > 0.5 ? "男" : "女";
				Classroom classroom = (Classroom)session.load(Classroom.class, c);
				
				Student student = new Student(name, gender, classroom);
				System.out.println(student.toString());
				session.save(student);
			}
			
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (session != null) {
				session.getTransaction().rollback();
			}
		}
	}
	
	String[] first_name = new String[]{"赵","钱","孙","李","周","吴","郑","王"};
	String[] last_name = new String[]{"老大","老二","老三","老四","老五","老六","老七","老八","老九","老末"};
	
	
}
