package top.zerotop.blog.data.mapper;

import java.util.List;

import org.apache.ibatis.annotations.*;

import top.zerotop.blog.data.model.Article;

@Mapper
public interface ArticleMapper {

    @Insert("delete from article where id = #{id} ")
    int deleteArticleById(int id);

    @Insert("insert into " +
            "article(title, author, img_url, digest, content, category, gmt_create, gmt_modified)" +
            "values(#{title}, #{author}, #{imgUrl}, #{digest}, #{content}, #{category}, #{gmtCreate}, #{gmtModified})")
    int insertArticle(Article article);

    @Select("select * from article where article_id = #{articleId}")
    Article selectByArticleId(String articleId);


    @Select("<script>" +
            "select * from article " +
            "<if test='orderBy != null'> order by #{orderBy} </if>" +
            "</script>"
    )
    List<Article> queryArticle(@Param("orderBy") String orderBy);

    @Update("<script>" +
            "update article" +
            "<set>" +
            "<if test='title!=null'>title = #{title}, </if>" +
            "<if test='author!=null'>author = #{author}, </if>" +
            "<if test='imgUrl!=null'>img_url = #{imgUrl}, </if>" +
            "<if test='digest!=null'>digest = #{digest}, </if>" +
            "<if test='content!=null'>content = #{content}, </if>" +
            "gmt_modified = #{gmtModified}" +
            "</set> " +
            "where article_id = #{articleId} " +
            "</script>")
    int updateByArticleId(@Param("article") Article article);
}