import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.openBrowser('')

WebUI.navigateToUrl('https://www.makemytrip.com/')

WebUI.waitForElementVisible(findTestObject('Object Repository/Main Page/Page_MakeMyTrip - 1 Travel Website 50 OFF o_75db21/span_ENG_commonModal__close'), 30, FailureHandling.OPTIONAL)

// WebUI.click(findTestObject('Object Repository/Main Page/Page_MakeMyTrip - 1 Travel Website 50 OFF o_75db21/span_ENG_commonModal__close'))
