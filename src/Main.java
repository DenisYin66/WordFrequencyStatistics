/**
* @Description:    中文词频统计
* @Author:         yzc
* @CreateDate:     2018/7/6 10:31
* @UpdateUser:     yzc
* @UpdateDate:     2018/7/6 10:31
* @UpdateRemark:   修改内容
* @Version:        1.0
*/
import org.omg.IOP.IORHelper;

import java.io.*;
import java.util.*;

public class Main {

    //存储未排序的词频
    static Map<String,Integer> map1 = new HashMap<String, Integer>();
    static Map<String,Integer> map2 = new HashMap<String, Integer>();
    static Map<String,Integer> map3 = new HashMap<String, Integer>();
    static List<Map.Entry<String,Integer>> orderList1;
    static List<Map.Entry<String,Integer>> orderList2;

    public static void main(String[] args) {
        try
        {
            FileReader fileReader = new FileReader("test.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            while(line!=null)
            {
                dealLine(line);                          //处理行数据，存入map当中. key:汉字 value:汉字出现的频数
                line = bufferedReader.readLine();
            }
            //while循环结束后便可得到一个无排序的map键值对

            bufferedReader.close();
            fileReader.close();
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
            System.out.println("文件未找到！请检查文件路径是否正确！");
            System.exit(0);
        } catch (IOException e)
        {
            e.printStackTrace();
            System.out.println("发生IO流错误！");
            System.exit(0);
        }

        orderList1 =  sortForWordFrequencyMap(map1);
        orderList2 = sortForWordFrequencyMap(map2);

        try
        {
            outPutByOrderList(orderList1,"context1");
            outPutByOrderList(orderList2,"context2");
        } catch (IOException e)
        {
            e.printStackTrace();
        }

    }

/**
* 处理bufferedReader读取出来的行数据
* @author      yzc
* @date        2018/7/6 10:57
*/
    public static  void dealLine(String line)
    {
        int LineLength = line.length();
        if(LineLength > 5)
        {
            for(int i=0 ;i < LineLength ;i++ )
            {
                char c = line.charAt(i);
                if(isChinese(c))
                {
                    String cTemp = String.valueOf(c);
                    try
                    {
                        if (map1.containsKey(cTemp)) {
                            map1.put(cTemp, map1.get(cTemp).intValue() + 1);
                        } else {
                            map1.put(cTemp, 1);
                        }
                     } catch (NullPointerException e)
                    {
                        e.printStackTrace();
                        System.out.println("出现空指针异常！");
                    } catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }

            for(int i=0 ;i < LineLength - 1 ;i++ )
            {
                String s = line.substring(i,i+2);
                char temp1 = s.charAt(0);
                char temp2 = s.charAt(1);
                if( isChinese(temp1) && isChinese(temp2) )
                {
                    try
                    {
                        if (map2.containsKey(s)) {
                            map2.put(s, map2.get(s).intValue() + 1);
                        } else {
                            map2.put(s, 1);
                        }
                    } catch (NullPointerException e)
                    {
                        e.printStackTrace();
                        System.out.println("出现空指针异常！");
                    } catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

/**
* 判断是否为中文汉字
* @author      yzc
* @date        2018/7/6 11:33
*/
    public static  boolean isChinese(char x)
    {
        return ( (x >= '\u4e00' && x <= '\u9fa5')  ? true : false);
    }

/**
* 排序
* @author      yzc
* @date        2018/7/6 11:43
*/
    public static List<Map.Entry<String,Integer>> sortForWordFrequencyMap (Map<String,Integer> map) {
        List<Map.Entry<String, Integer>> orderList = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
        Collections.sort(orderList, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });
        return orderList;
    }

/**
* 输出已排序好的列表至文本文件中
* @author      yzc
* @param       list : 待输出的已完成排序的List
* @param       fileName : 输出的文件名
* @exception   IOException
* @date        2018/7/6 15:11
*/
    public static void outPutByOrderList(List<Map.Entry<String,Integer>> list,String fileName) throws IOException
    {
        FileWriter fwriter = new FileWriter(fileName);
        for (Map.Entry<String,Integer> entry : list)
        {
            fwriter.write(entry.getKey() + " " + entry.getValue() + "\r\n");
        }
        fwriter.close();
    }
}
