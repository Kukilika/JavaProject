package CSP.services;

import CSP.models.Car;
import CSP.models.Comment;
import CSP.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CarService carService;

    public CommentService(CommentRepository commentRepository){
        this.commentRepository = commentRepository;
    }

    public void addComment(Long carId, Comment comment){
        Comment newComment = new Comment();
        newComment.setDescription(comment.getDescription());
        newComment.setUser(userService.loggedUser());
        newComment.setCar(carService.getCar(carId));
        commentRepository.save(newComment);
    }

    public void removeComment(Comment comment){
        commentRepository.delete(comment);
    }

    public void updateComment(Long id, Comment newComment){
        Comment comment = getComment(id);
        comment.setId(id);
        comment.setDescription(newComment.getDescription());
        commentRepository.save(comment);
    }


    public List<Comment> getAllComments(){
        return commentRepository.findAll();
    }

    public boolean doesCommentExist(Long id) {
        return commentRepository.findById(id).isPresent();
    }

    public Comment getComment(Long id){
        Comment comment = new Comment(commentRepository.findById(id));
        comment.setId(id);
        return comment;
    }

    public void deleteAllCommentsRelatedToACar(Long id){
        List<Comment> commentList= commentRepository.findAll();
        for(Comment c:commentList){
            if(c.getCar().getId() == id){
                removeComment(c);
            }
        }
    }
}
