package model;

public class Utilisateur {

    private int id;
    private String username;   // correspond à email en BD
    private String password;
    private String role;
    private Integer candidatId; // peut être null

    public Utilisateur(int id, String username, String password, String role, Integer candidatId) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.candidatId = candidatId;
    }

    public Utilisateur() {
    }

    // GETTERS

    public int getId() {
        return id;
    }

    public String getEmail() {
        return username;  // email = username
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public int getCandidatId() {
        return candidatId;
    }

    // SETTERS

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.username = email;  // email = username
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setCandidatId(Integer candidatId) {
        this.candidatId = candidatId;
    }
}