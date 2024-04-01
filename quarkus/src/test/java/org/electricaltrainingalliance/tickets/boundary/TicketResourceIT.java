package org.electricaltrainingalliance.tickets.boundary;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import org.electricaltrainingalliance.tickets.boudary.TicketResource;
import org.junit.jupiter.api.Test;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@TestHTTPEndpoint(TicketResource.class)
public class TicketResourceIT {

    private final Integer caseId = 3752258;
    private final String status = "Waiting on Red Hat";

    @Test
    public void get_with_valid_id_returns_200_with_json_payload() {
        given().when().get("/" + caseId)
        .then().statusCode(is(200))
        .body("caseStatus", is(status));
    }

    @Test
    public void get_with_valid_status_returns_200_with_json_payload() {
        given().when().get("/findByStatus/" + status)
        .then().statusCode(is(200))
        .body("size()", is(1))
        .body("[0].caseId", is(caseId));
    }

    @Test
    public void get_returns_200_with_records() {
        given().when().get()
        .then().statusCode(is(200))
        .body("size()", is(2));
    }
    
}
