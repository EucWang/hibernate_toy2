package cn.wxn.demo.test;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.hql.internal.ast.HqlASTFactory;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;

import cn.wxn.demo.entity.Special;
import cn.wxn.demo.entity.Student;
import cn.wxn.demo.entity.StudentDTO;

public class TestHql {

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

	@SuppressWarnings("unchecked")
	@org.junit.Test
	public void test() {
		List<Special> specials = session.createQuery("from Special").list();
		for (int i = 0; i < specials.size(); i++) {
			System.out.println(specials.get(i).getName());
		}
	}

	@SuppressWarnings("unchecked")
	@org.junit.Test
	public void test15() {
		List<Student> students = session.createQuery("from Student s where s.name like ?").setParameter(0, "%老八%")
				.list();
		for (int i = 0; i < students.size(); i++) {
			System.out.println(students.get(i));
		}
	}

	@SuppressWarnings("unchecked")
	@org.junit.Test
	public void test16() {
		List<Student> students = session.createQuery("from Student s where s.name like ?").setParameter(0, "%老八%")
				.setFirstResult(0).setMaxResults(5).list();
		for (int i = 0; i < students.size(); i++) {
			System.out.println(students.get(i));
		}

	}

	@SuppressWarnings("unchecked")
	@org.junit.Test
	public void test2() {
		List<Student> students = session.createQuery("from Student s where s.name like :name and gender = :gender ")
				.setParameter("name", "%老八%").setParameter("gender", "女").list();
		for (int i = 0; i < students.size(); i++) {
			System.out.println(students.get(i));
		}

	}

	@org.junit.Test
	public void test21() {
		Long count = (Long) session
				.createQuery("select count(*) from Student s where s.name like :name and gender = :gender ")
				.setParameter("name", "%老八%").setParameter("gender", "女").uniqueResult();
		System.out.println(count);
	}
	
	/**
	 * 查询男生多少人, 女生多少人
	 * group by 
	 */
	@SuppressWarnings("unchecked")
	@org.junit.Test
	public void test3(){
		List<Object[]> counts = session.createQuery("select stu.gender, count(*) from Student stu group by stu.gender")
				.list();
		for(Object[] objs : counts){
			System.out.println(objs[0] + " : " + objs[1]);
		}
	}
	
	/**
	 * 查询某个班级中的男生
	 * 如果对象中响应的导航对象, 可以直接导航完成查询
	 * 其实hibernate生成的最后的sql语句还是使用的 left out join来做的查询,
	 * 但是在hql中就可以是简单的 通过where属性的某个值来查询
	 */
	@SuppressWarnings("unchecked")
	@org.junit.Test
	public void test4(){ 
		List<Student> students = session.createQuery("select stu from Student stu where stu.classroom.id=?")
				.setParameter(0, 1)
				.list();
		for(Student s : students){
			System.out.println(s);
		}
	}
	
	/**
	 * 查询1班和2班所有姓张的学生
	 * 可以使用in 来基于列表的查询, 这里必须使用别名来查询, in语句必须放置到where语句最后
	 */
	@SuppressWarnings("unchecked")
	@org.junit.Test
	public void test5(){ 
		List<Student> students = session.createQuery("select stu from Student stu where stu.name like :name and stu.classroom.cid in (:ids)")
				.setParameter("name", "%钱%")
				.setParameterList("ids", new Integer[]{1,2})
				.list();
		for(Student s : students){
			System.out.println(s);
		}
	}
	
	
	/**
	 * 查询1班和2班所有姓张的学生
	 * 
	 * 分页查询
	 */
	@SuppressWarnings("unchecked")
	@org.junit.Test
	public void test6(){ 
		List<Student> students = session.createQuery("select stu from Student stu where stu.classroom.cid in (:ids)")
				.setParameterList("ids", new Integer[]{1,2,3,4})
				.setFirstResult(0)
				.setMaxResults(5)
				.list();
		for(Student s : students){
			System.out.println(s);
		}
	}

	
	/**
	 * 查询 没有班级的学生
	 * is null
	 * = null 
	 * 这两种形式都可以使用
	 */
	@SuppressWarnings("unchecked")
	@org.junit.Test
	public void test7(){ 
		List<Student> students = session.createQuery("select stu from Student stu  where stu.classroom.cid = null")
								.list();
		for(Student s : students) {
			System.out.println(s);
		}
	
	}
	/**
	 * 连接查询,所有的班级名称,以及班级中的学生人数,
	 * 即时班级中学生人数为0点班级也需要显示
	 */
	@SuppressWarnings("unchecked")
	@org.junit.Test
	public void test8(){ 
		List<Object[]> objsList= session.createQuery("select cla.name, count(stu.id) from Student stu right join stu.classroom cla group by cla.cid")
				.list();
		for(Object[] objs : objsList){
			System.out.println(objs[0] + ", " + objs[1]);
		}
	}
	
	/**
	 * 连接查询3张表.
	 * 学生姓名, 班级名称, 班级年级, 专业名称
	 * 创建一个新的DTO对象, 在hql中需要使用类全名, 这里对应的需要添加响应的构造函数,同时必须有默认的构造函数
	 * DTO对象中的各个属性必须和查询的属性保持一致性
	 */
	@SuppressWarnings("unchecked")
	@org.junit.Test
	public void test9(){
		List<StudentDTO> studentDTOs =  session.createQuery("select new cn.wxn.demo.entity.StudentDTO"
				+ "(stu.id as sid, stu.name as sname, cla.name as cname, cla.grade as grade, spe.name as speame) "
				+ "from Student stu left join "
				+ "stu.classroom cla left join "
				+ "cla.special spe")
				.setFirstResult(11)
				.setMaxResults(10)
				.list();
		for(StudentDTO s : studentDTOs){
			System.out.println(s);
		}
	}
	
	/**
	 * 专业中不同性别的人数
	 * 
	 *  select sss.sid, sss.s_name, sss.type, s.gender, count(s.id) stu_num
		from u_student s
		right join 
		(
			select c.cid, c.name, c.grade, ss.sid, ss.s_name,ss.type from u_classroom c
			left join u_special ss
			on (ss.sid = c.special_id) 
		) sss
		on (s.classroom_id = sss.cid)
		GROUP BY sss.sid, s.gender
		having s.gender is not null;
	 * 
	 */
	@SuppressWarnings("unchecked")
	@org.junit.Test
	public void test10(){
		String hql = "select ss.id, ss.name, s.gender, count(s.id) from Student s right join s.classroom.special ss group by ss.id, s.gender having s.gender is not null";
		List<Object[]> objsList = session.createQuery(hql).list();
		for(Object[] objects : objsList) {
			System.out.println(objects[0] + ",\t" + objects[1] + ",\t" + objects[2] + ",\t" + objects[3]);
		}
	}
	
	
	/**
	 * 统计人数大于5人的班
	 * having 为group by设置条件的
	 * 
	 * sql:
	 *      select c.name, c.grade , count(s.id) stu_num
			from u_classroom c
			left join u_student s
			on (s.classroom_id = c.cid)
			group by c.cid
			having stu_num > 5;
	 * 
	 */
	@SuppressWarnings("unchecked")
	@org.junit.Test
	public void test11(){
		
		String hql = "select c.grade, c.name, count(s.id) from Student s right join s.classroom c group by c.cid having count(s.id) > 5";
		List<Object[]> objsList = session.createQuery(hql).list();
		for(Object[] objects : objsList) {
			System.out.println(objects[0] + ",\t" + objects[1] + ",\t" + objects[2] );
		}
	}
	
	/**
	 * 统计人数大于5人的班, 分男女来分组
	 * having 为group by设置条件的 sql如下: 
	 * 
	 *     select c.cid, c.name, c.stu_num, s.gender, count(s.id) gender_num 
			from (
				select c.cid, c.grade, c.name, count(c.cid) as stu_num
				from u_classroom c 
				join  u_student s
				on s.classroom_id = c.cid
				group by c.cid
				having count(*)>4
			) c 
			left join u_student s
			on s.classroom_id = c.cid
			group by c.cid,s.gender ;
	 *		
	 * ????问题
	 * 使用hql不能创造出如上累死的sql语句
	 * ????问题
	 * @TODO
	 */
	@SuppressWarnings("unchecked")
	@org.junit.Test
	public void test12(){
		String hql = "select cla.name, s.gender, count(s.id) "
				+ "from Student s "
				+ "right join  s.classroom cla "
				+ "group by cla.cid, s.gender ";
		List<Object[]> objsList =session.createQuery(hql).list();
		for(Object[] objects : objsList) {
			System.out.println(objects[0] + ",\t" + objects[1] + ",\t" + objects[2]);
		}
	}
}
