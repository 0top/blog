package top.zerotop.blog.web.controller;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import top.zerotop.blog.domain.ArticleDTO;
import top.zerotop.blog.web.condition.ArticleCondition;
import top.zerotop.blog.data.model.Article;
import top.zerotop.blog.service.ArticleService;
import top.zerotop.utils.Result;
import top.zerotop.exception.BlogException;

/**
 * @author 作者: zerotop
 * @createDate 创建时间: 2018年5月21日下午8:42:50
 */
@Api(value = "文章列表")
@RestController
@RequestMapping(value = "/api/v1/article", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
public class ArticleController extends BaseController {

    @Autowired
    private ArticleService blogService;

    @ApiOperation(value = "获取单篇文章的内容",
            notes = "根据id获取文章内容")
    @GetMapping(value = "/get/{id}")
    public Result getArticleById(@ApiParam(value = "文章id")
                                 @PathVariable("id") Integer id) {
        Assert.notNull(id, "id不可为空");
        return Result.make(blogService.getArticleById(id));
    }

    //	@RequiresRoles("admin")
    @ApiOperation(value = "管理员添加文章", notes = "添加文章")
    @PostMapping(value = "/insert")
    public Result insertArticle(@ApiParam(value = "文章")
                                @RequestBody ArticleDTO articleDTO) throws BlogException {
        Assert.notNull(articleDTO, "文章不可为空");
        int id = blogService.insertArticle(articleDTO);
        articleDTO.setId(id);

        return Result.make(articleDTO);
    }

    //    @RequiresRoles("admin")
    @ApiOperation(value = "更新文章", notes = "更新文章")
    @PostMapping(value = "/update")
    public Result updateArticle(@ApiParam(value = "文章内容")
                                @RequestBody Article article) {
        Assert.notNull(article, "文章不可为空");
        blogService.updateArticleById(article);
        return Result.SUCCESS;
    }

    @ApiOperation(value = "获取文章列表", notes = "分页查询文章列表")
    @PostMapping(value = "/query")
    public Result queryArticle(@ApiParam(value = "文章查询条件")
                               @RequestBody ArticleCondition articleCondition) throws BlogException {
        Assert.notNull(articleCondition, "查询条件不可为空");
        List<Article> articles = blogService.queryArticle(articleCondition);
        return Result.make(articles);
    }
}
