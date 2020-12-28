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


public class PortfolioAnalyst {

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
	public void PAUserGuide() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/pa/Default.htm#pa_homeonline.htm?TocPath=_____1"); 
	}
	
	@Test
	public void GettingSTarted() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/pa/Default.htm#pa_overview.htm?TocPath=Getting%2520Started%257C_____0"); 
	}
	
	@Test
	public void AccountSelector() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/pa/Default.htm#pa_accountselector.htm?TocPath=Getting%2520Started%257CAccount%2520Selector%257C_____0"); 
	}
	
	@Test
	public void SelectAnAccount() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/pa/Default.htm#pa_selectanaccount.htm?TocPath=Getting%2520Started%257CAccount%2520Selector%257C_____1"); 
	}
	
	@Test
	public void SearchForAccounts() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/pa/Default.htm#pa_searchforaccounts.htm?TocPath=Getting%2520Started%257CAccount%2520Selector%257C_____2"); 
	}
	
	@Test
	public void FilterAccounts() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/pa/Default.htm#pa_filteraccounts.htm?TocPath=Getting%2520Started%257CAccount%2520Selector%257C_____3"); 
	}
	
	@Test
	public void DisplayAdditionalAccountInfo() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/pa/Default.htm#pa_displayaddaccountinfo.htm?TocPath=Getting%2520Started%257CAccount%2520Selector%257C_____4"); 
	}
	
	@Test
	public void SummaryScreenFunctions() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/pa/Default.htm#pa_summaryscreenfunctions.htm?TocPath=Summary%2520Screen%2520Functions%257C_____0"); 
	}
	
	@Test
	public void ManageMultipleAccounts() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/pa/Default.htm#pa_managemultaccts.htm?TocPath=Summary%2520Screen%2520Functions%257C_____1"); 
	}
	
	@Test
	public void Home() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/pa/Default.htm#pa_viewingaccountperformance.htm?TocPath=Home%257C_____0"); 
	}
	
	@Test
	public void NetAssetValueAllAccounts() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/pa/Default.htm#pa_viewingallaccounts.htm?TocPath=Home%257CNet%2520Asset%2520Value%2520for%2520All%2520Accounts%257C_____0"); 
	}
	
	@Test
	public void NetAssetValueBrokerageAccount() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/pa/Default.htm#pa_viewingallbrokers.htm?TocPath=Home%257CNet%2520Asset%2520Value%2520for%2520All%2520Accounts%257CNet%2520Asset%2520Value%2520for%2520All%2520Brokerage%2520Accounts%257C_____0"); 
	}
	
	@Test
	public void ViewConsolidatedPerformance() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/pa/Default.htm#pa_viewingallbrokers_view.htm?TocPath=Home%257CNet%2520Asset%2520Value%2520for%2520All%2520Accounts%257CNet%2520Asset%2520Value%2520for%2520All%2520Brokerage%2520Accounts%257C_____1"); 
	}
	
	@Test
	public void NetAssetValueNonBrokerageAccoun() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/pa/Default.htm#pa_viewingallnonbrokers.htm?TocPath=Home%257CNet%2520Asset%2520Value%2520for%2520All%2520Accounts%257CNet%2520Asset%2520Value%2520for%2520All%2520Non-Brokerage%2520Accounts%257C_____0"); 
	}
	
	@Test
	public void ViewConsolidatedPerformanceAll() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/pa/Default.htm#pa_viewingallnonbrokers_view.htm?TocPath=Home%257CNet%2520Asset%2520Value%2520for%2520All%2520Accounts%257CNet%2520Asset%2520Value%2520for%2520All%2520Non-Brokerage%2520Accounts%257C_____1"); 
	}
	
	@Test
	public void ViewSingleAccount() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/pa/Default.htm#pa_viewingsingleaccount.htm?TocPath=Home%257CNet%2520Asset%2520Value%2520for%2520All%2520Accounts%257C_____3"); 
	}
	
	@Test
	public void ViewSingleNonBrokerAccount() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/pa/Default.htm#pa_viewingsingleaccount_non.htm?TocPath=Home%257CNet%2520Asset%2520Value%2520for%2520All%2520Accounts%257C_____4"); 
	}
	
	@Test
	public void AssetClasses() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/pa/Default.htm#pa_viewingassetclasses.htm?TocPath=Home%257CNet%2520Asset%2520Value%2520for%2520All%2520Accounts%257CAsset%2520Classes%257C_____0"); 
	}
	
	@Test
	public void ViewAssetClassesAll() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/pa/Default.htm#pa_viewingassetclasses_allbrokers.htm?TocPath=Home%257CNet%2520Asset%2520Value%2520for%2520All%2520Accounts%257CAsset%2520Classes%257C_____1"); 
	}
	
	@Test
	public void ViewAssetClassesSingle() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/pa/Default.htm#pa_viewingassetclasses_onebroker.htm?TocPath=Home%257CNet%2520Asset%2520Value%2520for%2520All%2520Accounts%257CAsset%2520Classes%257C_____2"); 
	}
	
	@Test
	public void FilteringPositions() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/pa/Default.htm#pa_filterpositions.htm?TocPath=Home%257CNet%2520Asset%2520Value%2520for%2520All%2520Accounts%257CAsset%2520Classes%257CFiltering%2520Positions%257C_____0"); 
	}
	
	@Test
	public void FilterPositionsAll() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/pa/Default.htm#pa_filterpositions_all.htm?TocPath=Home%257CNet%2520Asset%2520Value%2520for%2520All%2520Accounts%257CAsset%2520Classes%257CFiltering%2520Positions%257C_____1"); 
	}
	
	@Test
	public void FilterPositionsSingle() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/pa/Default.htm#pa_filterpositions_single.htm?TocPath=Home%257CNet%2520Asset%2520Value%2520for%2520All%2520Accounts%257CAsset%2520Classes%257CFiltering%2520Positions%257C_____2"); 
	}
	
	@Test
	public void Transactions() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/pa/Default.htm#pa_viewingtransactions.htm?TocPath=Home%257CNet%2520Asset%2520Value%2520for%2520All%2520Accounts%257CTransactions%257C_____0"); 
	}
	
	@Test
	public void ViewTransSingle() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/pa/Default.htm#pa_viewingtransactions_single.htm?TocPath=Home%257CNet%2520Asset%2520Value%2520for%2520All%2520Accounts%257CTransactions%257C_____1"); 
	}
	
	@Test
	public void ViewTransMuliple() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/pa/Default.htm#pa_viewingtransactions_multiple.htm?TocPath=Home%257CNet%2520Asset%2520Value%2520for%2520All%2520Accounts%257CTransactions%257C_____2"); 
	}
	
	@Test
	public void ViewTransSingleNonBroker() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/pa/Default.htm#pa_viewingtransactions_singlenon.htm?TocPath=Home%257CNet%2520Asset%2520Value%2520for%2520All%2520Accounts%257CTransactions%257C_____3"); 
	}
	
	@Test
	public void SearchAccountDetails() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/pa/Default.htm#pa_searchaccountdetails.htm?TocPath=Home%257CNet%2520Asset%2520Value%2520for%2520All%2520Accounts%257C_____7"); 
	}
	
	@Test
	public void Reports() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/pa/Default.htm#pa_runningreports.htm?TocPath=Reports%257C_____0"); 
	}
	
	@Test
	public void DefaultReports() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/pa/Default.htm#pa_preconfiguredreports.htm?TocPath=Reports%257CDefault%2520Reports%257C_____0"); 
	}
	
	@Test
	public void ViewDefaultReports() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/pa/Default.htm#defaultreportdetails.htm?TocPath=Reports%257CDefault%2520Reports%257C_____1"); 
	}
	
	@Test
	public void RunDefaultReports() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/pa/Default.htm#pa_preconfiguredreports_run.htm?TocPath=Reports%257CDefault%2520Reports%257C_____2"); 
	}
	
	@Test
	public void BatchReports() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/pa/Default.htm#pa_batchreports.htm?TocPath=Reports%257CBatch%2520Reports%257C_____0"); 
	}
	
	@Test
	public void ViewBatchReports() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/pa/Default.htm#pa_viewbatchreports.htm?TocPath=Reports%257CBatch%2520Reports%257C_____1"); 
	}
	
	@Test
	public void CustomReports() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/pa/Default.htm#pa_customreports.htm?TocPath=Reports%257CCustom%2520Reports%257C_____0"); 
	}
	
	@Test
	public void CreateCustomReport() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/pa/Default.htm#pa_customreports_create.htm?TocPath=Reports%257CCustom%2520Reports%257C_____1"); 
	}
	
	@Test
	public void EditCustomReport() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/pa/Default.htm#pa_customreports_editdelete.htm?TocPath=Reports%257CCustom%2520Reports%257C_____2"); 
	}
	
	@Test
	public void RunCustomReport() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/pa/Default.htm#pa_customreports_run.htm?TocPath=Reports%257CCustom%2520Reports%257C_____3"); 
	}
	
	@Test
	public void SearchforCustomReport() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/pa/Default.htm#pa_customreports_view.htm?TocPath=Reports%257CCustom%2520Reports%257C_____4"); 
	}
	
	@Test
	public void ReportPagesInCustomReport() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/pa/Default.htm#pa_reportpages.htm?TocPath=Reports%257CCustom%2520Reports%257C_____5"); 
	}
	
	@Test
	public void PerformanceAttributionReport() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/pa/Default.htm#pa_performanceattributionreport.htm?TocPath=Reports%257CCustom%2520Reports%257CPerformance%2520Attribution%2520Report%257C_____0"); 
	}
	
	@Test
	public void PerformanceAttributionReportTable() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/pa/Default.htm#pa_performanceattributionreport_table.htm?TocPath=Reports%257CCustom%2520Reports%257CPerformance%2520Attribution%2520Report%257C_____1"); 
	}
	
	@Test
	public void PerformanceAttributionReportCharts() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/pa/Default.htm#pa_performanceattributionreport_charts.htm?TocPath=Reports%257CCustom%2520Reports%257CPerformance%2520Attribution%2520Report%257C_____2"); 
	}
	
	@Test
	public void ReportsDelivery() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/pa/Default.htm#pa_reportsdelivery.htm?TocPath=Reports%257CReports%2520Delivery%257C_____0"); 
	}
	
	@Test
	public void ConfigureReportsDelivery() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/pa/Default.htm#pa_deliveredreports_configure.htm?TocPath=Reports%257CReports%2520Delivery%257C_____1"); 
	}
	
	@Test
	public void CustomBenchmarks() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/pa/Default.htm#pa_custombenchmarks.htm?TocPath=Reports%257CCustom%2520Benchmarks%257C_____0"); 
	}
	
	@Test
	public void CreateCustomBenchmarks() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/pa/Default.htm#pa_custombenchmarks_create.htm?TocPath=Reports%257CCustom%2520Benchmarks%257C_____1"); 
	}
	
	@Test
	public void EditCustomBenchmarks() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/pa/Default.htm#pa_custombenchmarks_viewedit.htm?TocPath=Reports%257CCustom%2520Benchmarks%257C_____2"); 
	}
	
	@Test
	public void SampleReports() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/pa/Default.htm#pa_custombenchmarks_viewedit.htm?TocPath=Reports%257CCustom%2520Benchmarks%257C_____2"); 
	}
	
	@Test
	public void PASamples() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/pa/Default.htm#pa_samplereports.htm?TocPath=Reports%257CSample%2520Reports%257C_____1"); 
	}
	
	@Test
	public void Synopses() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/pa/Default.htm#pa_synopses.htm?TocPath=Reports%257CSynopses%257C_____0"); 
	}
	
	@Test
	public void CreateSynopses() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/pa/Default.htm#pa_synopses_createsave.htm?TocPath=Reports%257CSynopses%257C_____1"); 
	}
	
	@Test
	public void EditSynopses() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/pa/Default.htm#pa_synopses_vieweditdelete.htm?TocPath=Reports%257CSynopses%257C_____2"); 
	}
	
	@Test
	public void AssignSynopses() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/pa/Default.htm#pa_synopses_assigncustomreport.htm?TocPath=Reports%257CSynopses%257C_____3"); 
	}
	
	@Test
	public void PortfolioCheckup() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/pa/Default.htm#PA_Checkup.htm?TocPath=Portfolio%2520Checkup%257C_____0"); 
	}
	
	@Test
	public void ViewPortfolioCheckupTool() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/pa/Default.htm#PA_Checkup_viewtool.htm?TocPath=Portfolio%2520Checkup%257C_____1"); 
	}
	
	@Test
	public void FundParser() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/pa/Default.htm#analyzeetfs.htm?TocPath=_____7"); 
	}
	
	@Test
	public void ExternalAccounts() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/pa/Default.htm#pa_externalaccounts.htm?TocPath=External%2520Accounts%257C_____0"); 
	}
	
	@Test
	public void AddExternalAccounts() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/pa/Default.htm#pa_addingexternalaccounts.htm?TocPath=External%2520Accounts%257C_____1"); 
	}
	
	@Test
	public void EditExternalAccounts() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/pa/Default.htm#pa_vieweditdelexternalaccounts.htm?TocPath=External%2520Accounts%257C_____2"); 
	}
	
	@Test
	public void HistoricalData() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/pa/Default.htm#pa_uploadhistoricaldata.htm?TocPath=External%2520Accounts%257CHistorical%2520Data%257C_____0"); 
	}
	
	@Test
	public void UplaodHistoricalDataCSV() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/pa/Default.htm#pa_uploadhistoricaldata_csv.htm?TocPath=External%2520Accounts%257CHistorical%2520Data%257C_____1"); 
	}
	
	@Test
	public void AddHistoricalDataManually() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/pa/Default.htm#pa_uploadhistoricaldata_manual.htm?TocPath=External%2520Accounts%257CHistorical%2520Data%257C_____2"); 
	}
	
	@Test
	public void TransactionCodes() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/pa/Default.htm#pa_uploadhistoricaldata_transactioncodes.htm?TocPath=External%2520Accounts%257CHistorical%2520Data%257C_____3"); 
	}
	
	@Test
	public void HistoricalDataUplaodFormats() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/pa/Default.htm#pa_upload_format_samples.htm?TocPath=External%2520Accounts%257CHistorical%2520Data%257C_____4"); 
	}
	
	@Test
	public void ClientAuthorization() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/pa/Default.htm#PA_ClientAuthorizations.htm?TocPath=External%2520Accounts%257C_____4"); 
	}
	
	@Test
	public void EducationCenter() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/pa/Default.htm#EducationCenter.htm?TocPath=_____9"); 
	}
	
	@Test
	public void PAForNonIBKRClients() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/pa/Default.htm#Standalone_PA.htm?TocPath=PortfolioAnalyst%2520for%2520non-IBKR%2520Clients%257C_____0"); 
	}
	
	@Test
	public void AddExternalAccount() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/pa/Default.htm#Standalone_PA_addnew.htm?TocPath=PortfolioAnalyst%2520for%2520non-IBKR%2520Clients%257C_____1"); 
	}
	
	@Test
	public void AddInstitutionAccount() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/pa/Default.htm#Standalone_PA_institution.htm?TocPath=PortfolioAnalyst%2520for%2520non-IBKR%2520Clients%257C_____2"); 
	}
	
	@Test
	public void ReportsForNonIBKRAccounts() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/pa/Default.htm#Standalone_PA_reports.htm?TocPath=PortfolioAnalyst%2520for%2520non-IBKR%2520Clients%257C_____3"); 
	}
	
	@Test
	public void Notes() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/pa/Default.htm#Notes.htm?TocPath=_____11"); 
	}
	
	@Test
	public void Glossary() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/pa/Default.htm#pa_glossary.htm?TocPath=_____12"); 
	}
	
	
}
