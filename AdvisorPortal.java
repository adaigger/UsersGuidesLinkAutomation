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


public class AdvisorPortal {

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
	public void APUserGuide() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#advisorhomepage.htm?TocPath=_____1"); 
	}
	
	@Test
	public void Contents() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#Resources/TOC.htm?TocPath=_____2"); 
	}
	
	@Test
	public void GettingStarted() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#gettingstarted.htm?TocPath=Getting%2520Started%257C_____0"); 
	}
	
	@Test
	public void LogginIn() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#login.htm?TocPath=Getting%2520Started%257C_____1"); 
	}
	
	@Test
	public void IconRef() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#iconreference.htm?TocPath=Getting%2520Started%257C_____2"); 
	}
	
	@Test
	public void UsingAccountSelector() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#accountselector.htm?TocPath=Getting%2520Started%257C_____3"); 
	}
	
	@Test
	public void NavigateScreens() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#Navigating.htm?TocPath=Getting%2520Started%257C_____4"); 
	}
	
	@Test
	public void GettingHelp() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#gethelp.htm?TocPath=Getting%2520Started%257C_____5"); 
	}
	
	@Test
	public void ClientApplications() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#clientapps.htm?TocPath=Getting%2520Started%257CClient%2520Applications%257C_____0"); 
	}
	
	@Test
	public void StepsInApp() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#stepsappprocess.htm?TocPath=Getting%2520Started%257CClient%2520Applications%257C_____1"); 
	}
	
	@Test
	public void InviteClientElectronic() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#inviteclient.htm?TocPath=Getting%2520Started%257CClient%2520Applications%257C_____2"); 
	}
	
	@Test
	public void StartSemiElectronic() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#startsemielectronic.htm?TocPath=Getting%2520Started%257CClient%2520Applications%257C_____3"); 
	}
	
	@Test
	public void AdvisorSTLAccounts() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#advisorstl.htm?TocPath=Getting%2520Started%257CAdvisor%2520and%2520STL%2520Accounts%257C_____0"); 
	}
	
	@Test
	public void AddAdvisorAccount() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#addadvisor.htm?TocPath=Getting%2520Started%257CAdvisor%2520and%2520STL%2520Accounts%257C_____1"); 
	}
	
	@Test
	public void AddSTLAccount() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#addstl.htm?TocPath=Getting%2520Started%257CAdvisor%2520and%2520STL%2520Accounts%257C_____2"); 
	}
	
	@Test
	public void ClientAccountTemplates() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#clientaccttemplates.htm?TocPath=Getting%2520Started%257CClient%2520Account%2520Templates%257C_____0"); 
	}
	
	@Test
	public void EditAccountTemplate() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#createaccountemplate.htm?TocPath=Getting%2520Started%257CClient%2520Account%2520Templates%257C_____1"); 
	}
	
	@Test
	public void AdditionalApps() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#AdditionalApplicationsAdvisorClients.htm?TocPath=Getting%2520Started%257C_____9"); 
	}
	
	@Test
	public void AdvisorQualifications() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#advqualif.htm?TocPath=Getting%2520Started%257C_____10"); 
	}
	
	@Test
	public void AdditionalAdvisorAuthorization() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#addadvauth.htm?TocPath=Getting%2520Started%257CAdditional%2520Advisor%2520Authorizations%257C_____0"); 
	}
	
	@Test
	public void GrantAdditionalAdvisorAuthorization() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#grantaddadvauth.htm?TocPath=Getting%2520Started%257CAdditional%2520Advisor%2520Authorizations%257C_____1"); 
	}
	
	@Test
	public void Fees() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#fees.htm?TocPath=Getting%2520Started%257CFees%257C_____0"); 
	}
	
	@Test
	public void AdvisorFeeCap() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#feecapformula.htm?TocPath=Getting%2520Started%257CFees%257C_____1"); 
	}
	
	@Test
	public void ChargeClientFees() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#chargefeesmaster.htm?TocPath=Getting%2520Started%257CFees%257C_____2"); 
	}
	
	@Test
	public void Dashboard() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#dashboard.htm?TocPath=Dashboard%257C_____0"); 
	}
	
	@Test
	public void PendingItems() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#pendingitems.htm?TocPath=Dashboard%257C_____1"); 
	}
	
	@Test
	public void ViewRecentActivities() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#recentactivity.htm?TocPath=Dashboard%257C_____2"); 
	}
	
	@Test
	public void StartClientApp() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#newclientapp.htm?TocPath=Dashboard%257C_____3"); 
	}
	
	@Test
	public void Notifications() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#notifications.htm?TocPath=Dashboard%257C_____4"); 
	}
	
	@Test
	public void AsstesUnderManagement() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#aum.htm?TocPath=Dashboard%257C_____5"); 
	}
	
	@Test
	public void MasterAccountBalance() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#masteraccount.htm?TocPath=Dashboard%257C_____6"); 
	}
	
	@Test
	public void Calendar() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#calendar.htm?TocPath=Dashboard%257C_____7"); 
	}
	
	@Test
	public void Workflows() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#workflows.htm?TocPath=Dashboard%257C_____8"); 
	}
	
	@Test
	public void Notes() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#notes.htm?TocPath=Dashboard%257C_____9"); 
	}
	
	@Test
	public void Documents() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#docs.htm?TocPath=Dashboard%257C_____10"); 
	}
	
	@Test
	public void Contacts() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#contacts.htm?TocPath=Contacts%257C_____0"); 
	}
	
	@Test
	public void AddContacts() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#addcontact.htm?TocPath=Contacts%257C_____1"); 
	}
	
	@Test
	public void OpenIndividualContract() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#viewcontact.htm?TocPath=Contacts%257C_____2"); 
	}
	
	@Test
	public void ViewContactInfo() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#contactinfo.htm?TocPath=Contacts%257C_____3"); 
	}
	
	@Test
	public void MergeContacts() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#MergeContacts.htm?TocPath=Contacts%257C_____4"); 
	}
	
	@Test
	public void CloseClientAccount() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#Close%20Client%20Account.htm?TocPath=Contacts%257C_____5"); 
	}
	
	@Test
	public void FeeAdministration() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#feeadmin.htm?TocPath=Fee%2520Administration%257C_____0"); 
	}
	
	@Test
	public void ConfigureClientFees() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#configclientfees.htm?TocPath=Fee%2520Administration%257C_____1"); 
	}
	
	@Test
	public void ClientFeeTemplates() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#clientfeetemplates.htm?TocPath=Fee%2520Administration%257CClient%2520Fee%2520Templates%257C_____0"); 
	}
	
	@Test
	public void ConfigureClientFeeTemplates() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#configclientfeetemplate.htm?TocPath=Fee%2520Administration%257CClient%2520Fee%2520Templates%257C_____1"); 
	}
	
	@Test
	public void Invoicing() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#invoicing.htm?TocPath=Fee%2520Administration%257CInvoicing%257C_____0"); 
	}
	
	@Test
	public void CreateInvoice() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#createinvoice.htm?TocPath=Fee%2520Administration%257CInvoicing%257C_____1"); 
	}
	
	@Test
	public void UpdateInvoice() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#invoicerequest.htm?TocPath=Fee%2520Administration%257CInvoicing%257C_____2"); 
	}
	
	@Test
	public void DownoladInvoice() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#uploadinvoice.htm?TocPath=Fee%2520Administration%257CInvoicing%257C_____3"); 
	}
	
	@Test
	public void FeeReinbursment() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#feereimbursement.htm?TocPath=Fee%2520Administration%257CFee%2520Reimbursement%257C_____0"); 
	}
	
	@Test
	public void SubmitFeeRenburs() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#submitfee.htm?TocPath=Fee%2520Administration%257CFee%2520Reimbursement%257C_____1"); 
	}
	
	@Test
	public void ClientAuthorization() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#clientauth.htm?TocPath=Fee%2520Administration%257C_____5"); 
	}
	
	@Test
	public void Tools() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#tools.htm?TocPath=Tools%257C_____0"); 
	}
	
	@Test
	public void Queries() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#queries.htm?TocPath=Tools%257CQueries%257C_____0"); 
	}
	
	@Test
	public void RunPerformanceReport() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#reportperf.htm?TocPath=Tools%257CQueries%257C_____1"); 
	}
	
	@Test
	public void RunSymbolReposrt() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#reportsymbol.htm?TocPath=Tools%257CQueries%257C_____2"); 
	}
	
	@Test
	public void RunTransactionCount() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#reporttrans.htm?TocPath=Tools%257CQueries%257C_____3"); 
	}
	
	@Test
	public void RiskScores() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#risk.htm?TocPath=Tools%257C_____2"); 
	}
	
	@Test
	public void Groups() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#groups.htm?TocPath=Groups%257C_____0"); 
	}
	
	@Test
	public void ViewGroupMembers() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#groupmembers.htm?TocPath=Groups%257C_____1"); 
	}
	
	@Test
	public void EditGroup() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#addgroup.htm?TocPath=Groups%257C_____2"); 
	}
	
	@Test
	public void CreateHouseholdGroup() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#householdgroup.htm?TocPath=Groups%257C_____3"); 
	}
	
	@Test
	public void TransactionHistory() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#transhist.htm?TocPath=_____9"); 
	}
	
	@Test
	public void Calendar2() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#calendarfull.htm?TocPath=Calendar%257C_____0"); 
	}
	
	@Test
	public void EditCalendar() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#addevents.htm?TocPath=Calendar%257C_____1"); 
	}
	
	@Test
	public void Email() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#email.htm?TocPath=Email%257C_____0"); 
	}
	
	@Test
	public void CreateMailbox() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#mailbox.htm?TocPath=Email%257C_____1"); 
	}
	
	@Test
	public void Reports() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#reports.htm?TocPath=Reports%257C_____0"); 
	}
	
	@Test
	public void PA() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#pa.htm?TocPath=Reports%257C_____1"); 
	}
	
	@Test
	public void Statements() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#statements.htm?TocPath=Reports%257CStatements%257C_____0"); 
	}
	
	@Test
	public void RunStatement() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#runstatement.htm?TocPath=Reports%257CStatements%257C_____1"); 
	}
	
	@Test
	public void TypesOfStatements() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#typestatement.htm?TocPath=Reports%257CStatements%257CTypes%2520of%2520Statements%257C_____0"); 
	}
	
	@Test
	public void ThirdPartyDownloads() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#thirdparty.htm?TocPath=Reports%257CStatements%257CTypes%2520of%2520Statements%257C_____1"); 
	}
	
	@Test
	public void Models() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#model.htm?TocPath=Reports%257CStatements%257CTypes%2520of%2520Statements%257C_____2"); 
	}
	
	@Test
	public void CreatCustomStatement() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#createcustom.htm?TocPath=Reports%257CStatements%257CCreate%2520a%2520Custom%2520Statement%257C_____0"); 
	}
	
	@Test
	public void EditStatementTemps() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#edittemplate.htm?TocPath=Reports%257CStatements%257CCreate%2520a%2520Custom%2520Statement%257C_____1"); 
	}
	
	@Test
	public void ProfitAndLoss() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#profitandloss.htm?TocPath=Reports%257CStatements%257CCreate%2520a%2520Custom%2520Statement%257C_____2"); 
	}
	
	@Test
	public void TradeConfirmations() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#tradeconfirm.htm?TocPath=Reports%257CStatements%257CTrade%2520Confirmations%257C_____0"); 
	}
	
	@Test
	public void RunTradeConfirmation() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#runtradeconfirm.htm?TocPath=Reports%257CStatements%257CTrade%2520Confirmations%257C_____1"); 
	}
	
	@Test
	public void BatchReports() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#batch.htm?TocPath=Reports%257CStatements%257C_____5"); 
	}
	
	@Test
	public void DeliveredStatements() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#deliver.htm?TocPath=Reports%257CStatements%257C_____6"); 
	}
	
	@Test
	public void FlexQueries() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#flex.htm?TocPath=Reports%257CFlex%2520Queries%257C_____0"); 
	}
	
	@Test
	public void RunFlexQuery() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#runflex.htm?TocPath=Reports%257CFlex%2520Queries%257C_____1"); 
	}
	
	@Test
	public void CreateFlexQuery() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#activityflex.htm?TocPath=Reports%257CFlex%2520Queries%257C_____2"); 
	}
	
	@Test
	public void CreateTradeConfirmationFlex() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#tradeflex.htm?TocPath=Reports%257CFlex%2520Queries%257C_____3"); 
	}
	
	@Test
	public void EditFlexQueryTemplates() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#editflextemplate.htm?TocPath=Reports%257CFlex%2520Queries%257C_____4"); 
	}
	
	@Test
	public void ConfigDeliverySettings() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#deliverysettingsflex.htm?TocPath=Reports%257CFlex%2520Queries%257C_____5"); 
	}
	
	@Test
	public void OtherReports() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#otherreports.htm?TocPath=Reports%257COther%2520Reports%257C_____0"); 
	}
	
	@Test
	public void MarginReports() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#marginreport.htm?TocPath=Reports%257COther%2520Reports%257C_____1"); 
	}
	
	@Test
	public void StressTestReport() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#stresstest.htm?TocPath=Reports%257COther%2520Reports%257C_____2"); 
	}
	
	@Test
	public void ValueAtRiskReport() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#valueatrisk.htm?TocPath=Reports%257COther%2520Reports%257C_____3"); 
	}
	
	@Test
	public void TransationCostAnalysis() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#transcost.htm?TocPath=Reports%257COther%2520Reports%257CTransaction%2520Cost%2520Analysis%257C_____0"); 
	}
	
	@Test
	public void UnderstandTransationCostAnalysis() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#understandingtrans.htm?TocPath=Reports%257COther%2520Reports%257CTransaction%2520Cost%2520Analysis%257C_____1"); 
	}
	
	@Test
	public void AdvisorFeeCap2() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#advisorfeecap.htm?TocPath=Reports%257COther%2520Reports%257C_____5"); 
	}
	
	@Test
	public void ClientSummary() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#clientsummary.htm?TocPath=Reports%257COther%2520Reports%257C_____6"); 
	}
	
	@Test
	public void PLMarkup() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#plmarkup.htm?TocPath=Reports%257COther%2520Reports%257C_____7"); 
	}
	
	@Test
	public void AdvisorFeeInvoices() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#advisorfeeinvoices.htm?TocPath=Reports%257COther%2520Reports%257C_____8"); 
	}
	
	@Test
	public void TaxLotHolding() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#taxlotholding.htm?TocPath=Reports%257COther%2520Reports%257C_____9"); 
	}
	
	@Test
	public void TaxReportingTools() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#taxreporting.htm?TocPath=Reports%257CTax%2520Reporting%2520and%2520Tools%257C_____0"); 
	}
	
	@Test
	public void TaxFomrs() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#taxform.htm?TocPath=Reports%257CTax%2520Reporting%2520and%2520Tools%257C_____1"); 
	}
	
	@Test
	public void TaxOptimizer() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#taxoptimizer.htm?TocPath=Reports%257CTax%2520Reporting%2520and%2520Tools%257C_____2"); 
	}
	
	@Test
	public void CostBasis() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#costbasis.htm?TocPath=Reports%257CTax%2520Reporting%2520and%2520Tools%257CCost%2520Basis%257C_____0"); 
	}
	
	@Test
	public void PositionsTransferBasis() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#positiontransfer.htm?TocPath=Reports%257CTax%2520Reporting%2520and%2520Tools%257CCost%2520Basis%257C_____1"); 
	}
	
	@Test
	public void ForexCostBasis() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#forexcost.htm?TocPath=Reports%257CTax%2520Reporting%2520and%2520Tools%257CCost%2520Basis%257C_____2"); 
	}
	
	@Test
	public void TransferAndPay() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#transfer.htm?TocPath=Transfer%2520%2526%2520Pay%257C_____0"); 
	}
	
	@Test
	public void TransHistory() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#transhist.htm?TocPath=Transfer%2520%2526%2520Pay%257CTransaction%2520History%257C_____0"); 
	}
	
	@Test
	public void CancelTrans() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#canceltrans.htm?TocPath=Transfer%2520%2526%2520Pay%257CTransaction%2520History%257C_____1"); 
	}
	
	@Test
	public void StopPaymentRequest() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#stoppayment.htm?TocPath=Transfer%2520%2526%2520Pay%257CTransaction%2520History%257C_____2"); 
	}
	
	@Test
	public void TransferFunds() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#transferfunds.htm?TocPath=Transfer%2520%2526%2520Pay%257CTransfer%2520Funds%257C_____0"); 
	}
	
	@Test
	public void StatusOfFunds() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#checkstatus.htm?TocPath=Transfer%2520%2526%2520Pay%257CTransfer%2520Funds%257C_____1"); 
	}
	
	@Test
	public void DepositFunds() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#deposit.htm?TocPath=Transfer%2520%2526%2520Pay%257CTransfer%2520Funds%257CDeposit%2520Funds%257C_____0"); 
	}
	
	@Test
	public void EnterDeposits() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#enterdeposits.htm?TocPath=Transfer%2520%2526%2520Pay%257CTransfer%2520Funds%257CDeposit%2520Funds%257C_____1"); 
	}
	
	@Test
	public void WithdraFunds() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#withdrawfunds.htm?TocPath=Transfer%2520%2526%2520Pay%257CTransfer%2520Funds%257CWithdraw%2520Funds%257C_____0"); 
	}
	
	@Test
	public void EnterWithdrawals() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#enterwithdrawal.htm?TocPath=Transfer%2520%2526%2520Pay%257CTransfer%2520Funds%257CWithdraw%2520Funds%257C_____1"); 
	}
	
	@Test
	public void WithdrawLimits() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#withdrawallimits.htm?TocPath=Transfer%2520%2526%2520Pay%257CTransfer%2520Funds%257CWithdraw%2520Funds%257C_____2"); 
	}
	
	@Test
	public void WithdrawFundsThirdParty() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#withdrawfunds3rdparty.htm?TocPath=Transfer%2520%2526%2520Pay%257CTransfer%2520Funds%257CWithdraw%2520Funds%257C_____3"); 
	}
	
	@Test
	public void TransferFundsInternal() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#transferinternal.htm?TocPath=Transfer%2520%2526%2520Pay%257CTransfer%2520Funds%257C_____4"); 
	}
	
	@Test
	public void ACH() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#achcanada.htm?TocPath=Transfer%2520%2526%2520Pay%257CTransfer%2520Funds%257C_____5"); 
	}
	
	@Test
	public void TransferPositions() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#transpositions.htm?TocPath=Transfer%2520%2526%2520Pay%257CTransfer%2520Positions%257C_____0"); 
	}
	
	@Test
	public void ACATSTransfers() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#acatstrans.htm?TocPath=Transfer%2520%2526%2520Pay%257CTransfer%2520Positions%257CACATS%2520Transfers%257C_____0"); 
	}
	
	@Test
	public void EnterACATSTransfers() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#enteracattransfer.htm?TocPath=Transfer%2520%2526%2520Pay%257CTransfer%2520Positions%257CACATS%2520Transfers%257C_____1"); 
	}
	
	@Test
	public void ATONTransfers() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#atontrans.htm?TocPath=Transfer%2520%2526%2520Pay%257CTransfer%2520Positions%257CATON%2520Transfers%257C_____0"); 
	}
	
	@Test
	public void EnterATONTransfers() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#enteratontrans.htm?TocPath=Transfer%2520%2526%2520Pay%257CTransfer%2520Positions%257CATON%2520Transfers%257C_____1"); 
	}
	
	@Test
	public void DRSTransfer() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#drstrans.htm?TocPath=Transfer%2520%2526%2520Pay%257CTransfer%2520Positions%257C_____3"); 
	}
	
	@Test
	public void DWACTransfer() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#dwactrans.htm?TocPath=Transfer%2520%2526%2520Pay%257CTransfer%2520Positions%257C_____4"); 
	}
	
	@Test
	public void InternationalAssetTransfer() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#internationaltran.htm?TocPath=Transfer%2520%2526%2520Pay%257CTransfer%2520Positions%257C_____5"); 
	}
	
	@Test
	public void FOPTransfer() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#foptrans.htm?TocPath=Transfer%2520%2526%2520Pay%257CTransfer%2520Positions%257CFOP%2520Transfers%257C_____0"); 
	}
	
	@Test
	public void EnterFOPTransfer() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#enterfoptrans.htm?TocPath=Transfer%2520%2526%2520Pay%257CTransfer%2520Positions%257CFOP%2520Transfers%257C_____1"); 
	}
	
	@Test
	public void InternalPosTransfer() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#internaltrans.htm?TocPath=Transfer%2520%2526%2520Pay%257CTransfer%2520Positions%257C_____7"); 
	}
	
	@Test
	public void TransferBetweenMasterAndSub() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#transpos.htm?TocPath=Transfer%2520%2526%2520Pay%257CTransfer%2520Positions%257C_____8"); 
	}
	
	@Test
	public void SavedInfo() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#savedinfo.htm?TocPath=Transfer%2520%2526%2520Pay%257C_____4"); 
	}
	
	@Test
	public void Settings() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#settings.htm?TocPath=Settings%257C_____0"); 
	}
	
	@Test
	public void AccountSettings() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#acctset.htm?TocPath=Settings%257CAccount%2520Settings%257C_____0"); 
	}
	
	@Test
	public void UserProfiles() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#profile.htm?TocPath=Settings%257CAccount%2520Settings%257CUser%2520Profiles%257C_____0"); 
	}
	
	@Test
	public void UpdateTaxForms() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#updatetaxforms.htm?TocPath=Settings%257CAccount%2520Settings%257CUser%2520Profiles%257C_____1"); 
	}
	
	@Test
	public void MiFIRRegistration() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#mifirreg.htm?TocPath=Settings%257CAccount%2520Settings%257CUser%2520Profiles%257C_____2"); 
	}
	
	@Test
	public void FinancialInfo() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#fininfo.htm?TocPath=Settings%257CAccount%2520Settings%257C_____2"); 
	}
	
	@Test
	public void Configuration() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#config.htm?TocPath=Settings%257CAccount%2520Settings%257CConfiguration%257C_____0"); 
	}
	
	@Test
	public void AccountAlias() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#accountalias.htm?TocPath=Settings%257CAccount%2520Settings%257CConfiguration%257C_____1"); 
	}
	
	@Test
	public void AccountType() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#accounttype.htm?TocPath=Settings%257CAccount%2520Settings%257CConfiguration%257C_____2"); 
	}
	
	@Test
	public void BaseCurrency() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#basecurrency.htm?TocPath=Settings%257CAccount%2520Settings%257CConfiguration%257C_____3"); 
	}
	
	@Test
	public void PricingStructure() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#pricingstructure.htm?TocPath=Settings%257CAccount%2520Settings%257CConfiguration%257C_____4"); 
	}
	
	@Test
	public void PaperTradingAccount() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#paper.htm?TocPath=Settings%257CAccount%2520Settings%257CConfiguration%257CPaper%2520Trading%2520Account%257C_____0"); 
	}
	
	@Test
	public void WhiteBranding() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#whitebrand.htm?TocPath=Settings%257CAccount%2520Settings%257CConfiguration%257CWhite%2520Branding%257C_____0"); 
	}
	
	@Test
	public void WBWebTrader() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#whiteweb.htm?TocPath=Settings%257CAccount%2520Settings%257CConfiguration%257CWhite%2520Branding%257C_____2"); 
	}
	
	@Test
	public void AuditTrail() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#audit.htm?TocPath=Settings%257CAccount%2520Settings%257CConfiguration%257C_____7"); 
	}
	
	@Test
	public void CreateAccount() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#createaccount.htm?TocPath=Settings%257CAccount%2520Settings%257CConfiguration%257CCreate%252C%2520Move%252C%2520Link%2520or%2520Partition%2520Account%257C_____0"); 
	}
	
	@Test
	public void CreateLinkedAccount() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#createlink.htm?TocPath=Settings%257CAccount%2520Settings%257CConfiguration%257CCreate%252C%2520Move%252C%2520Link%2520or%2520Partition%2520Account%257CCreate%2520a%2520Linked%2520Account%257C_____0"); 
	}
	
	@Test
	public void LinkExistingAccounts() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#linkexist.htm?TocPath=Settings%257CAccount%2520Settings%257CConfiguration%257CCreate%252C%2520Move%252C%2520Link%2520or%2520Partition%2520Account%257CLink%2520Existing%2520Accounts%257C_____0"); 
	}
	
	@Test
	public void PartitionAccount() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#partition.htm?TocPath=Settings%257CAccount%2520Settings%257CConfiguration%257CCreate%252C%2520Move%252C%2520Link%2520or%2520Partition%2520Account%257C_____6"); 
	}
	
	@Test
	public void CancelLinkRequest() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#cancellink.htm?TocPath=Settings%257CAccount%2520Settings%257CConfiguration%257CCreate%252C%2520Move%252C%2520Link%2520or%2520Partition%2520Account%257C_____7"); 
	}
	
	@Test
	public void CloseAccount() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#closeaccount.htm?TocPath=Settings%257CAccount%2520Settings%257CConfiguration%257CClose%2520Your%2520Account%257C_____0"); 
	}
	
	@Test
	public void LargeTraderID() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#largetrader.htm?TocPath=Settings%257CAccount%2520Settings%257CConfiguration%257C_____11"); 
	}
	
	@Test
	public void ManaegAdmin() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#manage.htm?TocPath=Settings%257CAccount%2520Settings%257CConfiguration%257C_____12"); 
	}
	
	@Test
	public void TradingRestrictions() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#restrictions.htm?TocPath=Settings%257CAccount%2520Settings%257CConfiguration%257C_____13"); 
	}
	
	@Test
	public void ClientAccountTemplates2() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#acctemplates.htm?TocPath=Settings%257CAccount%2520Settings%257CConfiguration%257C_____14"); 
	}
	
	@Test
	public void DebitCard() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#debit.htm?TocPath=Settings%257CAccount%2520Settings%257CConfiguration%257C_____15"); 
	}
	
	@Test
	public void MoneyManager() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#moneymgr.htm?TocPath=Settings%257CAccount%2520Settings%257CConfiguration%257CMoney%2520Manager%257C_____0"); 
	}
	
	@Test
	public void ClientQuestionairreEditor() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#clientquestionnaire.htm?TocPath=Settings%257CAccount%2520Settings%257CConfiguration%257C_____17"); 
	}
	
	@Test
	public void AdvisorQualifications2() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#advqual.htm?TocPath=Settings%257CAccount%2520Settings%257CConfiguration%257C_____18"); 
	}
	
	@Test
	public void HedgeFundMarketplace() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#hedgefundmarketplace.htm?TocPath=Settings%257CAccount%2520Settings%257CConfiguration%257C_____19"); 
	}
	
	@Test
	public void Reporting() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#reporting.htm?TocPath=Settings%257CAccount%2520Settings%257CReporting%257C_____0"); 
	}
	
	@Test
	public void FlexWebService() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#flexweb.htm?TocPath=Settings%257CAccount%2520Settings%257CReporting%257CFlex%2520Web%2520Service%257C_____0"); 
	}
	
	@Test
	public void UsersAndAccessRights() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#usersandaccess.htm?TocPath=Settings%257CAccount%2520Settings%257CUsers%2520and%2520Access%2520Rights%257C_____0"); 
	}
	
	@Test
	public void AccessRightsOverview() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#accessoverview.htm?TocPath=Settings%257CAccount%2520Settings%257CUsers%2520and%2520Access%2520Rights%257COverview%257C_____0"); 
	}
	
	@Test
	public void Users() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#users.htm?TocPath=Settings%257CAccount%2520Settings%257CUsers%2520and%2520Access%2520Rights%257CUsers%257C_____0"); 
	}
	
	@Test
	public void UserRoles() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#userroles.htm?TocPath=Settings%257CAccount%2520Settings%257CUsers%2520and%2520Access%2520Rights%257CUser%2520Roles%257C_____0"); 
	}
	
	@Test
	public void AddUsers() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#adduser.htm?TocPath=Settings%257CAccount%2520Settings%257CUsers%2520and%2520Access%2520Rights%257CUsers%257C_____2"); 
	}
	
	@Test
	public void UserSettings() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#usersettings.htm?TocPath=Settings%257CUser%2520Settings%257C_____0"); 
	}
	
	@Test
	public void LogginSettings() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#loginsettings.htm?TocPath=Settings%257CUser%2520Settings%257CLogin%2520Settings%257C_____0"); 
	}
	
	@Test
	public void SecuritySettings() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#securitysettings.htm?TocPath=Settings%257CUser%2520Settings%257CSecurity%2520Settings%257C_____0"); 
	}
	
	@Test
	public void SecureLoginSystem() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#securelogin.htm?TocPath=Settings%257CUser%2520Settings%257CSecurity%2520Settings%257CSecure%2520Login%2520System%257C_____0"); 
	}
	
	@Test
	public void SecureLoginSystemFunctions() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#loginfunctions.htm?TocPath=Settings%257CUser%2520Settings%257CSecurity%2520Settings%257CSecure%2520Login%2520System%257CSecure%2520Login%2520System%2520Functions%257C_____0"); 
	}
	
	@Test
	public void AbountSecurityDevices() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#aboutsecuritydevices.htm?TocPath=Settings%257CUser%2520Settings%257CSecurity%2520Settings%257CSecure%2520Login%2520System%257CAbout%2520Security%2520Devices%257C_____0"); 
	}
	
	@Test
	public void TradingPLatformSettings() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#tradingplatform.htm?TocPath=Settings%257CUser%2520Settings%257CTrading%2520Platform%2520Settings%257C_____0"); 
	}
	
	@Test
	public void SubscribeToMarketData() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#subscribemarket.htm?TocPath=Settings%257CUser%2520Settings%257CTrading%2520Platform%2520Settings%257CSubscribe%2520to%2520Market%2520Data%257C_____0"); 
	}
	
	@Test
	public void SubscribeToResearch() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#subscriberesearch.htm?TocPath=Settings%257CUser%2520Settings%257CTrading%2520Platform%2520Settings%257CSubscribe%2520to%2520Research%257C_____0"); 
	}
	
	@Test
	public void CommunicationsSettings() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#communication.htm?TocPath=Settings%257CUser%2520Settings%257CCommunication%2520Settings%257C_____0"); 
	}
	
	@Test
	public void ReportingSystems() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#reportingset.htm?TocPath=Settings%257CUser%2520Settings%257CReporting%2520Settings%257C_____0"); 
	}
	
	@Test
	public void InvestorsMarketplace() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#invmarket.htm?TocPath=Investors'%2520Marketplace%257C_____0"); 
	}
	
	@Test
	public void FindServices() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#findservice.htm?TocPath=Investors'%2520Marketplace%257CFind%2520Services%257C_____0"); 
	}
	
	@Test
	public void Research() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#research.htm?TocPath=Investors'%2520Marketplace%257CFind%2520Services%257CResearch%257C_____0"); 
	}
	
	@Test
	public void Tech() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#tech.htm?TocPath=Investors'%2520Marketplace%257CFind%2520Services%257CTechnology%257C_____0"); 
	}
	
	@Test
	public void BusinessDev() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#busdev.htm?TocPath=Investors'%2520Marketplace%257CFind%2520Services%257CBusiness%2520Development%257C_____0"); 
	}
	
	@Test
	public void Administration() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#admin.htm?TocPath=Investors'%2520Marketplace%257CFind%2520Services%257CAdministration%257C_____0"); 
	}
	
	@Test
	public void Investing() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#investing.htm?TocPath=Investors'%2520Marketplace%257CFind%2520Services%257CInvesting%257C_____0"); 
	}
	
	@Test
	public void Support() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#support.htm?TocPath=Support%257C_____0"); 
	}
	
	@Test
	public void MessageCenter() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#messagecenter.htm?TocPath=Support%257CMessage%2520Center%257C_____0"); 
	}
	
	@Test
	public void MCWinddow() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#messagewindow.htm?TocPath=Support%257CMessage%2520Center%257C_____1"); 
	}
	
	@Test
	public void CreateNewTicket() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#createticket.htm?TocPath=Support%257CMessage%2520Center%257C_____2"); 
	}
	
	@Test
	public void ModifyCloseTicket() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#modifyticket.htm?TocPath=Support%257CMessage%2520Center%257C_____3"); 
	}
	
	@Test
	public void ViewCorporateActions() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#viewcorp.htm?TocPath=Support%257CMessage%2520Center%257C_____4"); 
	}
	
	@Test
	public void ViewExpiredInquiries() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#viewexpired.htm?TocPath=Support%257CMessage%2520Center%257C_____5"); 
	}

	
	@Test
	public void KnowledgeBase() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/ap/advisorportal.htm#knowledge.htm?TocPath=Support%257C_____13"); 
	}
}
