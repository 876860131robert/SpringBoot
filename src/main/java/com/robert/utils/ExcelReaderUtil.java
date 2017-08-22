package com.robert.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 * 读取excel工具
 * 
 * @author lWX430058
 *
 */
public class ExcelReaderUtil
{

    // single模式
    private static ExcelReaderUtil excelReaderUtil = new ExcelReaderUtil();

    private POIFSFileSystem fs;
    private HSSFWorkbook wb;
    private HSSFRow row;

    private ExcelReaderUtil()
    {
    }

    public static ExcelReaderUtil getInstance()
    {
        return excelReaderUtil;
    }

    /**
     * 读取Excel数据内容
     * 
     * @param is 文件
     * @return Map
     */
    public Map<Integer, String> readExcelContent(InputStream is)
    {
        Map<Integer, String> content = new HashMap<Integer, String>();
        String str = "";
        try
        {
            fs = new POIFSFileSystem(is);
            wb = new HSSFWorkbook(fs);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        int countRow = 1;
        for (int ii = 0; ii < wb.getNumberOfSheets(); ii++)
        {
            HSSFSheet sheet = wb.getSheetAt(ii);
            int rowNum = sheet.getLastRowNum();
            row = sheet.getRow(0);
            if (row != null)
            {
                int colNum = row.getPhysicalNumberOfCells();
                for (int i = 1; i <= rowNum; i++)
                {
                    // 取得源有excel Sheet的行
                    row = sheet.getRow(i);
                    if (row != null)
                    {
                        int j = 0;
                        while (j < colNum)
                        {
                            HSSFCell c = row.getCell(j);
                            if (c == null)
                            {
                                j++;
                                continue;
                            }
                            boolean isMerge = isMergedRegion(sheet, i, c.getColumnIndex());
                            // 判断是否具有合并单元格
                            if (isMerge)
                            {
                                String rs = getMergedRegionValue(sheet, row.getRowNum(), c.getColumnIndex());
                                str += rs + ",";
                            }
                            else
                            {
                                str += getCellFormatValue(c) + ",";
                            }
                            j++;
                        }
                        str = str.substring(0, str.length() - 1);
                        content.put(countRow, str);
                        countRow++;
                        str = "";
                    }
                }
            }
        }
        return content;
    }

    /**
     * 根据HSSFCell类型设置数据
     * 
     * @param cell
     * @return string
     */
    private String getCellFormatValue(HSSFCell cell)
    {
        String cellvalue = "";
        if (cell != null)
        {
            // 判断当前Cell的Type
            switch (cell.getCellType())
            {
            // 如果当前Cell的Type为NUMERIC
                case HSSFCell.CELL_TYPE_NUMERIC:
                case HSSFCell.CELL_TYPE_FORMULA:
                {
                    // 判断当前的cell是否为Date
                    if (HSSFDateUtil.isCellDateFormatted(cell))
                    {
                        // 如果是Date类型则，转化为Data格式
    
                        // 方法1：这样子的data格式是带时分秒的：2011-10-12 0:00:00
                        // cellvalue = cell.getDateCellValue().toLocaleString();
    
                        // 方法2：这样子的data格式是不带带时分秒的：2011-10-12
                        Date date = cell.getDateCellValue();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        cellvalue = sdf.format(date);
    
                    }
                    // 如果是纯数字
                    else
                    {
                        // 取得当前Cell的数值
                        cellvalue = String.valueOf(cell.getNumericCellValue());
                    }
                    break;
                }
                // 如果当前Cell的Type为STRIN
                case HSSFCell.CELL_TYPE_STRING:
                    // 取得当前的Cell字符串
                    cellvalue = cell.getRichStringCellValue().getString();
                    break;
                // 默认的Cell值
                default:
                    cellvalue = "-";
            }
        }
        else
        {
            cellvalue = "-";
        }
        return cellvalue;

    }

    /**
     * 判断指定的单元格是否是合并单元格
     * 
     * @param sheet
     * @param row 行下标
     * @param column 列下标
     * @return boolean
     */
    private boolean isMergedRegion(Sheet sheet, int sheetrow, int column)
    {
        int sheetMergeCount = sheet.getNumMergedRegions();
        for (int i = 0; i < sheetMergeCount; i++)
        {
            CellRangeAddress range = sheet.getMergedRegion(i);
            int firstColumn = range.getFirstColumn();
            int lastColumn = range.getLastColumn();
            int firstRow = range.getFirstRow();
            int lastRow = range.getLastRow();
            if (sheetrow >= firstRow && sheetrow <= lastRow)
            {
                if (column >= firstColumn && column <= lastColumn)
                {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 获取合并单元格的值
     * @param sheet excel表格
     * @param sheetrow 行
     * @param column 列
     * @return string
     */
    public String getMergedRegionValue(Sheet sheet, int sheetrow, int column)
    {
        int sheetMergeCount = sheet.getNumMergedRegions();

        for (int i = 0; i < sheetMergeCount; i++)
        {
            CellRangeAddress ca = sheet.getMergedRegion(i);
            int firstColumn = ca.getFirstColumn();
            int lastColumn = ca.getLastColumn();
            int firstRow = ca.getFirstRow();
            int lastRow = ca.getLastRow();

            if (sheetrow >= firstRow && sheetrow <= lastRow)
            {

                if (column >= firstColumn && column <= lastColumn)
                {
                    Row fRow = sheet.getRow(firstRow);
                    Cell fCell = fRow.getCell(firstColumn);
                    return getCellValue(fCell);
                }
            }
        }

        return null;
    }

    /**
     * 获取单元格的值
     * 
     * @param cell 单元格
     * @return string
     */
    public String getCellValue(Cell cell)
    {

        if (cell == null)
        {
            return "";
        }
        if (cell.getCellType() == Cell.CELL_TYPE_STRING)
        {

            return cell.getStringCellValue();

        }
        else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN)
        {

            return String.valueOf(cell.getBooleanCellValue());

        }
        else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA)
        {

            return cell.getCellFormula();

        }
        else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC)
        {

            return String.valueOf(cell.getNumericCellValue());

        }
        return "";
    }

//  public static void main(String[] args)
//    {
//        try
//        {
//            // 对读取Excel表格标题测试
//            ExcelReaderUtil excelReader = new ExcelReaderUtil();
//            // 对读取Excel表格内容测试
//            InputStream is2 = new FileInputStream("D:\\123.xls");
//            Map<Integer, String> map = excelReader.readExcelContent(is2);
//            for (int i = 1; i <= map.size(); i++)
//            {
//                System.out.println(map.get(i));
//            }
//        }
//        catch (FileNotFoundException e)
//        {
//            System.out.println("未找到指定路径的文件!");
//            e.printStackTrace();
//        }
//    }
}