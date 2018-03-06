package com.repository.impl;

import com.entity.FolderPath;
import com.entity.User;
import com.repository.UserRepository;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.lang.invoke.MethodHandles;

public class UserJsonRepositoryImpl implements UserRepository {

    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());


    private File getJsonFile() {
        return new File("C:\\Users\\User\\IdeaProjects\\nairis2\\filemanagerparent\\filemanagement\\src\\main\\resources\\users.json");
    }

    private JSONArray getJsonArray() {
        String jsonString = null;
        try {
            jsonString = FileUtils.readFileToString(getJsonFile(), "UTF-8");
        } catch (IOException e) {
        }

        JSONParser parser = new JSONParser();
        JSONArray jsonArray = null;

        try {
            jsonArray = (JSONArray) parser.parse(jsonString);
        } catch (ParseException e) {
        }

        return jsonArray;
    }

    public Long createUser(User user) {
        if (read(user) == null) {
            JSONArray jsonArray = getJsonArray();

            int newUserId = jsonArray.size() + 1;

            JSONObject newUser = new JSONObject();
            newUser.put("id", newUserId);
            newUser.put("name", user.getName());
            newUser.put("email", user.getEmail());
            newUser.put("username", user.getUsername());
            newUser.put("password_hash", user.getPassword_hash());

            jsonArray.add(newUser);

            logger.info("created new JsonArray: "+jsonArray.toJSONString());

            try {
                FileUtils.writeStringToFile(getJsonFile(), jsonArray.toJSONString(), "UTF-8");
            } catch (IOException e) {

            }
            return (long) newUserId;
        } else {
            return (long) 0;
        }

    }

    public User read(User user) {
        Boolean userExist = false;
        JSONArray jsonArray = getJsonArray();

        for (Object obj : jsonArray) {
            obj = (JSONObject) obj;
            if (user.getUsername().equals(((JSONObject) obj).get("username")) ||
                    user.getEmail().equals(((JSONObject) obj).get("email"))) {
                userExist = true;
                break;
            }
        }
        if (userExist) {
            return user;
        } else {
            return null;
        }
    }

    public User update(User user) {
        return null;
    }

    public User delete(User user) {
        return null;
    }
}
