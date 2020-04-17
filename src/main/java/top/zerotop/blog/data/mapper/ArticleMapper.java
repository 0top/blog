package top.zerotop.blog.data.mapper;

import java.util.List;

import org.apache.ibatis.annotations.*;

import top.zerotop.blog.data.model.Article;

@Mapper
public interface ArticleMapper {
    @Update("UPDATE `article` SET `del_flag`='1' WHERE `article_id`=#{articleId} ")
    int deleteArticleById(@Param("articleId") String articleId);

    @Insert("INSERT INTO " +
            "`article`(title, author, img_url, digest, content, category, gmt_create, gmt_modified) " +
            "values(#{title}, #{author}, #{imgUrl}, #{digest}, #{content}, #{category}, #{gmtCreate}, #{gmtModified})")
    int insertArticle(Article article);

    @Select("SELECT * FROM `article` WHERE `article_id`=#{articleId}")
    Article selectByArticleId(@Param("articleId") String articleId);

    @Select("<SCRIPT>" +
            "SELECT * FROM `article` WHERE `del_flag`='0' " +
            "<if test='orderBy!=null'> order by #{orderBy} </if>" +
            "</SCRIPT>")
    List<Article> findAllArticle(@Param("orderBy") String orderBy);

    @Update("<SCRIPT>" +
            "UPDATE `article` " +
            "<set>" +
            "<if test='title!=null'>title=#{title}, </if>" +
            "<if test='author!=null'>author=#{author}, </if>" +
            "<if test='imgUrl!=null'>img_url=#{imgUrl}, </if>" +
            "<if test='digest!=null'>digest=#{digest}, </if>" +
            "<if test='content!=null'>content=#{content}, </if>" +
            "gmt_modified=#{gmtModified} " +
            "</SET> " +
            "WHERE `article_id` = #{articleId} " +
            "</SCRIPT>")
    int updateByArticleId(@Param("article") Article article);
}