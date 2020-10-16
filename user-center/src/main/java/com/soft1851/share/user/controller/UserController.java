package com.soft1851.share.user.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.soft1851.share.user.dao.BonusEventLogMapper;
import com.soft1851.share.user.domain.dto.*;
import com.soft1851.share.user.domain.entity.User;
import com.soft1851.share.user.service.UserService;
import com.soft1851.share.user.util.JwtOperator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;
/**
 * 描述:
 *
 * @author：Guorc
 * @create 2020-09-27 11:51
 */
@RestController
@RequestMapping(value = "/users")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class UserController {
    private final RestTemplate restTemplate;
    private final UserService userService;
    private final WxMaService wxMaService;
    private final JwtOperator jwtOperator;
    private final BonusEventLogMapper bonusEventLogMapper;

    @PostMapping(value = "/login")
    public LoginResDTO login(@RequestBody LoginDTO loginDTO) throws WxErrorException {
        String openId;
        if (loginDTO.getLoginCode() != null){
            //微信服务端校验是否已经登录的结果
            WxMaJscode2SessionResult result = this.wxMaService.getUserService()
                    .getSessionInfo(loginDTO.getLoginCode());
            log.info(result.toString());
            //微信的openId，用户在微信这边的唯一标识
            openId = result.getOpenid();
        }else {
            openId = loginDTO.getOpenId();
        }
        //看用户是否注册，如果没有就注册，如果已经注册就返回信息
        User user = userService.login(loginDTO,openId);
        //颁发token
        Map<String,Object> userInfo = new HashMap<>(3);
        userInfo.put("id",user.getId());
        userInfo.put("wxNickname",user.getWxNickname());
        userInfo.put("role",user.getRoles());
        String token = jwtOperator.generateToken(userInfo);
        log.info("{}登录成功，生成的token = {}，有效期到： {}",user.getWxNickname(),token,jwtOperator.getExpirationTime());
        ResponseDTO responseDTO = this.userService.checkIsSign(UserSignInDTO.builder().userId(user.getId()).build());
        int isUserSignin = 0;
        if (responseDTO.getCode()=="200"){
            isUserSignin = 0;
        }else {
            isUserSignin = 1;
        }
        return LoginResDTO.builder()
                .user(UserRespDTO.builder()
                      .id(user.getId())
                        .wxNickname(user.getWxNickname())
                       .avatarUrl(user.getAvatarUrl())
                      .bonus(user.getBonus())
                       .build())
                .token(JwtTokenRespDTO.builder()
                .token(token)
                .expirationTime(jwtOperator.getExpirationTime().getTime())
                .build())
                .isUserSignin(isUserSignin)
                .build();
    }
//    @PostMapping(value = "wxLogin")
//    public LoginResDTO codeAuth(@RequestBody WxLoginDTO wxLoginDTO) throws WxErrorException {
////        //自己请求openId
////        String appId = "wx05dcf498882dad7a";
////        String secret = "836b3a0d3f0e28e21c64f9a4851ff38b";
////        String url = "http://api.weixin.qq.com/sns/jscode2session?appid=%s&secret%s&js_code=%s&grant_type=authorization_code";
////        String formatUrl = String.format(url,appId,secret,wxLoginDTO.getWxCode());
////        String result = restTemplate.getForObject(formatUrl,String.class);
//        //通过第三方SDK获得openId
//        System.out.println(wxLoginDTO.getOpenId());
//        LoginDTO loginDTO = LoginDTO.builder()
//                .wxId(wxLoginDTO.getOpenId())
//                .wxNickName(wxLoginDTO.getWxNickname())
//                .avatar(wxLoginDTO.getAvatarUrl())
//                .build();
//        User user = userService.login(loginDTO);
//        return LoginResDTO.builder()
//                .user(user)
//                .token("1122")
//                .build();
//    }

    @GetMapping(value = "/{id}")
    public ResponseDTO findUserById(@PathVariable Integer id) {
        log.info("我被请求了...");
        return this.userService.findById(id);
    }

    @PostMapping(value = "/bonus")
    public User updateBonus(@RequestBody UserAddBonusMsgDTO userAddBonusMsgDTO) {
        return userService.updateBonus(userAddBonusMsgDTO);
    }
    @PostMapping(value = "/reduceBonus")
    public User reduceBonus(@RequestBody UserAddBonusMsgDTO userAddBonusMsgDTO){
        return userService.reduceBonus(userAddBonusMsgDTO);
    }
    @PostMapping(value = "/signin")
    public ResponseDTO signIn(@RequestBody UserSignInDTO userSignInDTO){
        System.out.println(userSignInDTO.getUserId());
        System.out.println("111");
        return userService.signIn(userSignInDTO);
    }
    @PostMapping(value = "/mylog")
    public ResponseDTO myLog(@RequestBody UserDTO userDTO){
        return userService.getLog(userDTO);
    }
}
