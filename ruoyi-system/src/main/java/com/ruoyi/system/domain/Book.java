package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 图书管理对象 book
 * 
 * @author ruoyi
 * @date 2024-10-29
 */
public class Book extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Integer id;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 价格 */
    @Excel(name = "价格")
    private Long price;

    /** 作者 */
    @Excel(name = "作者")
    private String author;

    /** 分类 */
    @Excel(name = "分类")
    private String category;

    /** 内容 */
    @Excel(name = "内容")
    private String content;

    /** 数量 */
    @Excel(name = "数量")
    private Long count;

    /** 封面url */
    @Excel(name = "封面url")
    private String image;

    /** 是否可售 */
    @Excel(name = "是否可售")
    private Integer status;

    /** 是否特价 */
    @Excel(name = "是否特价")
    private Integer bargainPrice;

    /** 是否推荐 */
    @Excel(name = "是否推荐")
    private Integer recommended;

    /** 发布人 */
    @Excel(name = "发布人")
    private String createUser;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setPrice(Long price) 
    {
        this.price = price;
    }

    public Long getPrice() 
    {
        return price;
    }
    public void setAuthor(String author) 
    {
        this.author = author;
    }

    public String getAuthor() 
    {
        return author;
    }
    public void setCategory(String category) 
    {
        this.category = category;
    }

    public String getCategory() 
    {
        return category;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setCount(Long count) 
    {
        this.count = count;
    }

    public Long getCount() 
    {
        return count;
    }
    public void setImage(String image) 
    {
        this.image = image;
    }

    public String getImage() 
    {
        return image;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }
    public void setBargainPrice(Integer bargainPrice) 
    {
        this.bargainPrice = bargainPrice;
    }

    public Integer getBargainPrice() 
    {
        return bargainPrice;
    }
    public void setRecommended(Integer recommended) 
    {
        this.recommended = recommended;
    }

    public Integer getRecommended() 
    {
        return recommended;
    }
    public void setCreateUser(String createUser) 
    {
        this.createUser = createUser;
    }

    public String getCreateUser() 
    {
        return createUser;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("price", getPrice())
            .append("author", getAuthor())
            .append("category", getCategory())
            .append("content", getContent())
            .append("count", getCount())
            .append("image", getImage())
            .append("status", getStatus())
            .append("bargainPrice", getBargainPrice())
            .append("recommended", getRecommended())
            .append("createTime", getCreateTime())
            .append("createUser", getCreateUser())
            .toString();
    }
}
