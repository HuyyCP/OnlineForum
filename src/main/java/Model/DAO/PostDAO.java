package Model.DAO;

import Helper.DBHelper;
import Model.Bean.Post;
import java.sql.*;
import java.util.ArrayList;

public class PostDAO {

    private Post getPost(ResultSet rs) {
        try {
            Post post = new Post();
            post.setIdPost(rs.getString("idpost"));
            post.setTitle(rs.getString("title"));
            post.setDateCreated(rs.getDate("datecreate"));
            post.setIdSubSubject(rs.getString("idsubject"));
            post.setIdUser(rs.getString("iduser"));
            post.setContent(rs.getString("content"));
            return post;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<Post> getAllPostsBySubjectID(String idSubject) {
        try {
            String query = "SELECT * FROM post WHERE idsubject = ? ORDER BY datecreate DESC";
            ResultSet rs = DBHelper.query(query, idSubject);
            ArrayList<Post> posts = new ArrayList<>();
            while(rs.next()) {
                posts.add(getPost(rs));
            }
            return posts;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Post getPostByID(String idPost) {
        try {
            String query = "SELECT * FROM post WHERE idpost = ?";
            ResultSet rs = DBHelper.query(query, idPost);
            rs.next();
            return getPost(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addPost(Post post) {
        String query = "INSERT INTO post (idpost, title, datecreate, content, idsubject, iduser) VALUES (?, ?, ?, ?, ?, ?)";
        DBHelper.execute(query, post.getIdPost(), post.getTitle(), post.getDateCreated(), post.getContent(), post.getIdSubSubject(), post.getIdUser());
    }

    public void deletePost(String idPost) {
        String query = "DELETE FROM post WHERE idpost = ?";
        DBHelper.execute(query, idPost);
    }

    public void deletePostBySubjectID(String idSubject) {
        String query = "DELETE FROM post WHERE idsubject = ?";
        DBHelper.execute(query, idSubject);
    }

    public Integer getNumPost(String idSubSubject) {
        try {
            String query = "SELECT count(*) FROM post WHERE idsubject = ?";
            ResultSet rs = DBHelper.query(query, idSubSubject);

            if(rs.next()){
                return rs.getInt(1);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public ArrayList<Post> getLastPostForEachSubSubjects() {
        try {
            String query = "SELECT p.idpost, p.title, p.datecreate, p.idsubject, p.iduser, s.subjectname, p.content\n" +
                    "FROM post p JOIN subsubject s ON p.idsubject = s.idsubject\n" +
                    "INNER JOIN \n" +
                    "(\n" +
                    "  SELECT idsubject, MAX(datecreate) AS max_date \n" +
                    "  FROM post\n" +
                    "  GROUP BY idsubject\n" +
                    ") pm ON p.idsubject = pm.idsubject AND p.datecreate = pm.max_date";
            ResultSet rs = DBHelper.query(query);
            ArrayList<Post> posts = new ArrayList<>();
            while(rs.next()) {
                posts.add(getPost(rs));
            }
            return posts;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
