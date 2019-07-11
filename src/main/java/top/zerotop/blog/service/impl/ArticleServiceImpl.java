package top.zerotop.blog.service.impl;

import java.time.LocalDateTime;
import java.util.*;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.util.Assert;
import top.zerotop.blog.domain.ArticleDTO;
import top.zerotop.blog.web.condition.ArticleCondition;
import top.zerotop.blog.data.mapper.ArticleMapper;
import top.zerotop.blog.data.model.Article;
import top.zerotop.blog.service.ArticleService;
import top.zerotop.exception.BlogException;

/**
 * @author 作者: zerotop
 * @createDate 创建时间: 2018年5月21日下午8:36:23
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private DozerBeanMapper dozerMapper;

    @Override
    public List<Article> queryArticle(ArticleCondition articleCondition) throws BlogException {
        Assert.isTrue(articleCondition.getCurrent() < 0, "请求页码错误");
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
        return articleMapper.selectByArticleId(id);
    }

    @Override
    public int insertArticle(ArticleDTO articleDTO) {
        Article article = dozerMapper.map(articleDTO, Article.class);
        article.setGmtCreate(LocalDateTime.now().toString());
        article.setGmtModified(LocalDateTime.now().toString());
        return articleMapper.insertArticle(article);
    }

    @Override
    public int updateArticleById(Article article) {
        article.setGmtModified(LocalDateTime.now().toString());
        return articleMapper.updateByArticleId(article);
    }

}
