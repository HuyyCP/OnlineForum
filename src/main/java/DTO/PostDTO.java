package DTO;

import Model.Bean.Post;

public class PostDTO extends Post {
    int numComments;        // So luong comment

    public int getNumComments() {
        return numComments;
    }

    public void setNumComments(int numComments) {
        this.numComments = numComments;
    }

}
