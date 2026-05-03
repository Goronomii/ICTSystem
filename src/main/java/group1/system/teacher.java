/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group1.system;

/**
 *
 * @author Delmark Lusterio
 */
public class teacher {
    private String teacherUsername;
    private String sectionName; // Update property name to sectionName
    private String subject;

    public teacher(String teacherUsername, String sectionName, String subject) {
        this.teacherUsername = teacherUsername;
        this.sectionName = sectionName;
        this.subject = subject;
    }

    public String getTeacherUsername() {
        return teacherUsername;
    }

    public String getSectionName() { // Update getter method name to getSectionName
        return sectionName;
    }

    public String getSubject() {
        return subject;
    }
}
