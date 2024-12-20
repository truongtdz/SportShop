package com.sportshop.sportshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportshop.sportshop.entity.CommentEntity;
import com.sportshop.sportshop.enums.StatusEnum;
import com.sportshop.sportshop.repository.CommentRepository;
import com.sportshop.sportshop.repository.ProductRepository;
import com.sportshop.sportshop.service.CommentService;

import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<CommentEntity> findByProductId(Long productId) {
        return commentRepository.findByProductId(productId);
    }

    @Override
    public void AddComment(CommentEntity newComment, Long productId) {

        newComment.setCreateDate(new Date());
        newComment.setProduct(productRepository.findByIdAndStatus(productId, StatusEnum.Active));
        commentRepository.save(newComment);

    }
}
