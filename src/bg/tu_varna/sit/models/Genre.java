package bg.tu_varna.sit.models;

public enum Genre {
    NA("n/a", "N/A"),
    POP("pop", "Pop"),
    ROCK("rock", "Rock"),
    METAL("metal", "Metal"),
    HIP_HOP("hip-hop", "Hip-Hop"),
    RNB("rnb", "RNB"),
    JAZZ("jazz", "Jazz"),
    CLASSICAL("classical",  "Classical"),
    ELECTRONIC("electronic",  "Electronic"),
    COUNTRY("country", "Country"),
    FOLK("folk", "Folk"),
    REGGAE("reggae", "Reggae"),
    PUNK("punk",  "Punk"),
    FUNK("funk", "Funk"),
    DISCO("disco", "Disco"),
    TECHNO("techno", "Techno"),
    HOUSE("house", "House"),
    DUBSTEP("dubstep", "Dubstep"),
    INDIE("indie", "Indie"),
    K_POP("k-pop", "K-Pop"),
    J_POP("j-pop", "J-Pop"),
    ALTERNATIVE("alternative", "Alternative"),
    LO_FI("lo-fi", "Lo-Fi"),
    OTHER("other", "Other");

    private final String name;
    private final String fancyName;

    Genre(String name, String fancyName) {
        this.name = name;
        this.fancyName = fancyName;
    }

    String getFancyName() {
        return fancyName;
    }

    public static Genre fromName(String name) {
        for (Genre genre : Genre.values()) {
            if (genre.name.equals(name.toLowerCase())) {
                return genre;
            }
        }
        return Genre.OTHER; // not found
    }
}
