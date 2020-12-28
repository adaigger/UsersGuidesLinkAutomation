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


public class BrokerPortal {

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
	public void BPUserGuide() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/bp/Default.htm#broker_home.htm?TocPath=_____1"); 
	}
	
	@Test
	public void GettingStarted() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/bp/Default.htm#brokerportal.htm?TocPath=Getting%2520Started%257C_____0"); 
	}
	
	@Test
	public void LoggingInAndOut() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/bp/Default.htm#login.htm?TocPath=Getting%2520Started%257C_____1"); 
	}
	
	@Test
	public void IconReference() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/bp/Default.htm#iconreference.htm?TocPath=Getting%2520Started%257C_____2"); 
	}
	
	@Test
	public void UsingAccountSelector() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/bp/Default.htm#accountselector.htm?TocPath=Getting%2520Started%257C_____3"); 
	}
	
	@Test
	public void NavigateBetweenScreens() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/bp/Default.htm#navigating.htm?TocPath=Getting%2520Started%257C_____4"); 
	}
	
	@Test
	public void GettingHelp() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/bp/Default.htm#gettinghelp.htm?TocPath=Getting%2520Started%257C_____5"); 
	}
	
	@Test
	public void ClientApplications() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/bp/Default.htm#ClientApplications.htm?TocPath=Getting%2520Started%257CClient%2520Applications%257C_____0"); 
	}
	
	@Test
	public void StepsInApp() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/bp/Default.htm#StepsInApplicationProcess.htm?TocPath=Getting%2520Started%257CClient%2520Applications%257C_____1"); 
	}
	
	@Test
	public void IviteClientElectronicApp() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/bp/Default.htm#InviteClientStartElectronicApp.htm?TocPath=Getting%2520Started%257CClient%2520Applications%257C_____2"); 
	}
	
	@Test
	public void StartSemiEclectronicApp() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/bp/Default.htm#StartSemielectronicApp.htm?TocPath=Getting%2520Started%257CClient%2520Applications%257C_____3"); 
	}
	
	@Test
	public void BrokerClientApproval() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/bp/Default.htm#BrokerClientApproval.htm?TocPath=Getting%2520Started%257CClient%2520Applications%257C_____4"); 
	}
	
	@Test
	public void Dashboard() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/bp/Default.htm#dashboard.htm?TocPath=Dashboard%257C_____0"); 
	}
	
	@Test
	public void PendingItems() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/bp/Default.htm#PendingItems.htm?TocPath=Dashboard%257C_____1"); 
	}
	
	@Test
	public void ViewRecentActivity() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/bp/Default.htm#ViewRecentActivity.htm?TocPath=Dashboard%257C_____2"); 
	}
	
	@Test
	public void StartNewClientApp() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/bp/Default.htm#NewClientApplication.htm?TocPath=Dashboard%257C_____3"); 
	}
	
	@Test
	public void Notifications() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/bp/Default.htm#Notifications.htm?TocPath=Dashboard%257C_____4"); 
	}
	
	@Test
	public void AssetsUnderManagement() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/bp/Default.htm#AUM.htm?TocPath=Dashboard%257C_____5"); 
	}
	
	@Test
	public void MasterAccountBalance() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/bp/Default.htm#MasterAccountBalance.htm?TocPath=Dashboard%257C_____6"); 
	}
	
	@Test
	public void Calendar() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/bp/Default.htm#CalendarMini.htm?TocPath=Dashboard%257C_____7"); 
	}
	
	@Test
	public void Workflows() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/bp/Default.htm#CRM%20Workflow.htm?TocPath=Dashboard%257C_____8"); 
	}
	
	@Test
	public void Notes() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/bp/Default.htm#Notes.htm?TocPath=Dashboard%257C_____9"); 
	}
	
	@Test
	public void Documents() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/bp/Default.htm#Documents.htm?TocPath=Dashboard%257C_____10"); 
	}
	
	@Test
	public void Contacts() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/bp/Default.htm#Contacts.htm?TocPath=Contacts%257C_____0"); 
	}
	
	@Test
	public void AddImportContacts() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/bp/Default.htm#EnterNewRecord.htm?TocPath=Contacts%257C_____1"); 
	}
	
	@Test
	public void OpenIndividualContact() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/bp/Default.htm#ViewOpenIndContact.htm?TocPath=Contacts%257C_____2"); 
	}
	
	@Test
	public void ViewContactInfo() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/bp/Default.htm#ContactInformation.htm?TocPath=Contacts%257C_____3"); 
	}
	
	@Test
	public void FeeAdmin() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/bp/Default.htm#FeeAdmin.htm?TocPath=Fee%2520Administration%257C_____0"); 
	}
	
	@Test
	public void ConfigureClientFees() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/bp/Default.htm#ConfigureFees.htm?TocPath=Fee%2520Administration%257C_____1"); 
	}
	
	@Test
	public void ClientFeeTemplates() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/bp/Default.htm#ClientFeeTemplates.htm?TocPath=Fee%2520Administration%257CClient%2520Fee%2520Templates%257C_____0"); 
	}
	
	@Test
	public void ConfigureFeeTemplates() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/bp/Default.htm#ConfigureClientFeeTemplates.htm?TocPath=Fee%2520Administration%257CClient%2520Fee%2520Templates%257C_____1"); 
	}
	
	@Test
	public void Invoicing() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/bp/Default.htm#Invoicing.htm?TocPath=Fee%2520Administration%257CInvoicing%257C_____0"); 
	}
	
	@Test
	public void CreateInvoice() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/bp/Default.htm#CreateInvoice.htm?TocPath=Fee%2520Administration%257CInvoicing%257C_____1"); 
	}
	
	@Test
	public void ViewPendingInvoiceRequest() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/bp/Default.htm#ViewUpdateInvoices.htm?TocPath=Fee%2520Administration%257CInvoicing%257C_____2"); 
	}
	
	@Test
	public void DownloadInvoice() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/bp/Default.htm#UploadInvoices.htm?TocPath=Fee%2520Administration%257CInvoicing%257C_____3"); 
	}
	
	@Test
	public void Tools() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/bp/Default.htm#Tools.htm?TocPath=Tools%257C_____0"); 
	}
	
	@Test
	public void ClientDataQueries() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/bp/Default.htm#Queries.htm?TocPath=Tools%257CClient%2520Data%2520Queries%257C_____0"); 
	}
	
	@Test
	public void RunPerformanceReport() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/bp/Default.htm#Queries_Performance.htm?TocPath=Tools%257CClient%2520Data%2520Queries%257C_____1"); 
	}
	
	@Test
	public void RunSymbolReport() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/bp/Default.htm#Queries_Symbol.htm?TocPath=Tools%257CClient%2520Data%2520Queries%257C_____2"); 
	}
	
	@Test
	public void RunTransactionCountReport() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/bp/Default.htm#Queries_Transaction.htm?TocPath=Tools%257CClient%2520Data%2520Queries%257C_____3"); 
	}
	
	@Test
	public void Groups() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/bp/Default.htm#Groups.htm?TocPath=Groups%257C_____0"); 
	}
	
	@Test
	public void ViewGroupMembers() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/bp/Default.htm#CRMGroupsViewMembers.htm?TocPath=Groups%257C_____1"); 
	}
	
	@Test
	public void EditGroup() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/bp/Default.htm#CRMGroupsAddGroup.htm?TocPath=Groups%257C_____2"); 
	}
	
	@Test
	public void TransactionHistory() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/bp/Default.htm#TransactionHistory.htm?TocPath=Transaction%2520History%257C_____0"); 
	}
	
	@Test
	public void ViewTransactionHistory() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/bp/Default.htm#ViewTransactionHistory.htm?TocPath=Transaction%2520History%257C_____1"); 
	}
	
	@Test
	public void ViewTransactionDetails() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/bp/Default.htm#ViewTransactionDetails.htm?TocPath=Transaction%2520History%257C_____2"); 
	}
	
	@Test
	public void YourCalendar() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/bp/Default.htm#Calendar.htm?TocPath=Calendar%257C_____0"); 
	}
	
	@Test
	public void EditCalendarEvents() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/bp/Default.htm#AddEditDeleteCalendar.htm?TocPath=Calendar%257C_____1"); 
	}
	
	@Test
	public void Email() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/bp/Default.htm#crm_mailbox.htm?TocPath=Email%257C_____0"); 
	}
	
	@Test
	public void CreateMailbox() throws InterruptedException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		TestLinks("https://guides.interactivebrokers.com/bp/Default.htm#CRMMailboxCreate.htm?TocPath=Email%257C_____1"); 
	}
	
}
