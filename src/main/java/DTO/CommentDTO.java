package DTO;

import Model.Bean.Comment;
import Model.Bean.User;

public class CommentDTO extends Comment {
    User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
