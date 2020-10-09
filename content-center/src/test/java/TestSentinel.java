import org.springframework.web.client.RestTemplate;

/**
 * 描述:
 *
 * @author：Guorc
 * @create 2020-10-06 15:44
 */
public class TestSentinel {
    public static void main(String[] args) throws InterruptedException {
        RestTemplate restTemplate = new RestTemplate();
        for (int i = 0; i < 100; i++) {
            String list = restTemplate.getForObject("http://localhost:8081/test/byResources", String.class);
            System.out.println(list);
            System.out.println("ok");
//            Thread.sleep(1000);
        }

    }
}
