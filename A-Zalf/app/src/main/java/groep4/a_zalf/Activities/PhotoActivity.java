package groep4.a_zalf.Activities;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

import groep4.a_zalf.R;

public class PhotoActivity extends AppCompatActivity {

    private ImageView ivImage1, ivImage2;
    private ArrayList<Bitmap> pictures;
    public static Activity phActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        pictures = new ArrayList<>();
        phActivity = this;
        ivImage1 = (ImageView) this.findViewById(R.id.ivImage1);

        ivImage2 = (ImageView) this.findViewById(R.id.ivImage2);


    }

    public void continueClick(View view) {
        Intent goToAfspraakMaken = new Intent(getApplicationContext(), AfspraakMaken.class);
        String arts = (String) getIntent().getExtras().getString("Arts");
        String tijd = (String) getIntent().getExtras().getString("Tijd");
        int vraagnummer = (int) getIntent().getExtras().getInt("vraagNummer");
        goToAfspraakMaken.putExtra("Arts", arts);
        goToAfspraakMaken.putExtra("Tijd", tijd);
        goToAfspraakMaken.putExtra("vraagNummer", vraagnummer);
        startActivity(goToAfspraakMaken);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        Bitmap bp = (Bitmap) data.getExtras().get("data");

        if (ivImage1.getDrawable() == null && ivImage2.getDrawable() == null) {
            ivImage1.setImageBitmap(bp);
            ivImage1.setBackground(null);
            ivImage2.setImageBitmap(bp);
            ivImage2.setBackground(null);
        }
        pictures.add(bp);
    }

    public void makePhotos(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (ivImage1.getDrawable() == null && ivImage2.getDrawable() == null) {
                Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 0);
            }

        }
    }
}
