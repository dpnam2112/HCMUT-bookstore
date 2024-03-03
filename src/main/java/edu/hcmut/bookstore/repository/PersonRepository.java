package edu.hcmut.bookstore.repository;

import edu.hcmut.bookstore.dao.Person;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends ListCrudRepository<Person, Integer> {
    Person findById(int id);
    List<Person> findByAgeGreaterThan(int age);
}
