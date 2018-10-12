package top.zerotop.blog.controller.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSON;

import top.zerotop.blog.controller.BaseController;
import top.zerotop.blog.db.model.Article;
import top.zerotop.blog.service.BlogService;
import top.zerotop.blog.util.PageConstrant;
import top.zerotop.blog.util.Result;
import top.zerotop.exception.BlogException;

/**
 * @author 作者: zerotop
 * @createDate 创建时间: 2018年5月21日下午8:42:50
 */
@Api(value = "文章列表")
@RestController
@RequestMapping(value = "/article", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
public class ArticleController extends BaseController {

    @Autowired
    private BlogService blogService;

    @ApiOperation(value = "获取单篇文章的内容",
            notes = "根据id获取文章内容")
    @GetMapping(value = "/get/{id}")
    public Result getArticleById(@ApiParam(value = "文章id")
                                  @PathVariable("id") int id) {
        System.out.println("get id:"+id);
        return new Result(blogService.getArticleById(id));
    }

    @ApiOperation(value = "管理员添加文章",
            notes = "添加文章")
//	@RequiresRoles("admin")
    @PostMapping(value = "/insert")
    public Result insertArticle(HttpServletRequest req,
                                @ApiParam(value = "文章")
                                @RequestBody Article article) throws BlogException {

        int id = blogService.insertArticle(article);
        article.setId(id);

        return new Result("获取文章成功",article);
    }

    @ApiOperation(value = "更新文章",
            notes = "更新文章")
//    @RequiresRoles("admin")
    @PostMapping(value = "/update")
    public Result updateArticle(@ApiParam(value = "文章内容")
                                @RequestBody Article article) {
        blogService.updateByArticleId(article);
        return new Result("更新成功");
    }

    @ApiOperation(value = "获取文章列表",
            notes = "分页查询文章列表")
    @GetMapping(value = "/list")
    public Result listArticle(HttpServletRequest req,
                              @ApiParam(value = "pagenum")
                              @RequestParam("pagenum") int pagenum) {
        return new Result("获取文章列表",blogService.listArticle(pagenum, PageConstrant.pagesize));
    }
}
