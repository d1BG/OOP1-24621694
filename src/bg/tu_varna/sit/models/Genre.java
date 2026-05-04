package bg.tu_varna.sit.models;

import java.io.Serializable;

/**
 * Клас съхраняващ енумерация за информацията на Жанра на песен. Задължитено поле {@code name}
 * Имлементира {@code Serializable} за да може да се запазвя чрез ObjectOutputStream/InputObjectStream.
 */
public enum Genre implements Serializable {
    NA("N/A"),
    POP("Pop"),
    ROCK("Rock"),
    METAL("Metal"),
    HIP_HOP("Hip-Hop"),
    RNB("RNB"),
    JAZZ("Jazz"),
    CLASSICAL("Classical"),
    ELECTRONIC("Electronic"),
    COUNTRY("Country"),
    FOLK("Folk"),
    REGGAE("Reggae"),
    PUNK("Punk"),
    FUNK("Funk"),
    DISCO("Disco"),
    TECHNO("Techno"),
    HOUSE("House"),
    DUBSTEP("Dubstep"),
    INDIE("Indie"),
    K_POP("K-Pop"),
    J_POP("J-Pop"),
    ALTERNATIVE("Alternative"),
    LO_FI("Lo-Fi"),
    OTHER("Other");

    /**
     * Име на песента, използва се за извеждане на жанра в лесно четим от потребителя начин,
     * и се използва при търсене на {@code enum} стойност.
     */
    private final String name;

    /**
     * @param name String който се изнася и по който се търси.
     */
    Genre(String name) {
        this.name = name;
    }

    /**
     * @return String name
     */
    @Override
    public String toString() {
        return name;
    }

    /**
     * Метод за получаване на enum стойност от {@code String}
     * @param name String по който се търси за отнасяща се {@code enum} стойност
     * @return {@code enum} стойност отговаряща на своя name {@code String}
     */
    public static Genre fromName(String name) {
        for (Genre genre : Genre.values()) {
            if (genre.name.equalsIgnoreCase(name)) {
                return genre;
            }
        }
        return Genre.OTHER; // not found
    }
}
