package top.zerotop.blog.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import top.zerotop.blog.domain.Article;
import top.zerotop.blog.domain.User;

@Api(value = "用户请求接口")
@RestController
@RequestMapping(value = "/redis", produces = {MediaType.APPLICATION_JSON_VALUE})
public class RedisTestController {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @ApiOperation(value = "向redis中添加内容",
            notes = "向redis在添加对象")
    @PostMapping(value = "/add")
    @ResponseBody
    public String testRedisAdd(@ApiParam(name = "用户")
                               @RequestBody User user) {

//        if (user == null) {
            user = new User();
            user.setNickname("nick1135");
            user.setUsername("0top");
//        }
        System.out.println(user.toString());

        Article article = new Article();
        article.setId(2);
        article.setContent("sakfjsdkdsfjsdfkksd");
        System.out.println(article.toString());
        try {
            redisTemplate.opsForValue().set("val", user.toString());
            redisTemplate.opsForHash().put("user", user, article);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "success";
    }

    @ApiOperation(value = "向redis中添加内容",
            notes = "向redis在获取")
    @GetMapping(value = "/get")
    @ResponseBody
    public Article testRedisGet() {

        User user = new User();
        user.setNickname("nick1135");
        user.setUsername("0top");
        Article article = null;
        try {
            article = (Article) redisTemplate.opsForHash().get("user", user);
            System.out.println(article.toString());
            System.out.println(redisTemplate.opsForValue().get("val"));
        }catch (Exception e) {
            e.printStackTrace();
        }

        return article;
    }

}
