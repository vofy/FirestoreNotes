package tech.vofy.firestorenotes.ui.mainActivity.notes;

import android.app.Activity;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.databinding.DataBindingUtil;

import com.example.firestorenotes.R;
import com.example.firestorenotes.databinding.MainNotesFragmentBinding;
import com.example.firestorenotes.databinding.MainNotesListviewItemBinding;

import java.util.List;

import tech.vofy.firestorenotes.objects.Note;

public class NotesAdapter extends BaseAdapter {
    MainNotesListviewItemBinding binding;
    private List<Note> notes;
    private Activity activity;

    public NotesAdapter(MainNotesListviewItemBinding binding, Activity activity, List<Note> listPerson) {
        this.binding = binding;
        this.notes = listPerson;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return notes.size();
    }

    @Override
    public Object getItem(int position) {
        return notes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        binding.setNote(notes.get(position));
        return binding.getRoot();
    }
}
