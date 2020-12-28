package UsersGuides;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.TestName;


public class TaxOptimizer {

	static ExtentTest test;
	static ExtentReports report;



	String domain = ".com";
	//LLC .com
	//CAN .ca
	//UK .co.uk
	//EU .eu
	//AUS .com.au
	//HKG .com.hk
	//IND .co.in
	//JP .co.jp
	//SGP .com.sg

	@Rule
	public ErrorCollector collector = new ErrorCollector();
	public static TestName name = new TestName();


	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\daigg\\Desktop\\selenium\\chromedriver_win32\\chromedriver.exe");
		report = new ExtentReports(System.getProperty("user.dir")+"\\ExtentReportResults.html");
	}


	@Before
	public void setUp() throws Exception {
		System.out.println("--------Starting Test--------");

	}

	@After
	public void tearDown() throws Exception {
		System.out.println("--------Test Finished--------");
	}

	@AfterClass
	public static void endTest()
	{
		report.endTest(test);
		report.flush();
	}


	public void TestLinks(String page) throws InterruptedException {
		WebDriver driver = null;



		System.out.println("--------fetching homePage--------");
		String homePage = page;
		String url = "";
		HttpURLConnection huc = null;
		int respCode = 200;
		int linkNum = 0;

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors");
		driver = new ChromeDriver(options);
		//driver.manage().window().maximize();



		driver.get(homePage);
		Thread.sleep(3000);
		System.out.println("--------Collecting links--------");
		List<WebElement> links = driver.findElements(By.tagName("a"));
		linkNum = links.size();
		System.out.println("There are " + linkNum + " Links present");
		Iterator<WebElement> it = links.iterator();

		while(it.hasNext()){

			url = it.next().getAttribute("href");

			if(url == null || url.isEmpty()){
				System.out.println("URL is either not configured for anchor tag or it is empty");
				continue;
			}

			

			if(url.contains("mailto")){
				System.out.println("URL belongs to email. Skipping it");
				continue;
			}

			try {
				huc = (HttpURLConnection)(new URL(url).openConnection());

				huc.setReadTimeout(10000);

				huc.setConnectTimeout(10000);

				huc.setRequestMethod("HEAD");

				huc.connect();

				respCode = huc.getResponseCode();

				if(driver.getPageSource().contains("JavaScript is required to login. Please enable JavaScript and reload the page.")) {
					System.out.println(url+" leads to a page where javascript is broken");
					collector.addError(new Throwable(url+" leads to a page where javascript is broken"));
					test.log(LogStatus.FAIL, url+" leads to a page where javascript is broken");
				}
				else if(respCode >= 400){
					System.out.println(url+" is a broken link");
					collector.addError(new Throwable(url+" is a broken link"));
					test.log(LogStatus.FAIL, url+" is a broken link");
					// Assert.fail(url+" is a broken link");
				}	                
				else{
					
					System.out.println(url+" - "+huc.getResponseMessage());
					test.log(LogStatus.PASS, url+" is a valid link");
				}

			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		driver.quit();	    
	}



	@Test
	public void Overview() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ibto/ibto.htm#ibto/overview.htm%3FTocPath%3DOverview%7C_____0"); 
	}
	
	@Test
	public void AboutTaxLotSelection() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ibto/ibto.htm#ibto/abouttaxlotselection.htm%3FTocPath%3DOverview%7C_____1"); 
	}
	
	@Test
	public void LotMatchingMethods() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ibto/ibto.htm#ibto/lotmatchingmethods.htm%3FTocPath%3DOverview%7C_____2"); 
	}

	@Test
	public void Examples() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ibto/ibto.htm#ibto/examples.htm%3FTocPath%3DOverview%7CExamples%7C_____0"); 
	}
	
	@Test
	public void HighCostExample() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ibto/ibto.htm#ibto/highest%20cost%20example.htm%3FTocPath%3DOverview%7CExamples%7C_____1"); 
	}
	
	@Test
	public void LaunchTaxOptimizer() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ibto/ibto.htm#ibto/launchingtheibtaxoptimizer.htm%3FTocPath%3D_____2"); 
	}

	@Test
	public void TaxOptimizerWindow() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ibto/ibto.htm#ibto/theibtaxoptimizerwindow.htm%3FTocPath%3DThe%2520Tax%2520Optimizer%2520Window%7C_____0"); 
	}
	
	@Test
	public void PartsOfTheScreen() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ibto/ibto.htm#ibto/partsofthescreen.htm%3FTocPath%3DThe%2520Tax%2520Optimizer%2520Window%7C_____1"); 
	}
	
	@Test
	public void YearToDateSummary() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ibto/ibto.htm#ibto/year-to-date%20summary.htm%3FTocPath%3DThe%2520Tax%2520Optimizer%2520Window%7C_____2"); 
	}

	@Test
	public void Menus() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ibto/ibto.htm#ibto/menus.htm%3FTocPath%3D_____4"); 
	}
	
	@Test
	public void UsingTaxOptmizer() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ibto/ibto.htm#ibto/usingtheibtaxoptimizer.htm%3FTocPath%3DUsing%2520the%2520Tax%2520Optimizer%7C_____0"); 
	}
	
	@Test
	public void ChangeDefaultMatchMethod() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ibto/ibto.htm#ibto/changeyouraccountdefaultmatchmethod.htm%3FTocPath%3DUsing%2520the%2520Tax%2520Optimizer%7C_____1"); 
	}

	@Test
	public void ChangeMatchMethodCurrent() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ibto/ibto.htm#ibto/changematchmethodforthecurrentorpriorday.htm%3FTocPath%3DUsing%2520the%2520Tax%2520Optimizer%7C_____2"); 
	}
	
	@Test
	public void ChangeMatchMethodSpecific() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ibto/ibto.htm#ibto/changethematchmethodforaspecificsymbol.htm%3FTocPath%3DUsing%2520the%2520Tax%2520Optimizer%7C_____3"); 
	}
	
	@Test
	public void RunWhatIfMatchingScenario() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ibto/ibto.htm#ibto/runwhatifmatchingscenarios.htm%3FTocPath%3DUsing%2520the%2520Tax%2520Optimizer%7C_____4"); 
	}

	@Test
	public void ManuallyMatchSpecificLots() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ibto/ibto.htm#ibto/manuallymatchspecificlots.htm%3FTocPath%3DUsing%2520the%2520Tax%2520Optimizer%7C_____5"); 
	}
	
	@Test
	public void CorporateActions() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ibto/ibto.htm#ibto/corporate%20actions.htm%3FTocPath%3DUsing%2520the%2520Tax%2520Optimizer%7C_____6"); 
	}
	
	@Test
	public void SpecificLotExamples() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ibto/ibto.htm#ibto/specific%20lot%20example.htm%3FTocPath%3DUsing%2520the%2520Tax%2520Optimizer%7C_____7"); 
	}

	@Test
	public void ExitTaxOptmizer() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ibto/ibto.htm#ibto/exittheibtaxoptimizer.htm%3FTocPath%3DUsing%2520the%2520Tax%2520Optimizer%7C_____8"); 
	}
	
	@Test
	public void Copyright() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ibto/ibto.htm#ibto/copyright.htm%3FTocPath%3D_____6"); 
	}
	
}
