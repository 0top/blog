package top.zerotop.blog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.zerotop.blog.dao.ArticleMapper;
import top.zerotop.blog.domain.Article;
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
		return articleDao.selectByPrimaryKey(id);
	}

	@Override
	public int insertArticle(Article article) {
		return articleDao.insertSelective(article);
	}

	@Override
	public int updateArticleSelective(Article article) {
		return articleDao.updateByPrimaryKeySelective(article);
	}

}
