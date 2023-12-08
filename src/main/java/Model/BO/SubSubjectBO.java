package Model.BO;

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

    public SubSubjectBO() {
        subsubjectDAO = new SubsubjectDAO();
    }

    public ArrayList<SubSubject> getAllSubjects() {
        return subsubjectDAO.getAllSubjects();
    }

    public SubSubject getSubject(String idSubSubject) {
        return subsubjectDAO.getSubSubject(idSubSubject);
    }

    public ArrayList<SubjectStatDTO> getSubSubjectsStat(String idSubject) {
        return subsubjectDAO.getSubSubjectsStat(idSubject);
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
