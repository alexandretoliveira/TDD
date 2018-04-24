import model.Movie;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.core.Is.is;

public class MovieStoreTest {

    private final Movie harryPotter = new Movie("Harry Potter", "Rowling", 2000);
    private final Movie starWars = new Movie("Stars Wars", "John", 1999);
    private final Movie starTrek = new Movie("STARS Trek", "Jack", 2002);
    private final Movie shawshank = new Movie("Shawshank Redemption", "Mathew", 2001);
    private final Movie takeThat = new Movie("Take That", "Gary", 2010);
    private final MovieStore movieStore = new MovieStore();


    @Test
    public void returnsNoResultsWhenNoTitlesPartiallyMatchSearch() throws Exception{
        MovieStore movieStore = new MovieStore();

        List<Movie> results = movieStore.findByPartialTitle("abc");

        assertThat(results.size(), is(0));
    }

    @Before
    public void setUp() throws Exception {
        movieStore.add(shawshank);
        movieStore.add(harryPotter);
        movieStore.add(starWars);
        movieStore.add(starTrek);
        movieStore.add(takeThat);
    }

    @Test
    public void findMoviesWhenTitlesArePartiallyMatchedCaseInsensitive() throws Exception{
        List<Movie> results = movieStore.findByPartialTitle("tar");

        assertThat(results.size(), is(2));
        assertThat(results, containsInAnyOrder(starWars, starTrek));
    }

    @Test
    public void findMoviesWhenDirectorExactlyMatches() throws Exception{
        List<Movie> results = movieStore.findByDirector("John");

        assertThat(results.size(), is(1));
        assertThat(results, containsInAnyOrder(starWars));
    }

    @Test
    public void findMoviesWhenReleaseYearIsBetweenTwoValues() throws Exception{
        List<Movie> results = movieStore.findByReleaseYear(1999,2002);

        assertThat(results.size(), is(2));
        assertThat(results, containsInAnyOrder(harryPotter, shawshank));
    }
}
