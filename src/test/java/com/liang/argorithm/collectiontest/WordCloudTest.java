package com.liang.argorithm.collectiontest;


import com.github.houbb.segment.api.ISegmentResult;
import com.github.houbb.segment.bs.SegmentBs;
import com.github.houbb.segment.data.phrase.core.data.SegmentPhraseDatas;
import com.github.houbb.segment.data.pos.core.data.SegmentPosDatas;
import com.github.houbb.segment.support.format.impl.SegmentFormats;
import com.github.houbb.segment.support.segment.impl.Segments;
import com.github.houbb.segment.support.segment.mode.impl.SegmentModes;
import com.github.houbb.segment.support.segment.result.impl.SegmentResultHandlers;
import com.github.houbb.segment.support.tagging.pos.tag.impl.SegmentPosTaggings;
import com.github.houbb.segment.util.SegmentHelper;
import java.util.List;
import java.util.Map;
import org.junit.Assert;
import org.junit.Test;

/**
 * 云图测试,包含分词相关的测试
 *
 * @author liangbingtian
 * @date 2024/05/08 下午1:57
 */
public class WordCloudTest {

    @Test
    public void test1() {
        final String string = "这是一个伸手不见五指的黑夜。我叫孙悟空，我爱北京，我爱学习。";

        List<ISegmentResult> resultList = SegmentHelper.segment(string);
        Assert.assertEquals("[这是[0,2), 一个[2,4), 伸手不见五指[4,10), 的[10,11), 黑夜[11,13), 。[13,14), 我[14,15), 叫[15,16), 孙悟空[16,19), ，[19,20), 我爱[20,22), 北京[22,24), ，[24,25), 我爱[25,27), 学习[27,29), 。[29,30)]", resultList.toString());
    }

    @Test
    public void test2() {
        final String string = "这是一个伸手不见五指的黑夜。我叫孙悟空，我爱北京，我爱学习。";
        final Map<String, Integer> wordCount = SegmentHelper.wordCount(string);
        System.out.println();
    }


    @Test
    public void test3() {
        final String text = "自定义一个很长的分词，开心！";

        List<ISegmentResult> resultList = SegmentBs.newInstance()
                // 分词实现策略
                .segment(Segments.defaults())
                // 分词词组数据
                .segmentData(SegmentPhraseDatas.mixed())
                // 分词模式
                .segmentMode(SegmentModes.dict())
                // 格式化处理
                .segmentFormat(SegmentFormats.defaults())
                // 词性标注实现
                .posTagging(SegmentPosTaggings.simple())
                // 词性标注数据
                .posData(SegmentPosDatas.mixed())
                // 对文本进行分词处理
                .segment(text, SegmentResultHandlers.common());
        Assert.assertEquals("[自定义一个很长的分词[0,10)/un, ，[10,11)/un, 开心[11,13)/a, ！[13,14)/un]", resultList.toString());
    }


}
