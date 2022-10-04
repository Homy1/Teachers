package com.homy.rest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.homy.rest.controller.TeacherController;


@ExtendWith(SpringExtension.class)
@SpringBootTest

public class SpringBootDemoApplicationTests {

	@Autowired
	 TeacherController teacherController;
	@Test
	public void contextLoads() {
		Assertions.assertThat(teacherController);

	}

}
