package com.android.emrannik.dashboarddesign.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.emrannik.dashboarddesign.R;
import com.android.emrannik.dashboarddesign.YoutubePlayerActivity;
import com.android.emrannik.dashboarddesign.api.Constants;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;

import java.util.ArrayList;

/**
 * Created by nik on 10/11/19.
 */
public class YoutubeVideoAdapter extends RecyclerView.Adapter<YoutubeVideoAdapter.YoutubeViewHolder> {
    private static final String TAG = YoutubeVideoAdapter.class.getSimpleName();
    private Context context;
    private ArrayList<String> youtubeVideoModelArrayList;

    private ArrayList<String> youtubeVideoTitleArrayList;
   // private int selectedPosition = 0;

    public YoutubeVideoAdapter(Context context, ArrayList<String> youtubeVideoModelArrayList, ArrayList<String>youtubeVideoTitleArrayList) {
        this.context = context;
        this.youtubeVideoModelArrayList = youtubeVideoModelArrayList;
        this.youtubeVideoTitleArrayList=youtubeVideoTitleArrayList;
    }

    @NonNull
    @Override
    public YoutubeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.view_popular_food, parent, false);

        return new YoutubeViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final YoutubeViewHolder holder, final int position) {
        //final String youtubeVideoModel = youtubeVideoModelArrayList.get(position);

//        holder.videoTitle.setText(youtubeVideoTitleArrayList.get(position));

        /*  initialize the thumbnail image view , we need to pass Developer Key */

        //holder.videoThumbnailImageView1.
        holder.videoThumbnailImageView.initialize(Constants.DEVELOPER_KEY, new YouTubeThumbnailView.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, final YouTubeThumbnailLoader youTubeThumbnailLoader) {

                youTubeThumbnailLoader.setVideo(youtubeVideoModelArrayList.get(position));
                youTubeThumbnailLoader.setOnThumbnailLoadedListener(new YouTubeThumbnailLoader.OnThumbnailLoadedListener() {
                    @Override
                    public void onThumbnailLoaded(YouTubeThumbnailView youTubeThumbnailView, String s) {
                        //when thumbnail loaded successfully release the thumbnail loader as we are showing thumbnail in adapter
                        youTubeThumbnailLoader.release();
                    }

                    @Override
                    public void onThumbnailError(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader.ErrorReason errorReason) {
                        Log.e(TAG, "Youtube Thumbnail Error");
                    }
                });
                //holder.videoThumbnailImageView1.inti

            }

            @Override
            public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {
                Log.e(TAG, "Youtube Initialization Failure");

            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //start youtube player activity by passing selected video id via intent
               Intent intent=new Intent(v.getContext(), YoutubePlayerActivity.class);
                intent.putExtra("video_id",youtubeVideoModelArrayList.get(position));
                v.getContext().startActivity(intent);

                Toast.makeText(v.getContext(),"Clicked"+position, Toast.LENGTH_LONG).show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return youtubeVideoModelArrayList != null ? youtubeVideoModelArrayList.size() : 0;

    }



    public static class YoutubeViewHolder  extends RecyclerView.ViewHolder{
        public YouTubeThumbnailView videoThumbnailImageView;
        public CardView youtubeCardView,videoThumbnailImageView1;
        public LinearLayout linearLayout;
        public TextView videoTitle;

        public YoutubeViewHolder(View itemView) {
            super(itemView);
            videoThumbnailImageView = itemView.findViewById(R.id.video_thumbnail_image_view);
           //videoThumbnailImageView1 = itemView.findViewById(R.id.video_thumbnail_image_view1);
            youtubeCardView = itemView.findViewById(R.id.youtube_cardview);
            //videoTitle = itemView.findViewById(R.id.video_title_label);
            linearLayout=itemView.findViewById(R.id.linear_layout);


        }
    }
}
