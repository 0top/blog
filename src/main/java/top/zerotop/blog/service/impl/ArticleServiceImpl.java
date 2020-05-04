package top.zerotop.blog.service.impl;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import top.zerotop.blog.dto.ArticleDTO;
import top.zerotop.blog.web.Request.ArticleRequest;
import top.zerotop.blog.web.condition.ArticleCondition;
import top.zerotop.blog.data.mapper.ArticleMapper;
import top.zerotop.blog.data.model.Article;
import top.zerotop.blog.service.ArticleService;
import top.zerotop.utils.EncryptUtils;
import top.zerotop.utils.PageInfo;

/**
 * @author 作者: zerotop
 * @createDate 创建时间: 2018年5月21日下午8:36:23
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    private static Logger logger = LoggerFactory.getLogger(ArticleServiceImpl.class);
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private DozerBeanMapper dozerMapper;

    @Override
    public List<Article> queryArticle(ArticleCondition articleCondition) {
        if (!articleCondition.check()) {
            return new ArrayList<>();
        }
        List<String> categorySet = Arrays.asList(articleCondition.getCategory().split("\\|"));

        String orderBy = articleCondition.getOrderBy() == null ? "gmt_create" : articleCondition.getOrderBy();
        List<Article> articles = articleMapper.findAllArticle(orderBy);
        if (!CollectionUtils.isEmpty(articles) && StringUtils.hasText(articleCondition.getSearchString())) {
            articles = articles.stream().filter(a -> {
                if (categorySet.size() > 0 && !categorySet.contains(a.getCategory())) {
                    return false;
                }
                return a.getTitle().contains(articleCondition.getSearchString());
            }).collect(Collectors.toList());
        }
        return PageInfo.getPage(articles, articleCondition);
    }

    @Override
    public Article getArticleById(String articleId) {
        return StringUtils.hasText(articleId) ? articleMapper.selectByArticleId(articleId) : null;
    }

    @Override
    public String insertArticle(ArticleRequest articleRequest) {
        if (articleRequest != null) {
            Article article = dozerMapper.map(articleRequest, Article.class);
            String articleId = EncryptUtils.getUuid();
            article.setArticleId(articleId);
            article.setGmtCreate(LocalDateTime.now().toString());
            article.setGmtModified(LocalDateTime.now().toString());
            if (articleMapper.insertArticle(article) > 0) {
                return articleId;
            }
        }
        logger.warn("insert article failed, article request is null.");
        return null;
    }

    @Override
    public int updateArticleById(Article article) {
        if (article == null || !StringUtils.hasText(article.getArticleId())) {
            logger.warn("article is null or article id is empty, return");
            return 0;
        }
        article.setGmtModified(LocalDateTime.now().toString());
        return articleMapper.updateByArticleId(article);
    }

}
