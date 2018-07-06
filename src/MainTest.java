import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Map;


public class MainTest {

    @Test
    public void dealLineTest() {
        String testLine ="@null@nullyzcyzc";
        try {
            FileReader freader = new FileReader("test.txt");
            BufferedReader bReader = new BufferedReader(freader);
            testLine = bReader.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(testLine != "@null@nullyzcyzc"){
            Main.dealLine(testLine);
            Assert.assertNotNull(Main.map1);
            Assert.assertNotNull(Main.map2);
        } else {
            System.out.println("文件未成功读取！");
        }
    }

    @Test
    public void isChineseTest() {
        char testChar1 = '尹';
        char testChar2 = '志';
        char testChar3 = 'a';
        char testChar4 = ' ';
        char testChar5 = '1';
        Assert.assertTrue(Main.isChinese(testChar1));
        Assert.assertTrue(Main.isChinese(testChar2));
        Assert.assertFalse(Main.isChinese(testChar3));
        Assert.assertFalse(Main.isChinese(testChar4));
        Assert.assertFalse(Main.isChinese(testChar5));
    }

    @Test
    public void sortForWordFrequencyMapTest() {
        int last = 0;
        int next = 1;
        try {
            FileReader fileReader = new FileReader("test.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            Main.dealLine(line);
            List<Map.Entry<String,Integer>> orderList1 = Main.sortForWordFrequencyMap(Main.map1);

            for (Map.Entry<String,Integer> entry : orderList1) {
                if(last == 0 ){
                    last = entry.getValue();
                }  else {
                    next = entry.getValue();
                    Assert.assertTrue(last <= next);
                    last = next;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}