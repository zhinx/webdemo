import com.netease.zhinx.webdemo.dao.UserDAO;
import com.netease.zhinx.webdemo.model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");

        UserDAO dao = context.getBean("userDao", UserDAO.class);

        User user = dao.getUserByName("buyer");
        System.out.println("getUser: " + user.getNickName() + " " + user.getUserType());

        ((ConfigurableApplicationContext) context).close();
    }

}
