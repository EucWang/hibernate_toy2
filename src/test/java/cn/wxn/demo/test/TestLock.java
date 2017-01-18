package cn.wxn.demo.test;


/**
 * 并发的问题
 * @author wangxn
 *
 *
 * 一般并发会导致更新丢失,有两种方案可以解决并发问题
 * 1. 悲观锁
 * 	 悲观锁是hibernate基于数据库的机制来实现的,
 * 	此时hibernate3和4 所实现的机制是不一样的,
 * 			hibernate3 ,4  是基于同步的机制实现的 
 *          	//在hibernate4中使用如下的方式就是使用悲观锁
 *          	load(xx.Class, Object, LockOptions.UPGRADE);
 *          当只要读取了这个对象, 这个对象就会被加锁, 
 *          只有在第一个对象读取完成并提交之后第二个对象才能读取.这样大大的影响效率
 *          
 * 2. 乐观锁
 * 		乐观锁 是在数据库中增加了一个version字段来实现的, 每一次修改都会让这个字段的数字加1,
 * 		在读取的时候根据version这个版本来读取, 
 * 		如果并发修改, 就会抛出异常
 *          
 *      2.1 在对象的xx.hbm.xml中<class> 中增加一个一个元素
 *      	<class name="Student" table="u_student">
 *      		<id ...>
 *      		<version name="version" /> <!-- 只能放在id元素之后-->
 *      		<property>....
 *      	</class>    
 *      
 *      2.2 然后在对象中增加字段
 *      
 *      	public class Student{
 *      		...
 *      		
 *      		private int version;
 *      		...
 *      	}
 *          
 *      2.3 直接使用 load(xx.class, obj);就可以了,不需要使用其他方法
 *      
 *      2.4 在注解模式中 
 *      		直接为对象的属性version增加注解@Version
 */
public class TestLock {
	
	
	/**
	 * 
	 * 
	 * 更新丢失问题,
	 * 2个线程
	 * 
	 * 
	 */
	@org.junit.Test
	public void test1(){
		
	}

}
