package Avocado.main_project.Controllers;

import Avocado.main_project.Services.CSVService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RestController
@RequestMapping("/api/csv")
public class CSVController {

    @Autowired
    private CSVService csvService;

    @PostMapping("/import/students")
    public String importStudents(@RequestParam("file") MultipartFile file) {
        try {
            String filePath = saveUploadedFile(file);
            csvService.importStudents(filePath);
            return "courses";
        } catch (Exception e) {
            return "Error importing students: " + e.getMessage();
        }
    }

    // Endpoint to import teams
    @PostMapping("/import/teams")
    public String importTeams(@RequestParam("file") MultipartFile file) {
        try {
            String filePath = saveUploadedFile(file);
            csvService.importTeams(filePath);
            return "courses";
        } catch (Exception e) {
            return "Error importing teams: " + e.getMessage();
        }
    }

    private String saveUploadedFile(MultipartFile file) throws IOException {
        String tempDir = System.getProperty("java.io.tmpdir");
        String filePath = tempDir + File.separator + file.getOriginalFilename();
        File tempFile = new File(filePath);
        file.transferTo(tempFile);
        return filePath;
    }

    @GetMapping("/export/evaluations/{courseId}")
    public void exportEvaluations(HttpServletResponse response, @PathVariable Long courseId) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=evaluations.csv");

        Writer writer = response.getWriter();
        csvService.exportEvaluations(writer, courseId);

    }

}