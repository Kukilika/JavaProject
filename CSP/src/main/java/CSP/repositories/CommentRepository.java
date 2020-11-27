package CSP.repositories;

import CSP.models.Comment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CommentRepository {

    public List<Comment> commentList = new ArrayList<>();

    public void addComment(Comment comment){
        commentList.add(comment);
    }

    public void removeComment(Comment comment){
        commentList.remove(comment);
    }

    public void updateComment(Comment oldComment, Comment newComment){
        commentList.set(commentList.indexOf(oldComment) , newComment);
    }

    public List<Comment> getAllComments(){
        return commentList;
    }
}
