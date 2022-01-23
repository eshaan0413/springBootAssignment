package com.assignment.assignment1.milestones.contoller;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.assignment.assignment1.milestones.model.Netflix;

import com.assignment.assignment1.milestones.repository.NetflixRepository;
import com.assignment.assignment1.milestones.services.MilestoneService;
import com.univocity.parsers.common.record.Record;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;

@RestController
public class MilestoneController {

    @Autowired
    private MilestoneService milestoneService;

    @RequestMapping(value = "/AllMovies", method = RequestMethod.GET)
    public List<Netflix> getAllMovies() {
        return milestoneService.getAllMovies();
    }

    @GetMapping("/tvshows/{count}")
    public List<Netflix> getTvShows(@PathVariable int count) {
        return milestoneService.getTvShows(count);
    }

    @GetMapping("/tvshows/{country}")
    public List<Netflix> getTvShows(@PathVariable String country) {
        return milestoneService.getTvShowsByCountry(country);
    }

    @Autowired
    NetflixRepository service;

//	@PostMapping("/upload")

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String uploadedData(@RequestParam("file") MultipartFile file) throws Exception {
        List<Netflix> movieList = new ArrayList<>();
        InputStream inputStream = file.getInputStream();
        CsvParserSettings setting = new CsvParserSettings();
        setting.setHeaderExtractionEnabled(true);
        CsvParser parser=new CsvParser(setting);
        List<Record> parseAllRecords=parser.parseAllRecords(inputStream);
        parseAllRecords.forEach(record -> {
            Netflix row=new Netflix();
            row.setShowId(record.getString("Show_id"));
            row.setType(record.getString("type"));
            row.setTitle(record.getString("title"));
            row.setDirector(record.getString("director"));
            row.setCast(record.getString("cast"));
            row.setCountry(record.getString("country"));
            row.setDateAdded(record.getString ("date_added"));
            row.setReleaseYear(Integer.parseInt(record.getString("release_year")));
            row.setRating(record.getString("rating"));
            row.setDuration(record.getString("duration"));
            row.setListedIn(record.getString("listed_in"));
            row.setDescription(record.getString("description"));
            movieList.add(row);
        });
        service.saveAll(movieList);
        return "Uploaded Sucessfully";
    }
}

