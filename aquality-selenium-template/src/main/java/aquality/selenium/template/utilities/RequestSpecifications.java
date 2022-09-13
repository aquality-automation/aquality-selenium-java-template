package aquality.selenium.template.utilities;

import aquality.selenium.template.configuration.Configuration;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.experimental.UtilityClass;

import java.nio.charset.StandardCharsets;

import static io.restassured.RestAssured.given;

@UtilityClass
public class RequestSpecifications {

    public static RequestSpecification commonGiven() {
        return given()
                .baseUri(Configuration.getApiUrl())
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON.withCharset(StandardCharsets.UTF_8));
    }
}
