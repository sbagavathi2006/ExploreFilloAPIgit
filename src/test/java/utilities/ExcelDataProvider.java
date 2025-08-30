package utilities;

import org.testng.annotations.DataProvider;

public class ExcelDataProvider {
	
	 static {
	        // Load Excel data once at class load
	        ExcelReaderFillo.loadExcelData();
	    }
	
    @DataProvider(name = "homeDDTestData")	//data provider annotation to access the test data.
    public Object[][] homeDDTestData() {
        return ExcelReaderFillo.getExcelDataFromSheet("HomeDD"); //Calling a method from Excel Reader class
    }
    
    @DataProvider(name = "loginTestData")	//data provider annotation to access the test data.
    public Object[][] loginTestData(){
    	return ExcelReaderFillo.getExcelDataFromSheet("login");	//Calling a method from Excel Reader class
    }

}
