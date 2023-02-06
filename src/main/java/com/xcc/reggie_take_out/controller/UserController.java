package com.xcc.reggie_take_out.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xcc.reggie_take_out.common.R;
import com.xcc.reggie_take_out.entity.User;
import com.xcc.reggie_take_out.service.UserService;
import com.xcc.reggie_take_out.utils.ValidateCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Charles
 * @version 1.0
 * @className UserController
 */

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping("/sendMsg")
    public R<String> sendMsg(@RequestBody User user, HttpSession session) {
        //获取手机号
        String phone = user.getPhone();
        if (StringUtils.isNotEmpty(phone)) {
            //随机生成的4为验证码
            Integer integerCode = ValidateCodeUtils.generateValidateCode(4);
            String code = integerCode.toString();

            log.info("code={}", code);
            //调用阿里云提供的短信服务api完成发送短信  这里个人用户申请不了阿里云短信服务的签名，所以这里在后台输出了
            //SMSUtils.sendMessage("","","","");

            //把验证码存起来  这里使用session来存放验证码，当然也可以存到redis
            //session.setAttribute(phone,code);

            //需要将生成的验证码到Redis中，并且设置有效期为5分钟
            redisTemplate.opsForValue().set(phone, code, 5, TimeUnit.MINUTES);


            return R.success("手机验证码发送成功");
        }

        return R.error("手机验证码发送失败");
    }

    /**
     * 移动端用户登录
     *
     * @param map
     * @param session
     * @return
     */
    @PostMapping("/login")
    public R<User> login(@RequestBody Map map, HttpSession session) {
        //log.info(map.toString());

        //获取手机号
        String phone = map.get("phone").toString();

        //获取验证码
        String code = map.get("code").toString();

        //从Session中获取保存的验证码
        //Object codeInSession = session.getAttribute(phone);
        Object codeInSession = redisTemplate.opsForValue().get(phone);

        //进行验证码的比对（页面提交的验证码和Session中保存的验证码比对）
        if (codeInSession != null && codeInSession.equals(code)) {
            //如果能够比对成功，说明登录成功

            LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(User::getPhone, phone);
            //根据用户的手机号去用户表获取用户
            User user = userService.getOne(queryWrapper);
            if (user == null) {
                //判断当前手机号对应的用户是否为新用户，如果是新用户就自动完成注册
                user = new User();
                user.setPhone(phone);
                user.setStatus(1); //可设置也可不设置，因为数据库我们设置了默认值
                //注册新用户
                userService.save(user);
            }
            //这一行容易漏。。保存用户登录状态
            //在session中保存用户的登录状态,这样才过滤器的时候就不会被拦截了
            session.setAttribute("user", user.getId());

            //如果登录成功，删除验证码
            redisTemplate.delete(phone);

            return R.success(user);
        }
        return R.error("登录失败");
    }
}
