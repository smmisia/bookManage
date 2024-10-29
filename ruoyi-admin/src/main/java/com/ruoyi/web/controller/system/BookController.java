package com.ruoyi.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.Book;
import com.ruoyi.system.service.IBookService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 图书管理Controller
 * 
 * @author ruoyi
 * @date 2024-10-29
 */
@RestController
@RequestMapping("/system/book")
public class BookController extends BaseController
{
    @Autowired
    private IBookService bookService;

    /**
     * 查询图书管理列表
     */
    @GetMapping("/list")
    public TableDataInfo list(Book book)
    {
        startPage();
        List<Book> list = bookService.selectBookList(book);
        return getDataTable(list);
    }

    /**
     * 导出图书管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:book:export')")
    @Log(title = "图书管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Book book)
    {
        List<Book> list = bookService.selectBookList(book);
        ExcelUtil<Book> util = new ExcelUtil<Book>(Book.class);
        util.exportExcel(response, list, "图书管理数据");
    }

    /**
     * 获取图书管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:book:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return success(bookService.selectBookById(id));
    }

    /**
     * 新增图书管理
     */
    @PreAuthorize("@ss.hasPermi('system:book:add')")
    @Log(title = "图书管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Book book)
    {
        return toAjax(bookService.insertBook(book));
    }

    /**
     * 修改图书管理
     */
    @PreAuthorize("@ss.hasPermi('system:book:edit')")
    @Log(title = "图书管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Book book)
    {
        return toAjax(bookService.updateBook(book));
    }

    /**
     * 删除图书管理
     */
    @PreAuthorize("@ss.hasPermi('system:book:remove')")
    @Log(title = "图书管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(bookService.deleteBookByIds(ids));
    }
}
