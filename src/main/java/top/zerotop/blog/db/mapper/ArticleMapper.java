package top.zerotop.blog.db.mapper;

import java.util.Collection;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import org.apache.ibatis.annotations.Select;
import top.zerotop.blog.db.model.Article;

@Mapper
public interface ArticleMapper {

    int deleteArticleById(int id);

    int insertArticle(@Param("article") Article article);

    Article selectByArticleId(int id);


    @Select("<script>" +
            "select * from article " +
            "<if test=\"searchString != null or categorys != null \"> where </if>" +
            "<if test=\"searchString != null\">title like #{searchString} </if>" +
            "<if test=\"categorys != null \">" +
            " and category in " +
            "<foreach item=\"item\" index=\"index\" collection=\"categorys\" open=\"(\" separator=\",\" close=\")\">" +
            " #{item} " +
            "</foreach> " +
            "</if>" +
            "<if test=\"orderBy != null \"> order by #{orderBy} </if>" +
            "limit #{startNum}, #{endNum} " +
            "</script>"
    )
    List<Article> queryArticle(@Param("orderBy") String orderBy,
                               @Param("searchString") String searchString,
                               @Param("categorys") String[] categorys,
                               @Param("startNum") int startNum,
                               @Param("endNum") int endNum
    );

    int updateByArticleId(@Param("article") Article article);
}