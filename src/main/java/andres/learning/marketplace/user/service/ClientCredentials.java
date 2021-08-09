package andres.learning.marketplace.user.service;

import andres.learning.marketplace.user.model.Client;

import javax.servlet.http.*;


public class ClientCredentials extends HttpServlet {
    /*I noticed that the pool have to be inside a Servlet
    When I try to create inside a  JAVA normal class, it does not work
    @Resource(name = "jdbc/users")
    DataSource connectionPool;*/


    public static Cookie clientCookieValidation(Client clientToVerify){
        Cookie validUser =null;
        if (!(clientToVerify == null)) {
            validUser = new Cookie("valid.user", "VALID");
            validUser.setMaxAge(600);  //It cookie last ten minutes
        }
        return validUser;
    }

    public static HttpSession clientSessionData(Client clientToVerify, HttpServletRequest request){
        HttpSession session = request.getSession(true);
        session.setAttribute("clientData", clientToVerify);
        return session;
    }


    public static boolean checkClientAuthentication(HttpServletRequest request) {
        Cookie[] requestCookies = request.getCookies();
        boolean validUser = false;
        if (!(requestCookies == null)) {
            for (Cookie cookie : requestCookies) {
                if (cookie.getName().equals("valid.user")) {
                    if (cookie.getValue().equals("VALID")) {
                        validUser = true;
                    }
                }
            }
        }
        return validUser;
    }

}
