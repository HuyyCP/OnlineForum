package Model.DAO;

import Helper.DBHelper;
import Model.Bean.Comment;
import java.sql.*;
import java.util.ArrayList;

public class CommentDAO {

    Connection conn;

    public CommentDAO() {
        conn = DBHelper.getConnection();
    }

    public ArrayList<Comment> getAllCommentsByPostID(String idPost) {
        try {
            String query = "SELECT * " +
                            "FROM comment " +
                            "WHERE idpost = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, idPost);
            ResultSet rs = statement.executeQuery();
            ArrayList<Comment> comments = new ArrayList<>();
            while(rs.next()) {
                Comment comment = new Comment();
                comment.setIdcomment(rs.getString("idcomment"));
                comment.setMessage(rs.getString("message"));
                comment.setDateComment(rs.getDate("datecomment"));
                comment.setIdPost(rs.getString("idpost"));
                comment.setIdUser(rs.getString("iduser"));
                comments.add(comment);
            }
            statement.close();
            return comments;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getAmountCommentsByPostID(String idPost) {
        try {
            String query = "SELECT COUNT(*) AS numComments" +
                            "FROM comment " +
                            "WHERE idpost = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, idPost);
            ResultSet rs = statement.executeQuery();
            rs.next();
            statement.close();
            return rs.getInt("numComments");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Comment> getAllCommentsByUserID(String idUser) {
        try {
            String query = "SELECT * " +
                            "FROM comment " +
                            "WHERE iduser = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, idUser);
            ResultSet rs = statement.executeQuery();
            ArrayList<Comment> comments = new ArrayList<>();
            while(rs.next()) {
                Comment comment = new Comment();
                comment.setIdcomment(rs.getString("idcomment"));
                comment.setMessage(rs.getString("message"));
                comment.setDateComment(rs.getDate("datecomment"));
                comment.setIdPost(rs.getString("idpost"));
                comment.setIdUser(rs.getString("iduser"));
                comments.add(comment);
            }
            statement.close();
            return comments;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addComment(Comment comment) {
        try {
            String query = "INSERT INTO comment (idcomment, message, datecomment, idpost, iduser) " +
                            "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, comment.getIdcomment());
            statement.setString(2, comment.getMessage());
            statement.setDate(3, (Date)comment.getDateComment());
            statement.setString(4, comment.getIdPost());
            statement.setString(5, comment.getIdUser());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateComment(Comment comment) {
        try {
            String query = "UPDATE comment " +
                            "SET message = ? " +
                            "WHERE idcomment = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, comment.getMessage());
            statement.setString(2, comment.getIdcomment());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteComment(String idComment) {
        try {
            String query = "DELETE FROM comment " +
                            "WHERE idcomment = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, idComment);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
