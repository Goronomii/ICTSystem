package group1.system;

import java.util.HashMap;
import java.util.Map;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Student {
    
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty name;
    private final Map<String, Integer> grades;

    public Student(int id, String name) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.grades = new HashMap<>();
    }

    public int getId() {
        return id.get();
    }

    public String getName() {
        return name.get();
    }

    public void setGradeForColumn(String columnName, int grade) {
        grades.put(columnName, grade);
    }

    public int getGradeForColumn(String columnName) {
        return grades.getOrDefault(columnName, 0);
    }
       public void setGradeForColumn2ndSem(String columnName, int grade) {
        grades.put(columnName, grade);
    }
       public int getGradeForColumn2ndSem(String columnName) {
        return grades.getOrDefault(columnName, 0);
    }


}
