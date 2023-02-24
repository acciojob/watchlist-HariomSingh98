package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


public class MovieService {

    @Autowired//to create object of repo class
    MovieRepository repository;

    public void addMovie(Movie movie){
        repository.saveMovie(movie);
    }
    public void addDirector(Director director){
        repository.saveDirector(director);
    }
    public void addMovieDirectorPair(String movieName,String dirName){
        repository.saveMovieDirectorPair(movieName,dirName);
    }
    public Movie getMovieByName(String name){
        return repository.findMovie(name);
    }
    public Director getDirectorByName(String name){
        return repository.findDirector(name);
    }
    public List<String> getMoviesByDirectorName(String name){
        return repository.findMoviesFromDirector(name);
    }
    public List<String> findAllMovies(){
        return repository.findAllMovies();
    }
    public void deleteDirectorByName(String name){
        repository.deleteDirectorByName(name);
    }
    public void deleteAllDirectors(){
        repository.deleteAllDirectors();
    }
}
