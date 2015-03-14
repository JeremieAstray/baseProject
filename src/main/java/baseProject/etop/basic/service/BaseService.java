package baseProject.etop.basic.service;

import org.apache.log4j.Logger;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jessy on 2015/3/5.
 */
public class BaseService implements Serializable {

    protected Logger log = Logger.getLogger(this.getClass());

    protected String getKeyWords(String keyWords) {
        return "%" + keyWords + "%";
    }

    protected List<Object> getParam(Object... param) {
        return Arrays.asList(param);
    }

    protected Map<String, Object> createParamMap() {
        return new HashMap<>();
    }

    protected int parseInt(Object obj) {
        return ((Long) obj).intValue();
    }
}
