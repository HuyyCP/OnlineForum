package Model.Bean;

public class SubSubject extends Subject {
  private String idParentSubject;
  private Subject subject;

  public String getIdParentSubject() {
    return idParentSubject;
  }

  public void setIdParentSubject(String idParentSubject) {
    this.idParentSubject = idParentSubject;
  }

  public Subject getSubject() {
    return subject;
  }

  public void setSubject(Subject subject) {
    this.subject = subject;
  }
}
