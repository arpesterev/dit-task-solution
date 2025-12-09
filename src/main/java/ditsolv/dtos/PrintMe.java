package ditsolv.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * * Задержка печати на повторы в миллисекундах,
 * * имя пользователя,
 * * кол-во повторов печати
 * * и текст, что надо напечатать, указывается в запросе к микросервису.
 */
@NoArgsConstructor
@Getter
@Setter
public class PrintMe {

    private String username;
    // кол-во повторов печати
    private int countRepeats;
    // Задержка печати на повторы в миллисекундах,
    private int delayedTimeout;
    private String text;

}
