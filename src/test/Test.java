import com.netease.zhinx.webdemo.dao.UserDao;
import com.netease.zhinx.webdemo.model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");

        UserDao dao = context.getBean("userDao", UserDao.class);

        User user = dao.getUserByName("buyer");
        System.out.println("getUser: " + user.getNickName() + " " + user.getUserType());

        ((ConfigurableApplicationContext) context).close();
    }

}
