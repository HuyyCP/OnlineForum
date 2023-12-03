package Model.DAO;

import Helper.DBHelper;
import Model.Bean.Post;

import java.sql.*;
import java.util.ArrayList;

public class PostDAO {

    Connection conn;

    public PostDAO() {
        conn = DBHelper.getConnection();
    }

    public ArrayList<Post> getAllPostsBySubjectID(String idSubject) {
        try {
            String query = "SELECT * " +
                            "FROM post " +
                            "WHERE idsubject = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, idSubject);
            ResultSet rs = statement.executeQuery();
            ArrayList<Post> posts = new ArrayList<>();
            while(rs.next()) {
                Post post = new Post();
                post.setIdPost(rs.getString("idpost"));
                post.setTitle(rs.getString("title"));
                post.setDateCreated(rs.getDate("datecreate"));
                post.setIdSubject(rs.getString("idsubject"));
                post.setIdUser(rs.getString("iduser"));
                posts.add(post);
            }
            statement.close();
            return posts;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addPost(Post post) {
        try {
            String query = "INSERT INTO post (idpost, title, datecreate, idsubject, iduser) " +
                            "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, post.getIdPost());
            statement.setString(2, post.getTitle());
            statement.setDate(3, (Date)post.getDateCreated());
            statement.setString(4, post.getIdSubject());
            statement.setString(5, post.getIdUser());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletePost(String idPost) {
        try {
            String query = "DELETE FROM post " +
                            "WHERE idpost = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, idPost);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
