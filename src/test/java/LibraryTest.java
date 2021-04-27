import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.biblioteka.library.exception.PublicationAlreadyExistsException;
import pl.biblioteka.library.exception.UserAlreadyExistsException;
import pl.biblioteka.library.model.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {

    private Library library;

    @BeforeEach
    void setUp() {
        library = new Library();
    }


    @Test
    void sortUsers() {
        LibraryUser ziutek1 = new LibraryUser("Jan","Polak","1736543");
        LibraryUser ziutek2 = new LibraryUser("Piotr","Nowak","1236543");
        LibraryUser ziutek3 = new LibraryUser("Andrzej","Kowalski","2336543");
        library.addUser(ziutek1);
        library.addUser(ziutek2);
        library.addUser(ziutek3);

        Collection<LibraryUser> sortedUsers = library.getSortedUsers(Comparator.comparing(User::getLastName, String.CASE_INSENSITIVE_ORDER));

        assertEquals(sortedUsers, Arrays.asList(ziutek3,ziutek2,ziutek1));
    }

    @Test
    void addUsers() {
        LibraryUser ziutek1 = new LibraryUser("Jan","Kowalski","1236543");
        library.addUser(ziutek1);

        assertEquals(library.getUsers().get("1236543"),ziutek1);
    }

    @Test
    void addUsersExceptionWhenUserDuplicated() {
        LibraryUser ziutek1 = new LibraryUser("Jan","Kowalski","1236543");
        LibraryUser ziutek2 = new LibraryUser("Piotr","Nowak","1236543");
        library.addUser(ziutek1);

        Assertions.assertThrows(UserAlreadyExistsException.class, () -> {
            library.addUser(ziutek2);
        });
    }

    @Test
    void addPublications() {
        Publication book1 = new Book("W pustyni i w puszczy","Sienkiewicz",1950,423,"Helion","1245667345");

        library.addPublication(book1);

        assertEquals(library.getPublications().get("W pustyni i w puszczy"),book1);
    }

    @Test
    void addPublicationsExceptionWhenUserDuplicated() {
        Publication book1 = new Book("W pustyni i w puszczy","Sienkiewicz",1950,423,"Helion","1245667345");
        Publication book2 = new Book("W pustyni i w puszczy","Mickiewicz",1924,4673,"Helion","1245634345");
        library.addPublication(book1);

        Assertions.assertThrows(PublicationAlreadyExistsException.class, () -> {
            library.addPublication(book2);
        });
    }

    @Test
    void removePublications() {
        Publication book1 = new Book("W pustyni i w puszczy","Sienkiewicz",1950,423,"Helion","1245667345");

        library.addPublication(book1);

        assertEquals(library.removePublication(book1),true);
    }

    @Test
    void removeMissingPublications() {
        Publication book1 = new Book("W pustyni i w puszczy","Sienkiewicz",1950,423,"Helion","1245667345");

        assertEquals(library.removePublication(book1),false);
    }
}