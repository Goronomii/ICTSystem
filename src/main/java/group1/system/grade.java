/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group1.system;

/**
 *
 * @author Delmark Lusterio
 */
public class grade {
        private int grade_id;
    private int user_id;
    private String column_title;
    private int grade;
    private String subject;
    private String semester;
    private boolean checked;

public grade(int grade_id, int user_id, String column_title, int grade, String subject, String semester, boolean checked) {
    this.grade_id = grade_id;
    this.user_id = user_id;
    this.column_title = column_title;
    this.grade = grade; 
    this.subject = subject;
    this.semester = semester;
    this.checked = checked;
}

    // Getters and Setters
    public int getGrade_id() {
        return grade_id;
    }

    public void setGrade_id(int grade_id) {
        this.grade_id = grade_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getColumn_title() {
        return column_title;
    }

    public void setColumn_title(String column_title) {
        this.column_title = column_title;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
