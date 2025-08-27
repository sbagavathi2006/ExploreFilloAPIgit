package utilities;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

import baseclass.BaseClass;

public class ExcelReaderFillo extends BaseClass{
	
	   String filePath;
	
	    @DataProvider(name = "getExcelData")
	    public Object[][] getExcelDataFromSheet(String sheetName) {
	        Connection connection = null;
	        Recordset recordset = null;
      
	        try {
		        Fillo fillo = new Fillo();
//		        configReader = new ConfigReader();
//				prop = configReader.init_prop();
//		        filePath = prop.getProperty("testDataPath");
                connection = fillo.getConnection(filePath);
                recordset = connection.executeQuery("select * from "+ sheetName +"");
                int numOfRows = recordset.getCount();
                int numOfColumns = recordset.getFieldNames().size();
                Object[][] testData = new Object [numOfRows][numOfColumns];
                
                int row = 0;
                while (recordset.next()) {
                    int col = 0;
                    for (String fieldName : recordset.getFieldNames()) {
                    	testData[row][col] = recordset.getField(fieldName); // put value in right cell
                        col++;
                    }
                    row++;
                }
                return testData;

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (recordset != null) {
                    recordset.close();
                }
                if (connection != null) {
                    connection.close();
                }
            }
			return null;               
                       
	    }

	}
