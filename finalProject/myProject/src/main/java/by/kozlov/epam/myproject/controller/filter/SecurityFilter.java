package by.kozlov.epam.myproject.controller.filter;

import by.kozlov.epam.myproject.entity.Role;
import by.kozlov.epam.myproject.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

public class SecurityFilter implements Filter {
    private static final Map<String, Set<Role>> permissions = new HashMap<>();

    static {
        Set<Role> all = new HashSet<>();
        all.addAll(Arrays.asList(Role.values()));
        Set<Role> admin = new HashSet<>();
        admin.add(Role.ADMIN);
        Set<Role> travelAgent = new HashSet<>();
        travelAgent.add(Role.TRAVEL_AGENT);
        Set<Role> customer = new HashSet<>();
        customer.add(Role.USER);

        permissions.put("/logout", all);
        permissions.put("/tourList.html", all);
        permissions.put("/about.html", all);
        permissions.put("/admin.html", admin);

        permissions.put("/user/removeSelect.html", all);
        permissions.put("/user/select.html", all);
        permissions.put("/user/selectTour.html", all);

        permissions.put("/request/list.html", travelAgent);
        permissions.put("/request/add.html",all);
        permissions.put("/request/delete.html",all);

        permissions.put("/user/profile.html", all);
        permissions.put("/tour/list.html", admin);
        permissions.put("/tour/save.html", admin);
        permissions.put("/tour/edit.html", admin);
        permissions.put("/tour/delete.html", admin);
        permissions.put("/user/list.html", admin);
        permissions.put("/user/edit.html", admin);
        permissions.put("/user/save.html", admin);
        permissions.put("/user/saveProfile.html", all);
        permissions.put("/user/delete.html", admin);

    }
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest)req;
        HttpServletResponse httpResp = (HttpServletResponse)resp;
        String url = httpReq.getRequestURI();

        System.out.println(url);
        String context = httpReq.getContextPath();
        int postfixIndex = url.lastIndexOf(".html");
        if (postfixIndex != -1){
            url = url.substring(context.length(), postfixIndex);
        }else {
            url = url.substring(context.length());
        }
        System.out.println("uri: "+url);
        Set<Role> roles = permissions.get(url);
        if (roles != null){
            HttpSession session = httpReq.getSession(false);
            if (session != null){
                User user = (User)session.getAttribute("currentUser");
                if (user != null && roles.contains(user.getRole())){
                    chain.doFilter(req,resp);
                    return;
                }
            }
        } else {
            chain.doFilter(req,resp);
            return;
        }
        httpResp.sendRedirect(context + "/userLoginServlet.html?message=Нет ДОСТУПА");
    }

    @Override
    public void destroy() {}
}
