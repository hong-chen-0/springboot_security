package com.cc.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

@Component
public interface UserMapper extends JpaRepository<User,Integer> {
	List<User> findByUsername(String username);
	
	@Query(value="select role from user u where u.username = :username",nativeQuery = true)
	List<String> getRole(String username);
}
