package Model.BO;

import Model.Bean.Comment;
import Model.DAO.CommentDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class CommentBO {

    private CommentDAO commentDAO;

    public CommentBO() {
        commentDAO = new CommentDAO();
    }

    public ArrayList<Comment> getAllCommentsByPostID(String idPost) {
        return commentDAO.getAllCommentsByPostID(idPost);
    }

    public void addComment(Comment comment) {
        comment.setIdcomment(UUID.randomUUID().toString());
        comment.setDateComment(new Date());
        commentDAO.addComment(comment);
    }

    public void updateComment(Comment comment) {
        commentDAO.updateComment(comment);
    }

    public void deleteComment(String idComment) {
        commentDAO.deleteComment(idComment);
    }

}
