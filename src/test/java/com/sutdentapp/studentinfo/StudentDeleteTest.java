package com.sutdentapp.studentinfo;

import com.sutdentapp.testbase.TestBase;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class StudentDeleteTest extends TestBase {
    @Test
    public void deleteStudentInformationByDelete() {
       Response response =  given()
                .pathParam("id", 10)
                .when()
                .delete("/{id}");
          response.then().statusCode(204);
        response.prettyPrint();


    }
    }

