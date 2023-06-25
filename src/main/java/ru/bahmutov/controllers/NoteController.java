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
    public ResponseEntity<NoteResponse> get(@PathVariable("noteId") Long noteId) {
        var note =  service.get(noteId);
        return ResponseEntity.ok(note);
    }

    @PostMapping
    public ResponseEntity<NoteResponse> post(@RequestBody NoteRequest noteRequest) {
        var note =  service.post(noteRequest);
        return ResponseEntity.ok(note);
    }

    @PutMapping("{noteId}")
    public ResponseEntity<NoteResponse> put(@PathVariable("noteId") Long noteId,
                      @RequestBody NoteRequest noteRequest) {
        var note =  service.put(noteId, noteRequest);
        return ResponseEntity.ok(note);
    }

    @DeleteMapping("{noteId}")
    public void delete(@PathVariable("noteId") Long noteId) {
        service.delete(noteId);
    }
}
