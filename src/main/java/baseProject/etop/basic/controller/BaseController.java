package baseProject.etop.basic.controller;

import baseProject.etop.commons.util.DateUtils;
import baseProject.etop.modules.baseModules.entity.TUserEntity;
import org.apache.log4j.Logger;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.beans.PropertyEditorSupport;
import java.util.Date;

/**
 * 控制器基类
 * Created by jessy on 2015/3/5.
 */
public abstract class BaseController {

    public final static String ERROR = "error";
    public final static String SUCCESS = "success";

    protected Logger log = Logger.getLogger(this.getClass());

    public TUserEntity getCurrentUser(HttpServletRequest request){
        return (TUserEntity) request.getSession().getAttribute("currentUser");
    }

    /**
     * 添加Model消息
     *
     * @param message
     */
    protected void addMessage(Model model, String message) {
        model.addAttribute("message", message);
    }

    /**
     * 添加Model消息
     * @param type 消息类型
     * @param message
     */
    protected void addMessage(Model model,String type, String message) {
        model.addAttribute("message", message);
        model.addAttribute("type", type);
    }

    /**
     * 添加Flash消息
     *
     * @param message
     */
    protected void addMessage(RedirectAttributes redirectAttributes, String message) {
        redirectAttributes.addFlashAttribute("message", message);
    }

    /**
     * 添加Flash消息
     * @param type 消息类型
     * @param message
     */
    protected void addMessage(RedirectAttributes redirectAttributes, String type, String message) {
        redirectAttributes.addFlashAttribute("message", message);
        redirectAttributes.addFlashAttribute("type", type);
    }

    protected String getImagePath(HttpServletRequest request){
        return request.getServletContext().getRealPath("/") + "upload/images";
    }

    /**
     * 初始化数据绑定 1. 将所有传递进来的String进行HTML编码，防止XSS攻击 2. 将字段中Date类型转换为String类型
     */
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        // String类型转换，将所有传递进来的String进行HTML编码，防止XSS攻击
        binder.registerCustomEditor(String.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                //setValue(text == null ? null : StringEscapeUtils.escapeHtml(text.trim()));
                setValue(text == null ? null : text.trim());
            }

            @Override
            public String getAsText() {
                Object value = getValue();
                return value != null ? value.toString() : "";
            }
        });
        // Date 类型转换
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(DateUtils.parseDate(text));
            }
        });
    }

    /*@ExceptionHandler(Exception.class)
    public String handleException(Exception ex, HttpServletRequest request){
        if(ex instanceof UnauthenticatedException){
            log.error("当前用户没有此权限");
            return "errors/403";
        }else {
            log.error("系统发生异常", ex);
            ex.printStackTrace();
            request.setAttribute("exMsg", ex.getMessage());
            return "errors/exception";
        }
    }*/

}

