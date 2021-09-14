package lv.karlis.memorynotesapp.framework

import android.content.Context
import lv.karlis.core.data.Note
import lv.karlis.core.repository.NoteDataSource
import lv.karlis.memorynotesapp.framework.db.DatabaseService
import lv.karlis.memorynotesapp.framework.db.NoteEntity

class RoomNoteDataSource(context: Context): NoteDataSource {
    val noteDao = DatabaseService.getInstance(context).noteDao()


    override suspend fun add(note: Note) = noteDao.addNoteEntity(NoteEntity.fromNote(note))

    override suspend fun get(id: Long): Note? = noteDao.getNoteEntity(id)?.toNote()

    override suspend fun getAll(): List<Note> = noteDao.getAllNoteEntities().map { it.toNote() }

    override suspend fun remove(note: Note) = noteDao.deleteNoteEntity(NoteEntity.fromNote(note))
}