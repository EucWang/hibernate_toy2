package cn.wxn.demo.test;

/**
 * 使用hibernate 进行原生的sql查询
 * @author wangxn
 *
 * 	1.
 *  方法调用 ,注意 addEntity()方法设置了返回的对象
 *	session.createSQLQuery(sql).addEntity(Student.class).list();
 *
 *  2.如何将查询之后的结果直接封装到DTO中?
 *  		注意: select 之后每个单独的对象的属性上的小括号
 *  		注意:多个addEntity(),中使用到了SQL中使用的别名来区分不同的类对象
 *  		注意: 其他参数设置和调用createQuery()方法时完全相同
 *  
 *  
 *       	List<Object[]> objsList = session.createSQLQuery(
 *       		"select (stu.*), (cla.*), (spe.*) from u_student stu "
 *       		+ "left join u_classroom cla on (stu.classroom_id=cla.cid) "
 *       		+ "left join u_special spe on (spe.id = cla.special_id) "
 *              + "where name like ?")
 *              .addEntity("stu", Student.class)
 *              .addEntity("cla", Classroom.class)
 *              .addEntity("spe", Special.class)
 *              .setParameter(0, "%周%")
 *              .setFirstResult(0).setMaxResults(10)
 *              .list();
 *		Student stu = null;
 *		Classroom cla = null;
 *		Special spe = null;
 *		List<StudentDto> studentDtos = new ArrayList<StudentDto>();
 *		for(Object[] objs : objsList) {
 *			stu = (Student)objs[0];
 *			cla = (Classroom)objs[1];
 *			spe = (Special)objs[2];
 *			//通过三个对象来组装StudentDto对象中的属性
 *			...
 *		}
 *
 * 3. 如何将查询之后的结果直接封装到DTO中?
 * 			构造对应的StudentDto的构造方法以及默认构造方法
 * 			List<StudentDto> studentDtos = session.createSQLQuery(
 *       		"select stu.id as sid, stu.name as sname, cla.name as cname, cla.grade as grade, spe.name as spename, spe.type as type from u_student stu "
 *       		+ "left join u_classroom cla on (stu.classroom_id=cla.cid) "
 *       		+ "left join u_special spe on (spe.id = cla.special_id) "
 *              + "where name like ?")
 *              .setResultTransformer(Transformers.aliasToBean(StudentDto.class));
 *               .setParameter(0, "%周%")
 *              .setFirstResult(0).setMaxResults(10)
 *              .list();
 *			
 */
public class TestSql {
	
	
	

}
