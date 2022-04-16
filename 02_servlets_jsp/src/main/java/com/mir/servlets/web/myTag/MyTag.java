package com.mir.servlets.web.myTag;

import com.mir.servlets.dao.impl.JdbcUserDao;
import com.mir.servlets.entity.User;
import com.mir.servlets.dao.ConfigManager;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.Tag;
import java.io.IOException;
import java.util.ArrayList;

/**
 * MyTag custom tag.
 *
 * @author R.Mirzoiev
 * @see Tag
 * @since 31.01.2022
 */
public class MyTag implements Tag {
    private ArrayList users;
    private PageContext pageContext;
    private Tag parent;

    private JspFragment printlogin;

    public JspFragment getPrintlogin() {
        return printlogin;
    }

    public void setPrintlogin(JspFragment printlogin) {
        this.printlogin = printlogin;
    }

    public ArrayList getUsers() {
        return users;
    }

    public void setUsers(ArrayList users) {
        this.users = users;
    }

    public PageContext getPageContext() {
        return pageContext;
    }

    @Override
    public void setPageContext(PageContext pageContext) {
        this.pageContext = pageContext;
    }

    @Override
    public void setParent(Tag tag) {
        this.parent = tag;
    }

    @Override
    public Tag getParent() {
        return parent;
    }

    @Override
    public int doStartTag() throws JspException {

        ConfigManager configManager = new ConfigManager("/db.properties");
        JdbcUserDao userDao = new JdbcUserDao(configManager);
        users = (ArrayList) userDao.findAll();
        pageContext = getPageContext();
        JspWriter out=pageContext.getOut();

        for(Object obj : users) {
            User user = (User) obj;
            try {
                pageContext.setAttribute("usersList", user);
                printlogin.invoke(null);
                out.println(" <tr>");
                out.println(" <td>");
                out.println(user.getId());
                out.println("</td>");

                out.println(" <td>");
                out.println(user.getLogin());
                out.println("</td>");

                out.println(" <td>");
                out.println(user.getPassword());
                out.println("</td>");

                out.println(" <td>");
                out.println(user.getEmail());
                out.println("</td>");

                out.println(" <td>");
                out.println(user.getFirstName());
                out.println("</td>");

                out.println(" <td>");
                out.println(user.getLastName());
                out.println("</td>");

                out.println(" <td>");
                out.println(user.getBirthday());
                out.println("</td>");

                out.println(" <td>");
                out.println(user.getRole().getName());
                out.println("</td>");

                out.println(" <td>");
                out.println(" <script type=\"text/javascript\">\n" +
                        "                                   function confirmation() {\n" +
                        "                                     return confirm('Are you sure?');\n" +
                        "                                   }\n" +
                        "                               </script>\n" +
                        "                               <a href=" + "controller?command=deleteUser&login=" + user.getLogin() + " onclick=\"return confirmation()\">Delete</a>");
                out.println("</td>");

                out.println(" <td>");
                out.println(" <a href=" + "/controller?command=editUser&login=" + user.getLogin() + "\n/>Edit</a>");
                out.println("</td>");

                out.println("</tr>");
            } catch (IOException e) {
                throw new RuntimeException("IOException ", e);
            }
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {

        return EVAL_PAGE;
    }

    @Override
    public void release() {

    }
}
