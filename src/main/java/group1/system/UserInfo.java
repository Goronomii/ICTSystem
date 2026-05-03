/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group1.system;

/**
 *
 * @author Delmark Lusterio
 */
public class UserInfo {
    private int userId;
    private String section;

    public UserInfo(int userId, String section) {
        this.userId = userId;
        this.section = section;
    }

    public int getUserId() {
        return userId;
    }

    public String getSection() {
        return section;
    }
}
