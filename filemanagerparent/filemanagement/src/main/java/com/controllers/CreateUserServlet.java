package com.controllers;

import com.entity.User;
import com.entity.UserDTO;
import com.service.UserService;
import com.service.impl.UserServiceImpl;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.invoke.MethodHandles;

@WebServlet(urlPatterns = "/com.controllers.CreateUserServlet")
public class CreateUserServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        logger.info("CreateUserServlet - DoPost ethod");

        String name = req.getParameter("nameRegPage");
        String email = req.getParameter("emailRegPage");
        String username = req.getParameter("usernameRegPage");
        String pass = req.getParameter("passRegPage");
        String password_hash = DigestUtils.sha256Hex(pass);

        User user = new User(null, name, email, username, password_hash);
        UserDTO userDTO = new UserDTO();
        userDTO = userDTO.mapFromEntity(user);

        UserService userService = new UserServiceImpl();
        Long id = userService.createUser(userDTO);

        RequestDispatcher dispatcher;
        PrintWriter out = resp.getWriter();

        if (id>0){
            dispatcher = req.getRequestDispatcher("LoginPage.jsp");
            out.println("<p> Dear " + name + " Registration is done successfully" +
                    "<br> new folder <b>"+username+"</b> is created"+
                    "<br> now You can Login </p>");
        }
        else {
            dispatcher = req.getRequestDispatcher("RegisterPage.jsp");
            out.println("<p> Dear " + name + "</p>" +
                    "<font color=red> Username and/or Email already exist </font>"+
                    "<br> <p> Try to register with another Username and/or Password <p>");
        }
        dispatcher.include(req,resp);





    }
}