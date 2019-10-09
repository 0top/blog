package top.zerotop.blog.service;

import java.util.List;

import top.zerotop.blog.domain.ArticleDTO;
import top.zerotop.blog.web.condition.ArticleCondition;
import top.zerotop.blog.data.model.Article;
import top.zerotop.exception.BlogException;

/**
 *@author 作者: zerotop
 *@createDate 创建时间: 2018年5月21日下午8:38:38
 */
public interface ArticleService {

	List<Article> queryArticle(ArticleCondition articleCondition)  throws BlogException;
	
	Article getArticleById(String articleId);
	
	String insertArticle(ArticleDTO articleDTO);
	
	int updateArticleById(Article article);
}
