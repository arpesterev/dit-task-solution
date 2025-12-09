package ditsolv.services.impl;

import ditsolv.dtos.MessageInfo;
import ditsolv.dtos.WhoUsedMe;
import ditsolv.models.DoneCounts;
import ditsolv.services.IFirstEndpointMicroservice;
import ditsolv.services.ISecondEndpointMicroservice;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;

@Service
@RequiredArgsConstructor
public class SecondEndpointMicroservice implements ISecondEndpointMicroservice {

    private final IFirstEndpointMicroservice IFirstEndpointMicroservice;

    @Override
    public WhoUsedMe whoUsedThisService() {
        var result = new WhoUsedMe();

        var resultingItems = new ArrayList<MessageInfo>();

        IFirstEndpointMicroservice.getDoneTasks()
                .stream()
                .sorted(Comparator.comparing(DoneCounts::getRequestCame))
                .forEach(
                        el -> resultingItems.add(new MessageInfo(el.getUserRequest().getUsername(), el.getDone(), el.getUserRequest().getText()))
                );

        result.setResult(resultingItems);

        return result;
    }
}
