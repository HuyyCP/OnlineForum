package Model.BO;

import DTO.PostDTO;
import DTO.PostDetailDTO;
import Model.Bean.Comment;
import Model.Bean.Post;
import Model.DAO.*;

import javax.security.auth.Subject;
import java.sql.*;
import java.util.Date;
import java.util.ArrayList;
import java.util.UUID;

public class PostBO {

    private PostDAO postDAO;
    private CommentDAO commentDAO;
    private SubsubjectDAO subsubjectDAO;
    private SubjectDAO subjectDAO;
    private UserDAO userDAO;
    private CommentBO commentBO;

    public PostBO() {
        postDAO = new PostDAO();
        commentDAO = new CommentDAO();
        userDAO = new UserDAO();
        subjectDAO = new SubjectDAO();
        subsubjectDAO = new SubsubjectDAO();
        commentBO = new CommentBO();
    }

    public ArrayList<PostDTO> getAllPostsBySubjectID(String idSubject) {
        ArrayList<Post> posts = postDAO.getAllPostsBySubjectID(idSubject);
        ArrayList<PostDTO> postDTOs = new ArrayList<>();

        for (Post post : posts) {
            PostDTO postDTO = new PostDTO();
            postDTO.setIdPost(post.getIdPost());
            postDTO.setTitle(post.getTitle());
            postDTO.setDateCreated(post.getDateCreated());
            postDTO.setIdSubSubject(post.getIdSubSubject());
            postDTO.setIdUser(post.getIdUser());
            postDTO.setMemberName(userDAO.getUserByID(post.getIdUser()).getName());
            postDTO.setNumComments(commentDAO.getAmountCommentsByPostID(post.getIdPost()));
            postDTOs.add(postDTO);
        }
        return postDTOs;
    }

    public PostDetailDTO getPostByID(String idPost) {
        Post post = postDAO.getPostByID(idPost);
        PostDetailDTO postDetailDTO = new PostDetailDTO();
        postDetailDTO.setIdPost(post.getIdPost());
        postDetailDTO.setTitle(post.getTitle());
        postDetailDTO.setDateCreated(post.getDateCreated());
        postDetailDTO.setIdSubSubject(post.getIdSubSubject());
        postDetailDTO.setIdUser(post.getIdUser());
        postDetailDTO.setSubjectName(subjectDAO.getSubjectByID(subsubjectDAO.getSubjectID(post.getIdSubSubject())).getSubjectName());
        postDetailDTO.setSubsubjectName(subsubjectDAO.getSubSubjectName(post.getIdSubSubject()));
        postDetailDTO.setIdSubject(subjectDAO.getSubjectByID(subsubjectDAO.getSubjectID(post.getIdSubSubject())).getIdSubject());
        postDetailDTO.setCommentDTOs(commentBO.getAllCommentsByPostID(post.getIdPost()));
        return postDetailDTO;
    }

    public void addPost(Post post) {
        post.setIdPost(UUID.randomUUID().toString());
        post.setDateCreated(new Date());
        postDAO.addPost(post);
    }

    public boolean isEnable(String idSubject) {
        return subsubjectDAO.isEnable(idSubject);
    }

    public void deletePost(String idPost) {
        commentDAO.deleteCommentByPostID(idPost);
        postDAO.deletePost(idPost);
    }
}
