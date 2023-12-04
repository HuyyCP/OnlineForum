package Model.BO;

import Model.Bean.Subject;
import Model.DAO.SubjectDAO;
import Model.DAO.SubsubjectDAO;
import java.util.ArrayList;
import java.util.UUID;

public class SubjectBO {

    private SubjectDAO subjectDAO;
    private SubsubjectDAO subsubjectDAO;

    public SubjectBO() {
        subjectDAO = new SubjectDAO();
        subsubjectDAO = new SubsubjectDAO();
    }

    public ArrayList<Subject> getAllSubjects() {
        return subjectDAO.getAllSubjects();
    }

    public void addSubject(Subject subject) {
        subject.setIdSubject(UUID.randomUUID().toString());
        subjectDAO.addSubject(subject);
    }

    public void deleteSubject(String idSubject) {
        subsubjectDAO.deleteSubSubject(idSubject);
        subjectDAO.deleteSubject(idSubject);
    }

}
