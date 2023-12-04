package DTO;

import Model.Bean.Post;

public class PostDTO extends Post {
    int numComments;        // So luong comment
    String memberName;      // Ten cua nguoi dang

    public int getNumComments() {
        return numComments;
    }

    public void setNumComments(int numComments) {
        this.numComments = numComments;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
}
