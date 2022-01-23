package com.assignment.assignment1.milestones.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.assignment.assignment1.milestones.model.Netflix;
import com.assignment.assignment1.milestones.repository.NetflixRepository;

@Service
@Transactional
public class MilestoneService {

    @Autowired
    private NetflixRepository netflixRepository;

    public List<Netflix> getAllMovies() {
        List<Netflix> allMovies= (List<Netflix>) netflixRepository.findAll();
        return allMovies;
    }


    public List<Netflix> getTvShows(int count) {
        List<Netflix> tvShows= (List<Netflix>) netflixRepository.findByType("TV Show");
        return tvShows;
    }

    public List<Netflix> getTvShowsByCountry(String country) {
        List<Netflix> tvShows= (List<Netflix>) netflixRepository.findByType("TV Show");
        return tvShows;
    }
}
