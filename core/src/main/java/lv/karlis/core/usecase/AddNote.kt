package lv.karlis.core.usecase

import lv.karlis.core.data.Note
import lv.karlis.core.repository.NoteRepository

class AddNote(private val noteRepository: NoteRepository) {
    suspend operator fun invoke(note: Note) = noteRepository.addNote(note)
}