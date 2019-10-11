package top.zerotop.blog.web.controller;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import top.zerotop.blog.dto.ArticleDTO;
import top.zerotop.blog.web.Request.ArticleRequest;
import top.zerotop.blog.web.condition.ArticleCondition;
import top.zerotop.blog.data.model.Article;
import top.zerotop.blog.service.ArticleService;
import top.zerotop.utils.Result;
import top.zerotop.exception.BlogException;

/**
 * @author 作者: zerotop
 * @createDate 创建时间: 2018年5月21日下午8:42:50
 */
@Api(value = "文章列表", description = "文章请求相应API")
@RestController
@RequestMapping(value = "/api/v1/article", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
public class ArticleController extends BaseController {
    @Autowired
    private ArticleService blogService;

    @ApiOperation(value = "获取单篇文章的内容", notes = "根据articleId获取文章详细内容")
    @GetMapping(value = "/get/{articleId}")
    public Result getArticleById(@ApiParam(value = "文章id")
                                 @PathVariable("articleId") String articleId) {
        Assert.notNull(articleId, "文章id不可为空");
        return Result.make(blogService.getArticleById(articleId));
    }

    //	@RequiresRoles("admin")
    @ApiOperation(value = "管理员添加文章", notes = "添加文章")
    @PostMapping(value = "/insert")
    public Result insertArticle(@ApiParam(value = "文章")
                                @RequestBody ArticleRequest articleRequest) throws BlogException {
        Assert.notNull(articleRequest, "文章不可为空");
        return Result.make(blogService.insertArticle(articleRequest));
    }

    //    @RequiresRoles("admin")
    @ApiOperation(value = "更新文章", notes = "更新文章")
    @PostMapping(value = "/update")
    public Result<Boolean> updateArticle(@ApiParam(value = "文章内容")
                                @RequestBody Article article) {
        Assert.notNull(article, "文章不可为空");
        return Result.make(blogService.updateArticleById(article) > 0);
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
