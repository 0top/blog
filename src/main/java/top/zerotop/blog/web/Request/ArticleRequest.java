package top.zerotop.blog.web.Request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by:zerotop  date:2019/7/10
 */
@ApiModel(value = "文章Model")
public class ArticleRequest {
    @ApiModelProperty(value = "文章标题", position = 1)
    private String title;

    @ApiModelProperty(value = "作者", position = 2)
    private String author;

    @ApiModelProperty(value = "文章图片", position = 3)
    private String imgUrl;

    @ApiModelProperty(value = "摘要", position = 4)
    private String digest;

    @ApiModelProperty(value = "内容", position = 5)
    private String content;

    @ApiModelProperty(value = "类别, 选填", position = 6)
    private String category;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
