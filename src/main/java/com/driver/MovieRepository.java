package com.driver;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MovieRepository {
    private Map<String,Movie> movieMap;
    private Map<String,Director> directorMap;
    private Map<String, List<String>> directorMovieMap;

    public MovieRepository() {
        movieMap = new HashMap<>();
        directorMap= new HashMap<>();
        directorMovieMap = new HashMap<>();
    }

    public void saveMovie(Movie movie){
        movieMap.put(movie.getName(),movie);
    }
    public void saveDirector(Director director){
        directorMap.put(director.getName(),director);
    }
    public void saveMovieDirectorPair(String mName,String dName){
        if(movieMap.containsKey(mName) && directorMap.containsKey(dName)){
            List<String> currMovies = new ArrayList<>();
            if(directorMap.containsKey(dName))currMovies= directorMovieMap.get(dName);
            currMovies.add(mName);
            directorMovieMap.put(dName,currMovies);
        }
    }
    public Movie findMovie(String mName){
        return movieMap.get(mName);
    }

    public Director findDirector(String dName){
        return directorMap.get(dName);
    }
    public List<String> findMoviesFromDirector(String dName){
        List<String> list = new ArrayList<>();
        if(directorMovieMap.containsKey(dName))list = directorMovieMap.get(dName);
        return list;
    }
    public List<String> findAllMovies(){
        return new ArrayList<>(movieMap.keySet());
    }

    public void deleteDirectorByName(String dName){
        if(directorMap.containsKey(dName)) {
            if (directorMovieMap.containsKey(dName)) {
                List<String> list = directorMovieMap.get(dName);
                for (String s : list) {
                    movieMap.remove(s);
                }
                directorMovieMap.remove(dName);
            }
            directorMap.remove(dName);
        }
    }
    public void deleteAllDirectors(){
        HashSet<String> moviesSet = new HashSet<String>();

        //Finding out all the movies by all the directors combined
        for(String dName: directorMovieMap.keySet()){

            //Iterating in the list of movies by a director.
            for(String movie: directorMovieMap.get(dName)){
                moviesSet.add(movie);
            }
        }

        //Deleting the movie from the movieDb.
        for(String movie: moviesSet){
            if(movieMap.containsKey(movie)){
                movieMap.remove(movie);
            }
        }
        directorMap.clear();
        directorMovieMap.clear();
    }

}
