package Model.BO;

import DTO.PostDTO;
import Model.Bean.Post;
import Model.DAO.UserDAO;
import Model.DAO.CommentDAO;
import Model.DAO.PostDAO;

import java.sql.*;
import java.util.Date;
import java.util.ArrayList;
import java.util.UUID;

public class PostBO {

    private PostDAO postDAO;
    private CommentDAO commentDAO;
    private UserDAO userDAO;

    public PostBO() {
        postDAO = new PostDAO();
        commentDAO = new CommentDAO();
        userDAO = new UserDAO();
    }

    public ArrayList<PostDTO> getAllPostsBySubjectID(String idSubject) {
        ArrayList<Post> posts = postDAO.getAllPostsBySubjectID(idSubject);
        ArrayList<PostDTO> postDTOs = new ArrayList<>();
        for (Post post : posts) {
            PostDTO postDTO = new PostDTO();
            postDTO.setIdPost(post.getIdPost());
            postDTO.setTitle(post.getTitle());
            postDTO.setDateCreated(post.getDateCreated());
            postDTO.setIdSubject(post.getIdSubject());
            postDTO.setIdUser(post.getIdUser());
            postDTO.setNumComments(commentDAO.getAmountCommentsByPostID(post.getIdPost()));
            postDTOs.add(postDTO);
        }
        return postDTOs;
    }

    public void addPost(Post post) {
        post.setIdPost(UUID.randomUUID().toString());
        post.setDateCreated(new Date());
        postDAO.addPost(post);
    }

    public void deletePost(String idPost) {
        postDAO.deletePost(idPost);
    }
}
