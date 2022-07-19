package kr.io.playdata.dao;

import org.springframework.data.repository.CrudRepository;

import kr.io.playdata.domain.User;

public interface UserRepository extends CrudRepository<User, String>{

}
