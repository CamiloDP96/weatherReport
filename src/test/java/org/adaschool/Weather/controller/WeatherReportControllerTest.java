package org.adaschool.Weather.controller;

import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.when;

import org.adaschool.Weather.data.WeatherReport;
import org.adaschool.Weather.service.WeatherReportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class WeatherReportControllerTest {

    private MockMvc mockMvc;

    @Mock
    private WeatherReportService weatherReportService;

    @InjectMocks
    private WeatherReportController weatherReportController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(weatherReportController).build();
    }

    @Test
    void getWeatherReportTest() throws Exception {
        WeatherReport mockWeatherReport = new WeatherReport(); // Create a mock WeatherReport object

        // Mock the behavior of the service method when provided with any latitude and longitude
        when(weatherReportService.getWeatherReport(anyDouble(), anyDouble())).thenReturn(mockWeatherReport);

        // Perform GET request to /v1/api/weather-report endpoint with sample latitude and longitude
        mockMvc.perform(get("/v1/api/weather-report")
                .param("latitude", "37.8267")
                .param("longitude", "-122.4233")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
