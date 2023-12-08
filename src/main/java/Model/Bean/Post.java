package Model.Bean;

import java.util.Date;

public class Post {
    private String idPost;
    private String title;
    private Date dateCreated;
    private String idUser;
    private String idSubSubject;

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

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
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

}
