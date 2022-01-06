package com.sutdentapp.studentinfo;

import com.sutdentapp.testbase.TestBase;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class StudentGetTest extends TestBase {
    @Test
    public void getAllStudentInfo() {
        Response response = given()
                .when()
                .get("/list");
        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Test
    public void getSingleStudentInfo() {
        Response response = given()
                .pathParam("id", "2")
                .when()
                .get("/{id}");
        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Test
    public void searchStudentWithParameter() {
        HashMap<String, Object> qparams = new HashMap<>();
        qparams.put("programme", "Financial Analysis");
        qparams.put("limit", 2);
        Response response = given()
                .queryParams(qparams)
                .when()
                .get("/list");
        response.then().statusCode(200);
        response.prettyPrint();
    }

}
