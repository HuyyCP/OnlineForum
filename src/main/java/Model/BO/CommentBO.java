package Model.BO;

import Model.Bean.Comment;
import Model.DAO.CommentDAO;
import Model.DAO.PostDAO;
import Model.DAO.UserDAO;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class CommentBO {

    private CommentDAO commentDAO;
    private UserDAO userDAO;
    private PostDAO postDAO;
    private UserBO userBO;
    private PostBO postBO;
    public CommentBO() {
        commentDAO = new CommentDAO();
        userDAO = new UserDAO();
        postDAO = new PostDAO();
        userBO = new UserBO();
        postBO = new PostBO();
    }

    public ArrayList<Comment> getAllCommentsByPostID(String idPost, int pageLimit, int pageIndex) {
        ArrayList<Comment> comments = commentDAO.getAllCommentsByPostID(idPost);
        for (Comment comment : comments) {
            comment.setUser(userBO.getUserById(comment.getIdUser()));
            comment.setPost(postBO.getPostByID(comment.getIdPost()));
        }
        comments.subList((pageIndex - 1) * pageLimit, comments.size() < pageIndex * pageLimit ? comments.size() : pageIndex * pageLimit);
        return comments;
    }

    public ArrayList<Comment> getAllCommentsByUserID(String idPost, String idUser, int pageLimit, int pageIndex) {
        ArrayList<Comment> comments = commentDAO.getAllCommentsByUserID(idPost, idUser);
        for (Comment comment : comments) {
            comment.setUser(userBO.getUserById(comment.getIdUser()));
            comment.setPost(postBO.getPostByID(comment.getIdPost()));
        }
        comments.subList((pageIndex - 1) * pageLimit, comments.size() < pageIndex * pageLimit ? comments.size() : pageIndex * pageLimit);
        return comments;
    }

    public int getAmountCommentsByPostID(String idPost) {
        return commentDAO.getAmountCommentsByPostID(idPost);
    }

    public void addComment(Comment comment) {
        comment.setIdcomment(UUID.randomUUID().toString());
        comment.setDateComment(new Timestamp(System.currentTimeMillis()));
        commentDAO.addComment(comment);
    }

    public void updateComment(Comment comment) {
        commentDAO.updateComment(comment);
    }

    public void deleteComment(String idComment) {
        commentDAO.deleteComment(idComment);
    }

}
