package com.bitarts.parsian.service.implementation;

/**
 * Created with IntelliJ IDEA.
 * User: f-haghighi
 * Date: 5/30/15
 * Time: 3:49 PM
 * To change this template use File | Settings | File Templates.
 */

import org.springframework.context.annotation.Primary;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.bitarts.common.action.BaseAction;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Primary
public class customUsernamePasswordAuthenticator extends UsernamePasswordAuthenticationFilter{
    private String daftarType;

    public String getDaftarType() {
        return daftarType;
    }

    public void setDaftarType(String daftarType) {
        this.daftarType = daftarType;
    }

    public customUsernamePasswordAuthenticator() {
        super();
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        /*
         * String username = super.obtainUsername(request); if (username != null && !StringUtils.isEmpty(username)) {
         * HttpSession session = request.getSession(); session.setAttribute("username", username); }
         */
        BaseAction b=new BaseAction();
        b.putToSession("daftar",getDaftarType());
        System.out.println("setSessionDaftar:"+getDaftarType());
        return super.attemptAuthentication(request, response);

    }
}
