package hello;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.MalformedURLException;
import java.net.URL;

@RunWith(SpringRunner.class)
//As well as mocking the HTTP request cycle we can also use Spring Boot to write a very simple full-stack integration test.
//The embedded server is started up on a random port by virtue of the webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
// and the actual port is discovered at runtime with the @LocalServerPort.
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloControllerIT {

    @LocalServerPort
    private int port;

    private URL base;

    @Autowired
    private TestRestTemplate template;

    @Before
    public void setUp() throws MalformedURLException {
        this.base = new URL("http://localhost:" + port + "/");
    }

    @Test
    public void getHello() {
        ResponseEntity<String> entity = template.getForEntity(base.toString(), String.class);
        Assert.assertEquals(entity.getBody(), "Greetings from spring boot.");
    }
}
