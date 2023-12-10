package Model.DAO;

import DTO.BestPostDTO;
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
            post.setDateCreated(rs.getTimestamp("datecreate"));
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

    public int getNumPost() {
        try {
            String query = "SELECT COUNT(*) as numPost FROM post ";
            ResultSet rs = DBHelper.query(query);
            rs.next();
            return rs.getInt("numPost");
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

    public BestPostDTO getBestPost() {
        try {
            String query = "select post.idpost, post.title, user.iduser, user.name, count(*) as numComment " +
                            "from post left join comment on post.idpost = comment.idpost " +
                            "          left join user on post.iduser = user.iduser " +
                            "group by post.idpost " +
                            "order by numComment desc " +
                            "limit 1";
            ResultSet rs = DBHelper.query(query);
            rs.next();
            BestPostDTO post = new BestPostDTO();
            post.setIdPost(rs.getString("idpost"));
            post.setTitle(rs.getString("title"));
            post.setIdUser(rs.getString("iduser"));
            post.setName(rs.getString("name"));
            post.setNumComments(rs.getInt("numComment"));
            return post;
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
}
