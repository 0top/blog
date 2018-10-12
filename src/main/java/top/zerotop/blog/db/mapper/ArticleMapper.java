package top.zerotop.blog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import top.zerotop.blog.db.model.Article;

@Mapper
public interface ArticleMapper {
    int deleteByPrimaryKey(int id);

    int insertArticle(@Param("article") Article article);

    Article selectByArticleId(@Param("id") int id);
    
    List<Article> listArticle(@Param("startpage")int startpage, @Param("endpage")int endpage);

    int updateByArticleId(@Param("article") Article article);
}