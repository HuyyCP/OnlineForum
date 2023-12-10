package Model.BO;

import DTO.BestSubjectDTO;
import DTO.SubjectStatDTO;
import Helper.DBHelper;
import Model.Bean.Subject;
import Model.DAO.PostDAO;
import Model.DAO.SubsubjectDAO;
import Model.Bean.SubSubject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public class SubSubjectBO {
    private SubsubjectDAO subsubjectDAO;
    private PostDAO postDAO;
    private SubjectBO subjectBO;

    public SubSubjectBO() {
        subsubjectDAO = new SubsubjectDAO();
        postDAO = new PostDAO();
        subjectBO = new SubjectBO();
    }

    public ArrayList<SubSubject> getAllSubjects() {
        return subsubjectDAO.getAllSubjects();
    }

    public SubSubject getSubject(String idSubSubject) {
        SubSubject subSubject = subsubjectDAO.getSubSubject(idSubSubject);
        subSubject.setSubject(subjectBO.getSubjectByID(subSubject.getIdParentSubject()));
        return subSubject;
    }

    public int getNumSubsubject() {
        return subsubjectDAO.getNumSubject();
    }

    public ArrayList<SubjectStatDTO> getSubSubjectsStat(String idSubject) {
        return subsubjectDAO.getSubSubjectsStat(idSubject);
    }

    public BestSubjectDTO getBestSubject() {
        return subsubjectDAO.getBestSubject();
    }

    public void addSubject(SubSubject subSubject) {
        subSubject.setIdSubject(UUID.randomUUID().toString());
        subsubjectDAO.addSubject(subSubject);
    }

    public void deleteSubject(String idSubject) {
        postDAO.deletePostBySubjectID(idSubject);
        subsubjectDAO.deleteSubject(idSubject);
    }
}
