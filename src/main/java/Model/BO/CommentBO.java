package Model.BO;

import DTO.CommentDTO;
import Model.Bean.Comment;
import Model.DAO.CommentDAO;
import Model.DAO.UserDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class CommentBO {

    private CommentDAO commentDAO;
    private UserDAO userDAO;

    public CommentBO() {
        commentDAO = new CommentDAO();
        userDAO = new UserDAO();
    }

    public ArrayList<CommentDTO> getAllCommentsByPostID(String idPost) {
        ArrayList<Comment> comments = commentDAO.getAllCommentsByPostID(idPost);
        ArrayList<CommentDTO> commentDTOS = new ArrayList<>();
        for (Comment comment : comments) {
            CommentDTO commentDTO = new CommentDTO();
            commentDTO.setIdcomment(comment.getIdcomment());
            commentDTO.setMessage(comment.getMessage());
            commentDTO.setDateComment(comment.getDateComment());
            commentDTO.setIdPost(comment.getIdPost());
            commentDTO.setIdUser(comment.getIdUser());
            commentDTO.setUser(userDAO.getUserByID(comment.getIdUser()));
            commentDTOS.add(commentDTO);
        }
        return commentDTOS;
    }

    public ArrayList<CommentDTO> getAllCommentsByUserID(String idUser) {
        ArrayList<Comment> comments = commentDAO.getAllCommentsByUserID(idUser);
        ArrayList<CommentDTO> commentDTOS = new ArrayList<>();
        for (Comment comment : comments) {
            CommentDTO commentDTO = new CommentDTO();
            commentDTO.setIdcomment(comment.getIdcomment());
            commentDTO.setMessage(comment.getMessage());
            commentDTO.setDateComment(comment.getDateComment());
            commentDTO.setIdPost(comment.getIdPost());
            commentDTO.setIdUser(comment.getIdUser());
            commentDTO.setUser(userDAO.getUserByID(comment.getIdUser()));
            commentDTOS.add(commentDTO);
        }
        return commentDTOS;
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
