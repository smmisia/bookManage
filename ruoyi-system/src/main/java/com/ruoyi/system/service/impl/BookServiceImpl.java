package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.BookMapper;
import com.ruoyi.system.domain.Book;
import com.ruoyi.system.service.IBookService;

/**
 * 图书管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-10-29
 */
@Service
public class BookServiceImpl implements IBookService 
{
    @Autowired
    private BookMapper bookMapper;

    /**
     * 查询图书管理
     * 
     * @param id 图书管理主键
     * @return 图书管理
     */
    @Override
    public Book selectBookById(Integer id)
    {
        return bookMapper.selectBookById(id);
    }

    /**
     * 查询图书管理列表
     * 
     * @param book 图书管理
     * @return 图书管理
     */
    @Override
    public List<Book> selectBookList(Book book)
    {
        return bookMapper.selectBookList(book);
    }

    /**
     * 新增图书管理
     * 
     * @param book 图书管理
     * @return 结果
     */
    @Override
    public int insertBook(Book book)
    {
        book.setCreateTime(DateUtils.getNowDate());
        return bookMapper.insertBook(book);
    }

    /**
     * 修改图书管理
     * 
     * @param book 图书管理
     * @return 结果
     */
    @Override
    public int updateBook(Book book)
    {
        return bookMapper.updateBook(book);
    }

    /**
     * 批量删除图书管理
     * 
     * @param ids 需要删除的图书管理主键
     * @return 结果
     */
    @Override
    public int deleteBookByIds(Integer[] ids)
    {
        return bookMapper.deleteBookByIds(ids);
    }

    /**
     * 删除图书管理信息
     * 
     * @param id 图书管理主键
     * @return 结果
     */
    @Override
    public int deleteBookById(Integer id)
    {
        return bookMapper.deleteBookById(id);
    }
}
