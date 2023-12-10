package Model.Bean;

import java.sql.Timestamp;
import java.util.Date;

public class Comment {
    private String idComment;
    private String message;
    private Timestamp dateComment;
    private String idPost;
    private String idUser;
    private Post post;
    private User user;

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

    public Timestamp getDateComment() {
        return dateComment;
    }

    public void setDateComment(Timestamp dateComment) {
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

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
