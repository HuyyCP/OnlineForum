package DTO;

import Model.Bean.Comment;
import Model.Bean.Post;

import java.util.ArrayList;

public class PostDetailDTO extends Post {
    String idSubject;
    String subjectName;
    String subsubjectName;
    ArrayList<CommentDTO> commentDTOs;
    ArrayList<CommentDTO> userCommentDTOs;

    public ArrayList<CommentDTO> getUserCommentDTOs() {
        return userCommentDTOs;
    }

    public void setUserCommentDTOs(ArrayList<CommentDTO> userCommentDTOs) {
        this.userCommentDTOs = userCommentDTOs;
    }

    public String getIdSubject() {
        return idSubject;
    }

    public void setIdSubject(String idSubject) {
        this.idSubject = idSubject;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubsubjectName() {
        return subsubjectName;
    }

    public void setSubsubjectName(String subsubjectName) {
        this.subsubjectName = subsubjectName;
    }

    public ArrayList<CommentDTO> getCommentDTOs() {
        return commentDTOs;
    }

    public void setCommentDTOs(ArrayList<CommentDTO> commentDTOs) {
        this.commentDTOs = commentDTOs;
    }
}
