package com.prisoner.test.repositories;

import org.springframework.data.repository.CrudRepository;

import com.prisoner.test.entities.Person;

public interface PersonRepository extends CrudRepository<Person, String>{
}
