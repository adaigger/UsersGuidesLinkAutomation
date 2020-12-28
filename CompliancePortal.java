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


public class CompliancePortal {

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
	public void Home() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ecp/Default.htm#complianceportal_home.htm?TocPath=_____1"); 
	}
	
	@Test
	public void GettingStarted() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ecp/Default.htm#CompliancePortal/GettingStarted.htm?TocPath=Getting%2520Started%257C_____0"); 
	}
	
	@Test
	public void RegistrationAndSetup() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ecp/Default.htm#CompliancePortal/RegistrationandSetup.htm?TocPath=Getting%2520Started%257C_____1"); 
	}
	
	@Test
	public void LoginAndOut() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ecp/Default.htm#CompliancePortal/LogIn.htm?TocPath=Getting%2520Started%257C_____2"); 
	}
	
	@Test
	public void CompliancePortalMenus() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ecp/Default.htm#CompliancePortal/ManagementMenus.htm?TocPath=Getting%2520Started%257C_____3"); 
	}
	
	@Test
	public void GetHelp() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ecp/Default.htm#CompliancePortal/GetHelp.htm?TocPath=Getting%2520Started%257C_____4"); 
	}
	
	@Test
	public void Employees() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ecp/Default.htm#CompliancePortal/View.htm?TocPath=Employees%257C_____0"); 
	}
	
	@Test
	public void EditEmployee() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ecp/Default.htm#CompliancePortal/EditEmployee.htm?TocPath=Employees%257C_____1"); 
	}
	
	@Test
	public void PendingReviews() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ecp/Default.htm#CompliancePortal/PendingAccounts.htm?TocPath=Employees%257C_____2"); 
	}
	
	@Test
	public void InviteImportAccounts() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ecp/Default.htm#ET_InviteBrokerageAccount.htm?TocPath=Employees%257C_____3"); 
	}
	
	@Test
	public void ViewSTatementReviewLog() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ecp/Default.htm#StatementReviewLog.htm?TocPath=Employees%257C_____4"); 
	}
	
	@Test
	public void AddComments() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ecp/Default.htm#AddComments.htm?TocPath=Employees%257C_____5"); 
	}
	
	@Test
	public void ViewSatementAudit() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ecp/Default.htm#StatementAudit.htm?TocPath=Employees%257C_____6"); 
	}
	
	@Test
	public void Tools() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ecp/Default.htm#tools.htm?TocPath=Tools%257C_____0"); 
	}
	
	@Test
	public void DataQueries() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ecp/Default.htm#dataqueries.htm?TocPath=Tools%257C_____1"); 
	}
	
	@Test
	public void TradingRestrictions() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ecp/Default.htm#CompliancePortal/TradingRestrictions.htm?TocPath=_____5"); 
	}
	
	@Test
	public void Reports() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ecp/Default.htm#CompliancePortal/Reports.htm?TocPath=Reports%257C_____0"); 
	}
	
	@Test
	public void Statements() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ecp/Default.htm#statements.htm?TocPath=Reports%257CStatements%257C_____0"); 
	}
	
	@Test
	public void RunAStatement() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ecp/Default.htm#howtorunastatement.htm?TocPath=Reports%257CStatements%257C_____1"); 
	}
	
	@Test
	public void TypesOfStatements() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ecp/Default.htm#typesofstatements.htm?TocPath=Reports%257CStatements%257C_____2"); 
	}
	
	@Test
	public void CreateCustomStatement() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ecp/Default.htm#creatingcustomizedstatements.htm?TocPath=Reports%257CStatements%257CCreate%2520a%2520Custom%2520Statement%257C_____0"); 
	}
	
	@Test
	public void EditStatementTemplates() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ecp/Default.htm#viewstatementtemplates.htm?TocPath=Reports%257CStatements%257CCreate%2520a%2520Custom%2520Statement%257C_____1"); 
	}
	
	@Test
	public void ProfitOrLoss() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ecp/Default.htm#profitandloss.htm?TocPath=Reports%257CStatements%257CCreate%2520a%2520Custom%2520Statement%257C_____2"); 
	}
	
	@Test
	public void TradeConfirmations() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ecp/Default.htm#tradeconfirmationreports.htm?TocPath=Reports%257CStatements%257CTrade%2520Confirmations%257C_____0"); 
	}
	
	@Test
	public void RunAConfirmationReport() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ecp/Default.htm#runtradeconfirmreport.htm?TocPath=Reports%257CStatements%257CTrade%2520Confirmations%257C_____1"); 
	}
	
	@Test
	public void DeliveredStatements() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ecp/Default.htm#deliveredstatements.htm?TocPath=Reports%257CStatements%257CDelivered%2520Statements%257C_____0"); 
	}
	
	@Test
	public void ConfigureNotificationDefaults() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ecp/Default.htm#ConfigureNotificationDeliverySettings.htm?TocPath=Reports%257CStatements%257CDelivered%2520Statements%257C_____1"); 
	}
	
	@Test
	public void FlexQueries() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ecp/Default.htm#flexqueries.htm?TocPath=Reports%257CFlex%2520Queries%257C_____0"); 
	}
	
	@Test
	public void HowToRunFlex() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ecp/Default.htm#runaflexquery.htm?TocPath=Reports%257CFlex%2520Queries%257C_____1"); 
	}
	
	@Test
	public void CreateActivityFlexQuery() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ecp/Default.htm#activityflexqueries.htm?TocPath=Reports%257CFlex%2520Queries%257C_____2"); 
	}
	
	@Test
	public void CreatTradeConfirmationFlex() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ecp/Default.htm#tradeconfirmationflexqueries.htm?TocPath=Reports%257CFlex%2520Queries%257C_____3"); 
	}
	
	@Test
	public void EditFlexTemplate() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ecp/Default.htm#viewflexquerytemplates.htm?TocPath=Reports%257CFlex%2520Queries%257C_____4"); 
	}
	
	@Test
	public void DeliveredFlexQueries() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ecp/Default.htm#deliveredflexqueries.htm?TocPath=Reports%257CFlex%2520Queries%257C_____5"); 
	}
	
	@Test
	public void FlexWebServices() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ecp/Default.htm#CompliancePortal/flexweb.htm?TocPath=Reports%257CFlex%2520Web%2520Service%257C_____0"); 
	}
	
	@Test
	public void FlexVersion3() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ecp/Default.htm#CompliancePortal/flexweb3.htm?TocPath=Reports%257CFlex%2520Web%2520Service%257C_____1"); 
	}
	
	@Test
	public void Settings() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ecp/Default.htm#Settings.htm?TocPath=Settings%257C_____0"); 
	}
	
	@Test
	public void AccountSettings() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ecp/Default.htm#accountsettings.htm?TocPath=Settings%257CAccount%2520Settings%257C_____0"); 
	}
	
	@Test
	public void EditProfiles() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ecp/Default.htm#ViewEditDeleteProfiles.htm?TocPath=Settings%257CAccount%2520Settings%257C_____1"); 
	}
	
	@Test
	public void ActivateFlexWebServices() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ecp/Default.htm#CompliancePortal/UseFlexWebService.htm?TocPath=Settings%257CAccount%2520Settings%257C_____2"); 
	}
	
	@Test
	public void UsersAndAccessRights() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ecp/Default.htm#CompliancePortal/UserAccessRights.htm?TocPath=Settings%257CAccount%2520Settings%257CUsers%2520and%2520Access%2520Rights%257C_____0"); 
	}
	
	@Test
	public void AddUsers() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ecp/Default.htm#CompliancePortal/ModifyUserAccessRights.htm?TocPath=Settings%257CAccount%2520Settings%257CUsers%2520and%2520Access%2520Rights%257C_____1"); 
	}
	
	@Test
	public void EditAccessRights() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ecp/Default.htm#CompliancePortal/ViewUserAccessRights.htm?TocPath=Settings%257CAccount%2520Settings%257CUsers%2520and%2520Access%2520Rights%257C_____2"); 
	}
	
	@Test
	public void AddUserRoles() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ecp/Default.htm#Add%20User%20Roles.htm?TocPath=Settings%257CAccount%2520Settings%257CUsers%2520and%2520Access%2520Rights%257C_____3"); 
	}
	
	@Test
	public void UserSettings() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ecp/Default.htm#usersettings.htm?TocPath=Settings%257CUser%2520Settings%257C_____0"); 
	}
	
	@Test
	public void Login() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ecp/Default.htm#CompliancePortal/ChangePassword.htm?TocPath=Settings%257CUser%2520Settings%257C_____1"); 
	}
	
	@Test
	public void Communication() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ecp/Default.htm#CompliancePortal/ChangeEmailAddress.htm?TocPath=Settings%257CUser%2520Settings%257C_____2"); 
	}
	
	@Test
	public void Reporting() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ecp/Default.htm#Reporting.htm?TocPath=Settings%257CUser%2520Settings%257C_____3"); 
	}
	
	@Test
	public void Support() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ecp/Default.htm#am_support.htm?TocPath=Support%257C_____0"); 
	}
	
	@Test
	public void MessageCenter() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ecp/Default.htm#messagecenter.htm?TocPath=Support%257CMessage%2520Center%257C_____0"); 
	}
	
	@Test
	public void MCWindow() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ecp/Default.htm#messagecenterscreen.htm?TocPath=Support%257CMessage%2520Center%257C_____1"); 
	}
	
	@Test
	public void CreateANewTicket() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ecp/Default.htm#creatingaticket.htm?TocPath=Support%257CMessage%2520Center%257C_____2"); 
	}
	
	@Test
	public void ModifyATicket() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ecp/Default.htm#modifyingaticket.htm?TocPath=Support%257CMessage%2520Center%257C_____3"); 
	}
	
	@Test
	public void CloseATicket() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ecp/Default.htm#closingaticket.htm?TocPath=Support%257CMessage%2520Center%257C_____4"); 
	}
	
	@Test
	public void ViewCorporateActions() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ecp/Default.htm#viewingcorporateactions.htm?TocPath=Support%257CMessage%2520Center%257C_____5"); 
	}
	
	@Test
	public void ViewExpiredInquiries() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ecp/Default.htm#viewingexpiredinquiries.htm?TocPath=Support%257CMessage%2520Center%257C_____6"); 
	}
	
	@Test
	public void MessageCenterPreferences() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ecp/Default.htm#messagecenterpreferences.htm?TocPath=Support%257CMessage%2520Center%257C_____7"); 
	}
	
	@Test
	public void ContractSearch() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ecp/Default.htm#Support_Contract.htm?TocPath=Support%257C_____2"); 
	}
	
	@Test
	public void SubmitCorrectionForm() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ecp/Default.htm#taxcorrectionform.htm?TocPath=Support%257C_____3"); 
	}
	
	@Test
	public void MarketDataAssistant() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ecp/Default.htm#support_marketdata.htm?TocPath=Support%257C_____4"); 
	}
	
	@Test
	public void PRIIPSKID() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ecp/Default.htm#Support_PRIPS_KID.htm?TocPath=Support%257C_____5"); 
	}
	
	@Test
	public void FXBrowser() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ecp/Default.htm#support_fxbrowser.htm?TocPath=Support%257C_____6"); 
	}
	
	@Test
	public void SubmitTradeCancellation() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ecp/Default.htm#tradecancellation.htm?TocPath=Support%257C_____7"); 
	}
	
	@Test
	public void ShortStock() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ecp/Default.htm#Support_shortstock.htm?TocPath=Support%257C_____8"); 
	}
	
	@Test
	public void LiveChat() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ecp/Default.htm#livechat.htm?TocPath=Support%257C_____9"); 
	}
	
	@Test
	public void PatternDayTraderReset() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ecp/Default.htm#support_pdtreset.htm?TocPath=Support%257C_____10"); 
	}
	
	@Test
	public void CorporateActionManager() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ecp/Default.htm#Support_corporateaction.htm?TocPath=Support%257C_____11"); 
	}
	
	@Test
	public void IBot() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ecp/Default.htm#Support_IBOT.htm?TocPath=Support%257C_____12"); 
	}
	
	@Test
	public void Copyright() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ecp/Default.htm#copyright.htm?TocPath=_____9"); 
	}
	
	
}
