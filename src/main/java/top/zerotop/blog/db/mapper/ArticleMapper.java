package top.zerotop.blog.db.mapper;

import java.util.List;

import org.apache.ibatis.annotations.*;

import top.zerotop.blog.db.model.Article;

@Mapper
public interface ArticleMapper {

    @Insert("delete from article where id = #{id} ")
    int deleteArticleById(int id);

    @Insert("insert into " +
            "article(title, author, img_url, digest, content, category, gmt_create, gmt_modified)" +
            "values(#{title}, #{author}, #{imgUrl}, #{digest}, #{content}, #{category}, #{gmtCreate}, #{gmtModified})")
    int insertArticle(@Param("article") Article article);

    @Select("select * from article where id = #{id}")
    Article selectByArticleId(int id);


    @Select("<script>" +
            "select * from article " +
            "<if test='searchString != null or categorys != null'> where </if>" +
            "<if test='searchString != null'>title like #{searchString} </if>" +
            "<if test='categorys != null'>" +
            " and category in " +
            "<foreach item='item' index='index' collection='categorys' open='(' separator=',' close=')'>" +
            " #{item} " +
            "</foreach> " +
            "</if>" +
            "<if test='orderBy != null'> order by #{orderBy} </if>" +
            "limit #{startNum}, #{endNum} " +
            "</script>"
    )
    List<Article> queryArticle(@Param("orderBy") String orderBy,
                               @Param("searchString") String searchString,
                               @Param("categorys") String[] categorys,
                               @Param("startNum") int startNum,
                               @Param("endNum") int endNum);

    @Update("<script>" +
            "update article" +
            "<set>" +
            "<if test='title!=null'>title = #{title}, </if>" +
            "<if test='author!=null'>author = #{author}, </if>" +
            "<if test='imgUrl!=null'>img_url = #{imgUrl}, </if>" +
            "<if test='digest!=null'>digest = #{digest}, </if>" +
            "<if test='content!=null'>content = #{content}, </if>" +
            "gmt_modified = #{gmtModified}" +
            "</set>" +
            "</script>")
    int updateByArticleId(@Param("article") Article article);
}