package em.pg.rpv.projeto.gui;


import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import em.pg.rpv.R;
import em.pg.rpv.infra.negocio.Converter;

public class ImageAdapter extends PagerAdapter {
    Context context;
    private Converter converter = Converter.getInstancia();

    private ArrayList<Bitmap> imagesGal = new ArrayList<>();

    private int[] GalImages = new int[] {

    };

    ImageAdapter(Context context){
        this.context=context;
    }
    @Override
    public int getCount() {
        return GalImages.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((ImageView) object);
    }

    public void setImagesGal(ArrayList<String> imagens){
        for (String imagem : imagens){
            Bitmap imagemBitmap = converter.StringToBitMap(imagem);
            imagesGal.add(imagemBitmap);
        }
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);
        int padding = context.getResources().getDimensionPixelSize(R.dimen.padding_medium);
        imageView.setPadding(padding, padding, padding, padding);
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imageView.setImageBitmap(imagesGal.get(position));//GalImages[position]
        ((ViewPager) container).addView(imageView, 0);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((ImageView) object);
    }
}
