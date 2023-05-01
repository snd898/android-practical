package media_player.interfaces;

import android.widget.ImageButton;

public interface IPlayAudio {
    public void playAudio(Long id, String name, ImageButton vImageButton);
    public void pauseAudio(Long id, ImageButton vImageButton);
}
