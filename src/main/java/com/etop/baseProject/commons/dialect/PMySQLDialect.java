package com.etop.baseProject.commons.dialect;

import org.hibernate.dialect.MySQLDialect;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.type.StandardBasicTypes;

/**
 * 扩展Hibernate中的MySQLDialect，添加Convert(字段名 using gbk)函数, 中文排序
 * @author Pengo.Wen
 * Created by pengo on 14-9-13.
 */
public class PMySQLDialect extends MySQLDialect {

    public PMySQLDialect() {
        super();
        registerFunction("convert_gbk", new SQLFunctionTemplate(StandardBasicTypes.STRING, "convert(?1 using gbk)"));
    }

    @Override
    public String getTableTypeString() {
        return " ENGINE=InnoDB";
    }

}