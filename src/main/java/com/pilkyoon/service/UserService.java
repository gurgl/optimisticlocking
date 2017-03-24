package com.pilkyoon.service;

import com.pilkyoon.domain.User;
import com.pilkyoon.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	@PersistenceContext
	EntityManager entityManager;

	@Transactional
	public User active(Long id, Long delay) throws InterruptedException {
		User user = userRepository.findById(id);
		user.setActive(false);
		user.setUpdateDate(new Date());

		LockModeType lock = entityManager.getLockMode(user);
		System.out.println("### LOCK MODE TYPE ### " + lock);
		Thread.sleep(delay);
		return userRepository.save(user);
	}

	@Transactional
	public User activeRefresh(Long id, Long delay) throws InterruptedException {
		User user = userRepository.findById(id);
		user.setActive(false);
		user.setUpdateDate(new Date());

		LockModeType lock = entityManager.getLockMode(user);
		System.out.println("### LOCK MODE TYPE ### " + lock);
		entityManager.refresh(user);
		Thread.sleep(delay);
		return userRepository.save(user);
	}

	@Transactional
	public User activeByEntityManager(Long id, Long delay) throws InterruptedException {
		User user = entityManager.find(User.class, id, LockModeType.NONE);
		user.setActive(false);
		user.setUpdateDate(new Date());

		LockModeType lock = entityManager.getLockMode(user);
		System.out.println("### LOCK MODE TYPE ### " + lock);

		Thread.sleep(delay);
		entityManager.merge(user);
		return user;
	}

	public User save(User user) {
		return userRepository.save(user);
	}

	public List<User> getAll() {
		return userRepository.findAll();
	}


}
