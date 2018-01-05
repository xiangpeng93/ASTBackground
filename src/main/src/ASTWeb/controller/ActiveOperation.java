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
import java.util.List;

/**
 * Created by xiangpeng on 2017/12/25.
 */
@CrossOrigin(origins="http://astspace.org",maxAge=3600)
//@CrossOrigin(origins="*",maxAge=3600)
@Controller
@RequestMapping("/AST")

public class ActiveOperation {
    // JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    //static final String DB_URL = "jdbc:mysql://10.11.160.101:3306/ASTData";
    static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/ASTData";
    //static String LocalUrl = "http://127.0.0.1:8080";
    static String LocalUrl = "http://astspace.org";
    static String LocalActiveUrl = LocalUrl + "/active";
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
    @RequestMapping(value = "/activeAdd",method = RequestMethod.POST)
    public void activeAdd(HttpServletRequest request,
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
            String querySqlActive;
            querySqlActive = String.format(
                    "select id from active_infos where activeName='%s';", request.getParameter("active_name"));
            System.out.println(querySqlActive);

            if (doSelect(querySqlActive).size() == 0) {
                String insertSql;
                insertSql = String.format(
                        "insert into active_infos (\n" +
                                "activeName,activeTime,\n" +
                                "activeAuthor,activePic,\n" +
                                "activeBody,activeLink,\n" +
                                "activeNumber,activePeopleNumber\n" +
                                ") values(\n" +
                                "'%s',\n" +
                                "'%s',\n" +
                                "'%s',\n" +
                                "'%s',\n" +
                                "'%s',\n" +
                                "'%s',\n" +
                                "'%s',\n" +
                                "'%s'\n" +
                                ");",
                        request.getParameter("active_name"),
                        dateNow.toString(),
                        request.getParameter("active_author"),
                        request.getParameter("active_pic"),
                        request.getParameter("active_body"),
                        request.getParameter("active_link"),
                        request.getParameter("active_number"),
                        request.getParameter("active_person_number")
                        );
                System.out.println(insertSql);
                if(doUpdateOrInsert(insertSql) == 0)
                {
                    out.print("活动添加成功");
                }
                else
                {
                    out.print("活动添加失败");
                }
            } else {
                String updateSql = String.format("update active_infos set activeName='%s', activeTime='%s'," +
                                "activePic='%s',activeBody='%s', " +
                                "activeNumber='%s',activePeopleNumber='%s' " +
                                " where " +
                                "activeName='%s';",
                        request.getParameter("active_name"),
                        dateNow.toString(),
                        request.getParameter("active_pic"),
                        request.getParameter("active_body"),
                        request.getParameter("active_number"),
                        request.getParameter("active_person_number"),
                        request.getParameter("active_name")
                );
                System.out.println(updateSql);
                if(doUpdateOrInsert(updateSql) == 0)
                {
                    out.print("活动更新成功");
                }
                else
                {
                    out.print("活动更新失败");
                }
            }

            ArrayList<Integer> idList = doSelect(querySqlActive);
            for (Integer id:idList
                 ) {
                String updateSql = String.format("update active_infos set activeLink='%s' " +
                                " where " +
                                "id='%d';",
                        LocalActiveUrl+"?id="+id,
                        id
                );
                doUpdateOrInsert(updateSql);
                System.out.println(updateSql);
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/activeDelete",method = RequestMethod.GET)
    public void activeDelete(HttpServletRequest request,
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
            String querySqlActive;
            querySqlActive = String.format(
                    "select id from active_infos where id=%s;", request.getParameter("id"));
            System.out.println(querySqlActive);

            if (doSelect(querySqlActive).size() == 0) {
                System.out.println("活动不存在");
            } else {
                String updateSql = String.format("delete from active_infos where id=%s;",
                        request.getParameter("id"));
                System.out.println(updateSql);
                if(doUpdateOrInsert(updateSql) == 0)
                {
                    out.print("活动删除成功");
                }
                else
                {
                    out.print("活动删除失败");
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }


    @ResponseBody
    @RequestMapping(value = "/activeRegister",method = RequestMethod.GET)
    public void activeRegister(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "http://astspace.org");  // 第二个参数填写允许跨域的域名称，不建议直接写 "*"
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
                    "select id from active_registration where userName='%s' and activeName='%s';", request.getParameter("user_name")
            , request.getParameter("active_name"));
            System.out.println(querySqlUser);

            if (doSelect(querySqlUser).size() == 0) {
                String insertSql;
                insertSql = String.format(
                        "insert into active_registration (\n" +
                                "activeName,userName,\n" +
                                "userPhone,\n" +
                                "userAge,registrationTime,\n" +
                                "activeSession\n" +
                                ") values(\n" +
                                "'%s',\n" +
                                "'%s',\n" +
                                "'%s',\n" +
                                "'%s',\n" +
                                "'%s',\n" +
                                "'%s'\n" +
                                ");",
                        request.getParameter("active_name"),
                        request.getParameter("user_name"),
                        request.getParameter("user_age"),
                        request.getParameter("user_phone"),
                        dateNow.toString(),
                        request.getParameter("active_session")
                );
                System.out.println(insertSql);
                if(doUpdateOrInsert(insertSql) == 0)
                {
                    out.print("报名成功");
                }
                else
                {
                    out.print("报名失败");
                }
            } else {
                {
                    out.print("报名失败，您已经报名。");
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/queryRegisterNumber",method = RequestMethod.GET)
    public void queryRegisterNumber(HttpServletRequest request,
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
                    "select id from active_registration where activeName='%s' and activeSession='%s';", request.getParameter("active_name")
            ,request.getParameter("active_session"));
            System.out.println(querySqlUser);
            out.print("{ \"result\":"+doSelect(querySqlUser).size() + "}");
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    class activeRegisterInfoStruct{
        public String activeName;
        public String userName;
        public String userAge;
        public String userPhone;
        public String registrationTime;
        public String isConfirm;
        public int activeSession;
    };

    @ResponseBody
    @RequestMapping(value = "/activeQueryRegisterList",method = RequestMethod.GET)
    public void activeQueryRegisterList(HttpServletRequest request,  HttpServletResponse response)
            throws ServletException, IOException
    {
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

        ArrayList<activeRegisterInfoStruct> activeRegisterInfoList = new ArrayList<activeRegisterInfoStruct>();

        try{
            Connection conn = null;
            Statement stmt = null;
            try {
                String strActiveName =  request.getParameter("active_name");
                //strActiveName = new String(strActiveName .getBytes("iso8859-1"),"utf-8");
                String sql = String.format( "select * from active_registration where activeName='%s' order by id desc;",strActiveName);
                System.out.println(sql);
                // 注册 JDBC 驱动
                Class.forName("com.mysql.jdbc.Driver");
                // 打开链接
                conn = DriverManager.getConnection(DB_URL, USER, PASS);
                // 执行查询
                stmt = conn.createStatement();
                //执行并得到结果
                ResultSet rs = stmt.executeQuery(sql);
                // 展开结果集数据库
                while (rs.next()) {
                    // 通过字段检索
                    activeRegisterInfoStruct stActiveRegisterInfo = new activeRegisterInfoStruct();
                    stActiveRegisterInfo.activeSession = rs.getInt("activeSession");
                    stActiveRegisterInfo.userAge = rs.getString("userAge");
                    stActiveRegisterInfo.userName = rs.getString("userName");
                    stActiveRegisterInfo.activeName = rs.getString("activeName");
                    stActiveRegisterInfo.userPhone = rs.getString("userPhone");
                    stActiveRegisterInfo.registrationTime = rs.getString("registrationTime");
                    stActiveRegisterInfo.isConfirm = rs.getString("isConfirm");
                    activeRegisterInfoList.add(stActiveRegisterInfo);
                }

                try{
                    String json = JSON.toJSONString(activeRegisterInfoList);
                    System.out.println(json);
                    out.print(json);  //返回url地址
                }catch (Exception e)
                {
                    System.out.println(e);
                }

                // 完成后关闭
                rs.close();
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
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    class activeInfoStruct{
        public int id;
        public String activeName;
        public String activeTime;
        public String activeAuthor;
        public String activePic;
        public String activeBody;
        public String activeLink;
        public int activeReadCount;
        public int activeNumber;
        public int activePeopleNumber;
    };

    @ResponseBody
    @RequestMapping(value = "/activeQueryById",method = RequestMethod.GET)
    public void activeQueryById(HttpServletRequest request,  HttpServletResponse response)
            throws ServletException, IOException
    {
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

        activeInfoStruct stActiveInfo = new activeInfoStruct();

        try{
            Connection conn = null;
            Statement stmt = null;
            try {

                String sql = String.format( "select * from active_infos where id='%s';", request.getParameter("id"));
                // 注册 JDBC 驱动
                Class.forName("com.mysql.jdbc.Driver");
                // 打开链接
                conn = DriverManager.getConnection(DB_URL, USER, PASS);
                // 执行查询
                stmt = conn.createStatement();
                //执行并得到结果
                ResultSet rs = stmt.executeQuery(sql);
                // 展开结果集数据库
                while (rs.next()) {
                    // 通过字段检索
                    stActiveInfo.id = rs.getInt("id");
                    stActiveInfo.activeReadCount = rs.getInt("activeReadCount");
                    stActiveInfo.activeAuthor = rs.getString("activeAuthor");
                    stActiveInfo.activeBody = rs.getString("activeBody");
                    stActiveInfo.activeName = rs.getString("activeName");
                    stActiveInfo.activeTime = rs.getString("activeTime");
                    stActiveInfo.activePic = rs.getString("activePic");
                    stActiveInfo.activeLink = rs.getString("activeLink");
                    stActiveInfo.activeNumber = rs.getInt("activeNumber");
                    stActiveInfo.activePeopleNumber = rs.getInt("activePeopleNumber");
                }

                try{
                    String json = JSON.toJSONString(stActiveInfo);
                    System.out.println(json);
                    out.print(json);  //返回url地址
                }catch (Exception e)
                {
                    System.out.println(e);
                }

                // 完成后关闭
                rs.close();
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
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        String updateReadCount = String.format("update active_infos set activeReadCount='%d' where activeName='%s';",stActiveInfo.activeReadCount+1,stActiveInfo.activeName);
        doUpdateOrInsert(updateReadCount);
    }


    @ResponseBody
    @RequestMapping(value = "/activeQueryListByPage",method = RequestMethod.GET)
    public void activeQueryListByPage(HttpServletRequest request,  HttpServletResponse response)
            throws ServletException, IOException
    {
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

        ArrayList<activeInfoStruct> activeInfoList = new ArrayList<activeInfoStruct>();

        try{
            Connection conn = null;
            Statement stmt = null;
            try {
                String strNumber =  request.getParameter("Number");
                String strPageNumber =  request.getParameter("PageNumber");

                String sql = String.format( "select * from active_infos order by id desc limit %s,%s;", strPageNumber,strNumber);
                // 注册 JDBC 驱动
                Class.forName("com.mysql.jdbc.Driver");
                // 打开链接
                conn = DriverManager.getConnection(DB_URL, USER, PASS);
                // 执行查询
                stmt = conn.createStatement();
                //执行并得到结果
                ResultSet rs = stmt.executeQuery(sql);
                // 展开结果集数据库
                while (rs.next()) {
                    // 通过字段检索
                    activeInfoStruct stActiveInfo = new activeInfoStruct();
                    stActiveInfo.id = rs.getInt("id");
                    stActiveInfo.activeReadCount = rs.getInt("activeReadCount");
                    stActiveInfo.activeAuthor = rs.getString("activeAuthor");
                    stActiveInfo.activeBody = rs.getString("activeBody");
                    stActiveInfo.activeName = rs.getString("activeName");
                    stActiveInfo.activeTime = rs.getString("activeTime");
                    stActiveInfo.activePic = rs.getString("activePic");
                    stActiveInfo.activeLink = rs.getString("activeLink");
                    activeInfoList.add(stActiveInfo);
                }

                try{
                    String json = JSON.toJSONString(activeInfoList);
                    System.out.println(json);
                    out.print(json);  //返回url地址
                }catch (Exception e)
                {
                    System.out.println(e);
                }

                // 完成后关闭
                rs.close();
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
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

}
