package top.zerotop.blog.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.zerotop.blog.db.mapper.ArticleMapper;
import top.zerotop.blog.db.model.Article;
import top.zerotop.blog.service.BlogService;

/**
 *@author 作者: zerotop
 *@createDate 创建时间: 2018年5月21日下午8:36:23
 */
@Service
public class BlogServiceImpl implements BlogService {
	
	@Autowired
	private ArticleMapper articleDao;

	@Override
	public List<Article> listArticle(int pagenum, int pagesize) {
		
		if(pagenum <= 1){
			pagenum = 1;
		}
		int startpage = (pagenum-1)*pagesize;
		
		return articleDao.listArticle(startpage, startpage + pagesize);
	}

	@Override
	public Article getArticleById(int id) {
		if (id < 0) {
			throw new IllegalArgumentException();
		}
		return articleDao.selectByArticleId(id);
	}

	@Override
	public int insertArticle(Article article) {
	    article.setCreateTime(new Date());
		return articleDao.insertArticle(article);
	}

	@Override
	public int updateByArticleId(Article article) {
		return articleDao.updateByArticleId(article);
	}

}
