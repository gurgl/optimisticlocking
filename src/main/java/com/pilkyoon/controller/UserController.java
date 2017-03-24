package com.pilkyoon.controller;

import com.pilkyoon.domain.User;
import com.pilkyoon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserService userService;

	@RequestMapping("{id}/active/{delay}")
	public User active(@PathVariable("id") Long id, @PathVariable("delay") Long delay) throws InterruptedException {
		return userService.active(id, delay);
	}

	@RequestMapping("{id}/activeByEntityManager/{delay}")
	public User activeByEntityManager(@PathVariable("id") Long id, @PathVariable("delay") Long delay) throws InterruptedException {
		return userService.activeByEntityManager(id, delay);
	}

	@RequestMapping("{id}/activeByRefreshEntity/{delay}")
	public User activeRefresh(@PathVariable("id") Long id, @PathVariable("delay") Long delay) throws InterruptedException {
		return userService.activeByRefreshEntity(id, delay);
	}

	@RequestMapping(value = "{id}", method = { RequestMethod.POST })
	public User addUser(@PathVariable("id") Long id) {
		User user = new User();
		user.setId(id);
		user.setName(id + "'s superman");
		user.setActive(true);
		user.setUpdateDate(new Date());
		return userService.save(user);
	}

	@RequestMapping(value = "", method = { RequestMethod.GET })
	public List<User> getUser() {
		return userService.getAll();
	}
}
