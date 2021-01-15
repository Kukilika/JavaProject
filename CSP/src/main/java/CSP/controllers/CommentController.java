package CSP.controllers;

import CSP.models.Car;
import CSP.models.Comment;
import CSP.repositories.CommentRepository;
import CSP.services.CarService;
import CSP.services.CommentService;
import CSP.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private CarService carService;

    @Autowired
    private UserService userService;

    @GetMapping("")
    @ResponseBody
    public List<Comment> getComments(){
        return commentService.getAllComments();
    }

    @PostMapping("/registration/{id}")
    public ResponseEntity<Comment> addComment(@Validated @RequestBody Comment comment, @PathVariable Long id){
        if (carService.doesCarExist(id)){
            commentService.addComment(id, comment);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comment> updateComment(@RequestBody Comment comment, @PathVariable Long id){
        if(commentService.doesCommentExist(id) && userService.hasUserPermissionsToEditComment(id)){
            commentService.updateComment(id, comment);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Comment> deleteComment(@PathVariable Long id){

        if(commentService.doesCommentExist(id) && userService.hasUserPermissionsToEditComment(id)){
            commentService.removeComment(commentService.getComment(id));
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

}
