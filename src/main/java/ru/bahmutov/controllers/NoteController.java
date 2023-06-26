package ru.bahmutov.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bahmutov.dto.reqest.NoteRequest;
import ru.bahmutov.dto.response.NoteResponse;
import ru.bahmutov.servises.NoteService;

@RestController
@RequiredArgsConstructor
@RequestMapping("note")
public class NoteController {

    private final NoteService service;

    @GetMapping("{noteId}")
    public ResponseEntity<NoteResponse> getNote(@PathVariable("noteId") Long noteId) {
        var note =  service.getNote(noteId);
        return ResponseEntity.ok(note);
    }

    @PostMapping
    public ResponseEntity<NoteResponse> createNote(@RequestBody NoteRequest noteRequest) {
        var note =  service.createNote(noteRequest);
        return ResponseEntity.status(201).body(note);
    }

    @PutMapping("{noteId}")
    public ResponseEntity<NoteResponse> changeNote(@PathVariable("noteId") Long noteId,
                                                   @RequestBody NoteRequest noteRequest) {
        var note =  service.changeNote(noteId, noteRequest);
        return ResponseEntity.ok(note);
    }

    @DeleteMapping("{noteId}")
    public void deleteNote(@PathVariable("noteId") Long noteId) {
        service.deleteNote(noteId);
    }
}
