package com.sutdentapp.studentinfo;

import com.sutdentapp.StudentPojo;
import com.sutdentapp.testbase.TestBase;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class StudentPutTest extends TestBase {
    @Test
//HomeWork
    public void updateStudentInformationByPUT() {
        List<String> courseList = new ArrayList<>();
        courseList.add("Postman");
        courseList.add("Mobile Testing");

        StudentPojo studentPojo = new StudentPojo();
        studentPojo.setFirstName("Sitaram");
        studentPojo.setLastName("ram");
        studentPojo.setEmail("ramayan1242@yahoo.com");
        studentPojo.setProgramme("Automation");
        studentPojo.setCourses(courseList);
        Response response = given()
                .pathParam("id", 102)
                .contentType("application.json")
              //  .header("Content-Type", "application/json")
                .body(studentPojo)
                .when()
                .put("/{id}");
        response.then().statusCode(200);
        response.prettyPrint();


    }
}
