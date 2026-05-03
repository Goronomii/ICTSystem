/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group1.system;

/**
 *
 * @author Delmark Lusterio
 */
public class User {
      private int UID;
    private String Username;
    private String LRN;
    private String Email;
    private int Age;
    private String Section;

    // Constructor
    public User(int UID, String Username, String LRN, String Email, int Age, String Section) {
        this.UID = UID;
        this.Username = Username;
        this.LRN = LRN;
        this.Email = Email;
        this.Age = Age;
        this.Section = Section;
    }

    // Getters
    public int getUID() {
        return UID;
    }

    public String getUsername() {
        return Username;
    }

    public String getLRN() {
        return LRN;
    }

    public String getEmail() {
        return Email;
    }

    public int getAge() {
        return Age;
    }

    public String getSection() {
        return Section;
    }
}
