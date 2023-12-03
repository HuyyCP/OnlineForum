package Model.BO;

import Model.Bean.Subject;
import Model.DAO.SubjectDAO;
import java.util.ArrayList;
import java.util.UUID;

public class SubjectBO {

    private SubjectDAO subjectDAO;

    public SubjectBO() {
        subjectDAO = new SubjectDAO();
    }

    public ArrayList<Subject> getAllSubjects() {
        return subjectDAO.getAllSubjects();
    }

    public void addSubject(Subject subject) {
        subject.setIdSubject(UUID.randomUUID().toString());
        subjectDAO.addSubject(subject);
    }

    public void deleteSubject(String idSubject) {
        subjectDAO.deleteSubject(idSubject);
    }

}
