package ru.bahmutov.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.bahmutov.dto.reqest.NoteRequest;
import ru.bahmutov.dto.response.NoteResponse;
import ru.bahmutov.exeption.NotFoundException;
import ru.bahmutov.servises.NoteService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class NoteControllerTest {

    @MockBean
    private NoteService noteService;


    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("should return status 200 after get request")
    void shouldReturn200ResponseAfterGetRequest() throws Exception {
        var request = new NoteResponse(1L, "body", "author");
        when(noteService.get(1L)).thenReturn(request);
        mockMvc.perform(MockMvcRequestBuilders.get("/note/1"))
                .andExpectAll(status().isOk(),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                        content().json("""
                                {
                                  "id": 1,
                                  "body": "body",
                                  "author": "author"
                                }
                                """));
    }

//    @Test
//    @DisplayName("should return status 400 after get request by not valid id")
//    void shouldReturn400ResponseAfterGetRequest() throws Exception {
//        var request = new NoteResponse(1L, "body", "author");
//        Mockito.when(noteService.get(1L)).thenReturn(request);
//        mockMvc.perform(MockMvcRequestBuilders.get("/note/-1"))
//                .andExpectAll(status().isBadRequest()
//                        );
//    }

    @Test
    @DisplayName("should return status 404 after get request")
    void shouldReturn400ResponseAfterGetRequest() throws Exception {
        when(noteService.get(1L)).thenThrow(NotFoundException.class);
        mockMvc.perform(MockMvcRequestBuilders.get("/note/1"))
                .andExpectAll(status().isNotFound());
    }

    @Test
    @DisplayName("should create new note")
    void shouldCreateNewNote() throws Exception {
        when(noteService.post(new NoteRequest("body", "author")))
                .thenReturn(new NoteResponse(1L, "body", "author"));

        ResultActions responce = mockMvc.perform(post("/note")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                        "body": "body",
                        "author":"author"
                        }
                        """));

        responce.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json("""
                        {
                          "id": 1,
                          "body": "body",
                          "author": "author"
                        }
                        """));
    }

    @Test
    @DisplayName("should change note and return 200 status")
    void shouldChangeNoteAndReturn200Status() throws Exception {
        when(noteService.put(1L, new NoteRequest("body", "author")))
                .thenReturn(new NoteResponse(1L, "body", "author"));

        ResultActions responce = mockMvc.perform(put("/note/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                        "body": "body",
                        "author":"author"
                        }
                        """));

        responce.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json("""
                        {
                          "id": 1,
                          "body": "body",
                          "author": "author"
                        }
                        """));

    }

    @Test
    @DisplayName("should delete and return 200 status")
    void  shouldDeleteAndReturn200Status() throws Exception {
     mockMvc.perform(
             delete("/note/1")
             .contentType(MediaType.APPLICATION_JSON))
             .andExpect(status().isOk());

        Mockito.verify(noteService, Mockito.times(1)).delete(1L);
    }

}