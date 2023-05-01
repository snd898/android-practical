package media_player;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.customviews.R;

import java.util.List;

import media_player.interfaces.IPlayAudio;

public class AudioListAdapter extends RecyclerView.Adapter<AudioListAdapter.AVHolder> {

    List<AudioData> audioDataList;
    IPlayAudio iPlayAudio;
    public AudioListAdapter(List<AudioData> audioDataList, IPlayAudio iPlayAudio) {
        this.audioDataList = audioDataList;
        this.iPlayAudio = iPlayAudio;
    }

    @NonNull
    @Override
    public AVHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AVHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.audio_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AVHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.vAudioName.setText(audioDataList.get(position).name);
        holder.vPlayAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPlayAudio.playAudio(audioDataList.get(position).id, audioDataList.get(position).name);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class AVHolder extends RecyclerView.ViewHolder{
        TextView vAudioName;
        ImageButton vPlayAudio;
        public AVHolder(@NonNull View itemView) {
            super(itemView);
            vAudioName = itemView.findViewById(R.id.vAudioName);
            vPlayAudio = itemView.findViewById(R.id.vPlayAudio);
        }
    }
}
