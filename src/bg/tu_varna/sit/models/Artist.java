package bg.tu_varna.sit.models;

public class Artist {
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
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (firstName != null) {
            sb.append(firstName);
            if (lastName != null) {
                sb.append(" ").append(lastName);
            }
        }

        if (sb.isEmpty()) {
            sb.append(username);
        }
        return sb.toString();
    }
}
