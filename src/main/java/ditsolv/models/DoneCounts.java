package ditsolv.models;

import ditsolv.dtos.PrintMe;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class DoneCounts {

    private final PrintMe userRequest;
    // Сколько раз на текущий момент вывели на консоль
    private int done;
    // Когда с учётом задержки в следующий раз выводить на консоль
    private Instant nextTimeToCall;
    private final StringBuilder totalPrintedText = new StringBuilder();
    private final Instant requestCame = Instant.now();

}
