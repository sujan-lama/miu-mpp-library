package library.librarysystem.business;

import java.io.Serializable;
import java.util.Objects;

final public class Author extends Person implements Serializable {
    private String bio;

    public String getBio() {
        return bio;
    }

    public Author(String f, String l, String t, Address a, String bio) {
        super(f, l, t, a);
        this.bio = bio;
    }

    private static final long serialVersionUID = 7508481940058530471L;

    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return Objects.equals(getFirstName(), author.getFirstName()) && Objects.equals(getLastName(), author.getLastName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(bio);
    }
}
