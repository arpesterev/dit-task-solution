package ditsolv.controllers;


import ditsolv.dtos.PrintMe;
import ditsolv.dtos.WhoUsedMe;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ditsolv.services.IFirstEndpointMicroservice;
import ditsolv.services.ISecondEndpointMicroservice;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SimpleMicroserviceController {

    private final IFirstEndpointMicroservice IFirstEndpointMicroservice;
    private final ISecondEndpointMicroservice ISecondEndpointMicroservice;

    @Operation(
            description = "Метод апи, который осуществляет вывод на консоль",
            summary = "Вывод информации в консоль с параметрами"
    )
    @PostMapping("/first-endpoint")
    public ResponseEntity<Object> firstEndpoint(@RequestBody PrintMe printMe) {
        IFirstEndpointMicroservice.povtoryushka(printMe);

        return ResponseEntity.ok(null);
    }

    @Operation(
            description = "Метод апи, который осуществляет получение истории использования метода /first-endpoint",
            summary = "Аудит запросов, поступивших на сервис"
    )
    @GetMapping("/second-endpoint")
    public ResponseEntity<WhoUsedMe> firstEndpoint() {
        return ResponseEntity.ok(ISecondEndpointMicroservice.whoUsedThisService());
    }

}
