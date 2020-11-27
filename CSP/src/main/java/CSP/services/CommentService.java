package CSP.services;

import CSP.models.Comment;
import CSP.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository){
        this.commentRepository = commentRepository;
    }

    public void addComment(Comment comment){
        commentRepository.addComment(comment);
    }

    public void removeComment(Comment comment){
        commentRepository.removeComment(comment);
    }

    public void updateComment(Comment oldComment, Comment newComment){
        commentRepository.updateComment(oldComment, newComment);
    }

    public List<Comment> getAllComments(){
        return commentRepository.getAllComments();
    }
}
