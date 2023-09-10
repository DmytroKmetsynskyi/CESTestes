package org.fernfog;

import com.moandjiezana.toml.Toml;
import org.apache.commons.io.IOUtils;
import spark.ModelAndView;
import spark.Session;
import spark.template.freemarker.FreeMarkerEngine;

import javax.servlet.MultipartConfigElement;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

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

            String[] files = new File("tests/").list();

            ArrayList<Object> arrayListofLists = new ArrayList<>();

            for(String i: files) {
                if (i != null) {
                    String path = "tests/" + i;

                    InputStream testFiles = new FileInputStream(path);

                    Toml tomlFile = new Toml().read(testFiles);

                    ArrayList<Object> arrayList = new ArrayList<>();

                    arrayList.add(tomlFile.getString("test.name"));
                    arrayList.add(tomlFile.getString("test.description"));
                    arrayList.add(tomlFile.getString("test.image"));
                    arrayList.add(i);

                    arrayListofLists.add(arrayList);
                }
            }

            model.put("arrayListofLists", arrayListofLists);

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
            try {
                InputStream inputStream = request.raw().getPart("file").getInputStream();

                String fileContent = IOUtils.toString(inputStream, "UTF-8");

                inputStream.close();

                Files.createDirectories(Paths.get("tests/"));

                try (FileOutputStream outputStream = new FileOutputStream("tests/" + new Toml().read(new ByteArrayInputStream(fileContent.getBytes("UTF-8"))).getString("test.name").trim().replace(" ", "_") + ".toml")) {
                    outputStream.write(fileContent.getBytes("UTF-8"));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            response.redirect("/uploadTest");

            return null;
        });

        get("/deleteTest", (request, response) -> {
            Session session = request.session(false);

            if (session != null && session.attribute("passwordFrom").equals(adminPassword)) {
                HashMap<String, Object> model = new HashMap<>();

                String[] files = new File("tests/").list();

                model.put("fileList", files);

                return new ModelAndView(model, "deleteTest.ftl");
            } else {
                response.redirect("/");
                return null;
            }
        }, new FreeMarkerEngine());

        post("/deleteTestFile", (req, res) -> {
            String[] filesToDelete = req.queryParamsValues("fileToDelete");
            if (filesToDelete != null) {
                for (String fileName : filesToDelete) {
                    File fileToDelete = new File("tests/" + fileName);
                    if (fileToDelete.exists() && fileToDelete.isFile()) {
                        fileToDelete.delete();
                    }
                }
            }
            res.redirect("/deleteTest");
            return null;
        });

        get("/viewTest/:file", (request, response) -> {
            HashMap<String, Object> model = new HashMap<>();

            if (request.params(":file") != null) {
                String path = "tests/" + request.params(":file");

                InputStream testFiles = new FileInputStream(path);

                Toml tomlFile = new Toml().read(testFiles);

                model.put("testName", tomlFile.getString("test.name"));
                model.put("testDescription", tomlFile.getString("test.description"));
                model.put("testImage", tomlFile.getString("test.image"));

                model.put("file", request.params(":file"));
            }

            return new ModelAndView(model, "viewTest.ftl");
        }, new FreeMarkerEngine());

        get("/quiz/:file", (request, response) -> {
            HashMap<String, Object> model = new HashMap<>();

            if (request.params(":file") != null) {
                String path = "tests/" + request.params(":file");

                InputStream testFiles = new FileInputStream(path);

                Toml tomlFile = new Toml().read(testFiles);

                ArrayList<Object> questions = new ArrayList<>();

                List<Toml> questions_ = tomlFile.getTables("question");
                for (Toml questionTable : questions_) {
                    String questionText = questionTable.getString("question");
                    String image = questionTable.getString("image");

                    String trueAnswer = questionTable.getString("true_answer");
                    List<String> falseAnswers = questionTable.getList("false_answer", Collections.emptyList());

                    List<String> allAnswers = new ArrayList<>(falseAnswers);
                    allAnswers.add(trueAnswer);

                    Collections.shuffle(allAnswers);

                    ArrayList<Object> quuestions = new ArrayList<>();
                    quuestions.add(questionText);
                    quuestions.add(image);
                    quuestions.add(questions_.indexOf(questionTable));
                    quuestions.add(allAnswers);

                    questions.add(quuestions);
                }

                model.put("questions", questions);
                model.put("fileName", request.params(":file"));
            }

            return new ModelAndView(model, "quiz.ftl");
        }, new FreeMarkerEngine());

        post("/results/:file", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();

            List<String> selectedAnswers = req.queryParams().stream()
                    .filter(param -> param.startsWith("flexRadio_"))
                    .map(req::queryParams)
                    .collect(Collectors.toList());

            String path = "tests/" + req.params(":file");
            InputStream testFiles = new FileInputStream(path);
            Toml tomlFile = new Toml().read(testFiles);
            List<Toml> questions_ = tomlFile.getTables("question");

            int totalQuestions = questions_.size();
            int trueAnswers = 0;

            if (req.params(":file") != null) {
                for (Toml questionTable : questions_) {
                    String trueAnswer = questionTable.getString("true_answer");

                    if (selectedAnswers.contains(trueAnswer)) {
                        trueAnswers++;
                    }
                }
            }

            int falseAnswers = totalQuestions - trueAnswers;

            model.put("totalQuestions", totalQuestions);
            model.put("trueAnswers", trueAnswers);
            model.put("falseAnswers", falseAnswers);

            return new ModelAndView(model, "results.ftl");
        }, new FreeMarkerEngine());
    }
}