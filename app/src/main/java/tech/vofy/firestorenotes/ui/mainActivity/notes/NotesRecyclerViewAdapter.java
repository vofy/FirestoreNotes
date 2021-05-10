package tech.vofy.firestorenotes.ui.mainActivity.notes;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firestorenotes.R;
import com.example.firestorenotes.databinding.NotesListviewItemBinding;

import java.util.Map;

import tech.vofy.firestorenotes.NotesDao;
import tech.vofy.firestorenotes.NotesDaoFirestore;
import tech.vofy.firestorenotes.objects.DeletedObject;
import tech.vofy.firestorenotes.objects.Note;
import tech.vofy.firestorenotes.ui.editorActivity.EditorActivity;

public class NotesRecyclerViewAdapter extends RecyclerView.Adapter<NotesRecyclerViewAdapter.ViewHolder> {
    private final Map<String, Note> notes;
    private final Context context;
    private DeletedObject<Note> deletedNote;
    private NotesDao mDatabase = NotesDaoFirestore.getInstance();

    public NotesRecyclerViewAdapter(Context context) {
        this.context = context;
        this.notes = mDatabase.getAllNotes().getValue();
    }

    @NonNull
    @Override
    public NotesRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        NotesListviewItemBinding binding = NotesListviewItemBinding.inflate(LayoutInflater.from(context), viewGroup, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesRecyclerViewAdapter.ViewHolder viewHolder, int position) {
        if (notes.size() > 0) {
            String id = (String) notes.keySet().toArray()[position];
            viewHolder.itemView.setTag(id);

            viewHolder.binding.setNote(notes.get(id));

            viewHolder.itemView.setOnClickListener(v -> {
                Intent editNote = new Intent(context, EditorActivity.class);
                editNote.putExtra("noteId", id);
                context.startActivity(editNote);
            });
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final NotesListviewItemBinding binding;

        public ViewHolder(NotesListviewItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @Override
    public int getItemCount() {
        return notes == null ? 0 : notes.size();
    }
}