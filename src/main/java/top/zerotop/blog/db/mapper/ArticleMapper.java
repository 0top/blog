package top.zerotop.blog.db.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import top.zerotop.blog.db.model.Article;

@Mapper
public interface ArticleMapper {

    int deleteArticleById(int id);

    int insertArticle(@Param("article") Article article);

    Article selectByArticleId( int id);
    
    List<Article> listArticle(@Param("startpage")int startpage, @Param("endpage")int endpage);

    int updateByArticleId(@Param("article") Article article);
}