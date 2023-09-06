package org.fernfog;

import com.moandjiezana.toml.Toml;
import spark.ModelAndView;
import spark.Request;
import spark.Session;
import spark.template.freemarker.FreeMarkerEngine;

import javax.servlet.MultipartConfigElement;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import static spark.Spark.*;

public class Main {
    static InputStream adminPasswordFile = null;
    public static void main(String[] args) {

        // getting the admin password from toml file
        try {
            adminPasswordFile = new FileInputStream(args[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String adminPassword = new Toml().read(adminPasswordFile).getString("site.adminPassword");

        // multipart config for servlet
        long maxFileSize = 10000000;
        long maxRequestSize = 10000000;
        int fileSizeThreshold = 1024;

        MultipartConfigElement multipartConfig = new MultipartConfigElement(
                "upload", maxFileSize, maxRequestSize, fileSizeThreshold
        );

        staticFiles.externalLocation("upload");

        before((request, response) -> {
            request.raw().setAttribute("org.eclipse.jetty.multipartConfig", multipartConfig);
        });




        get("/", (request, response) -> {
            HashMap<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "index.ftl");
        }, new FreeMarkerEngine());


        get("/adminLogin", (request, response) -> {
            HashMap<String, Object> model = new HashMap<>();

            Session session = request.session(false);

            if (session != null && session.attribute("passwordFrom").equals(adminPassword)) {
                response.redirect("/admin");
                return null;
            } else {
                return new ModelAndView(model, "adminLogin.ftl");
            }
        }, new FreeMarkerEngine());

        post("/adminLogin", (request, response) -> {
            String passwordFrom = request.queryParams("passwordInput");

            if(passwordFrom.trim().equals(adminPassword)) {
                Session session = request.session(true);
                session.attribute("passwordFrom", passwordFrom);

                response.redirect("/admin");

                return null;
            } else {
                response.redirect("/adminLogin");

                return null;
            }
        });

        get("/admin", (request, response) -> {
            Session session = request.session(false);

            if (session != null && session.attribute("passwordFrom").equals(adminPassword)) {
                HashMap<String, Object> model = new HashMap<>();
                return new ModelAndView(model, "admin.ftl");
            } else {
                response.redirect("/");
                return null;
            }
        }, new FreeMarkerEngine());

        get("/uploadTest", (request, response) -> {
            Session session = request.session(false);

            if (session != null && session.attribute("passwordFrom").equals(adminPassword)) {
                HashMap<String, Object> model = new HashMap<>();
                return new ModelAndView(model, "uploadTest.ftl");
            } else {
                response.redirect("/");
                return null;
            }
        }, new FreeMarkerEngine());

        post("/uploadTestFile", (request, response) -> {
            Toml toml = new Toml().read(request.raw().getPart("file").getInputStream());

            System.out.println(toml.getString("botek.botekName"));

            response.redirect("/uploadTest");

            return null;
        });
    }
}