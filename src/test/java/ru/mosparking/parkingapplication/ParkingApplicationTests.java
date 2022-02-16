package ru.mosparking.parkingapplication;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.http.converter.json.Jackson2ObjectMapperBuilder.json;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.mosparking.parkingapplication.controller.ParkingFreeSpaceController;
import ru.mosparking.parkingapplication.dto.ResponseDto;
import ru.mosparking.parkingapplication.model.Parking;
import ru.mosparking.parkingapplication.repository.ParkingPlaceRepository;
import ru.mosparking.parkingapplication.repository.ParkingRepository;
import ru.mosparking.parkingapplication.service.ControlParkingFreeSpaceService;

import java.util.Optional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ParkingApplicationTests {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void statusProviding() {
        assertThat(this.restTemplate.getForObject(
                "http://localhost:" + port + "/api/parking?name=moscow_parking&action=status",
                ResponseDto.class)).extracting("message").isEqualTo("10");
    }
}
