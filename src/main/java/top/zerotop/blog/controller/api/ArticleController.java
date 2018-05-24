package top.zerotop.blog.controller.api;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import top.zerotop.blog.controller.BaseController;
import top.zerotop.blog.entity.Article;
import top.zerotop.blog.serivce.BlogService;
import top.zerotop.blog.util.ReqJson;

/**
 *@author 作者: zerotop
 *@createDate 创建时间: 2018年5月21日下午8:42:50
 */
@Controller
public class ArticleController extends BaseController {
	
	@Autowired
	private BlogService blogService;

	@RequestMapping(value = "/article/get/{id}", produces="application/json;charset=utf-8")
	public @ResponseBody Article getArticleById(@PathVariable("id")long id){
		
		return blogService.getArticleById(id);
		
	}
	
	@RequestMapping(value = "/article/insert", produces="application/json;charset=utf-8")
	public @ResponseBody String insertArticle(HttpServletRequest req){
	
		String json = ReqJson.Json(req);
		Article article = JSON.parseObject(json, Article.class);
		
		long id =  blogService.insertArticle(article);
		article.setId(id);
		
		return JSON.toJSONString(article);
	}
	
}
