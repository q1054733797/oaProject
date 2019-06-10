package com.project.oa;

import com.project.oa.base.mapper.TestMapper;
import com.project.oa.base.util.ExcelUtil;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName: Test02
 * @Author: zhanghongkai
 * @Date: Create in 2019/6/10 10:45
 * @Version: 1.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class Test02 {
    @Autowired
    private TestMapper testMapper;

    @Test
    public void test() throws IOException {
        File file = new File("F:/test.xls");
        InputStream is = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile("file", file.getName(), "text/plain", IOUtils.toByteArray(is));
        List<List<List<String>>> data = ExcelUtil.readExcel(multipartFile);
        if(data != null){
            System.out.println("sheet的数量：" + data.size());
            List<List<String>> sheet1 = data.get(0);
            HashMap<String,Object> map = null;
            for (List<String> row : sheet1) {
                map = new HashMap<>();
                map.put("name", row.get(0));
                map.put("username", row.get(2));
                map.put("password", row.get(4));
                map.put("birthday", row.get(3));
                System.out.println(testMapper.addTest(map));
            }
        }
    }
}
