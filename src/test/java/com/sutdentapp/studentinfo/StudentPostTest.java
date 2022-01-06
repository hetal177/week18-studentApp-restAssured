package com.sutdentapp.studentinfo;

import com.sutdentapp.StudentPojo;
import com.sutdentapp.testbase.TestBase;
import com.sutdentapp.utils.TestUtils;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class StudentPostTest extends TestBase {

    @Test
    public  void createStudent(){
        String Email="ramayan24"+ TestUtils.getRandomValue()+"@gmail.com";

        List<String>courseList= new ArrayList<>();
        courseList.add("Jira");
        courseList.add("Selenium");

        StudentPojo studentPojo=new StudentPojo();
        studentPojo.setFirstName("Ram");
        studentPojo.setLastName("Patel");
        studentPojo.setEmail(Email);
        studentPojo.setProgramme("Automation Testing");
        studentPojo.setCourses(courseList);
        Response response=given()
                .header("Content-Type","application/json")
                .body(studentPojo)
                .when()
                .post();
        response.then().statusCode(201);
        response.prettyPrint();

    }
}
