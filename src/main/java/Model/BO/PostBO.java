package Model.BO;

import DTO.BestPostDTO;
import DTO.PostDTO;
import Model.Bean.Post;
import Model.DAO.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.ArrayList;
import java.util.UUID;

public class PostBO {

    private PostDAO postDAO;
    private CommentDAO commentDAO;
    private SubsubjectDAO subsubjectDAO;
    private UserDAO userDAO;
    private SubSubjectBO subSubjectBO;
    private UserBO userBO;

    public PostBO() {
        postDAO = new PostDAO();
        commentDAO = new CommentDAO();
        userDAO = new UserDAO();
        subsubjectDAO = new SubsubjectDAO();
        subSubjectBO = new SubSubjectBO();
        userBO = new UserBO();
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
            postDTO.setUser(userDAO.getUserByID(post.getIdUser()));
            postDTO.setNumComments(commentDAO.getAmountCommentsByPostID(post.getIdPost()));
            postDTOs.add(postDTO);
        }
        return postDTOs;
    }

    public ArrayList<PostDTO> getPostsPaging(String idSubject, Integer maxPage, Integer numPage) {
        ArrayList<Post> posts = postDAO.getAllPostsBySubjectID(idSubject);
        ArrayList<PostDTO> postDTOs = new ArrayList<>();

        for (Post post : posts) {
            PostDTO postDTO = new PostDTO();
            postDTO.setIdPost(post.getIdPost());
            postDTO.setTitle(post.getTitle());
            postDTO.setDateCreated(post.getDateCreated());
            postDTO.setIdSubSubject(post.getIdSubSubject());
            postDTO.setIdUser(post.getIdUser());
            postDTO.setUser(userDAO.getUserByID(post.getIdUser()));
            postDTO.setNumComments(commentDAO.getAmountCommentsByPostID(post.getIdPost()));
            postDTOs.add(postDTO);
        }

        ArrayList<PostDTO> postDTOsPaging = new ArrayList<>();

        for (int i = (numPage - 1) * maxPage; i < postDTOs.size() && i < numPage * maxPage; i++) {
            postDTOsPaging.add(postDTOs.get(i));
        }

        System.out.println(postDTOsPaging.size());

        return  postDTOsPaging;
    }


    public int getNumPost() {
        return postDAO.getNumPost();
    }

    public Post getPostByID(String idPost) {
        Post post = postDAO.getPostByID(idPost);
        post.setUser(userBO.getUserById(post.getIdUser()));
        post.setSubsubject(subSubjectBO.getSubject(post.getIdSubSubject()));
        return post;
    }

    public ArrayList<PostDTO> getLastPostForEachSubSubjects() {
        ArrayList<Post> posts = postDAO.getLastPostForEachSubSubjects();
        ArrayList<PostDTO> postDTOs = new ArrayList<>();

        for (Post post : posts) {
            PostDTO postDTO = new PostDTO();
            postDTO.setIdPost(post.getIdPost());
            postDTO.setTitle(post.getTitle());
            postDTO.setDateCreated(post.getDateCreated());
            postDTO.setIdSubSubject(post.getIdSubSubject());
            postDTO.setIdUser(post.getIdUser());
            postDTO.setUser(userDAO.getUserByID(post.getIdUser()));
            postDTO.setNumComments(commentDAO.getAmountCommentsByPostID(post.getIdPost()));
            postDTOs.add(postDTO);
        }
        return postDTOs;
    }

    public BestPostDTO getBestPost() {
        return postDAO.getBestPost();
    }

    public String addPost(Post post) {
        String uuid = UUID.randomUUID().toString();
        post.setIdPost(uuid);
        post.setDateCreated(new Timestamp(System.currentTimeMillis()));
        postDAO.addPost(post);
        return uuid;
    }

    public boolean isEnable(String idSubject) {
        return subsubjectDAO.isEnable(idSubject);
    }

    public void deletePost(String idPost) {
        commentDAO.deleteCommentByPostID(idPost);
        postDAO.deletePost(idPost);
    }

    public void updatePost(Post post) {
        postDAO.updatePost(post);
    }

    public Integer getNumPost(String idSubSubject) {
        return postDAO.getNumPost(idSubSubject);
    }

    public ArrayList<PostDTO> getPostsPagingFilter(String idSubSubject, Integer maxPage, Integer numPage, String text) {
        ArrayList<Post> posts = postDAO.getAllPostsBySubjectIDFilter(idSubSubject, text);
        ArrayList<PostDTO> postDTOs = new ArrayList<>();

        for (Post post : posts) {
            PostDTO postDTO = new PostDTO();
            postDTO.setIdPost(post.getIdPost());
            postDTO.setTitle(post.getTitle());
            postDTO.setDateCreated(post.getDateCreated());
            postDTO.setIdSubSubject(post.getIdSubSubject());
            postDTO.setIdUser(post.getIdUser());
            postDTO.setUser(userDAO.getUserByID(post.getIdUser()));
            postDTO.setNumComments(commentDAO.getAmountCommentsByPostID(post.getIdPost()));
            postDTOs.add(postDTO);
        }

        ArrayList<PostDTO> postDTOsPaging = new ArrayList<>();

        for (int i = (numPage - 1) * maxPage; i < postDTOs.size() && i < numPage * maxPage; i++) {
            postDTOsPaging.add(postDTOs.get(i));
        }

        System.out.println(postDTOsPaging.size());

        return postDTOsPaging;
    }

    public Integer getNumPostFilter(String idSubSubject, String text) {
        return postDAO.getNumPostFilter(idSubSubject, text);
    }
}
