package io.reflectoring.CRUDL_application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Arrays;

@SpringBootApplication
public class CrudlApplication implements CommandLineRunner {

	@Autowired
	TestRepository testRepo;

	public static void main(String[] args) {
		SpringApplication.run(CrudlApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		UserStat user1 =  new UserStat("chandra", "chandra3@iastate.edu", 65);
		UserStat user2 =  new UserStat("anirudha", "asc@iastate.edu", 62);

		testRepo.userStat.addAll(Arrays.asList(user1,user2));
	}
}
