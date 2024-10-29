package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Book;

/**
 * 图书管理Service接口
 * 
 * @author ruoyi
 * @date 2024-10-29
 */
public interface IBookService 
{
    /**
     * 查询图书管理
     * 
     * @param id 图书管理主键
     * @return 图书管理
     */
    public Book selectBookById(Integer id);

    /**
     * 查询图书管理列表
     * 
     * @param book 图书管理
     * @return 图书管理集合
     */
    public List<Book> selectBookList(Book book);

    /**
     * 新增图书管理
     * 
     * @param book 图书管理
     * @return 结果
     */
    public int insertBook(Book book);

    /**
     * 修改图书管理
     * 
     * @param book 图书管理
     * @return 结果
     */
    public int updateBook(Book book);

    /**
     * 批量删除图书管理
     * 
     * @param ids 需要删除的图书管理主键集合
     * @return 结果
     */
    public int deleteBookByIds(Integer[] ids);

    /**
     * 删除图书管理信息
     * 
     * @param id 图书管理主键
     * @return 结果
     */
    public int deleteBookById(Integer id);
}
