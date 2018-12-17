package com.wangyuxuan.service.ssmtwo;

import com.wangyuxuan.dao.DatasourceMapper;
import com.wangyuxuan.pojo.Datasource;
import com.wangyuxuan.service.DataSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: wangyuxuan
 * @Date: 2018/12/17 15:18
 * @Description:
 */

@Service
public class DataSourceServiceImpl implements DataSourceService {

    @Autowired
    private DatasourceMapper datasourceMapper;

    @Override
    public Datasource selectByPrimaryKey(Integer id) {
        return datasourceMapper.selectByPrimaryKey(id);
    }
}
