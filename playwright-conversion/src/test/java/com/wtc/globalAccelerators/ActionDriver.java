package com.wtc.globalAccelerators;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.apache.commons.collections.map.MultiValueMap;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

/**
 * Converted from Selenium to Playwright
 * Handles test data processing and metadata management
 */
public class ActionDriver extends DriverMethods {

    public void getWebdriverMethods(String MetadataFile, String Datafile)
            throws IOException, InvalidFormatException {
        MultiValueMap MultiValueMap = null;
        try {
            String meta = Utils.loader.getResource(Utils.properties.getProperty(MetadataFile)).getPath();
            MultiValueMap = appReaderUtil.getMetaDataValues(meta);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Unable to get multimap");
        }
        
        try {
            if ((filePath == null) || (filePath.length() == 0)) {
                String data = Utils.loader.getResource(Utils.properties.getProperty(Datafile)).getPath();
                dataList = appReaderUtil.getFieldDataValues(data);
            } else {
                dataList = appReaderUtil.getFieldDataValues(filePath);
            }
            dataListNum = dataList.size();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        String str[] = script_id.split("_");
        System.out.println("map==" + MultiValueMap);
        ArrayList<?> row = new ArrayList<Object>();
        int num = 0;
        num = Integer.parseInt(str[1]);
        int i = 1;
        
        while (num > 0) {
            row = (ArrayList<?>) MultiValueMap.get(String.valueOf(i));
            i++;
            Iterator<?> itr = row.iterator();
            while (itr.hasNext()) {
                String val = (String) itr.next();
                int firstOcc = val.indexOf("&&");
                String key = val.substring(0, firstOcc);
                String value = val.substring(firstOcc + 2);
                metaDataMap.put(key, value);
            }
            num--;
        }
    }
    
    public void processRequest(int x1, int rowCount, HashMap<String, String> dataListMap) 
            throws InterruptedException, FileNotFoundException, IOException, InvalidFormatException {
        int count = 0;
        System.out.println("Processing request");
        fieldDataValueMap.clear();
        keys = dataListMap.keySet();
        Iterator keyItr = keys.iterator();
        int k1 = 0;
        key = null;
        
        while(keyItr.hasNext()){
            if(x1 > 0){
                for(int i1 = 0; i1 < x1; i1++){
                    key = (String) keyItr.next();
                    continue;
                }
            }
            break;
        }
        
        while (keyItr.hasNext()) {                
            if(x1 < rowCount){
                key = (String) keyItr.next();
                fieldDataValueMap.put(key, dataListMap.get(key));
                x1++;
                if(x1 == rowCount){
                    x1 = 0;
                    break;
                }
            }
        }    
    }
}
