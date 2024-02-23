package com.cmpt276.studentdatabase.models;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Integer> {
    Student findById(int id);
    Student deleteById(int id);
    List<Student> findAllByOrderByIdAsc();
}