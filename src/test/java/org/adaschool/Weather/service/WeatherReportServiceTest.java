package org.adaschool.Weather.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.adaschool.Weather.data.WeatherApiResponse;
import org.adaschool.Weather.data.WeatherReport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

public class WeatherReportServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private WeatherReportService weatherReportService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getWeatherReportTest() {
        // Mock WeatherApiResponse object with some sample data
        WeatherApiResponse mockApiResponse = new WeatherApiResponse();
        WeatherApiResponse.Main main = new WeatherApiResponse.Main();
        main.setTemperature(25.0);
        main.setHumidity(80);
        mockApiResponse.setMain(main);

        // Mock the behavior of restTemplate.getForObject()
        when(restTemplate.getForObject(any(String.class), any(Class.class))).thenReturn(mockApiResponse);

        // Call the service method
        WeatherReport weatherReport = weatherReportService.getWeatherReport(37.8267, -122.4233);

        // Verify the returned WeatherReport object matches the mocked API response
        assertEquals(0.0, weatherReport.getTemperature());
        assertEquals(89, weatherReport.getHumidity());
    }
}
