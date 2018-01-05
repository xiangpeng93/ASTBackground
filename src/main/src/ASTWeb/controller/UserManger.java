package ASTWeb.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by xiangpeng on 2018/1/5.
 */
@CrossOrigin(origins="http://astspace.org",maxAge=3600)
//@CrossOrigin(origins="*",maxAge=3600)
@Controller
@RequestMapping("/AST")

public class UserManger {
    // JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://10.11.160.101:3306/ASTData";
    //static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/ASTData";
    //static String LocalUrl = "http://127.0.0.1:8080";
    static String LocalUrl = "http://astspace.org";
    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "password";

    public int doUpdateOrInsert(String sql)
    {
        int nRet = 0;
        Connection conn = null;
        Statement stmt = null;
        try {
            // 注册 JDBC 驱动
            Class.forName("com.mysql.jdbc.Driver");
            // 打开链接
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            // 执行查询
            stmt = conn.createStatement();
            //执行并得到结果
            int result = stmt.executeUpdate(sql);
            if(result != 0)
            {
                nRet = 0;
            }
            else
            {
                nRet = -1;
            }

            // 完成后关闭
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch (Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            }// 什么都不做
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return nRet;
    };

    public ArrayList<Integer> doSelect(String sql) {
        ArrayList<Integer> nRet = new ArrayList<Integer>();
        Connection conn = null;
        Statement stmt = null;
        try {
            // 注册 JDBC 驱动
            Class.forName("com.mysql.jdbc.Driver");
            // 打开链接
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            // 执行查询
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            // 展开结果集数据库
            while (rs.next()) {
                // 通过字段检索
                int id = rs.getInt("id");
                nRet.add(id);
            }
            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch (Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            }// 什么都不做
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return nRet;
    };


    @ResponseBody
    @RequestMapping(value = "/doRegister",method = RequestMethod.GET)
    public void doRegister(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", LocalUrl);  // 第二个参数填写允许跨域的域名称，不建议直接写 "*"
        response.setHeader("Access-Control-Allow-Headers", "X-Requested-With");
        response.setHeader("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");
        // 接收跨域的cookie
        response.setHeader("Access-Control-Allow-Credentials", "true");

//for print out
        PrintWriter out = response.getWriter();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String dateNow = df.format(new Date());

        try {
            String querySqlUser;
            querySqlUser = String.format(
                    "select * from user_infos where userName='%s';", request.getParameter("userName"));
            System.out.println(querySqlUser);

            if (doSelect(querySqlUser).size() == 0) {
                String insertSql;
                insertSql = String.format(
                        "insert into user_infos (\n" +
                                "userName,userPhone,\n" +
                                "userEmail,userPasswd,\n" +
                                "userRegion,userPrivalege\n" +
                                ") values(\n" +
                                "'%s',\n" +
                                "'%s',\n" +
                                "'%s',\n" +
                                "'%s',\n" +
                                "'%s',\n" +
                                "%d\n" +
                                ");",
                        request.getParameter("userName"),
                        request.getParameter("userPhoneNum"),
                        request.getParameter("userEmailAddr"),
                        request.getParameter("userPasswd"),
                        request.getParameter("userRegion"),
                        100
                );
                System.out.println(insertSql);
                if(doUpdateOrInsert(insertSql) == 0)
                {
                    out.print( "恭喜您注册成功，前往登录！" );
                }
                else
                {
                    out.print("注册失败，请重新注册！");
                }
            } else {
                out.print("注册失败，用户名已经存在！");
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

    }


    class userInfo{
        public String userName;
        public int userIsRoot;
    }
    @ResponseBody
    @RequestMapping(value = "/userInfoQuery",method = RequestMethod.GET)
    public void userInfoQuery(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", LocalUrl);  // 第二个参数填写允许跨域的域名称，不建议直接写 "*"
        response.setHeader("Access-Control-Allow-Headers", "X-Requested-With");
        response.setHeader("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");
        // 接收跨域的cookie
        response.setHeader("Access-Control-Allow-Credentials", "true");

//for print out
        PrintWriter out = response.getWriter();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String dateNow = df.format(new Date());

        userInfo tUserInfo = new userInfo();
        try {
            String querySqlUser;
            querySqlUser = String.format(
                    "select * from user_infos where userName='%s';", request.getParameter("userName"));
            System.out.println(querySqlUser);

            if (doSelect(querySqlUser).size() > 0) {
                String queryIsRootUser;
                queryIsRootUser = String.format(
                        "select * from user_infos where userName='%s';", request.getParameter("userName"));
                System.out.println(queryIsRootUser);
                Connection conn = null;
                Statement stmt = null;
                try {
                    // 注册 JDBC 驱动
                    Class.forName("com.mysql.jdbc.Driver");
                    // 打开链接
                    conn = DriverManager.getConnection(DB_URL, USER, PASS);
                    // 执行查询
                    stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(queryIsRootUser);
                    // 展开结果集数据库
                    while (rs.next()) {
                        // 通过字段检索
                        tUserInfo.userName = rs.getString("userName");
                        tUserInfo.userIsRoot = rs.getInt("userPrivalege");
                        break;
                    }
                    // 完成后关闭
                    rs.close();
                    stmt.close();
                    conn.close();
                } catch (SQLException se) {
                    // 处理 JDBC 错误
                    se.printStackTrace();
                } catch (Exception e) {
                    // 处理 Class.forName 错误
                    e.printStackTrace();
                } finally {
                    // 关闭资源
                    try {
                        if (stmt != null) stmt.close();
                    } catch (SQLException se2) {
                    }// 什么都不做
                    try {
                        if (conn != null) conn.close();
                    } catch (SQLException se) {
                        se.printStackTrace();
                    }
                }
            } else {
                // 通过字段检索
                tUserInfo.userName = "error";
                tUserInfo.userIsRoot = -1;
            }
            String json = JSON.toJSONString(tUserInfo);
            out.print(json);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

    }


    @ResponseBody
    @RequestMapping(value = "/userLogin",method = RequestMethod.GET)
    public void userLogin(HttpServletRequest request,
                              HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", LocalUrl);  // 第二个参数填写允许跨域的域名称，不建议直接写 "*"
        response.setHeader("Access-Control-Allow-Headers", "X-Requested-With");
        response.setHeader("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");
        // 接收跨域的cookie
        response.setHeader("Access-Control-Allow-Credentials", "true");

//for print out
        PrintWriter out = response.getWriter();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String dateNow = df.format(new Date());

        userInfo tUserInfo = new userInfo();
        try {
            String querySqlUser;
            querySqlUser = String.format(
                    "select * from user_infos where userName='%s' and userPasswd='%s';", request.getParameter("userName"),request.getParameter("userPasswd"));
            System.out.println(querySqlUser);

            if (doSelect(querySqlUser).size() > 0) {
                // 通过字段检索
                tUserInfo.userName = request.getParameter("userName");
            } else {
                // 通过字段检索
                tUserInfo.userName = "error";
            }
            String json = JSON.toJSONString(tUserInfo);
            out.print(json);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

    }
}
