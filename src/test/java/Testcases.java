import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Testcases {

    String petID;

    @Test(priority = 0)
    public void createNewPet() {
       Response response = given().header("Content-type","application/json").and().body("{\n" +
                       "  \"id\": 0,\n" +
                       "  \"category\": {\n" +
                       "    \"id\": 0,\n" +
                       "    \"name\": \"string\"\n" +
                       "  },\n" +
                       "  \"name\": \"Cat\",\n" +
                       "  \"photoUrls\": [\n" +
                       "    \"string\"\n" +
                       "  ],\n" +
                       "  \"tags\": [\n" +
                       "    {\n" +
                       "      \"id\": 0,\n" +
                       "      \"name\": \"string\"\n" +
                       "    }\n" +
                       "  ],\n" +
                       "  \"status\": \"available\"\n" +
                       "}")

                .when()
                .post("https://petstore.swagger.io/v2/pet")
                .then()
                .extract()
                .response();
      petID = response.jsonPath().get("id").toString();
      System.out.println(petID);
    }

    @Test(priority = 1)
    public void getPetDetails() {
        //given().when().get().then().statusCode(200);
        Response response = given()
                .get("https://petstore.swagger.io/v2/pet/"+petID)
                .then()
                .extract()
                .response();
        response.prettyPrint();
    }

    @Test(priority = 2)
    public void updatePetDetails() {
        //given().when().get().then().statusCode(200);
        Response response = given().header("Content-type","application/json").and().body("{\n" +
                        "  \"name\": \"updated_cat\"\n" +
                        "}")

                .when()
                .put("https://petstore.swagger.io/v2/pet")
                .then()
                .extract()
                .response();
       response.prettyPrint();
    }

    @Test(priority = 3)
    public void deletePet() {
        //given().when().get().then().statusCode(200);
        Response response = given()
                .delete("https://petstore.swagger.io/v2/pet/"+petID)
                .then()
                .extract()
                .response();
        response.prettyPrint();
    }
}
