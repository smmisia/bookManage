package com.ruoyi.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.dto.reviewDTO;
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
import com.ruoyi.system.domain.BookReview;
import com.ruoyi.system.service.IBookReviewService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 图书评论管理Controller
 * 
 * @author ruoyi
 * @date 2024-10-29
 */
@RestController
@RequestMapping("/system/review")
public class BookReviewController extends BaseController
{
    @Autowired
    private IBookReviewService bookReviewService;

    /**
     * 查询图书评论管理列表
     */
    @GetMapping("/list")
    public TableDataInfo list(BookReview bookReview)
    {
        startPage();
        List<reviewDTO> list = bookReviewService.selectBookReviewList(bookReview);
        return getDataTable(list);
    }

    /**
     * 新增图书评论管理
     */
    @PreAuthorize("@ss.hasPermi('system:review:add')")
    @Log(title = "图书评论管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BookReview bookReview)
    {
        bookReview.setUserId(SecurityUtils.getUserId());
        return toAjax(bookReviewService.insertBookReview(bookReview));
    }

}
