package com.sou.College.controller;

//import com.sou.College.College;
//import com.sou.College.service.CollegeService;
import com.sou.College.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping("/sender")
public class CollegeController {

    @Autowired
    private RestTemplate restTemplate;
    //private CollegeService collegeService;

    @GetMapping("/send")
    public ResponseEntity<String> sendRequest() {
        String receiverUrl = "http://localhost:8080/webapp/students"; // URL of the receiver application
        ResponseEntity<Map> response = restTemplate.getForEntity(receiverUrl, Map.class);
        return ResponseEntity.ok("Response from receiver: " + response.getBody());
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<String> getStudentById(@PathVariable Long id) {
        String receiverUrl = "http://localhost:8080/webapp/students/" + id;
        ResponseEntity<String> response = restTemplate.getForEntity(receiverUrl, String.class);
        return ResponseEntity.ok("Response from receiver: " + response.getBody());
    }

    @PostMapping
    public ResponseEntity<?> createStudent(@RequestBody Student student) {
        String receiverUrl = "http://localhost:8080/webapp/students";
        ResponseEntity<String> response = restTemplate.postForEntity(receiverUrl, student, String.class);
        return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
    }

    @PatchMapping("students/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable String id, @RequestBody Student student) {
        String receiverUrl = "http://localhost:8080/webapp/students/" + id;
        ResponseEntity<String> response = restTemplate.postForEntity(receiverUrl, student, String.class);

//        String response = restTemplate.patchForObject(receiverUrl, studentDetails, String.class);
        return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable long id) {
        String receiverUrl = "http://localhost:8080/webapp/students/" + id;
        restTemplate.delete(receiverUrl);
        return new ResponseEntity<>("Student deleted successfully!!", HttpStatus.OK);
    }
}


