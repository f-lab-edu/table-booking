package me.app.tablebooking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement //mybatis는 DataSourceTransactionManager로 커넥션 레벨 트랜잭션을 관리한다. JPA와 달리 영속성 컨텍스트가 없어, 변경 감지 1차 캐시 flush와 같은 동작을 하지 않는다.
public class TablebookingApplication {

	public static void main(String[] args) {
		SpringApplication.run(TablebookingApplication.class, args);
	}

}
