package cn.wxn.demo.test;

public class TestCache {

	
	
	/**
	 * 通过hql查询所有的学生, 返回学生的List集合, 只发出一条sql
	 */
	@org.junit.Test
	public void test1(){
		
	}
	
	
	/**通过hql查询所有的学生, 返回学生的Iterator集合,
	 * 对于hibernate而言,它仅仅只是发出一条取id列表的sql
	 * 在查询相应的具体的某个学生信息时,才会发出相应的sql去取学生信息
	 * 这个就是典型的N+1问题
	 * 
	 * 存在Iterator的原因是:
	 * 有可能会在一个session中查询两次数据,如果使用list每一次都会把所有的对象查询上来
	 * 而通过iterator仅仅只会查询id,此时所有的对象已经存储在一级缓存(session的缓存中)
	 */
	@org.junit.Test
	public void test2(){
		
	}
	

	
	/**
	 * 一级缓存就是session 的缓存,只要session没有关闭, 就首先从session中取数据,而不会去数据库中查
	 */
	@org.junit.Test
	public void test3(){
		
	}
	
	/**
	 * 二级缓存就是sessionFactory 的缓存,
	 * 
	 * 使用二级缓存的步骤:
	 *    1. hibernate并没有提供相应的二级缓存组件,所以需要额外的二级缓存包,常用的二级缓存包时EHcache
	 *         ehcache-core-x.x.x.jar
	 *         hiberante-ehcache-x.x.x.jar
	 *         slf4j-api-x.x.x.jar
	 *    
	 *    2.在配置中开启二级缓存
	 *      <property name="hibernate.cache.use_second_level_cache">true</property>
	 *     
	 *    3.在配置中设置二级缓存提供的类
	 *      <property name="hibernate.cache.provider_class">net.sf.ehcache.hibernate.EhCacheProvider</property>
	 *      在hiberante4.0之后需要设置如下FactoryClass选项
	 *      <property name="hibernate.cache.factory_class">org.hiberante.cache.ehcache.EhCacheRegionFactory</property>
	 *   
	 *    4.设置相应的ehcache.xml文件,在这个文件中配置二级缓存的参数
	 *    		<ehcache>
	 *    			<diskStore path="java.io.tmpdir" />   <!--硬盘上临时存储文职 -->
	 *    		
	 *          	<defaultCache
	 *         			maxElementsInMemory="10000"       <!--内存中最大存储对象数量 -->
	 *         			eternal="false"                   <!-- 是否永远不再内存中消失-->
	 *         			timeToIdleSeconds="120"            <!--空闲的对象每个120second读一次 -->
	 *         			timeToLiveSeconds="120"           <!--活动的对象每个120second读一次 -->
	 *         			overflowToDisk="true" />          <!--true:超过如上时间就将其存储到硬盘中 -->
	 *         
	 *         	   <!-- 每一个独立的cache可以单独为不同的对象进行设置 -->
	 *             <cache name="sampleCache1"
	 *         			maxElementsInMemory="10000"       <!--内存中最大存储对象数量 -->
	 *         			eternal="false"                   <!-- 是否永远不再内存中消失-->
	 *         			timeToIdleSeconds="300"            <!--空闲的对象每个120second读一次 -->
	 *         			timeToLiveSeconds="600"           <!--活动的对象每个120second读一次 -->
	 *         			overflowToDisk="true" />          <!--超过如上时间就将其存储到硬盘中 -->
	 *         		 
	 *         		<cache name="sampleCache2"
	 *         			maxElementsInMemory="1000"       <!--内存中最大存储对象数量 -->
	 *         			eternal="true"                   <!-- 是否永远不再内存中消失-->
	 *         			timeToIdleSeconds="0"            <!--空闲的对象每个120second读一次 -->
	 *         			timeToLiveSeconds="0"           <!--活动的对象每个120second读一次 -->
	 *         			overflowToDisk="false" />          <!--超过如上时间就将其存储到硬盘中 -->
	 *         
	 *              <!-- 每一个独立的cache可以单独为不同的对象进行设置 -->
	 *         		<cache name="cn.wxn.demo.entity.Student"
	 *         			maxElementsInMemory="1000"       <!--内存中最大存储对象数量 -->
	 *         			eternal="true"                   <!-- 是否永远不再内存中消失-->
	 *         			timeToIdleSeconds="100"            <!--空闲的对象每个120second读一次 -->
	 *         			timeToLiveSeconds="100"           <!--活动的对象每个120second读一次 -->
	 *         			overflowToDisk="false" />          <!--超过如上时间就将其存储到硬盘中 -->
	 *          </ehcache>
	 *    		
	 *    5.在hibernate.cfg.xml中配置ehcache.ml文件的位置
	 *    		<property name="hibernate.cache.provider_configuration_file_resource_path">ehcache.xml</property> 
	 *    
	 *    6. 开启对象的缓存, 最好只设置为read-only , 如果设置其他的比如read-write还不如不用二级缓存
	 *    	在对象的  xxx.hbm.xml中设置
	 *    		<class name="Student" table="u_student">
	 *    			<cache usage="read-only" />
	 *    		....
	 *    		
	 *    7. 二级缓存是基于对象的缓存,是缓存的整个对象	
	 *    
	 *    
	 *    iterator的使用主要就是为了配合二级缓存使用
	 *    在二级缓存已经获取到数据的情况下, 通过hql获取到对象的List集合,这样的查询依然会去数据库中查,而不会使用二级缓存中以及存在的
	 *    但是通过hql返回对象的Iterator集合,是会先去二级缓存中找的,而不会直接去数据库中查的
	 *    
	 */
	@org.junit.Test
	public void test4(){
		
	}
	
	
	
	/**
	 * 
	 * 	  查询缓存必须在开启二级缓存的情况下使用.
	 *    查询缓存是针对hql语句的缓存,查询缓存也是SessionFactory级别的缓存 
	 *    
	 *    
	 *    1.. 设置查询缓存
	 *    	<property name="hibernate.cache.use_query_cache">true</property>
	 *    
	 *    2.使用查询缓存  调用 setCachable()方法开启结果的查询缓存
	 *    	eg:
	 *    		List<Student> ls = session.createQuery("from Student")
	 *    							.setCachable(true)                //开启查询缓存,
	 *    							.setFirstResult(0).setMaxResult(50).list();
 	 *    
 	 *    使用查询缓存的问题:
 	 *    		1. 会占用大量的内存
 	 *    		2. 只要hql的语句不一样就不会开启查询缓存,查询缓存缓存的是hql语句
 	 *    			只有两个hql完全一致(并且参数都是一致) 才会使用查询缓存
 	 *    		3. 查询缓存缓存的不是对象,而是id,关闭对象的二级缓存设置, 第一次hql查询会缓存大量学生数据,
 	 *    			但是仅仅只是缓存了学生的id, 所以此时会发出大量sql语句去根据id查询对象,会发送大量的sql去数据库中查询
 	 *    			这也是发生N+1问题的第二个原因
 	 *    		3. 基于第二条, 使用查询缓存的情况很少
 	 *    
 	 *   所以如果使用查询缓存,必须开启二级缓存,
 	 *    
	 */
	@org.junit.Test
	public void test5(){
		
	}
	
	
	/**
	 * 注解方式开启 查询缓存的方式
 		
 	 *    @Entity
 	 *    @Table(name="u_student")
 	 *    @Cache(usage=CacheConcurrencyStrategy.READ_ONLY)
 	 *    public class Student {...}
	 */
	@org.junit.Test
	public void test6(){
		
	}
	
	
	
	
}
