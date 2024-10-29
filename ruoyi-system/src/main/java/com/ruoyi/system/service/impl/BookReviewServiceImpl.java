package com.ruoyi.system.service.impl;

import java.nio.file.Watchable;
import java.util.ArrayList;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.system.dto.reviewDTO;
import com.ruoyi.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.BookReviewMapper;
import com.ruoyi.system.domain.BookReview;
import com.ruoyi.system.service.IBookReviewService;

/**
 * 图书评论管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-10-29
 */
@Service
public class BookReviewServiceImpl implements IBookReviewService 
{
    @Autowired
    private BookReviewMapper bookReviewMapper;

    @Autowired
    private SysUserMapper sysUserMapper;


    /**
     * 查询图书评论管理列表
     * 
     * @param bookReview 图书评论管理
     * @return 图书评论管理
     */
    @Override
    public List<reviewDTO> selectBookReviewList(BookReview bookReview)
    {
        List<reviewDTO> list = new ArrayList<>();
        List<BookReview> bookReviews = bookReviewMapper.selectBookReviewList(bookReview);
        bookReviews.stream().forEach(book->{
            reviewDTO reviewDTO = new reviewDTO();
            reviewDTO.setUsername(sysUserMapper.selectUserById(book.getUserId()).getUserName());
            reviewDTO.setReview(book.getReview());
            reviewDTO.setBookId(book.getBookId());
            list.add(reviewDTO);
        });
        return list;
    }

    /**
     * 新增图书评论管理
     * 
     * @param bookReview 图书评论管理
     * @return 结果
     */
    @Override
    public int insertBookReview(BookReview bookReview)
    {
        return bookReviewMapper.insertBookReview(bookReview);
    }

}
