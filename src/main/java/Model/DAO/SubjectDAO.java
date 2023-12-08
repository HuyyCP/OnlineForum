package Model.DAO;

import DTO.SubjectStatDTO;
import Helper.DBHelper;
import Model.Bean.SubSubject;
import Model.Bean.Subject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SubjectDAO {

    private Subject getSubject(ResultSet rs) {
        try {
            Subject subject = new Subject();
            subject.setIdSubject(rs.getString("idsubject"));
            subject.setSubjectName(rs.getString("subjectname"));
            subject.setEnable(rs.getBoolean("status"));
            return subject;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Subject> getAllSubjects() {
        try {
            String query = "SELECT * FROM subject";
            ResultSet rs = DBHelper.query(query);
            ArrayList<Subject> subjects = new ArrayList<>();
            while(rs.next()) {
                subjects.add(getSubject(rs));
            }
            return subjects;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Subject getSubjectByID(String idSubject) {
        try {
            String query = "SELECT * FROM subject WHERE idsubject = ?";
            ResultSet rs = DBHelper.query(query, idSubject);
            if(rs.next()) {
                return getSubject(rs);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getSubjectName(String idSubject) {
        try {
            String query = "SELECT * FROM subject WHERE idsubject = ?";
            ResultSet rs = DBHelper.query(query, idSubject);
            if(rs.next()) {
                return getSubject(rs).getSubjectName();
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<SubjectStatDTO> getSubjectsStat() {
        try {
            String query = "select subject.idsubject, subject.subjectname, count(distinct post.idpost) as numpost, count(distinct comment.idcomment) as numcomment\n" +
                            "from subject left join subsubject  on subject.idsubject    = subsubject.idparentsubject " +
                            "             left join post        on subsubject.idsubject = post.idsubject " +
                            "             left join comment     on post.idpost          = comment.idpost " +
                            "group by subject.idsubject";
            ArrayList<SubjectStatDTO> statDTOs = new ArrayList<>();
            ResultSet rs = DBHelper.query(query);
            while (rs.next()) {
                SubjectStatDTO statDTO = new SubjectStatDTO();
                statDTO.setIdSubject(rs.getString("idsubject"));
                statDTO.setSubjectName(rs.getString("subjectname"));
                statDTO.setNumPost(rs.getInt("numpost"));
                statDTO.setNumComment(rs.getInt("numcomment"));
                statDTOs.add(statDTO);
            }
            return statDTOs;
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
