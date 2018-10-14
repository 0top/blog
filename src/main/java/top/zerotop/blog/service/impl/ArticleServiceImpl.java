package top.zerotop.blog.service.impl;

import java.time.LocalDate;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.zerotop.blog.controller.condition.ArticleCondition;
import top.zerotop.blog.db.mapper.ArticleMapper;
import top.zerotop.blog.db.model.Article;
import top.zerotop.blog.service.BlogService;
import top.zerotop.exception.ArticleException;
import top.zerotop.exception.BlogException;

/**
 * @author 作者: zerotop
 * @createDate 创建时间: 2018年5月21日下午8:36:23
 */
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public List<Article> queryArticle(ArticleCondition articleCondition) throws BlogException {

        if (articleCondition.getCurrent() < 0) {
            throw new ArticleException("请求页码错误");
        }
        int startIndex = articleCondition.getCurrent() * articleCondition.getSize();
        int endIndex = startIndex + articleCondition.getSize();
        String[] categorys = null;
        if (articleCondition.getCategory() != null) {
            categorys = articleCondition.getCategory().split("\\|");
        }
        articleCondition.setSearchString("%"+articleCondition.getSearchString()+"%");

        return articleMapper.queryArticle(
                articleCondition.getOrderBy(),
                articleCondition.getSearchString(),
                categorys,
                startIndex,
                endIndex
        );
    }

    @Override
    public Article getArticleById(int id) {
        if (id < 0) {
            throw new IllegalArgumentException();
        }
        return articleMapper.selectByArticleId(id);
    }

    @Override
    public int insertArticle(Article article) {
        article.setCreateTime(new Date());
        return articleMapper.insertArticle(article);
    }

    @Override
    public int updateByArticleId(Article article) {
        article.setModifyTime(new Date());
        return articleMapper.updateByArticleId(article);
    }

}
