package lv.karlis.memorynotesapp.framework

import lv.karlis.core.usecase.AddNote
import lv.karlis.core.usecase.GetAllNotes
import lv.karlis.core.usecase.GetNote
import lv.karlis.core.usecase.RemoveNote

data class UseCases(
    val addNote: AddNote,
    val getAllNotes: GetAllNotes,
    val getNote: GetNote,
    val removeNote: RemoveNote
)