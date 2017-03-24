package com.pilkyoon.controller;

import com.pilkyoon.domain.User;
import com.pilkyoon.repository.UserRepository;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest
public class UserControllerTest {

	@Autowired
	UserController userController;

	@Autowired
	UserRepository userRepository;

	private MockMvc mockMvc;

	@Before
	@Transactional
	public void before() {
		mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
		User user = new User();
		user.setId(1L);
		user.setActive(false);
		user.setName("Mulan");
		userRepository.save(user);
	}

	@Test
	public void testOptimisticFailureException() {
		List<Long> delayList = Lists.newArrayList(200L, 100L);

		delayList.parallelStream().forEach(p -> {
			try {
				mockMvc.perform(get("/users/1/active/" + p))
				        .andExpect(status().isOk())
						.andDo(print())
				        .andReturn()
						;
			} catch (Exception e) {
				Assert.fail();
			}
		});
	}

	@Test
	public void testRefreshEntity() {
		List<Long> delayList = Lists.newArrayList(200L, 100L);

		delayList.parallelStream().forEach(p -> {
			try {
				mockMvc.perform(get("/users/1/activeRefresh/" + p))
						.andExpect(status().isOk())
						.andDo(print())
						.andReturn()
				;
			} catch (Exception e) {
				Assert.fail();
			}
		});
	}


	@Test
	public void testActiveByEntityManager() {
		List<Long> delayList = Lists.newArrayList(200L, 100L);

		delayList.parallelStream().forEach(p -> {
			try {
				mockMvc.perform(get("/users/1/activeByEntityManager/" + p))
						.andExpect(status().isOk())
						.andDo(print())
						.andReturn()
				;
			} catch (Exception e) {
				Assert.fail();
			}
		});
	}
}
