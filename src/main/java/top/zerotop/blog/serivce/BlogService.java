package top.zerotop.blog.serivce;

import java.util.List;

import top.zerotop.blog.domain.Article;

/**
 *@author 作者: zerotop
 *@createDate 创建时间: 2018年5月21日下午8:38:38
 */
public interface BlogService {

	public List<Article> listArticle(int pagenum, int pagesize);
	
	public Article getArticleById(long id);
	
	public int insertArticle(Article article);
	
	public int updateArticleSelective(Article article);
}
