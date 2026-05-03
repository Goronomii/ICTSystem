/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group1.system;

import java.sql.Date;

/**
 *
 * @author Delmark Lusterio
 */
public class submissiondata {
    private String studentName;
    private String section;
    private String subject;
    private String title;
    private String fileName;
    private String submissionDate;
    private String submissionTime;
    private byte[] fileData;

    public submissiondata(String studentName, String section, String subject, String title, String fileName, String submissionDate, String submissionTime, byte[] fileData) {
        this.studentName = studentName;
        this.section = section;
        this.subject = subject;
        this.title = title;
        this.fileName = fileName;
        this.submissionDate = submissionDate;
        this.submissionTime = submissionTime;
        this.fileData = fileData;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(String submissionDate) {
        this.submissionDate = submissionDate;
    }

    public String getSubmissionTime() {
        return submissionTime;
    }

    public void setSubmissionTime(String submissionTime) {
        this.submissionTime = submissionTime;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }

}
