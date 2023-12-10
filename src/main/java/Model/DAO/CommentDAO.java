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
            comment.setDateComment(rs.getTimestamp("datecomment"));
            comment.setIdPost(rs.getString("idpost"));
            comment.setIdUser(rs.getString("iduser"));
            return comment;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Comment> getAllCommentsByPostID(String idPost) {
        try {
            String query = "SELECT * FROM comment WHERE idpost = ? ORDER BY datecomment DESC";
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

    public ArrayList<Comment> getAllCommentsByUserID(String idPost, String idUser) {
        try {
            ArrayList<Comment> comments = new ArrayList<>();
            String query = "SELECT * FROM comment WHERE idpost = ? AND iduser = ? ORDER BY datecomment DESC";
            ResultSet rs = DBHelper.query(query, idPost, idUser);
            while(rs.next()) {
                comments.add(getComment(rs));
            }
            query = "SELECT * FROM comment WHERE idpost = ? AND iduser != ? ORDER BY datecomment DESC";
            rs = DBHelper.query(query, idPost, idUser);
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

    public ArrayList<Comment> getLastCommentForEachPost(String idSubject) {
        try {
            String query = "SELECT c.*\n" +
                    "FROM comment c\n" +
                    "INNER JOIN post p ON c.idpost = p.idpost\n" +
                    "INNER JOIN \n" +
                    "(\n" +
                    "  SELECT idpost, MAX(datecomment) AS max_date \n" +
                    "  FROM comment\n" +
                    "  GROUP BY idpost\n" +
                    ") last_comments ON c.idpost = last_comments.idpost AND c.datecomment = last_comments.max_date\n" +
                    "WHERE p.idsubject = ?";
            ResultSet rs = DBHelper.query(query, idSubject);
            ArrayList<Comment> comments = new ArrayList<>();
            while(rs.next()) {
                comments.add(getComment(rs));
            }
            return comments;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
