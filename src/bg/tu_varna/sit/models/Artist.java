package bg.tu_varna.sit.models;

import java.io.Serializable;
import java.util.Objects;

public class Artist implements Serializable {
    // must-have
    private String username;
    // optional
    private String firstName;
    private String lastName;

    public Artist(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Artist artist = (Artist) o;
        return Objects.equals(username, artist.username);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(username);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(username);
        if (firstName != null) {
            sb.append(" - ").append(firstName);
            if (lastName != null) {
                sb.append(" ").append(lastName);
            }
        }
        return sb.toString();
    }
}
