package utilities;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.DataProvider;

import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

public class ExcelReaderFillo{
	
		private static Map<String, Object[][]> sheetDataCache = new HashMap<>();
		private static boolean isDataLoaded = false;
		
	   //Load all sheets into memory
	    public static void loadExcelData() { 
	    	if(isDataLoaded) return; //If the data already loaded.
	        Connection connection = null; 	//To connect to excel file
	        Recordset recordset = null; 	//To hold query result
      
	        try {
		        Fillo fillo = new Fillo(); 	//Fillo Object creation 
		        ConfigReader configReader = new ConfigReader(); 	//configReader object creation to read config.properites
                connection = fillo.getConnection(configReader.getFilePath()); //Getting a excel filepath from config reader and opens a connection to the excel
                
                //Get all sheets
                for(String sheetName : connection.getMetaData().getTableNames()) {                	
	                recordset = connection.executeQuery("select * from "+ sheetName +""); //Fetch all the rows and columns from a sheet
	                int numOfRows = recordset.getCount();	//Get row count
	                int numOfColumns = recordset.getFieldNames().size();	//Get column/headers count
	                
	                // Create 2D array to hold sheet data
	                Object[][] testData = new Object [numOfRows][numOfColumns];	//2D array holds value from the excel sheet
	                int row = 0;
		                while (recordset.next()) {	//loop through each row in the recordset
		                    int col = 0;
		                    for (String fieldName : recordset.getFieldNames()) { 	//for each row, loop through all field/column names
		                    	testData[row][col] = recordset.getField(fieldName); // put value in right cell
		                        col++;
		                    }
		                    row++;
		                }
		                
                //Cache the data for the sheetName
                sheetDataCache.put(sheetName, testData);
                recordset.close();
                }
                
                isDataLoaded = true;
            } 
                catch (Exception e) {	//Catch if any exception happens
                e.printStackTrace();
            } finally {
                if (recordset != null) {	
                    recordset.close();	//Close recordset
                }
                if (connection != null) {
                    connection.close();	//Close connection.
                }
            }                     
	    }
	    
	    // Retrieve data for a specific sheet
	    public static Object[][] getExcelDataFromSheet(String sheetName) {
	        if (!isDataLoaded) {
	            loadExcelData(); 
	        }
	        return sheetDataCache.get(sheetName);
	    }

	}
