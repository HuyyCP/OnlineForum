package Model.DAO;

import Helper.DBHelper;
import Model.Bean.Subject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SubjectDAO {

    public ArrayList<Subject> getAllSubjects() {
        try {
            String query = "SELECT * FROM subject";
            ResultSet rs = DBHelper.query(query);
            ArrayList<Subject> subjects = new ArrayList<>();
            while(rs.next()) {
                Subject subject = new Subject();
                subject.setIdSubject(rs.getString("idsubject"));
                subject.setSubjectName(rs.getString("subjectname"));
                subjects.add(subject);
            }
            return subjects;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addSubject(Subject subject) {
        String query = "INSERT INTO subject (idsubject, subjectname) VALUES (?, ?)";
        DBHelper.execute(query, subject.getIdSubject(), subject.getSubjectName());
    }

    public void deleteSubject(String idSubject) {
        String query = "DELETE FROM subject WHERE idsubject = ?";
        DBHelper.execute(query, idSubject);
    }

}
