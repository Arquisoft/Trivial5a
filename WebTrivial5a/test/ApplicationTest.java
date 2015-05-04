import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.contentAsString;
import static play.test.Helpers.contentType;
import model.User;

import org.junit.Test;

import controllers.Application;
import controllers.routes;
import controllers.Application.Register;
import play.data.Form;
import play.test.TestBrowser;
import play.twirl.api.Content;

import com.thoughtworks.selenium.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.ie.*;
import org.openqa.selenium.html5.*;
import org.openqa.selenium.htmlunit.*;

/**
*
* Simple (JUnit) tests that can call all parts of a play app.
* If you are interested in mocking a whole application, see the wiki for more details.
*
*/
public class ApplicationTest {

    @Test
    public void simpleCheck() {
        int a = 1 + 1;
        assertThat(a).isEqualTo(2);
    }

//    @Test
//    public void renderTemplate() {
//        Content html = views.html.index.render(Form.form(User.class));
//        assertThat(contentType(html)).isEqualTo("text/html");
//        assertThat(contentAsString(html)).contains("Your new application is ready.");
//    }
    
    @Test
    public void runInBrowser() {
        running(testServer(3333), HTMLUNIT, new Callback<TestBrowser>() {
            public void invoke(TestBrowser browser) {
               browser.goTo("http://localhost:3333"); 
               assertThat(browser.$("#title").getTexts().get(0)).isEqualTo("Web Trivial 5a - Login");
            }
        });
    }

}
