package hello;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
// Having used @SpringBootTest we are asking for the whole application context to be created
//An alternative would be to ask Spring Boot to create only the web layers of the context using the @WebMvcTest.
//Spring Boot automatically tries to locate the main application class of your application in either case,
// but you can override it, or narrow it down, if you want to build something different.
@SpringBootTest
// the use of the @AutoConfigureMockMvc together with @SpringBootTest to inject a MockMvc instance.
@AutoConfigureMockMvc
public class HelloControllerTest {

    //The MockMvc comes from Spring Test and allows you, via a set of convenient builder classes,
    // to send HTTP requests into the DispatcherServlet and make assertions about the result.
    @Autowired
    private MockMvc mockMvc;
    @Test
    public void getHello() throws Exception {
        ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON));
        perform.andExpect(MockMvcResultMatchers.content().string("Greetings from spring boot."))
                .andExpect(MockMvcResultMatchers.status().isOk());
        System.out.println("=============== Result: "+perform.toString());
    }
}
