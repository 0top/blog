package top.zerotop.blog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import top.zerotop.blog.entity.Article;

public interface ArticleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(Long id);
    
    List<Article> listArticle(@Param("pagenum")int pagenum, @Param("pagesize")int pagesize);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKeyWithBLOBs(Article record);

    int updateByPrimaryKey(Article record);
}