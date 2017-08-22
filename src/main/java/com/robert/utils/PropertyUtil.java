package com.robert.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * 读取配置文件
 * 
 * @author Administrator
 *
 */
public class PropertyUtil
{
    // singleģʽ
    private static PropertyUtil propertyUtil = new PropertyUtil();

    private PropertyUtil()
    {
    }

    public static PropertyUtil getInstance()
    {
        return propertyUtil;
    }
    /**
     * @param key
     * @return
     * @throws IOException
     */
    public String getVal(String key) throws IOException
    {
        Properties props = new Properties();
        FileInputStream in = null;
        String value = "";
        try
        {
            props.load(this.getClass().getResourceAsStream("/slightServerURL.properties"));
            value = props.getProperty(key);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (null != in)
            {
                in.close();
            }
        }
        return value;
    }

}