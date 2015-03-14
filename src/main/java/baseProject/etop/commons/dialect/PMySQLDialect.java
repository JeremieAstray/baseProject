package baseProject.etop.commons.dialect;

import org.hibernate.dialect.MySQLDialect;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.type.StandardBasicTypes;

/**
 * Created by jessy on 2015/3/5.
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
