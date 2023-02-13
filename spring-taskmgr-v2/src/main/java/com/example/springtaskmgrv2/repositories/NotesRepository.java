package com.example.springtaskmgrv2.repositories;

import com.example.springtaskmgrv2.entities.NoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotesRepository extends JpaRepository<NoteEntity, Integer> {
    @Query(" select ne from notes ne join tasks te on ne.task.id = te.id WHERE te.id = :id")
    List<NoteEntity> findNotesByTasksId(Integer id);
}
