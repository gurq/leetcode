package interview.ant;

import interview.ant.GlobalVariable;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * 初始化文件
 *
 * @author gurq
 * @date 2020/11/30 10:04 下午
 */
public class CreateTestFile {


    /**
     * 生成的id起始值
     */
    private static final int ID_START = 2000100;
    /**
     * groupId生成起始值和范围都取100
     */
    private static final int GROUP_ID_START_AND_RANGE = 100;
    /**
     * quota的范围是0到200，这个数字用的时候要除以10
     */
    private static final int QUOTA_RANGE = 2000;

    /**
     * 初始化测试数据
     *
     * @return boolean
     */
    public static String init() {
        boolean result = false;
        try {
            result = createFileIfNotExist();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result ? "初始化文件成功" : "初始化文件失败";
    }

    /**
     * 文件不存在则创建
     *
     * @return boolean
     * @throws IOException IOException
     */
    private static boolean createFileIfNotExist() throws IOException {
        File folder = new File(GlobalVariable.FOLDER_PATH);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        for (int fileNum = 1; fileNum <= GlobalVariable.TOTAL_FILE_COUNT; fileNum++) {
            File file = new File(GlobalVariable.FOLDER_PATH + fileNum + GlobalVariable.FILE_SUFFIX);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter br = new BufferedWriter(fileWriter);
            Random random = new Random();
            for (int lineNum = 0; lineNum < GlobalVariable.DATA_COUNT_PER_FILE; lineNum++) {
                br.write(getRandomData(random, fileNum, lineNum));
                br.newLine();
            }
            br.close();
            fileWriter.close();

        }
        return true;
    }

    /**
     * 获取测试数据
     *
     * @param random  避免重复创建Random对象
     * @param fileNum fileNum
     * @param line    line
     * @return 行数据
     */
    private static String getRandomData(Random random, int fileNum, int line) {
        String id = String.valueOf(ID_START + (fileNum - 1) * GlobalVariable.DATA_COUNT_PER_FILE + line);
        String groupId = String.valueOf(random.nextInt(GROUP_ID_START_AND_RANGE) + GROUP_ID_START_AND_RANGE);
        float quota = random.nextInt(QUOTA_RANGE) / 10.0f;
        return id + GlobalVariable.SPLIT + groupId + GlobalVariable.SPLIT + quota;
    }
}
