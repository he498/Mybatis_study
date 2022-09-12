package dao;

import com.dao.UserMapper;
import com.pojo.User;
import com.utils.MybatisUtils;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

public class UserMapperTest {

    static Logger logger = Logger.getLogger(UserMapper.class);
    @Test
    public void test() {
        //1.获取SqlSession对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        logger.info("测试，进入test方法成功");

        //getmapper
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.getUserById(1);

        System.out.println(user);

        //关闭流
        sqlSession.close();
    }

    @Test
    public void log4jtest(){
        logger.info("info:进入了test4j");
        logger.debug("debug:进入了test4j");
        logger.error("error:进入了test4j");
    }

    @Test
    public void getUserByLimit(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        HashMap<String,Integer> map = new HashMap<String,Integer>();
        map.put("startIndex",0);
        map.put("pageSize",2);
        List<User> userList = mapper.getUserByLimit(map);
        for (User user : userList) {
            System.out.println(user);
        }

        sqlSession.close();
    }

    @Test
    public void getUserByRowBounds(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        //rowbounds实现
        RowBounds rowBounds = new RowBounds(1,2);
        List<User> userList = sqlSession.selectList("com.dao.UserMapper.getUserByRowBounds",null,rowBounds);
        for (User user : userList) {
            System.out.println(user);
        }

        sqlSession.close();

    }



}
