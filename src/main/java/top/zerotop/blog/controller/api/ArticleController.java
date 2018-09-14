package top.zerotop.blog.controller.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSON;

import top.zerotop.blog.controller.BaseController;
import top.zerotop.blog.domain.Article;
import top.zerotop.blog.serivce.BlogService;
import top.zerotop.blog.util.PageConstrant;
import top.zerotop.blog.util.ReMap;
import top.zerotop.blog.util.ReqJson;

/**
 *@author 作者: zerotop
 *@createDate 创建时间: 2018年5月21日下午8:42:50
 */
@Api(value = "文章列表")
@RestController
@RequestMapping(value = "/article", produces = "application/json;charset=utf-8")
public class ArticleController extends BaseController {
	
	@Autowired
	private BlogService blogService;

	@ApiOperation(value = "获取单篇文章的内容",
					notes = "根据id获取文章内容")
	@GetMapping(value = "/get/{id}", produces="application/json;charset=utf-8")
	public @ResponseBody Article getArticleById(@PathVariable("id")long id){
		
		return blogService.getArticleById(id);
	}

	@ApiOperation(value = "管理员添加文章",
					notes = "添加文章")
//	@RequiresRoles("admin")
	@RequestMapping(value = "/insert", produces="application/json;charset=utf-8")
	public @ResponseBody String insertArticle(HttpServletRequest req) throws Exception{
	
		String json = ReqJson.Json(req);
		Article article = JSON.parseObject(json, Article.class);
		
		long id =  blogService.insertArticle(article);
		article.setId(id);
		
		return JSON.toJSONString(article);
	}

	@ApiOperation(value = "更新文章",
			notes = "更新文章")
	@RequiresRoles("admin")
	@RequestMapping(value = "/update", produces="application/json;charset=utf-8")
	public @ResponseBody String updateArticle(@RequestBody Article article){

//		Article article = JSON.parseObject(json, Article.class);
		
//		blogService.updateArticleSelective(article);

		System.out.println(article.toString());
		
		return ReMap.ResultMap(0, "更新成功", null);
	}
	
	/**
	 * 获取文章列表
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/list", produces="application/json;charset=utf-8")
	public @ResponseBody String listArticle(HttpServletRequest req){
	
		int pagenum = Integer.parseInt(req.getParameter("pagenum"));
		
		List<Article> articleList = blogService.listArticle(pagenum, PageConstrant.pagesize);
		
		return JSON.toJSONString(articleList);
	}
}
