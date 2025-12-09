package ditsolv.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MessageInfo {

    private String username;
    private int countDone;
    private String message;

}
