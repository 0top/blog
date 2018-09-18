package top.zerotop.blog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import top.zerotop.blog.domain.Article;

public interface ArticleMapper {
    int deleteByPrimaryKey(int id);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(int id);
    
    List<Article> listArticle(@Param("startpage")int startpage, @Param("endpage")int endpage);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKeyWithBLOBs(Article record);

    int updateByPrimaryKey(Article record);
}