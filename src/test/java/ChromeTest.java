import com.codeborne.selenide.WebDriverRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testcontainers.containers.BrowserWebDriverContainer;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.*;

public class ChromeTest {

  @Rule
  public BrowserWebDriverContainer chrome =
    new BrowserWebDriverContainer()
//      .withRecordingMode(BrowserWebDriverContainer.VncRecordingMode.RECORD_ALL, new File("build"))
      .withCapabilities(DesiredCapabilities.chrome());

  @Before
  public void setUp() {
    RemoteWebDriver driver = chrome.getWebDriver();
    WebDriverRunner.setWebDriver(driver);
  }

  @After
  public void tearDown() {
    WebDriverRunner.closeWebDriver();
  }

  @Test
  public void runningInChromeIsBroken() {
    open("https://duckduckgo.com/");
    $(By.name("q")).val("chrome").pressEnter();
    $$(".results .result").shouldHave(sizeGreaterThan(3));
    sleep(1000);
  }
}
