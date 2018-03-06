package com;

import com.entity.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.invoke.MethodHandles;

@WebServlet(urlPatterns = "/com.LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    @Value("${root.dir}")
    String root;


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        if (Main.context == null) {
            Main.initContext();
        }
        AnnotationConfigWebApplicationContext context = Main.context;

        String username = req.getParameter("usernameForm");
        String pass = req.getParameter("passForm");
        String passHash = DigestUtils.sha256Hex(pass);


        Boolean wrongUsername = true;

        File file = new File("C:\\Users\\User\\IdeaProjects\\nairis\\filemanagerparent\\filemanagement\\src\\main\\resources\\users.json");
        String jsonArrayString = FileUtils.readFileToString(file, "UTF-8");
        JSONParser parser = new JSONParser();
        JSONArray jsonArray = null;
        try {
            jsonArray = (JSONArray) parser.parse(jsonArrayString);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        for (int i = 0; i < jsonArray.size(); i++) {
            if (username.equals(((JSONObject) jsonArray.get(i)).get("username")) &&
                    passHash.equals(((JSONObject) jsonArray.get(i)).get("password_hash"))) {
                wrongUsername = false;

                User user=new User();
                user.setName((String)((JSONObject) jsonArray.get(i)).get("name"));
                user.setEmail((String)((JSONObject) jsonArray.get(i)).get("email"));
                user.setUsername(username);

                HttpSession session = req.getSession();
                session.setAttribute("isLoggedIn", "true");
                session.setAttribute("user'sName", ((JSONObject) jsonArray.get(i)).get("name"));
                session.setAttribute("username", username + File.separator);
                session.setAttribute("user",user);
                resp.sendRedirect("HomePage.jsp");

                logger.info("LoginServlet: username - "+username +" pass_hash - "+passHash);
            }
        }

        LoginServlet loginServlet = context.getBean("loginServlet",LoginServlet.class);


        if (wrongUsername) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("LoginPage.jsp");

            PrintWriter out = resp.getWriter();
            Point point=context.getBean("point",Point.class);

            out.println("<font color=red> " + point.getPointX() + "Wrong username " + "" + "and/or password" + loginServlet.root + " </font>");

            dispatcher.include(req, resp);
        }


    }
}
