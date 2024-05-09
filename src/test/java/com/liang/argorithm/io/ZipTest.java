package com.liang.argorithm.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import org.junit.Test;

/**
 * 测试压缩文件
 *
 * @author liangbingtian
 * @date 2024/04/26 下午5:20
 */
public class ZipTest {

    @Test
    public void testZipFiles() throws IOException {
        Path filePath = Paths.get("/Users/liangbingtian/Desktop/鲲驰/投放/迭代6/10096401588910-3.jpg");
        final byte[] bytes = Files.readAllBytes(filePath);
        try(ZipInputStream zipInputStream = new ZipInputStream(new ByteArrayInputStream(bytes))) {
            ZipEntry zipEntry;
            while ((zipEntry = zipInputStream.getNextEntry())!=null) {
                final byte[] file = getByteArrayFromZipInputStream(zipInputStream);

            }
        }
    }


    private byte[] getByteArrayFromZipInputStream(ZipInputStream zipInputStream) {
        try(ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[8192];
            int bytesRead;
            while ((bytesRead = zipInputStream.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
            return out.toByteArray();
        }catch (Exception e) {
            return null;
        }
    }
}
