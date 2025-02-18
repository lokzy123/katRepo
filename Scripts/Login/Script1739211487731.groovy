import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

WebUI.openBrowser('')

WebUI.navigateToUrl('https://www.makemytrip.com/')

WebUI.click(findTestObject('Object Repository/Page_MakeMyTrip - 1 Travel Website 50 OFF o_75db21/span_ENG_commonModal__close'))

WebUI.click(findTestObject('Object Repository/Page_MakeMyTrip - 1 Travel Website 50 OFF o_75db21/span_Hotels'))

WebUI.click(findTestObject('Object Repository/Page_MakeMyTrip.com Save upto 60 on Hotel B_affcea/span_Homestays  Villas'))

WebUI.click(findTestObject('Object Repository/Page_MakeMyTrip - 1 Travel Website 50 OFF o_75db21/span_Holiday Packages'))

WebUI.click(findTestObject('Object Repository/Page_Holiday Packages, Indian Holidays, Hon_9f5851/span_Trains'))

WebUI.click(findTestObject('Object Repository/Page_Online Train Ticket Booking, IRCTC Tic_07822b/span_Buses'))

WebUI.click(findTestObject('Object Repository/Page_Online Bus Ticket Booking, Book Confir_a0abd1/span_Cabs'))

WebUI.click(findTestObject('Object Repository/Page_Online Cab Booking - Book Outstation C_1a3eba/span_Forex Card  Currency'))

