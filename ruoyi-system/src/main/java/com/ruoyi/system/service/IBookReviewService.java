package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.BookReview;
import com.ruoyi.system.dto.reviewDTO;

/**
 * 图书评论管理Service接口
 * 
 * @author ruoyi
 * @date 2024-10-29
 */
public interface IBookReviewService 
{
    /**
     * 查询图书评论管理列表
     * 
     * @param bookReview 图书评论管理
     * @return 图书评论管理集合
     */
    public List<reviewDTO> selectBookReviewList(BookReview bookReview);

    /**
     * 新增图书评论管理
     * 
     * @param bookReview 图书评论管理
     * @return 结果
     */
    public int insertBookReview(BookReview bookReview);

}
