package Model.Bean;

import java.sql.Timestamp;
import java.util.Date;

public class Post {
    private String idPost;
    private String title;
    private Timestamp dateCreated;
    private String idUser;
    private String idSubSubject;
    private User user;
    private SubSubject subsubject;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private String content;

    public String getIdPost() {
        return idPost;
    }

    public void setIdPost(String idPost) {
        this.idPost = idPost;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getIdSubSubject() {
        return idSubSubject;
    }

    public void setIdSubSubject(String idSubSubject) {
        this.idSubSubject = idSubSubject;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public SubSubject getSubsubject() {
        return subsubject;
    }

    public void setSubsubject(SubSubject subsubject) {
        this.subsubject = subsubject;
    }
}
