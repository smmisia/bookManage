package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.BookReview;

/**
 * 图书评论管理Mapper接口
 * 
 * @author ruoyi
 * @date 2024-10-29
 */
public interface BookReviewMapper 
{

    /**
     * 查询图书评论管理列表
     * 
     * @param bookReview 图书评论管理
     * @return 图书评论管理集合
     */
    public List<BookReview> selectBookReviewList(BookReview bookReview);

    /**
     * 新增图书评论管理
     * 
     * @param bookReview 图书评论管理
     * @return 结果
     */
    public int insertBookReview(BookReview bookReview);

    /**
     * 修改图书评论管理
     * 
     * @param bookReview 图书评论管理
     * @return 结果
     */
    public int updateBookReview(BookReview bookReview);

    /**
     * 删除图书评论管理
     * 
     * @param id 图书评论管理主键
     * @return 结果
     */
    public int deleteBookReviewById(Long id);

    /**
     * 批量删除图书评论管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBookReviewByIds(Long[] ids);
}
