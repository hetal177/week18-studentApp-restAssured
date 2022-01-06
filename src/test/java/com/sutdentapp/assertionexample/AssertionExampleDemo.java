package com.sutdentapp.assertionexample;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class AssertionExampleDemo {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/products")
                .then().statusCode(200);

    }

    //1>>verify that the products limit= 10
    @Test
    public void test001() {
        response.body("limit", equalTo(10));
    }

    //2>>verify that products total is 51962
    @Test
    public void test002() {
        response.body("total", equalTo(51965));
    }

    // 3>> Check the Name 'Duracell - AA Batteries (8-Pack)' is available in List of product's name
    //?? data.name>>> should data[1].name or data[*].name ... * sign work for json path but not in IJ

    @Test
    public void test003() {
        response.body("data.name", hasItem("Duracell - AA Batteries (8-Pack)"));
    }

    //4>>  Check Multiple Names (Energizer - MAX Batteries AA (4-Pack), Duracell - 9V Batteries (2-Pack)) are available in list of product's name
    @Test
    public void test004() {
        response.body("data.name", hasItems("Energizer - MAX Batteries AA (4-Pack)", "Duracell - 9V Batteries (2-Pack)"));
    }

    // 5>> Verify the 'name' field inside first categories map for the first data (Checking Values inside Map using hasKey(entityType))
    @Test
    public void test005() {
        response.body("data[0].categories[0]", hasKey("name"));
    }
    // 6) Check entry 'manufacturer = Energizer' is inside map of product name is 'Energizer - N Cell E90 Batteries (2-Pack)'
    @Test
    public void test006(){
        response.body("data.findAll{it.name=='Energizer - N Cell E90 Batteries (2-Pack)'}",hasItem(hasEntry("manufacturer","Energizer")));
    }
    // 7) >> checking multiple values in the same statement
    @Test
    public void test007() {
        response.body("data.findAll{it.name=='Energizer - N Cell E90 Batteries (2-Pack)'}", hasItem(hasEntry("manufacturer", "Energizer")))
                .body("data[0].categories[0]", hasKey("name"))
                .body("limit", equalTo(10));
    }
        //8>> logical Assertions

        @Test
                public void test008(){
            response.body("limit",equalTo(10))
                    .body("limit",lessThan(12))
                    .body("limit",greaterThan(9))
                    .body("limit",greaterThanOrEqualTo(10));
        }
    }


