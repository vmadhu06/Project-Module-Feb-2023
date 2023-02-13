package com.example.springtaskmgrv2.controllers;

import com.example.springtaskmgrv2.entities.NoteEntity;
import com.example.springtaskmgrv2.services.NoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/tasks/{id}/notes")
public class NoteController {
    NoteService noteService;
    public NoteController(NoteService noteService){
      this.noteService = noteService;
    }
    @GetMapping
    public ResponseEntity<List<NoteEntity>> getNotesForTaskId(@PathVariable Integer id){
           List<NoteEntity> notes =  noteService.getNotesForTaskId(id);
           return ResponseEntity.ok(notes);
    }

    @PostMapping
    public ResponseEntity<NoteEntity> createNotesForTaskId(@PathVariable Integer id, @RequestBody NoteEntity note){
        NoteEntity newNote =  noteService.createNotesForTaskId(id, note);
        return ResponseEntity.created(URI.create("/tasks/"+id+"/notes/"+newNote.getId())).body(newNote);
    }

    @DeleteMapping("/{noteId}")
    public ResponseEntity<NoteEntity> deleteNotes(@PathVariable Integer noteId){
        NoteEntity notes =  noteService.deleteNotes(noteId);
        return ResponseEntity.accepted().body(notes);
    }




}
