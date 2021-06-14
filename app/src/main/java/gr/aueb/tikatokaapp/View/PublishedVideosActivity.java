package gr.aueb.tikatokaapp.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.MediaController;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import gr.aueb.tikatokaapp.Core.ConnectedAppNode;
import gr.aueb.tikatokaapp.Core.Value;
import gr.aueb.tikatokaapp.R;
import gr.aueb.tikatokaapp.View.fragmentVideoList.VideoListFragment;

public class PublishedVideosActivity extends AppCompatActivity implements VideoListFragment.OnListFragmentInteractionListener {


    private static final String VIDEO_ΝΑΜΕ = "video_name_extra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_published_videos);
        ((FloatingActionButton) findViewById(R.id.add_video_button)).setOnClickListener(v -> onAddVideo());


        if (findViewById(R.id.fragment_container) != null) {

            if (savedInstanceState != null) {
                return;
            }

            VideoListFragment videoListFragment = VideoListFragment.newInstance(1);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, videoListFragment)
                    .commit();
        }
    }


    @Override
    public void onListFragmentInteraction(Value item) {
        String pathVideo = ConnectedAppNode.getAppNode().getPubDir() + "/videos/" + item.getName();
        Intent intent = new Intent(PublishedVideosActivity.this, VideoPlayerActivity.class);
        intent.putExtra("PATH", pathVideo);
        intent.putExtra(VIDEO_ΝΑΜΕ, item.getName());
        startActivity(intent);
    }

    @Override
    public ArrayList<Value> getVideoList() {
        ArrayList<Value> vals = ConnectedAppNode.getAppNode().getVideos(ConnectedAppNode.getAppNode().getName());
        if (vals == null)
            return new ArrayList<Value>();
        return vals;
    }

    public void onAddVideo() {
        Intent intent = new Intent(PublishedVideosActivity.this, UploadVideosActivity.class);
        startActivity(intent);
    }


}