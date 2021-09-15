package lv.karlis.memorynotesapp.framework

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import lv.karlis.core.data.Note
import lv.karlis.core.repository.NoteRepository
import lv.karlis.core.usecase.AddNote
import lv.karlis.core.usecase.GetAllNotes
import lv.karlis.core.usecase.GetNote
import lv.karlis.core.usecase.RemoveNote

class NoteViewModel(application: Application): AndroidViewModel(application) {
    private val coroutinesScope = CoroutineScope(Dispatchers.IO)

    val repository = NoteRepository(RoomNoteDataSource(application))
    val useCases = UseCases(
        AddNote(repository),
        GetAllNotes(repository),
        GetNote(repository),
        RemoveNote(repository)
    )

    val saved = MutableLiveData<Boolean>()
    val currentNote = MutableLiveData<Note?>()

    fun saveNote(note: Note){
        coroutinesScope.launch {
            useCases.addNote(note)
            saved.postValue(true)
        }
    }

    fun getNote(id: Long){
        coroutinesScope.launch {
            val note = useCases.getNote(id)
            currentNote.postValue(note)
        }
    }

}