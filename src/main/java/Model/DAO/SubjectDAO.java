package Model.DAO;

import Helper.DBHelper;
import Model.Bean.Subject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SubjectDAO {

    Connection conn;

    public SubjectDAO() {
        conn = DBHelper.getConnection();
    }

    public ArrayList<Subject> getAllSubjects() {
        try {
            String query = "SELECT * " +
                            "FROM subject";
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            ArrayList<Subject> subjects = new ArrayList<>();
            while(rs.next()) {
                Subject subject = new Subject();
                subject.setIdSubject(rs.getString("idsubject"));
                subject.setSubjectName(rs.getString("subjectname"));
                subjects.add(subject);
            }
            statement.close();
            return subjects;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addSubject(Subject subject) {
        try {
            String query = "INSERT INTO subject (idsubject, subjectname) " +
                            "VALUES (?, ?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, subject.getIdSubject());
            statement.setString(2, subject.getSubjectName());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteSubject(String idSubject) {
        try {
            String query = "DELETE FROM subject " +
                            "WHERE idsubject = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, idSubject);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
