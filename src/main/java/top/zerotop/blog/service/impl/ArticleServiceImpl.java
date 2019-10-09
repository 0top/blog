package top.zerotop.blog.service.impl;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import top.zerotop.blog.domain.ArticleDTO;
import top.zerotop.blog.web.condition.ArticleCondition;
import top.zerotop.blog.data.mapper.ArticleMapper;
import top.zerotop.blog.data.model.Article;
import top.zerotop.blog.service.ArticleService;
import top.zerotop.exception.BlogException;
import top.zerotop.utils.EncryptUtils;
import top.zerotop.utils.PageInfo;

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
    public List<Article> queryArticle(ArticleCondition articleCondition) {
        Assert.isTrue(articleCondition.getCurrent() < 0, "请求页码错误");
        int startIndex = articleCondition.getCurrent() * articleCondition.getSize();
        int endIndex = startIndex + articleCondition.getSize();
        String[] categorys = articleCondition.getCategory().split("\\|");
        List<String> categorySet = CollectionUtils.arrayToList(categorys);

        List<Article> articles = articleMapper.queryArticle(articleCondition.getOrderBy());
        if (!CollectionUtils.isEmpty(articles) && StringUtils.hasText(articleCondition.getSearchString())) {
            articles = articles.stream().filter(a -> {
                if (categorySet.size() > 0 && !categorySet.contains(a.getCategory())) {
                    return false;
                }
                return a.getTitle().contains(articleCondition.getSearchString());
            }).collect(Collectors.toList());
        }
        return PageInfo.getPage(articles, startIndex, endIndex);
    }

    @Override
    public Article getArticleById(String articleId) {
        return StringUtils.hasText(articleId) ? articleMapper.selectByArticleId(articleId) : null;
    }

    @Override
    public String insertArticle(ArticleDTO articleDTO) {
        Article article = dozerMapper.map(articleDTO, Article.class);
        String articleId = EncryptUtils.getUuid();
        article.setArticleId(articleId);
        article.setGmtCreate(LocalDateTime.now().toString());
        article.setGmtModified(LocalDateTime.now().toString());
        if (articleMapper.insertArticle(article) > 0) {
            return articleId;
        }
        return null;
    }

    @Override
    public int updateArticleById(Article article) {
        article.setGmtModified(LocalDateTime.now().toString());
        return articleMapper.updateByArticleId(article);
    }

}
