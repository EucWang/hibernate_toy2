package cn.wxn.demo.test;

/**
 * 测试抓取策略
 * @author wangxn
 *
 *
 * 
 */
public class TestFetch {
	
	
	/**
	 * xml配置时, 
	 * load 加载Student, 然后通过student来获取classroom, special
	 * 默认情况下会发出3条sql,
	 * 分别取Student,classroom, special
	 * 
	 * 通过设置 <many-to-one name="" column="" fetch="join" />
	 * 可以完成对抓取的设置
	 * 需要对student,classroom都设置这个属性
	 * 然后才会只发出一条sql
	 * 
	 * 问题:
	 * 		使用fetch="join"
	 * 		虽然可以将关联对象一次抓取,但是在不使用关联对象的情况下依然会一并查询出来
	 * 		这样会占用相应的内存,延迟加载无效
	 * 
	 * 
	 * 注解配置时,
	 * 默认就是join的抓取策略,所以会只发出一条sql
	 */
	@org.junit.Test
	public void test1(){
		
	}
	
	
	/**
	 * 通过hql查询学生列表, from Student
	 * 由于基于注解的配置没有延迟加载,会发出大量的sql语句
	 * 
	 * 可以设置
	 *   @ManyToOne(fetch=FetchType.LAZY)
	 * 就默认不会查询相关联的对象
	 * 
	 * 
	 */
	@org.junit.Test
	public void test2(){
		
	}
	
	
	/**
	 * 通过hql查询学生列表, from Student
	 * 然后打印出学生名称,以及对应的classroom信息
	 * 
	 * 在XML中配置配置了 fetch=join 仅仅只对load有用,对hql中查询的对象无用,
	 * 所以此时会发出大量查询班级的sql,解决这个sql的问题有两种方案
	 * 
	 * 1.  设置xml中对象抓取的batch-size , 默认为1,改为20,一次就会join抓取20条数据
	 * 		<class name="classroom" table="u_classroom" batch-size="20">
	 * 
	 *     对应注解, 批量数据获取时, 也可以设置batchSize属性,在类对象上设置,比如
	 *     
	 *     @Entity
	 *     @Table(name="u_classroom")
	 *     @BatchSize(size=20)
	 *     public class Classroom{...}
	 *     
	 * 
	 * 2. 在hql中使用fetch 来指定抓取策略
	 *       加上 "join fetch"
	 * 		比如: "select stu from Student stu join fetch stu.classroom"
	 * 
	 * 基于注解的配置默认的many-to-one的抓取策略是EAGER,所以当抓取classroom时会自动
	 * 发出多条sql去查询相应的special,
	 * 此时可以通过join fetch继续完成关联的抓取
	 * 
	 * 或者直接将关联对象的fetch设置为lazy,
	 * 但是使用lazy所带来的问题是在查询关联对象时需要发出相应的sql,很多时候会影响效率
	 */
	@org.junit.Test
	public void test3(){
		
	}

	/**
	 * 在hql中使用join fetch
	 * 使用了join fetch , 就不能使用count()
	 */
	@org.junit.Test
	public void test4(){
		
	}
	
	
	/**
	 * 针对 一的一方的抓取
	 * 
	 * load 对象Classroom
	 * 打印
	 */
	@org.junit.Test
	public void test5(){
		
	}
	
	
	
	
	
	
	
}
