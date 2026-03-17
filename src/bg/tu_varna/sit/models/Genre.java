package bg.tu_varna.sit.models;

public enum Genre {
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

    private final String name;

    Genre(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public static Genre fromName(String name) {
        for (Genre genre : Genre.values()) {
            if (genre.name.equalsIgnoreCase(name)) {
                return genre;
            }
        }
        return Genre.OTHER; // not found
    }
}
