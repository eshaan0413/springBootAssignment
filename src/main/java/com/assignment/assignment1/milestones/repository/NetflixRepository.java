package com.assignment.assignment1.milestones.repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.assignment.assignment1.milestones.model.Netflix;

@Repository
public interface NetflixRepository extends CrudRepository<Netflix, String> {

    List<Netflix> findByType(String type);


}