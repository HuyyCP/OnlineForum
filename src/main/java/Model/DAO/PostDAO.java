package Model.DAO;

import Helper.DBHelper;
import Model.Bean.Post;
import java.sql.*;
import java.util.ArrayList;

public class PostDAO {

    public ArrayList<Post> getAllPostsBySubjectID(String idSubject) {
        try {
            String query = "SELECT * FROM post WHERE idsubject = ?";
            ResultSet rs = DBHelper.query(query, idSubject);
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
            return posts;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addPost(Post post) {
        String query = "INSERT INTO post (idpost, title, datecreate, idsubject, iduser) VALUES (?, ?, ?, ?, ?)";
        DBHelper.execute(query, post.getIdPost(), post.getTitle(), post.getDateCreated(), post.getIdSubject(), post.getIdUser());
    }

    public void deletePost(String idPost) {
        String query = "DELETE FROM post WHERE idpost = ?";
        DBHelper.execute(query, idPost);
    }
}
