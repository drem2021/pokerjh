package com.github.drem2021.pokerjh.etc;

import org.nutz.dao.Dao;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.util.Daos;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DbConnConfig {

    /**
     * 配置Nutz Dao操作数据库
     * @param druidDataSource
     * @return
     */
    @Bean
    public Dao dao(DataSource druidDataSource) {
        Dao dao = new NutDao(druidDataSource);
        Daos.createTablesInPackage(dao, "com.github.drem2021.pokerjh.entity", false);
        return dao;
    }

}
