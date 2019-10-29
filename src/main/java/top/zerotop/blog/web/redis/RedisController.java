package top.zerotop.blog.web.redis;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import top.zerotop.blog.data.model.Article;
import top.zerotop.blog.data.model.User;
import top.zerotop.utils.EncryptUtils;
import top.zerotop.utils.JsonUtils;

@Api(value = "redis用户请求接口", description = "redis请求接口")
@RestController
@RequestMapping(value = "/test/api/redis", produces = {MediaType.APPLICATION_JSON_VALUE})
public class RedisController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @ApiOperation(value = "向redis中添加内容",
            notes = "向redis在添加对象")
    @PostMapping(value = "/add")
    @ResponseBody
    public String testRedisAdd(@ApiParam(name = "用户")
                               @RequestBody User user) {

        if (user == null) {
            user = new User();
            user.setNickname("nick1135");
            user.setUsername("0top");
        }

        Article article = new Article();
        article.setArticleId(EncryptUtils.getUuid());
        article.setContent("content");
        try {
            stringRedisTemplate.opsForValue().set("val", user.toString());
            stringRedisTemplate.opsForHash().put("user", user.toString(), JsonUtils.toJson(article));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "success";
    }

    @ApiOperation(value = "向redis中添加内容", notes = "向redis在获取")
    @GetMapping(value = "/get")
    @ResponseBody
    public Article testRedisGet() {

        User user = new User();
        user.setNickname("nickname111");
        user.setUsername("username111");
        Article article = null;
        try {
//            article = (Article) stringRedisTemplate.opsForHash().get("user", user.toString());
//            System.out.println(article.toString());
            System.out.println(stringRedisTemplate.opsForValue().get("val"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return article;
    }

}
