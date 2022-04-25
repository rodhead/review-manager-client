package org.block.analytics.feedback.service;

import com.opencsv.CSVParser;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVReaderHeaderAware;
import com.opencsv.exceptions.CsvException;
import org.block.analytics.core.constant.ApplicationConstants;
import org.block.analytics.core.utility.ApllicationPropertiesValueBinder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
public class FeedbackUploadService {

    @Autowired
    ApllicationPropertiesValueBinder apllicationPropertiesValueBinder;

    public String initialFeedbackProcess(MultipartFile file) throws IOException {
        Reader reader = null;
        CSVReader csvReader = null;
        List<String> headers = new ArrayList<>();
        JSONArray responseArray ;
        JSONObject responseObject = new JSONObject();
        Optional<Object> fileType = Optional.ofNullable(file.getOriginalFilename()).filter(
                f -> f.contains(".")
        ).map(f-> f.substring(file.getOriginalFilename().lastIndexOf(".")+1));
        try{
            if(fileType.get().toString().equalsIgnoreCase(ApplicationConstants.FILE_TYPE_CSV)){
                reader = new InputStreamReader(file.getInputStream());
                csvReader = new CSVReader(reader);
                String[] nextRecord = csvReader.readNext();
                responseArray =  new JSONArray(Arrays.asList(nextRecord));
                responseObject.put("csv_header",responseArray);
                return responseObject.toString();
            }else{
                XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
                XSSFSheet sheet = workbook.getSheetAt(0);
                headers.add(sheet.getRow(0).toString());
                for(int i=0; i<sheet.getPhysicalNumberOfRows();i++) {
                    XSSFRow row = sheet.getRow(i);
                    for (int j = 0; j < row.getPhysicalNumberOfCells(); j++) {
                        System.out.print(row.getCell(j) + " ");
                    }
                    System.out.println("");
                }
            }

        }
        catch (IOException | CsvException | JSONException e) {
            e.printStackTrace();
        }
        finally {
            reader.close();
            csvReader.close();
        }
        return headers.toString();
    }

    public String getStartupDataForUpload() throws JSONException {
        JSONObject responseData = new JSONObject();
        responseData.put("uploadPermittedColumnName" , apllicationPropertiesValueBinder.getUploadColumnNames());
        return responseData.toString();
    }
}
