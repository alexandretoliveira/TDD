import model.Movie;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;


public class MovieStore {
    List<Movie> movies = new LinkedList<Movie>();

    public List<Movie> findByPartialTitle(final String partialTitle) {
        return findBy(movie -> (movie.title().toUpperCase().contains(partialTitle.toUpperCase())));
    }

    public void add(Movie movie) {
        movies.add(movie);
    }

    public List<Movie> findByDirector(final String director) {
            return findBy(movie -> (movie.director().equals(director)));
    }

    public List<Movie> findByReleaseYear(final int from, final int to) {
        return findBy(movie -> (movie.releaseYear() > from && movie.releaseYear() < to));
    }

    private List<Movie> findBy(Predicate<Movie> predicate){
        List<Movie> results = new LinkedList<Movie>();
        for (Movie movie : movies) {
            if(predicate.test(movie)){
                results.add(movie);
            }
        }
        return results;
    }

//    interface Predicate{
//        boolean matches(Movie movie);
//    }
}
