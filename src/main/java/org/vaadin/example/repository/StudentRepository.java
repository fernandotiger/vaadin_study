package org.vaadin.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vaadin.example.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>{

}
