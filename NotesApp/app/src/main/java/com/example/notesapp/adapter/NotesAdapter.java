package com.example.notesapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notesapp.R;
import com.example.notesapp.models.Notes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

    private Context context;
    private List<Notes> notesList;
    private NotesInterface notesInterface;

    public NotesAdapter(Context context, List<Notes> notesList, NotesInterface notesInterface) {
        this.context = context;
        this.notesList = notesList;
        this.notesInterface = notesInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_notes_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Notes notes = notesList.get(position);
        holder.txtTitle.setText(notes.getTitle());
        holder.txtTitle.setSelected(true);
        holder.txtNotes.setText(notes.getNotes());
        holder.txtDate.setText(notes.getDate());
        holder.txtDate.setSelected(true);

        if (notes.isPinned()) {
            holder.imgPin.setImageResource(R.drawable.ic_pin);
        } else {
            holder.imgPin.setImageResource(0);
        }

        //for random color
        int color = getRandomColor();
        holder.notesItem.setCardBackgroundColor(holder.itemView.getResources().getColor(color));

        //onclick cardView
        holder.notesItem.setOnClickListener(view -> notesInterface.onClick(notes));

        //onLongClick cardView
        holder.notesItem.setOnLongClickListener(view -> {
            notesInterface.onLongClick(notes, holder.notesItem);
            return true;
        });


    }

    private int getRandomColor() {
        List<Integer> colorCode = new ArrayList<>();
        colorCode.add(R.color.colorOne);
        colorCode.add(R.color.colorTwo);
        colorCode.add(R.color.colorThree);
        colorCode.add(R.color.colorFour);
        colorCode.add(R.color.colorFive);
        colorCode.add(R.color.colorSix);
        colorCode.add(R.color.colorSeven);

        Random random = new Random();
        int randomColor = random.nextInt(colorCode.size());
        return colorCode.get(randomColor);
    }

    @Override
    public int getItemCount() {
        if (notesList == null)
            return 0;
        else
            return notesList.size();
    }

    public void filterList(List<Notes>filteredNoteList){
        notesList=filteredNoteList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.notesItem)
        CardView notesItem;
        @BindView(R.id.txtTitle)
        TextView txtTitle;
        @BindView(R.id.txtNotes)
        TextView txtNotes;
        @BindView(R.id.txtDate)
        TextView txtDate;
        @BindView(R.id.imgPin)
        ImageView imgPin;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
