package ditsolv.configurations;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Objects;

@ConfigurationProperties("povtoryushka")
@Setter
@NoArgsConstructor
@ToString
public class PovtoryushkaConfig {

    private Integer threadCount = null;

    public int getThreadCount() {
        return Objects.requireNonNullElse(threadCount, 1);
    }
}
