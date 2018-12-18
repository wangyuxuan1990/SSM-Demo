package com.wangyuxuan.service.ssmone;

import com.wangyuxuan.pojo.Content;
import com.wangyuxuan.pojo.ContentLog;
import com.wangyuxuan.service.ContentLogService;
import com.wangyuxuan.service.ContentService;
import com.wangyuxuan.util.CommonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Auther: wangyuxuan
 * @Date: 2018/12/18 10:48
 * @Description:
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/*.xml"})
public class ContentLogServiceImplTest {

    @Autowired
    private ContentService contentService;

    @Autowired
    private ContentLogService contentLogService;

    @Test
    public void insertSelective() throws Exception{
        Content content = new Content();
        content.setContent("asdsf");
        content.setCreatedate("2016-12-09");
        contentService.insertSelective(content);

        ContentLog log = new ContentLog();
        log.setContentid(content.getContentid());
        log.setContent("asdsf");
        log.setCreatedate("2016-12-09");
        contentLogService.insertSelective(log);
    }

    @Test
    public void insertSelective2() throws Exception {
        Content content = new Content();
        content.setContent("你好");
        content.setContentname("1");
        content.setCreatedate("2016-09-23");

        contentService.insertSelective(content);

        ContentLog log = new ContentLog();
        CommonUtil.setLogValueModelToModel(content, log);
        contentLogService.insertSelective(log);
    }
}
