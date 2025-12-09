package ditsolv;

import ditsolv.configurations.PovtoryushkaConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * Test task Department of Information Technologies
 * 09.12.2025 17:10
 * <p>
 * Жил был один маленький микросервис с двумя эндоинтами.
 * С помощью первого эндпоинта микросервис принимал авторизованные
 * другими микросервисами запросы и печатал текст запроса
 * заданное кол-во раз в консоль, после чего возвращал код 200 в ответном сообщении.
 * С помощью второго эндпоинта возвращал, то что было напечатано
 * в консоль в виде:
 * пользователь, который заказал печать,
 * номер повторения печати указанного текста
 * и текст который был напечатан.
 * <p>
 * И вроде был простенький микросервис,
 * но была у него одна особенность - умел этот микросервис производить временную задержку выполнения повтора печати для каждого пользователя,
 * а также выполнять печать для всех пользователей в указанном количестве потоков.
 * Кол-во потоков задаётся в application.properties микросервиса.
 * Задержка печати на повторы в миллисекундах,
 * имя пользователя,
 * кол-во повторов печати
 * и текст, что надо напечатать, указывается в запросе к микросервису.
 * Пользователь в запросе представляется ввиде строки с именем пользователя.
 * <p>
 * Задачу решать в java 11 Spring Boot до 2.7 используя Thread и не синхронизированные коллекции.
 * Подключить свагер и описать по русски представленный микросервисом API.
 */

@EnableConfigurationProperties({PovtoryushkaConfig.class})
@SpringBootApplication
public class Solution {

    public static void main(String[] args) {
        // Артём Романович Пестерев
        SpringApplication.run(Solution.class, args);
    }

}