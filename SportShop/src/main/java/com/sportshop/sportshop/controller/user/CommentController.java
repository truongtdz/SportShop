package com.sportshop.sportshop.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.sportshop.sportshop.entity.CommentEntity;
import com.sportshop.sportshop.service.CommentService;
import com.sportshop.sportshop.utils.GetUserAuthentication;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private GetUserAuthentication getUserAuthentication;

    @PostMapping("/user/comment/{productId}")
    public String comment(@ModelAttribute("newComment") CommentEntity newComment,
                          @PathVariable Long productId){

        newComment.setUser(getUserAuthentication.getUser());
        commentService.AddComment(newComment, productId);
        
        return "redirect:/home/product/{productId}";
    }
}
