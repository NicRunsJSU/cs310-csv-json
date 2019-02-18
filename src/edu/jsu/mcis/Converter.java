package edu.jsu.mcis;

import java.io.*;
import java.util.*;
import com.opencsv.*;
import org.json.simple.*;
import org.json.simple.parser.*;

public class Converter {
    
    /*
    
        Consider the following CSV data:
        
        "ID","Total","Assignment 1","Assignment 2","Exam 1"
        "111278","611","146","128","337"
        "111352","867","227","228","412"
        "111373","461","96","90","275"
        "111305","835","220","217","398"
        "111399","898","226","229","443"
        "111160","454","77","125","252"
        "111276","579","130","111","338"
        "111241","973","236","237","500"
        
        The corresponding JSON data would be similar to the following (tabs and
        other whitespace have been added for clarity).  Note the curly braces,
        square brackets, and double-quotes!  These indicate which values should
        be encoded as strings, and which values should be encoded as integers!
        
        {
            "colHeaders":["ID","Total","Assignment 1","Assignment 2","Exam 1"],
            "rowHeaders":["111278","111352","111373","111305","111399","111160",
            "111276","111241"],
            "data":[[611,146,128,337],
                    [867,227,228,412],
                    [461,96,90,275],
                    [835,220,217,398],
                    [898,226,229,443],
                    [454,77,125,252],
                    [579,130,111,338],
                    [973,236,237,500]
            ]
        }
    
        Your task for this program is to complete the two conversion methods in
        this class, "csvToJson()" and "jsonToCsv()", so that the CSV data shown
        above can be converted to JSON format, and vice-versa.  Both methods
        should return the converted data as strings, but the strings do not need
        to include the newlines and whitespace shown in the examples; again,
        this whitespace has been added only for clarity.
    
        NOTE: YOU SHOULD NOT WRITE ANY CODE WHICH MANUALLY COMPOSES THE OUTPUT
        STRINGS!!!  Leave ALL string conversion to the two data conversion
        libraries we have discussed, OpenCSV and json-simple.  See the "Data
        Exchange" lecture notes for more details, including example code.
    
    */
    
    @SuppressWarnings("unchecked")
    public static String csvToJson(String csvString) {
        
        String results = "";
        
        try {
            
            CSVReader reader = new CSVReader(new StringReader(csvString));
            List<String[]> full = reader.readAll();
            Iterator<String[]> iterator = full.iterator();
            
            // INSERT YOUR CODE HERE
            
            //Object Initialization
            JSONObject csvNowJson = new JSONObject(); 
            //Array Initialization
            JSONArray colHeaders = new JSONArray(); 
            JSONArray rowHeaders = new JSONArray(); 
            JSONArray data = new JSONArray();
            String[] items;
            
            
                    
            
            //Other Initializers
           
            String jsonString = "";
            int itemCounter = 0;
            //Constants
            final int NUMBER_OF_HEADERS = 5;
            
            
            while (iterator.hasNext()) {
                                
                items = (iterator.next());
                
                    // Col Headers
                
                    
                    if (itemCounter < 1) {
                        
                        for (int i = 0; i < items.length; ++i){

                        colHeaders.add(items[i]);
                        
                        }
                    }

                    else {

                        rowHeaders.add(items[0]);
                        
                        ArrayList<Integer> listOfData = new ArrayList();
                        
                        for (int q = 1; q < items.length; ++q) {
                                                        
                            listOfData.add(Integer.parseInt(items[q]));
                              
                        }
                        data.add(listOfData);

                    }

                    // Data
                    
                
        
                //Increment items every loop
                ++itemCounter;
               
                }
            
                csvNowJson.put("rowHeaders", rowHeaders);
                csvNowJson.put("colHeaders", colHeaders);
                csvNowJson.put("data", data);
                
                results = JSONValue.toJSONString(csvNowJson);
        }  
        
        catch(Exception e) { return e.toString(); }
        
        return results.trim();
        
    }
    
    public static String jsonToCsv(String jsonString) {
        
        String results = "";
        
        try {
            
            StringWriter writer = new StringWriter();
            CSVWriter csvWriter = new CSVWriter(writer, ',', '"', '\n');
            
            
            
            // INSERT YOUR CODE HERE
            
            /*  Attemptimng to Iterate through the String and seperate everything
                into Arrays
            */
            //Seperating into colHeaders
            
            final int NUM_PER_ARRAY = 5;
            JSONObject obj;
            
            JSONArray arr;
            JSONArray arrData;
            JSONArray arrRow;
            
            String[] stuff = new String[5];
            String[] colHeaders = new String[5];
            String[] data = new String[5];
            String[] transfer = new String[4];
           
            
            
            JSONParser parser = new JSONParser();
            obj = (JSONObject) parser.parse(jsonString);
            
            //Attempt to populate String with colHeaders
            
            arr = (JSONArray) obj.get("colHeaders");
            arrData = (JSONArray) obj.get("data");
            arrRow = (JSONArray) obj.get("rowHeaders");
            
            //System.out.println(arr);
            
            for (int i = 0; i < NUM_PER_ARRAY; ++i) {
                
                colHeaders[i] = arr.get(i).toString();
            }
            //System.out.println(Arrays.toString(colHeaders));
            
            String csvString = colHeaders.toString();
            
            // Attempt to Populate String With rowHeader and data
            
            for (int i = 0; i < NUM_PER_ARRAY; ++i) {
                
                if ( i == 0) {
                    
                    stuff[i] = arrRow.get(i).toString();
                }
                else {
                    
                    for (int q = 0; q < 4; ++q) {
                        
                        transfer[q] =  arrData.get(i).toString();  
                        System.out.print(Arrays.toString(transfer));
                        stuff[i] = transfer[q];
                    }
                    
                } 
            }
            //System.out.println(Arrays.toString(stuff));
        }
        
        catch(Exception e) { return e.toString(); }
        
        return results.trim();
        
    }

}