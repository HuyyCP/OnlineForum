package Model.DAO;

import DTO.SubjectStatDTO;
import Helper.DBHelper;
import Model.Bean.SubSubject;

import javax.security.auth.Subject;
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

    public SubSubject getSubSubject(String idSubSubject) {
        try {
            String query = "SELECT * FROM subsubject WHERE idsubject = ?";
            ResultSet rs = DBHelper.query(query, idSubSubject);
            if(rs.next()) {
                return getSubSubject(rs);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<SubjectStatDTO> getSubSubjectsStat(String idSubject) {
        try {
            String query = "select subsubject.idsubject, subsubject.subjectname, count(distinct post.idpost) as numpost, count(distinct comment.idcomment) as numcomment " +
                            "from subsubject left join post on subsubject.idsubject = post.idsubject " +
                                            "left join comment on post.idpost = comment.idpost " +
                            "where subsubject.idparentsubject = ? " +
                            "group by subsubject.idsubject";
            ArrayList<SubjectStatDTO> statDTOs = new ArrayList<>();
            ResultSet rs = DBHelper.query(query, idSubject);
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

  public void addSubject(SubSubject subject) {
    String query = "INSERT INTO subsubject (idsubject, subjectname, idparentsubject) VALUES (?, ?, ?)";
    DBHelper.execute(query, subject.getIdSubject(), subject.getSubjectName(), subject.getIdParentSubject());
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
