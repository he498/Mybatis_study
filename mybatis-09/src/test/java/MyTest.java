import com.dao.UserMapper;
import com.pojo.User;
import com.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class MyTest {

//    缓存失效的情况：1.查询不同的东西
//    2.增删改操作，刷新缓存
//    3.查询不同的mapper.xml
//    4.手动清理缓存
    @Test
    public void test1(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.queryUserById(1);
        System.out.println(user);
//        增删改，可能会改变原来的数据所以会刷新缓存
        mapper.updateUser(new User(2,"aaaa","bbbb"));
//        sqlSession.clearCache(); 手动清理缓存

        System.out.println("=================");
        User user2 = mapper.queryUserById(1);
        System.out.println(user == user2);
        sqlSession.close();
    }

    @Test
    public void test2(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        SqlSession sqlSession2 = MybatisUtils.getSqlSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        UserMapper mapper2 = sqlSession2.getMapper(UserMapper.class);

        User user = mapper.queryUserById(1);
        System.out.println(user);

        User user2 = mapper.queryUserById(1);
        System.out.println(user2);

        System.out.println("==============");
        System.out.println(user == user2);


        sqlSession.close();
        sqlSession2.close();
    }
}
