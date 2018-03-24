import java.util.HashSet;
import java.util.Set;

public class NaiveTest {

    public static void main(String[] args) {
        Set<String> s = new HashSet<String>();
        s.add("aa");
        s.add("bb");
        s.add("cc");

        System.out.println(s.contains("bb"));
    }

}
