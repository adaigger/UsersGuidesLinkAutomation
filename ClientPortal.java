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


public class ClientPortal {

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
	public void LoggingInAndOut() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#cp/gettingstarted/cp_login.htm?TocPath=Getting%2520Started%257C_____1"); 
	}
	
	@Test
	public void NavigatingBetweenScreens() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#cp/gettingstarted/cp_navigating.htm?TocPath=Getting%2520Started%257C_____2"); 
	}
	
	@Test
	public void ClientPortalIcons() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#cp/gettingstarted/cp_iconreference.htm?TocPath=Getting%2520Started%257C_____3"); 
	}
	
	@Test
	public void UsingAccountSelector() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#cp/gettingstarted/cp_accountselector.htm?TocPath=Getting%2520Started%257C_____4"); 
	}
	
	@Test
	public void PendingItems() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/gettingstarted/am_pendingitems.htm?TocPath=Getting%2520Started%257CFunctions%2520Available%2520from%2520Every%2520Screen%257C_____1"); 
	}
	
	@Test
	public void CorporateActions() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/gettingstarted/am_corporateactions.htm?TocPath=Getting%2520Started%257CFunctions%2520Available%2520from%2520Every%2520Screen%257C_____2"); 
	}
	
	@Test
	public void Messages() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/gettingstarted/am_messages.htm?TocPath=Getting%2520Started%257CFunctions%2520Available%2520from%2520Every%2520Screen%257C_____3"); 
	}
	
	@Test
	public void ChangeTheDisplayLanguage() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/gettingstarted/am_language.htm?TocPath=Getting%2520Started%257CFunctions%2520Available%2520from%2520Every%2520Screen%257C_____4"); 
	}
	
	@Test
	public void AMIconReference() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/gettingstarted/am_iconreference.htm?TocPath=Getting%2520Started%257C_____6"); 
	}
	
	@Test
	public void UsingAccountTheSelector() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/gettingstarted/am_accountselector.htm?TocPath=Getting%2520Started%257C_____7"); 
	}
	
	@Test
	public void AMNavigatingBetweenScreens() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/gettingstarted/am_navigating.htm?TocPath=Getting%2520Started%257C_____8"); 
	}
	
	@Test
	public void GettingHelp() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/gettingstarted/am_gettinghelp.htm?TocPath=Getting%2520Started%257C_____9"); 
	}
	
	@Test
	public void Printing() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/gettingstarted/am_printing.htm?TocPath=Getting%2520Started%257C_____10"); 
	}
	
	@Test
	public void LoggingOut() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/gettingstarted/am_logout.htm?TocPath=Getting%2520Started%257C_____11"); 
	}
	
	@Test
	public void PortfolioAnalyst() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#pa/pa_summaryscreen.htm?TocPath=_____2"); 
	}
	
	@Test
	public void IBKRAssetManagement() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#cp/cp-ibkr-am.htm?TocPath=_____3"); 
	}
	
	@Test
	public void Forum() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#cp/forum/cp_forum.htm?TocPath=Forum%257C_____0"); 
	}
	
	@Test
	public void CreateAScreenName() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#cp/cp-screen_name.htm?TocPath=Forum%257C_____1"); 
	}
	
	@Test
	public void BrowsePosts() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#cp/cp-forum-browse-posts.htm?TocPath=Forum%257C_____2"); 
	}
	
	@Test
	public void FilterForumPosts() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#cp/cp-forum-filters.htm?TocPath=Forum%257C_____3"); 
	}
	
	@Test
	public void PostToTheForum() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#cp/cp-forum-posting.htm?TocPath=Forum%257C_____4"); 
	}
	
	@Test
	public void Trading() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#cp/cp-trading.htm?TocPath=Trading%257C_____0"); 
	}
	
	@Test
	public void PlaceOrdersWithTradeButton() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#cp/cp-trade-button.htm?TocPath=Trading%257C_____1"); 
	}
	
	@Test
	public void TradeFromMarketScanner() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#cp/cp-trade-scanner.htm?TocPath=Trading%257C_____2"); 
	}
	
	@Test
	public void InstrumentDetails() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#cp/other/cp-quote-details.htm?TocPath=Trading%257C_____3"); 
	}
	
	@Test
	public void ViewTodaysOrders() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#cp/trading/cp-vieworders.htm?TocPath=Trading%257C_____4"); 
	}
	
	@Test
	public void OptionsChains() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#cp/cp-option-chain.htm?TocPath=Trading%257C_____5"); 
	}
	
	@Test
	public void QuickTrade() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/trading/quicktrade.htm?TocPath=Trading%257C_____6"); 
	}
	
	@Test
	public void IPOSubscription() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#cp/cp-ipo.htm?TocPath=Trading%257C_____7"); 
	}
	
	@Test
	public void Reports() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/reporting/am_reports.htm?TocPath=Reports%257C_____0"); 
	}
	
	@Test
	public void Statemnets() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/reporting/statements.htm?TocPath=Reports%257CStatements%257C_____0"); 
	}
	
	@Test
	public void HowToRunAStatement() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/reporting/howtorunastatement.htm?TocPath=Reports%257CStatements%257C_____1"); 
	}
	
	@Test
	public void TypesOfStatements() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/reporting/typesofstatements.htm?TocPath=Reports%257CStatements%257CTypes%2520of%2520Statements%257C_____0"); 
	}
	
	@Test
	public void ThirdPartyDownloads() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/reporting/thirdpartydownloads.htm?TocPath=Reports%257CStatements%257CTypes%2520of%2520Statements%257C_____1"); 
	}
	
	@Test
	public void Modals() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/reporting/modelstatements.htm?TocPath=Reports%257CStatements%257CTypes%2520of%2520Statements%257C_____2"); 
	}
	
	@Test
	public void CreateACustomStatement() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/reporting/creatingcustomizedstatements.htm?TocPath=Reports%257CStatements%257CCreate%2520a%2520Custom%2520Statement%257C_____0"); 
	}
	
	@Test
	public void ViewEditDeleteStatement() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/reporting/viewstatementtemplates.htm?TocPath=Reports%257CStatements%257CCreate%2520a%2520Custom%2520Statement%257C_____1"); 
	}
	
	@Test
	public void ProfitAndLossSelection() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/reporting/profitandloss.htm?TocPath=Reports%257CStatements%257CCreate%2520a%2520Custom%2520Statement%257C_____2"); 
	}
	
	@Test
	public void TradeConfirmations() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/reporting/tradeconfirmationreports.htm?TocPath=Reports%257CStatements%257CTrade%2520Confirmations%257C_____0"); 
	}
	
	@Test
	public void RunTradeConfirmations() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/reporting/runtradeconfirmreport.htm?TocPath=Reports%257CStatements%257CTrade%2520Confirmations%257C_____1"); 
	}
	
	@Test
	public void BatchReports() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/reporting/batchreports.htm?TocPath=Reports%257CStatements%257C_____5"); 
	}
	
	@Test
	public void DeliveredStatements() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/reporting/deliveredstatements.htm?TocPath=Reports%257CStatements%257C_____6"); 
	}
	
	@Test
	public void FlexQueries() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/reporting/flexqueries.htm?TocPath=Reports%257CFlex%2520Queries%257C_____0"); 
	}
	
	@Test
	public void HowToRunAFlexQuery() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/reporting/runaflexquery.htm?TocPath=Reports%257CFlex%2520Queries%257C_____1"); 
	}
	
	@Test
	public void CreateActivityFlexQuery() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/reporting/activityflexqueries.htm?TocPath=Reports%257CFlex%2520Queries%257C_____2"); 
	}
	
	@Test
	public void CreateTradeConfirmationQuery() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/reporting/tradeconfirmationflexqueries.htm?TocPath=Reports%257CFlex%2520Queries%257C_____3"); 
	}
	
	@Test
	public void EditFlexQueryTemplates() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/reporting/viewflexquerytemplates.htm?TocPath=Reports%257CFlex%2520Queries%257C_____4"); 
	}
	
	@Test
	public void DeliveredFlexQueries() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/reporting/deliveredflexqueries.htm?TocPath=Reports%257CFlex%2520Queries%257C_____5"); 
	}
	
	@Test
	public void OtherReports() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/reporting/otherreports.htm?TocPath=Reports%257COther%2520Reports%257C_____0"); 
	}
	
	@Test
	public void TransactionCostAnalysis() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/reporting/transactioncostanalysis.htm?TocPath=Reports%257COther%2520Reports%257CTransaction%2520Cost%2520Analysis%257C_____0"); 
	}
	
	@Test
	public void UnderstandingTransactionCostAnalysis() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/reporting/understandingthetransactioncostanalysis.htm?TocPath=Reports%257COther%2520Reports%257CTransaction%2520Cost%2520Analysis%257C_____1"); 
	}
	
	@Test
	public void MarginReport() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/reporting/marginreports.htm?TocPath=Reports%257COther%2520Reports%257C_____2"); 
	}
	
	@Test
	public void StressTestReport() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/reporting/stresstestreport.htm?TocPath=Reports%257COther%2520Reports%257C_____3"); 
	}
	
	@Test
	public void ValueAtRiskReport() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/reporting/valueatriskreport.htm?TocPath=Reports%257COther%2520Reports%257C_____4"); 
	}
	
	@Test
	public void DaysToLongReport() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/reporting/tax_lot_holding_period_change_report.htm?TocPath=Reports%257COther%2520Reports%257C_____5"); 
	}
	
	@Test
	public void TaxReportingAndTools() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/reporting/tax.htm?TocPath=Reports%257CTax%2520Reporting%2520and%2520Tools%257C_____0"); 
	}
	
	@Test
	public void TaxForms() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/reporting/taxforms.htm?TocPath=Reports%257CTax%2520Reporting%2520and%2520Tools%257C_____1"); 
	}
	
	@Test
	public void TaxOptimizer() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/reporting/taxoptimizer.htm?TocPath=Reports%257CTax%2520Reporting%2520and%2520Tools%257C_____2"); 
	}
	
	@Test
	public void CostBasis() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/reporting/costbasis.htm?TocPath=Reports%257CTax%2520Reporting%2520and%2520Tools%257CCost%2520Basis%257C_____0"); 
	}
	
	@Test
	public void PositionTransferBasis() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/reporting/positiontransferbasis.htm?TocPath=Reports%257CTax%2520Reporting%2520and%2520Tools%257CCost%2520Basis%257C_____1"); 
	}
	
	@Test
	public void ForexCostBasis() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/reporting/forexcostbasis.htm?TocPath=Reports%257CTax%2520Reporting%2520and%2520Tools%257CCost%2520Basis%257C_____2"); 
	}
	
	@Test
	public void TransferAndPay() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/funding/am_transferandpay.htm?TocPath=Transfer%2520%2526%2520Pay%257C_____0"); 
	}
	
	@Test
	public void ViewingTransactionHistory() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/funding/viewingtransactionhistory.htm?TocPath=Transfer%2520%2526%2520Pay%257CViewing%2520Transaction%2520History%257C_____0"); 
	}
	
	@Test
	public void CancelTransaction() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/funding/cancellingatransaction.htm?TocPath=Transfer%2520%2526%2520Pay%257CViewing%2520Transaction%2520History%257C_____1"); 
	}
	
	@Test
	public void StopPayment() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/funding/stoppaymentrequests.htm?TocPath=Transfer%2520%2526%2520Pay%257CViewing%2520Transaction%2520History%257C_____2"); 
	}
	
	@Test
	public void TransferingFunds() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/funding/transferfunds.htm?TocPath=Transfer%2520%2526%2520Pay%257CTransferring%2520Funds%257C_____0"); 
	}
	
	@Test
	public void CheckStatusOfFunds() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/funding/checkingthestatusofyourfunds.htm?TocPath=Transfer%2520%2526%2520Pay%257CTransferring%2520Funds%257C_____1"); 
	}
	
	@Test
	public void DepositingFunds() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/funding/am_depositingfunds.htm?TocPath=Transfer%2520%2526%2520Pay%257CTransferring%2520Funds%257CDepositing%2520Funds%257C_____0"); 
	}
	
	@Test
	public void EnterDeposits() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/funding/enteringdeposits.htm?TocPath=Transfer%2520%2526%2520Pay%257CTransferring%2520Funds%257CDepositing%2520Funds%257C_____1"); 
	}
	
	@Test
	public void WithdrawingFunds() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/funding/withdrawingfunds.htm?TocPath=Transfer%2520%2526%2520Pay%257CTransferring%2520Funds%257CWithdrawing%2520Funds%257C_____0"); 
	}
	
	@Test
	public void EnterWithdrawals() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/funding/enteringwithdrawals.htm?TocPath=Transfer%2520%2526%2520Pay%257CTransferring%2520Funds%257CWithdrawing%2520Funds%257C_____1"); 
	}
	
	@Test
	public void WithdrawLimits() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/funding/withdrawallimits.htm?TocPath=Transfer%2520%2526%2520Pay%257CTransferring%2520Funds%257CWithdrawing%2520Funds%257C_____2"); 
	}
	
	@Test
	public void WithdrawFundsThirdParty() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/funding/thirdpartywithdrawals.htm?TocPath=Transfer%2520%2526%2520Pay%257CTransferring%2520Funds%257CWithdrawing%2520Funds%257C_____3"); 
	}
	
	@Test
	public void TransferFundsInternal() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/funding/transferringfundsinternally.htm?TocPath=Transfer%2520%2526%2520Pay%257CTransferring%2520Funds%257C_____4"); 
	}
	
	@Test
	public void ACHCanada() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/funding/achcanadianefttransactions.htm?TocPath=Transfer%2520%2526%2520Pay%257CTransferring%2520Funds%257C_____5"); 
	}
	
	@Test
	public void TransferingPositions() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/funding/transferpositions.htm?TocPath=Transfer%2520%2526%2520Pay%257CTransferring%2520Positions%257C_____0"); 
	}
	
	@Test
	public void ACATSTransfers() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/funding/acatstransfers.htm?TocPath=Transfer%2520%2526%2520Pay%257CTransferring%2520Positions%257CACATS%2520Transfers%257C_____0"); 
	}
	
	@Test
	public void EnterACATS() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/funding/enteringacats.htm?TocPath=Transfer%2520%2526%2520Pay%257CTransferring%2520Positions%257CACATS%2520Transfers%257C_____1"); 
	}
	
	@Test
	public void SaveBrokerACATSInfo() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/funding/savingbrokerinformation.htm?TocPath=Transfer%2520%2526%2520Pay%257CTransferring%2520Positions%257CACATS%2520Transfers%257C_____2"); 
	}
	
	@Test
	public void ATONTransfers() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/funding/atontransfers.htm?TocPath=Transfer%2520%2526%2520Pay%257CTransferring%2520Positions%257CATON%2520Transfers%257C_____0"); 
	}
	
	@Test
	public void EnterATON() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/funding/enteringaton.htm?TocPath=Transfer%2520%2526%2520Pay%257CTransferring%2520Positions%257CATON%2520Transfers%257C_____1"); 
	}
	
	@Test
	public void SaveATONPositionTransfer() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/funding/savingbrokerinformation.htm?TocPath=Transfer%2520%2526%2520Pay%257CTransferring%2520Positions%257CATON%2520Transfers%257C_____2"); 
	}
	
	@Test
	public void DRSTransfer() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/funding/drstransfers.htm?TocPath=Transfer%2520%2526%2520Pay%257CTransferring%2520Positions%257C_____3"); 
	}
	
	@Test
	public void DWACTransfer() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/funding/dwactransfers.htm?TocPath=Transfer%2520%2526%2520Pay%257CTransferring%2520Positions%257C_____4"); 
	}
	
	@Test
	public void InternationalAssetTransfer() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/funding/transfer_international_assets.htm?TocPath=Transfer%2520%2526%2520Pay%257CTransferring%2520Positions%257C_____5"); 
	}
	
	@Test
	public void FOPTransfer() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/funding/foptransfers.htm?TocPath=Transfer%2520%2526%2520Pay%257CTransferring%2520Positions%257CFOP%2520Transfers%257C_____0"); 
	}
	
	@Test
	public void EnterFOP() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/funding/enteringfop.htm?TocPath=Transfer%2520%2526%2520Pay%257CTransferring%2520Positions%257CFOP%2520Transfers%257C_____1"); 
	}
	
	@Test
	public void SaveFOP() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/funding/savingbrokerinformation.htm?TocPath=Transfer%2520%2526%2520Pay%257CTransferring%2520Positions%257CFOP%2520Transfers%257C_____2"); 
	}
	
	@Test
	public void InternalPositionTransfer() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/funding/transferringpositionsinternally.htm?TocPath=Transfer%2520%2526%2520Pay%257CTransferring%2520Positions%257C_____7"); 
	}
	
	@Test
	public void SavedInfo() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/funding/am_savedinformation.htm?TocPath=Transfer%2520%2526%2520Pay%257C_____4"); 
	}
	
	@Test
	public void IntegratedCashManagement() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/funding/am_transferandpay.htm?TocPath=Transfer%2520%2526%2520Pay%257CIntegrated%2520Cash%2520Mangement%257C_____0"); 
	}
	
	@Test
	public void DebitCard() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/funding/debitcard.htm?TocPath=Transfer%2520%2526%2520Pay%257CIntegrated%2520Cash%2520Mangement%257CIB%2520Debit%2520Mastercard%257C_____0"); 
	}
	
	@Test
	public void AdditionalCards() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/funding/debitcard_additionalcard.htm?TocPath=Transfer%2520%2526%2520Pay%257CIntegrated%2520Cash%2520Mangement%257CIB%2520Debit%2520Mastercard%257C_____1"); 
	}
	
	@Test
	public void BillPay() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/funding/billpay.htm?TocPath=Transfer%2520%2526%2520Pay%257CIntegrated%2520Cash%2520Mangement%257C_____2"); 
	}
	
	@Test
	public void MarketScanner() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#cp/cp-market-scanner.htm?TocPath=Market%2520Scanner%257C_____0"); 
	}
	
	@Test
	public void SaveMarketScanner() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#cp/cp-scanner-as-watchlist.htm?TocPath=Market%2520Scanner%257C_____1"); 
	}
	
	@Test
	public void Watchlist() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#cp/cp-watchlists.htm?TocPath=Watchlist%257C_____0"); 
	}
	
	@Test
	public void ManageWatchlist() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#cp/cp-manage-watchlists.htm?TocPath=Watchlist%257C_____1"); 
	}
	
	@Test
	public void FYI() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#cp/cp-fyis.htm?TocPath=_____10"); 
	}
	
	@Test
	public void EventsCalandar() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#cp/other/cp-events-calendar.htm?TocPath=_____11"); 
	}
	
	@Test
	public void Settings() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/settings/am_settings.htm?TocPath=Settings%257C_____0"); 
	}
	
	@Test
	public void AccountSettings() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/settings/accountsettings.htm?TocPath=Settings%257CAccount%2520Settings%257C_____0"); 
	}
	
	@Test
	public void UserProfiles() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/settings/profile.htm?TocPath=Settings%257CAccount%2520Settings%257CUser%2520Profiles%257C_____0"); 
	}
	
	@Test
	public void UpdateTaxForms() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/settings/taxforms.htm?TocPath=Settings%257CAccount%2520Settings%257CUser%2520Profiles%257C_____1"); 
	}
	
	@Test
	public void MiFIRRegistration() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/settings/mifr.htm?TocPath=Settings%257CAccount%2520Settings%257CUser%2520Profiles%257C_____2"); 
	}
	
	@Test
	public void FinancialInfo() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/settings/financialinformation.htm?TocPath=Settings%257CAccount%2520Settings%257C_____2"); 
	}
	
	@Test
	public void TradingPermissions() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/settings/tradingpermissions.htm?TocPath=Settings%257CAccount%2520Settings%257CTrading%2520Permissions%257C_____0"); 
	}
	
	@Test
	public void Configuration() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/settings/am_configuration.htm?TocPath=Settings%257CAccount%2520Settings%257CConfiguration%257C_____0"); 
	}
	
	@Test
	public void AccountAlias() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/settings/accountalias.htm?TocPath=Settings%257CAccount%2520Settings%257CConfiguration%257C_____1"); 
	}
	
	@Test
	public void AccountType() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/settings/accounttype.htm?TocPath=Settings%257CAccount%2520Settings%257CConfiguration%257C_____2"); 
	}
	
	@Test
	public void BaseCurrency() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/settings/basecurrency.htm?TocPath=Settings%257CAccount%2520Settings%257CConfiguration%257C_____3"); 
	}
	
	@Test
	public void PricingStructre() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/settings/pricingstructure.htm?TocPath=Settings%257CAccount%2520Settings%257CConfiguration%257C_____4"); 
	}
	
	@Test
	public void DividendElection() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/settings/dividendreinvestment.htm?TocPath=Settings%257CAccount%2520Settings%257CConfiguration%257C_____5"); 
	}
	
	@Test
	public void ExcessFundSweep() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/settings/excessfundssweep.htm?TocPath=Settings%257CAccount%2520Settings%257CConfiguration%257C_____6"); 
	}
	
	@Test
	public void PaperTradingAccount() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/settings/papertradingaccount.htm?TocPath=Settings%257CAccount%2520Settings%257CConfiguration%257CPaper%2520Trading%2520Account%257C_____0"); 
	}
	
	@Test
	public void HowItWorks() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/settings/aboutpapertradingaccounts.htm?TocPath=Settings%257CAccount%2520Settings%257CConfiguration%257CPaper%2520Trading%2520Account%257C_____1"); 
	}
	
	@Test
	public void PaperTradingAccountAM() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/settings/accountmanagementforpapertradingaccount.htm?TocPath=Settings%257CAccount%2520Settings%257CConfiguration%257CPaper%2520Trading%2520Account%257C_____2"); 
	}
	
	@Test
	public void ResetPaperTrading() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/settings/resetpapertradingaccount.htm?TocPath=Settings%257CAccount%2520Settings%257CConfiguration%257CPaper%2520Trading%2520Account%257C_____3"); 
	}
	
	@Test
	public void LargeTraderID() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/settings/largetraderid.htm?TocPath=Settings%257CAccount%2520Settings%257CConfiguration%257C_____8"); 
	}
	
	@Test
	public void RegulatoryInfo() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/settings/regulatoryinformation.htm?TocPath=Settings%257CAccount%2520Settings%257CConfiguration%257C_____9"); 
	}
	
	@Test
	public void ManageAdmin() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/settings/manageadministrators.htm?TocPath=Settings%257CAccount%2520Settings%257CConfiguration%257C_____10"); 
	}
	
	@Test
	public void TraderReferalProgram() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/settings/trader_referral.htm?TocPath=Settings%257CAccount%2520Settings%257CConfiguration%257C_____11"); 
	}
	
	@Test
	public void InsuredBankProgram() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/settings/insuredbankdepositsweepprogram.htm?TocPath=Settings%257CAccount%2520Settings%257CConfiguration%257C_____12"); 
	}
	
	@Test
	public void IRA() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/settings/ira.htm?TocPath=Settings%257CAccount%2520Settings%257CConfiguration%257CIRA%257C_____0"); 
	}
	
	@Test
	public void IRAActivity() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/settings/iraactivity.htm?TocPath=Settings%257CAccount%2520Settings%257CConfiguration%257CIRA%257C_____1"); 
	}
	
	@Test
	public void IRAConversion() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/settings/iraconversion.htm?TocPath=Settings%257CAccount%2520Settings%257CConfiguration%257CIRA%257C_____2"); 
	}
	
	@Test
	public void IRARef() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/settings/irareference.htm?TocPath=Settings%257CAccount%2520Settings%257CConfiguration%257CIRA%257CIRA%2520Reference%257C_____0"); 
	}
	
	@Test
	public void IRAAccountTypes() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/settings/iraaccounttypes.htm?TocPath=Settings%257CAccount%2520Settings%257CConfiguration%257CIRA%257CIRA%2520Reference%257C_____1"); 
	}
	
	@Test
	public void IRATranferMehtods() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/settings/iratransfermethods.htm?TocPath=Settings%257CAccount%2520Settings%257CConfiguration%257CIRA%257CIRA%2520Reference%257C_____2"); 
	}
	
	@Test
	public void IRAConversions() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/settings/conversionsandrecharacterizations.htm?TocPath=Settings%257CAccount%2520Settings%257CConfiguration%257CIRA%257CIRA%2520Reference%257C_____3"); 
	}
	
	@Test
	public void AccountInheritance() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/settings/accountinheritance.htm?TocPath=Settings%257CAccount%2520Settings%257CConfiguration%257CAccount%2520Inheritance%257C_____0"); 
	}
	
	@Test
	public void ModifyBene() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/settings/removingmodifyingtodbeneficiary.htm?TocPath=Settings%257CAccount%2520Settings%257CConfiguration%257CAccount%2520Inheritance%257C_____1"); 
	}
	
	@Test
	public void IBFYI() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/settings/ibfyi.htm?TocPath=Settings%257CAccount%2520Settings%257CConfiguration%257C_____15"); 
	}
	
	@Test
	public void InstitutionalServices() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/settings/institutionalservices.htm?TocPath=Settings%257CAccount%2520Settings%257CConfiguration%257C_____16"); 
	}
	
	@Test
	public void InvestorCategory() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/settings/investorcategory.htm?TocPath=Settings%257CAccount%2520Settings%257CConfiguration%257C_____17"); 
	}
	
	@Test
	public void CreateLinkedAccount() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/settings/createlinkedaccountscenarios.htm?TocPath=Settings%257CAccount%2520Settings%257CConfiguration%257CCreate%252C%2520Move%252C%2520Link%2520or%2520Partition%2520Account%257CCreate%2520a%2520New%2520Linked%2520Account%257C_____1"); 
	}
	
	@Test
	public void IfYouDontFinishApp() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/settings/ifyoudonotfinish.htm?TocPath=Settings%257CAccount%2520Settings%257CConfiguration%257CCreate%252C%2520Move%252C%2520Link%2520or%2520Partition%2520Account%257CCreate%2520a%2520New%2520Linked%2520Account%257C_____2"); 
	}
	
	@Test
	public void RulesForLinking() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/settings/rulesforlinkingdelinkingfromadvisor.htm?TocPath=Settings%257CAccount%2520Settings%257CConfiguration%257CCreate%252C%2520Move%252C%2520Link%2520or%2520Partition%2520Account%257CCreate%2520a%2520New%2520Linked%2520Account%2520Managed%2520by%2520an%2520Advisor%2520or%2520Broker%257C_____1"); 
	}
	
	@Test
	public void MoveAccountToAnAdvisor() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/settings/moveaccounttoadvisorbroker.htm?TocPath=Settings%257CAccount%2520Settings%257CConfiguration%257CCreate%252C%2520Move%252C%2520Link%2520or%2520Partition%2520Account%257C_____3"); 
	}
	
	@Test
	public void AMLinkedAccounts() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/settings/accountmanagementforlinkedaccounts.htm?TocPath=Settings%257CAccount%2520Settings%257CConfiguration%257CCreate%252C%2520Move%252C%2520Link%2520or%2520Partition%2520Account%257CLink%2520Existing%2520Accounts%257C_____1"); 
	}
	
	@Test
	public void PartitionAccount() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/settings/partitionaccount.htm?TocPath=Settings%257CAccount%2520Settings%257CConfiguration%257CCreate%252C%2520Move%252C%2520Link%2520or%2520Partition%2520Account%257C_____5"); 
	}
	
	@Test
	public void CancelLinkRequest() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/settings/cancelinglinkrequest.htm?TocPath=Settings%257CAccount%2520Settings%257CConfiguration%257CCreate%252C%2520Move%252C%2520Link%2520or%2520Partition%2520Account%257C_____7"); 
	}
	
	@Test
	public void AccountClosureStatus() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/settings/accountclosurestatus.htm?TocPath=Settings%257CAccount%2520Settings%257CConfiguration%257CClosing%2520Your%2520Account%257C_____1"); 
	}
	
	@Test
	public void ReopenAccount() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/settings/reopeningaclosedaccount.htm?TocPath=Settings%257CAccount%2520Settings%257CConfiguration%257CClosing%2520Your%2520Account%257C_____2"); 
	}
	
	@Test
	public void CSID() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/settings/csid.htm?TocPath=Settings%257CAccount%2520Settings%257CConfiguration%257C_____20"); 
	}
	
	@Test
	public void FlexWebService3() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/reporting/flex_web_service_version_3.htm?TocPath=Settings%257CAccount%2520Settings%257CReporting%257CFlex%2520Web%2520Service%257C_____1"); 
	}
	
	@Test
	public void ThirdPartyServices() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/settings/thirdpartyservices.htm?TocPath=Settings%257CAccount%2520Settings%257CReporting%257C_____2"); 
	}
	
	@Test
	public void AccessRightOverview() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/settings/accessrightsoverview.htm?TocPath=Settings%257CAccount%2520Settings%257CUsers%2520and%2520Access%2520Rights%257C_____1"); 
	}
	
	@Test
	public void ViewUserInfo() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/settings/viewinguserinfo.htm?TocPath=Settings%257CAccount%2520Settings%257CUsers%2520and%2520Access%2520Rights%257CUsers%257C_____1"); 
	}
	
	@Test
	public void AddUser() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/settings/addingauser.htm?TocPath=Settings%257CAccount%2520Settings%257CUsers%2520and%2520Access%2520Rights%257CUsers%257C_____2"); 
	}
	
	@Test
	public void ModifyAccessRights() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/settings/modifyinguseraccessrights.htm?TocPath=Settings%257CAccount%2520Settings%257CUsers%2520and%2520Access%2520Rights%257CUsers%257C_____3"); 
	}
	
	@Test
	public void AddUsernames() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/settings/addingusernamestoauser.htm?TocPath=Settings%257CAccount%2520Settings%257CUsers%2520and%2520Access%2520Rights%257CUsers%257C_____4"); 
	}
	
	@Test
	public void DeleteUser() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/settings/deletingauser.htm?TocPath=Settings%257CAccount%2520Settings%257CUsers%2520and%2520Access%2520Rights%257CUsers%257C_____5"); 
	}
	
	@Test
	public void ViewUserRoles() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/settings/viewuserrolesetails.htm?TocPath=Settings%257CAccount%2520Settings%257CUsers%2520and%2520Access%2520Rights%257CUser%2520Roles%257C_____1"); 
	}
	
	@Test
	public void AddUserRole() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/settings/addingauserrole.htm?TocPath=Settings%257CAccount%2520Settings%257CUsers%2520and%2520Access%2520Rights%257CUser%2520Roles%257C_____2"); 
	}
	
	@Test
	public void ModifyUserRole() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/settings/modifyingauserrole.htm?TocPath=Settings%257CAccount%2520Settings%257CUsers%2520and%2520Access%2520Rights%257CUser%2520Roles%257C_____3"); 
	}
	
	@Test
	public void DeleteUserRole() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/settings/deletingauserrole.htm?TocPath=Settings%257CAccount%2520Settings%257CUsers%2520and%2520Access%2520Rights%257CUser%2520Roles%257C_____4"); 
	}
	
	@Test
	public void ClientQuestionnaireEditor() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/settings/clientquestionnaire.htm?TocPath=Settings%257CAccount%2520Settings%257C_____7"); 
	}
	
	@Test
	public void ChangePassword() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/settings/changepassword.htm?TocPath=Settings%257CUser%2520Settings%257CLogin%2520Settings%257C_____1"); 
	}
	
	@Test
	public void VotingSubscription() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/settings/votingsubscriptions.htm?TocPath=Settings%257CUser%2520Settings%257CLogin%2520Settings%257C_____2"); 
	}
	
	@Test
	public void ChangeEmail() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/settings/changeemailaddress.htm?TocPath=Settings%257CUser%2520Settings%257CCommunication%2520Settings%257C_____1"); 
	}
	
	@Test
	public void SMSAddress() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/settings/smsaddress.htm?TocPath=Settings%257CUser%2520Settings%257CCommunication%2520Settings%257C_____2"); 
	}
	
	@Test
	public void MobileNumber() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/settings/mobilenumber.htm?TocPath=Settings%257CUser%2520Settings%257CCommunication%2520Settings%257C_____3"); 
	}
	
	@Test
	public void AlertNotifications() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/settings/alertnotification.htm?TocPath=Settings%257CUser%2520Settings%257CCommunication%2520Settings%257C_____4"); 
	}
	
	@Test
	public void MarketingPreferences() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/settings/marketingpreferences.htm?TocPath=Settings%257CUser%2520Settings%257CCommunication%2520Settings%257C_____5"); 
	}
	
	@Test
	public void IPRestrictions() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/settings/iprestrictions.htm?TocPath=Settings%257CUser%2520Settings%257CSecurity%2520Settings%257C_____1"); 
	}
	
	@Test
	public void RecieveDevice() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/settings/whenwillireceivemydevice.htm?TocPath=Settings%257CUser%2520Settings%257CSecurity%2520Settings%257CSecure%2520Login%2520System%257CSecurity%2520Device%257C_____1"); 
	}
	
	@Test
	public void SeamlessAuthentification() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/settings/sls_seamlessauthentication.htm?TocPath=Settings%257CUser%2520Settings%257CSecurity%2520Settings%257CSecure%2520Login%2520System%257CSecurity%2520Device%257C_____2"); 
	}
	
	@Test
	public void SecurityDeviceSharing() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/settings/securitydevicesharing.htm?TocPath=Settings%257CUser%2520Settings%257CSecurity%2520Settings%257CSecure%2520Login%2520System%257C_____2"); 
	}
	
	@Test
	public void SecurityCards() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/settings/securitycodecards.htm?TocPath=Settings%257CUser%2520Settings%257CSecurity%2520Settings%257CSecure%2520Login%2520System%257CAbout%2520Security%2520Devices%257C_____1"); 
	}
	
	@Test
	public void IBKey() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/settings/ibkey.htm?TocPath=Settings%257CUser%2520Settings%257CSecurity%2520Settings%257CSecure%2520Login%2520System%257CAbout%2520Security%2520Devices%257C_____2"); 
	}
	
	@Test
	public void DigitalSecurityCard() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/settings/digitalsecuritycard+.htm?TocPath=Settings%257CUser%2520Settings%257CSecurity%2520Settings%257CSecure%2520Login%2520System%257CAbout%2520Security%2520Devices%257C_____3"); 
	}
	
	@Test
	public void WithdrawLimits1() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/funding/withdrawallimits.htm?TocPath=Settings%257CUser%2520Settings%257CSecurity%2520Settings%257CSecure%2520Login%2520System%257CAbout%2520Security%2520Devices%257C_____4"); 
	}
	
	@Test
	public void SLSOptOut() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/settings/slsoptout.htm?TocPath=Settings%257CUser%2520Settings%257CSecurity%2520Settings%257CSecure%2520Login%2520System%257C_____4"); 
	}
	
	@Test
	public void ReadOnlyAccess() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/settings/readonlyaccess.htm?TocPath=Settings%257CUser%2520Settings%257CTrading%2520Platform%2520Settings%257C_____1"); 
	}
	
	@Test
	public void SubscriberStatus() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/settings/marketdatasubscriberstatus.htm?TocPath=Settings%257CUser%2520Settings%257CTrading%2520Platform%2520Settings%257CSubscribing%2520to%2520Market%2520Data%257C_____1"); 
	}
	
	@Test
	public void MarketDataAssistant() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/settings/marketdataassistant.htm?TocPath=Settings%257CUser%2520Settings%257CTrading%2520Platform%2520Settings%257CSubscribing%2520to%2520Market%2520Data%257C_____2"); 
	}
	
	@Test
	public void ChangeBillableAccount() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/settings/billableaccount.htm?TocPath=Settings%257CUser%2520Settings%257CTrading%2520Platform%2520Settings%257CSubscribing%2520to%2520Market%2520Data%257C_____3"); 
	}
	
	@Test
	public void ResearchWebsiteLogins() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/settings/researchwebsitelogin.htm?TocPath=Settings%257CUser%2520Settings%257CTrading%2520Platform%2520Settings%257CSubscribing%2520to%2520Research%257C_____1"); 
	}
	
	@Test
	public void AsiaGatewayAccess() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/settings/asiagatewayaccess.htm?TocPath=Settings%257CUser%2520Settings%257CTrading%2520Platform%2520Settings%257C_____4"); 
	}
	
	@Test
	public void EducationAndCoaches() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/marketplace/educationcoaches_search.htm?TocPath=Investors'%2520Marketplace%257CFind%2520Services%257CResearch%257C_____1"); 
	}
	
	@Test
	public void NewsFeeds() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/marketplace/newsfeeds_searchservices.htm?TocPath=Investors'%2520Marketplace%257CFind%2520Services%257CResearch%257C_____2"); 
	}
	
	@Test
	public void ResearchMarketplace() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/marketplace/research_search.htm?TocPath=Investors'%2520Marketplace%257CFind%2520Services%257CResearch%257C_____3"); 
	}
	
	@Test
	public void TradingPublications() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/marketplace/tradingpublications.htm?TocPath=Investors'%2520Marketplace%257CFind%2520Services%257CResearch%257C_____4"); 
	}
	
	@Test
	public void SoftwareVendors() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/marketplace/softwarevendors_search.htm?TocPath=Investors'%2520Marketplace%257CFind%2520Services%257CTechnology%257C_____1"); 
	}
	
	@Test
	public void ProgrammingConsultants() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/marketplace/programmingconsultants_search.htm?TocPath=Investors'%2520Marketplace%257CFind%2520Services%257CTechnology%257C_____2"); 
	}
	
	@Test
	public void Advertising() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/marketplace/advertising_searchservices.htm?TocPath=Investors'%2520Marketplace%257CFind%2520Services%257CBusiness%2520Development%257C_____1"); 
	}
	
	@Test
	public void Careers() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/marketplace/jobs_searchservices.htm?TocPath=Investors'%2520Marketplace%257CFind%2520Services%257CBusiness%2520Development%257C_____2"); 
	}
	
	@Test
	public void BussinessAnalytics() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/marketplace/businessanalyst_searchservices.htm?TocPath=Investors'%2520Marketplace%257CFind%2520Services%257CBusiness%2520Development%257C_____3"); 
	}
	
	@Test
	public void CorporateAccessProviders() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/marketplace/corporateaccess_searchservices.htm?TocPath=Investors'%2520Marketplace%257CFind%2520Services%257CBusiness%2520Development%257C_____4"); 
	}
	
	@Test
	public void FinancingFirms() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/marketplace/financingfirms_searchservices.htm?TocPath=Investors'%2520Marketplace%257CFind%2520Services%257CBusiness%2520Development%257C_____5"); 
	}
	
	@Test
	public void AccountantMarketplace() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/marketplace/accountantmarketplace_search.htm?TocPath=Investors'%2520Marketplace%257CFind%2520Services%257CAdministration%257C_____1"); 
	}
	
	@Test
	public void AdminMakretplace() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/marketplace/administratormarketplace_searchservices.htm?TocPath=Investors'%2520Marketplace%257CFind%2520Services%257CAdministration%257C_____2"); 
	}
	
	@Test
	public void AuditoMarketplace() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/marketplace/auditormarketplace_search.htm?TocPath=Investors'%2520Marketplace%257CFind%2520Services%257CAdministration%257C_____3"); 
	}
	
	@Test
	public void AdvisorMarketplace() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/marketplace/advisormarketplace_search.htm?TocPath=Investors'%2520Marketplace%257CFind%2520Services%257CInvesting%257C_____1"); 
	}
	
	@Test
	public void BrokerMarketplace() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/marketplace/brokermarketplace_search.htm?TocPath=Investors'%2520Marketplace%257CFind%2520Services%257CInvesting%257C_____2"); 
	}
	
	@Test
	public void HedgeFundMarketplace() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/marketplace/hedgefundmarketplaceforinvestors.htm?TocPath=Investors'%2520Marketplace%257CFind%2520Services%257CInvesting%257C_____3"); 
	}
	
	@Test
	public void AdvertiseServices() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/marketplace/advertiseservices.htm?TocPath=Investors'%2520Marketplace%257C_____2"); 
	}
	
	@Test
	public void MessageCenterWindow() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/support/messagecenterscreen.htm?TocPath=Message%2520Center%257C_____1"); 
	}
	
	@Test
	public void CreateNewTicket() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/support/creatingaticket.htm?TocPath=Message%2520Center%257C_____2"); 
	}
	
	@Test
	public void ViewCorporateTicket() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/support/viewingcorporateactions.htm?TocPath=Message%2520Center%257C_____3"); 
	}
	
	@Test
	public void ViewInquiryTickets() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/support/viewingexpiredinquiries.htm?TocPath=Message%2520Center%257C_____4"); 
	}
	
	@Test
	public void MessageCenterPreferences() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/support/messagecenterpreferences.htm?TocPath=Message%2520Center%257C_____5"); 
	}
	
	@Test
	public void FXBrowser() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/support/support_fxbrowser.htm?TocPath=Support%257C_____1"); 
	}
	
	@Test
	public void CancellTrade() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/support/tradecancellation.htm?TocPath=Support%257C_____2"); 
	}
	
	@Test
	public void SubmitTaxCorrectionForm() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/support/taxcorrectionform.htm?TocPath=Support%257C_____3"); 
	}
	
	@Test
	public void LiveChat() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/support/livechat.htm?TocPath=Support%257C_____4"); 
	}
	
	@Test
	public void PRIIPSKID() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/support/support_prips_kid.htm?TocPath=Support%257C_____5"); 
	}
	
	@Test
	public void PatternDayTradeReset() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#am/support/support_pdtreset.htm?TocPath=Support%257C_____6"); 
	}
	
	@Test
	public void Portfolio() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/cp/cp.htm#cp/other/cp-portfolio.htm?TocPath=Support%257C_____7"); 
	}
	
	@Test
	public void BrokerPortal() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/bp/Default.htm#StepsInApplicationProcess.htm?TocPath=Getting%2520Started%257CClient%2520Applications%257C_____1"); 
	}
	
	
	
	
	

}
