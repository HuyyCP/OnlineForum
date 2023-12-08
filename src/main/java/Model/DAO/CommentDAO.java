package Model.DAO;

import Helper.DBHelper;
import Model.Bean.Comment;
import java.sql.*;
import java.util.ArrayList;

public class CommentDAO {

    private Comment getComment(ResultSet rs) {
        try {
            Comment comment = new Comment();
            comment.setIdcomment(rs.getString("idcomment"));
            comment.setMessage(rs.getString("message"));
            comment.setDateComment(rs.getDate("datecomment"));
            comment.setIdPost(rs.getString("idpost"));
            comment.setIdUser(rs.getString("iduser"));
            return comment;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Comment> getAllCommentsByPostID(String idPost) {
        try {
            String query = "SELECT * FROM comment WHERE idpost = ? ORDER BY datecomment";
            ResultSet rs = DBHelper.query(query, idPost);
            ArrayList<Comment> comments = new ArrayList<>();
            while(rs.next()) {
                comments.add(getComment(rs));
            }
            return comments;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getAmountCommentsByPostID(String idPost) {
        try {
            String query = "SELECT COUNT(*) AS numComments FROM comment WHERE idpost = ?";
            ResultSet rs = DBHelper.query(query, idPost);
            rs.next();
            return rs.getInt("numComments");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Comment> getAllCommentsByUserID(String idUser) {
        try {
            String query = "SELECT * FROM comment WHERE iduser = ? ORDER BY datecomment";
            ResultSet rs = DBHelper.query(query, idUser);
            ArrayList<Comment> comments = new ArrayList<>();
            while(rs.next()) {
                comments.add(getComment(rs));
            }
            return comments;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addComment(Comment comment) {
        String query = "INSERT INTO comment (idcomment, message, datecomment, idpost, iduser) VALUES (?, ?, ?, ?, ?)";
        DBHelper.execute(query, comment.getIdcomment(), comment.getMessage(), comment.getDateComment(), comment.getIdPost(), comment.getIdUser());
    }

    public void updateComment(Comment comment) {
        String query = "UPDATE comment SET message = ? WHERE idcomment = ?";
        DBHelper.execute(query, comment.getMessage(), comment.getIdcomment());
    }

    public void deleteComment(String idComment) {
        String query = "DELETE FROM comment WHERE idcomment = ?";
        DBHelper.execute(query, idComment);
    }

    public void deleteCommentByPostID(String idPost) {
        String query = "DELETE FROM comment WHERE idpost = ?";
        DBHelper.execute(query, idPost);
    }

}
