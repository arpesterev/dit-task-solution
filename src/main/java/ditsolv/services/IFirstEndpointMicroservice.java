package ditsolv.services;

import ditsolv.dtos.PrintMe;
import ditsolv.models.DoneCounts;

import java.util.List;

public interface IFirstEndpointMicroservice {

    void povtoryushka(PrintMe obj);

    List<DoneCounts> getDoneTasks();
}
