package com.spoom.service;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-jpa.xml")
public class UserServiceJunitTest {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/*@Autowired
	private UserService userService;
	
	
	@Test
	public void testFindOne2stCache(){
		User user = userService.findById(1);
		User user2 = userService.findById(1);
	}
	
	
	@Transactional
	public void testFindOne1stCache(){
		//Hibernate 一级缓存测试注意一定要在同一个事务里面
		//同一个session内部，一级缓存生效，同一个id的对象只有一个。不同session，一级缓存无效
		User user = userService.findById(1);
		User user2 = userService.findById(1);
	}
	
	
	public void testFindAll(){
		try{
			int i = 1/0;
			System.out.println(userService.findAll().size());
			logger.info("熊猫：{}","abc");
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		
	}
	
	
	public void testSave(){
		//User user = userService.findById(1);
		User user = new User();
		user.setName("Jony");
		Killed killed = new Killed();
		killed.setUser(user);
		List<Killed> killeds = new ArrayList<Killed>();
		killeds.add(killed);
		user.setKilleds(killeds);
		userService.save(user);
	}
	
	
	public void testInsert(){
		User user = new User();
		user.setName("Jony");
		user.setPhone("13414985655");
		userService.save(user);
	}
	
	public void testTransactionRollBack(){
		userService.testTransactionRollBack(1);
	}*/
}
