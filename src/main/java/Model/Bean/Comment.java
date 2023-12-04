package Model.Bean;

import java.util.Date;

public class Comment {
    private String idComment;
    private String message;
    private Date dateComment;
    private String idPost;
    private String idUser;

    public String getIdcomment() {
        return idComment;
    }

    public void setIdcomment(String idcomment) {
        this.idComment = idcomment;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDateComment() {
        return dateComment;
    }

    public void setDateComment(Date dateComment) {
        this.dateComment = dateComment;
    }

    public String getIdPost() {
        return idPost;
    }

    public void setIdPost(String idPost) {
        this.idPost = idPost;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }
}
