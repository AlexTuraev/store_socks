package com.example.task1.controller;

import com.example.task1.dto.SocksDto;
import com.example.task1.mapper.SocksMapper;
import com.example.task1.model.SocksModel;
import com.example.task1.repository.SocksRepository;
import com.example.task1.service.ServiceImpl.SocksServiceImpl;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = SocksController.class)
class SocksControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private SocksRepository socksRepository;

    @SpyBean
    private SocksServiceImpl socksService;
    @SpyBean
    private SocksMapper socksMapper;

    @SpyBean
    private SocksDto socksDto;
    @SpyBean
    private SocksModel socksModel;

    @InjectMocks
    private SocksController socksController;
    @Test
    void incomeControllerTests() throws Exception{
        // Initial data
        long id = 1L;
        String colorRed = "red";
        int cottonPart = 20;
        int quantity = 50;

        JSONObject socksRedDtoJsonObject = new JSONObject();
        socksRedDtoJsonObject.put("id", id);
        socksRedDtoJsonObject.put("color", colorRed);
        socksRedDtoJsonObject.put("cottonPart", cottonPart);
        socksRedDtoJsonObject.put("quantity", quantity);

        SocksModel socksRedModel = new SocksModel();
        socksRedModel.setId(id);
        socksRedModel.setQuantity(quantity);
        socksRedModel.setColor("RED");
        socksRedModel.setCottonPart(cottonPart);

        // -----------------------------------------------------

        // INCOME TEST. Checks "red" socks. They are not (0) in the DataBase.
        when(socksRepository.findByColorAndCottonPart(eq("RED"), anyInt())).thenReturn(Optional.empty());
        when(socksRepository.save(any(SocksModel.class))).thenReturn(socksRedModel);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/socks/income")
                        .content(socksRedDtoJsonObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cottonPart").value(cottonPart))
                .andExpect(jsonPath("$.color").value("RED"))
                .andExpect(jsonPath("$.quantity").value(quantity));

        // INCOME TEST. Checks "red" socks with cottonPart=20. There are 50 socks in DB
        int prevAmount = 150;

        SocksModel socksPrevModel= new SocksModel();
        socksPrevModel.setId(id);
        socksPrevModel.setQuantity(prevAmount); // previous amount
        socksPrevModel.setColor("RED");
        socksPrevModel.setCottonPart(cottonPart);

        SocksModel socksResult = new SocksModel();
        socksResult.setId(id);
        socksResult.setQuantity(prevAmount + quantity); // new amount
        socksResult.setColor("RED");
        socksResult.setCottonPart(cottonPart);


        when(socksRepository.findByColorAndCottonPart(eq("RED"), eq(20))).thenReturn(Optional.of(socksPrevModel));
        when(socksRepository.save(eq(socksResult))).thenReturn(socksResult);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/socks/income")
                        .content(socksRedDtoJsonObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cottonPart").value(cottonPart))
                .andExpect(jsonPath("$.color").value("RED"))
                .andExpect(jsonPath("$.quantity").value(prevAmount + quantity));
    }

    @Test
    void outcomeControllerTests() throws Exception{
        long id = 1L;
        String colorRed = "red";
        int cottonPart = 20;
        int quantity = 50;

        JSONObject socksRedDtoJsonObject = new JSONObject();
        socksRedDtoJsonObject.put("id", id);
        socksRedDtoJsonObject.put("color", colorRed);
        socksRedDtoJsonObject.put("cottonPart", cottonPart);
        socksRedDtoJsonObject.put("quantity", quantity);

        // Socks aren't in DataBase. Bad Request.
        when(socksRepository.findByColorAndCottonPart(eq("RED"), eq(20))).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/socks/outcome")
                        .content(socksRedDtoJsonObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        //-----------------------------------------------------------
        // Previous Socks amount are 150.

        int prevAmount = 150;

        SocksModel socksPrevModel = new SocksModel();
        socksPrevModel.setId(id);
        socksPrevModel.setQuantity(prevAmount); // previous amount = 150
        socksPrevModel.setColor("RED");
        socksPrevModel.setCottonPart(cottonPart);

        SocksModel socksExpectedResult = new SocksModel();
        socksExpectedResult.setId(id);
        socksExpectedResult.setQuantity(prevAmount-quantity);
        socksExpectedResult.setColor("RED");
        socksExpectedResult.setCottonPart(cottonPart);

        when(socksRepository.findByColorAndCottonPart(eq("RED"), eq(20))).thenReturn(Optional.of(socksPrevModel));
        when(socksRepository.save(eq(socksExpectedResult))).thenReturn(socksExpectedResult);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/socks/outcome")
                        .content(socksRedDtoJsonObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cottonPart").value(cottonPart))
                .andExpect(jsonPath("$.color").value("RED"))
                .andExpect(jsonPath("$.quantity").value(prevAmount - quantity));
    }
}