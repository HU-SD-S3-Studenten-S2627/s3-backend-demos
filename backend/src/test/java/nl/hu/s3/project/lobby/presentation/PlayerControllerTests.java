package nl.hu.s3.project.lobby.presentation;

import jakarta.transaction.Transactional;
import nl.hu.s3.project.lobby.application.PlayerResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import tools.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@Transactional //Transactional op een test zorgt voor een auto-rollback aan het eind
public class PlayerControllerTests {

    @Autowired
    private MockMvc mockMvc;


    @Test
    @WithMockUser(username = "bob", roles = {"ADMIN"})
    public void canFetchPlayers() throws Exception {

        ObjectMapper jsonMapper = new ObjectMapper();
        MvcResult result = mockMvc.perform(get("/players").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        PlayerResponse[] responses = jsonMapper.readValue(result.getResponse().getContentAsString(), PlayerResponse[].class);
        assertEquals(3, responses.length);
    }

    @Test
    @WithMockUser
    public void cantFetchPlayerAsNonAdmin() throws Exception {
        mockMvc.perform(get("/players/bubbahotep").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }


    @Test
    @WithMockUser(username = "bob", roles = {"ADMIN"})
    public void canFetchPlayer() throws Exception {
        ObjectMapper jsonMapper = new ObjectMapper();
        MvcResult result = mockMvc.perform(get("/players/bubbahotep").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        PlayerResponse response = jsonMapper.readValue(result.getResponse().getContentAsString(), PlayerResponse.class);
        assertEquals("Bubba Hotep", response.name());
    }

    @Test
    @WithMockUser(username = "bob", roles = {"ADMIN"})
    public void canAddPlayer() throws Exception {
        ObjectMapper jsonMapper = new ObjectMapper();
        PlayerResponse newPlayer = new PlayerResponse("test", "Test Test", "test@example.org");

        MvcResult result = mockMvc.perform(post("/players")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(jsonMapper.writeValueAsString(newPlayer))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        PlayerResponse response = jsonMapper.readValue(result.getResponse().getContentAsString(), PlayerResponse.class);
        assertEquals(newPlayer.name(), response.name());
    }


}
