package bg.tu_varna.sit.models;

import java.io.Serializable;
import java.util.Objects;

/**
 * Клас за обект артист, с задължителни полета за псевдоним и незадължителни за първо и послено име.
 * Имлементира {@code Serializable} за да може да се запазвя чрез ObjectOutputStream/InputObjectStream.
 */
public class Artist implements Serializable {
    /**
     * Псевдоним на артиста (задължително)
     * използва се при търсене за артист.
     */
    private String username;

    /**
     * Първо име на артист.
     */
    private String firstName;

    /**
     * Последно име на артист.
     */
    private String lastName;

    /**
     * Конструктор за обект съхраняващ информация за артист.
     * @param username уникален псевдоним на артист (задължително)
     */
    public Artist(String username) {
        this.username = username;
    }

    /**
     * @return псевдонима на артиста
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return Първото име на артиста (може да е {@code null})
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @return Последното име на артиста (може да е {@code null})
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param firstName Първо име на артист
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @param lastName Последно име на артист
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Метод за равенство по псевдоним
     * @param obj обекта с който се прави сравнение.
     * @return {@code boolean} определящ равенство между този обект и {@code obj}
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) return false;
        Artist artist = (Artist) obj;
        return Objects.equals(username, artist.username);
    }

    /**
     * @return Хеш на обекта по псевдоним
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(username);
    }

    /**
     * @return Лесно четима информация за артиста
     */
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
