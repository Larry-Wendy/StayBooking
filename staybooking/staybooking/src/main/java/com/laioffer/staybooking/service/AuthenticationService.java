package com.laioffer.staybooking.service;

import com.laioffer.staybooking.exception.UserNotExistException;
import com.laioffer.staybooking.model.UserRole;
import com.laioffer.staybooking.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import com.laioffer.staybooking.model.Token;
import com.laioffer.staybooking.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

@Service
public class AuthenticationService {
    private AuthenticationManager authenticationManager;
    private JwtUtil jwtUtil;

    @Autowired
    public AuthenticationService(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    public Token authenticate(User user, UserRole role) throws UserNotExistException {
        Authentication auth = null;
        try {
            auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
            );
        } catch (AuthenticationException exception) {
            throw new UserNotExistException("User Doesn't Exist");
        }

        if (auth == null || !auth.isAuthenticated() || !auth.getAuthorities().contains(new SimpleGrantedAuthority(role.name()))) {
            throw new UserNotExistException("User Doesn't Exist");
        }
        return new Token(jwtUtil.generateToken(user.getUsername()));
    }
}

// token存储在客户端，服务器端不需要存token的任何信息，服务器接受token会解密，知道你是谁（降低服务器端的存储压力，用户在线数量多的话比较好）
// local的内存就能解决token加密解析操作，不需要借助数据库，速度快
// session机制中session id需要用户端存储，服务端存储session的所有信息。如果需要记录50条以上登录相关的信息，token就会非常长，某些落后的user
// 可能handle不了（用户的环境是多样的），这种情况session会有优势
// 目前很多网站是session和token并用，token存储验证最基本的信息，其他额外信息在session中