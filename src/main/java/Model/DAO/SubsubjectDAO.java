package Model.DAO;

import Helper.DBHelper;
import Model.Bean.SubSubject;
import Model.Bean.Subject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SubsubjectDAO {

    private SubSubject getSubSubject(ResultSet rs) {
        try {
            SubSubject subject = new SubSubject();
            subject.setIdSubject(rs.getString("idsubject"));
            subject.setSubjectName(rs.getString("subjectname"));
            subject.setEnable(rs.getBoolean("status"));
            subject.setIdParentSubject(rs.getString("idparentsubject"));
            return subject;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<SubSubject> getAllSubjects() {
        try {
            String query = "SELECT * FROM subsubject";
            ResultSet rs = DBHelper.query(query);
            ArrayList<SubSubject> subjects = new ArrayList<>();
            while (rs.next()) {
                subjects.add(getSubSubject(rs));
            }
            return subjects;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<SubSubject> getAllSubSubject(String idParentSubject) {
        try {
            String query = "SELECT * FROM subsubject WHERE idparentsubject = ?";
            ResultSet rs = DBHelper.query(query, idParentSubject);
            ArrayList<SubSubject> subjects = new ArrayList<>();
            while (rs.next()) {
                subjects.add(getSubSubject(rs));
            }
            return subjects;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getSubSubjectName(String idSubSubject) {
        try {
            String query = "SELECT * FROM subsubject WHERE idsubject = ?";
            ResultSet rs = DBHelper.query(query, idSubSubject);
            if(rs.next()) {
                return getSubSubject(rs).getSubjectName();
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getSubjectID(String idSubSubject) {
        try {
            String query = "SELECT * FROM subsubject where idsubject = ?";
            ResultSet rs = DBHelper.query(query, idSubSubject);
            if(rs.next()) {
                return getSubSubject(rs).getIdParentSubject();
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addSubject(SubSubject subject) {
        String query = "INSERT INTO subsubject (idsubject, subjectname, idparentsubject, status) VALUES (?, ?, ?)";
        DBHelper.execute(query, subject.getIdSubject(), subject.getSubjectName(), subject.getIdParentSubject(), 1);
    }

    public void deleteSubject(String idSubject) {
        String query = "DELETE FROM subsubject WHERE idsubject = ?";
        DBHelper.execute(query, idSubject);
    }

    public void deleteSubSubject(String idParentSubject) {
        String query = "DELETE FROM subsubject WHERE idparentsubject = ?";
        DBHelper.execute(query, idParentSubject);
    }

    public boolean isEnable(String idSubject) {
        try {
            String query = "SELECT status FROM subsubject WHERE idsubject = ?";
            ResultSet rs = DBHelper.query(query, idSubject);
            if (rs.next()) {
                return rs.getBoolean("status");
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
