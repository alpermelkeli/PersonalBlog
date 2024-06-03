package com.alpermelkeli.personalblog.uı.skills.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import com.alpermelkeli.personalblog.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
public class CustomPolygonView extends View {

    private Paint paint;
    private Path path;
    private LinkedHashMap<Integer, Double> cornerProgressMap;
    private ArrayList<String> skillList = new ArrayList<>();

    public CustomPolygonView(Context context) {
        super(context);
        init();
    }

    public CustomPolygonView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomPolygonView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        paint = new Paint();

        paint.setColor(getResources().getColor(R.color.polygon));

        paint.setAlpha(50);

        paint.setStyle(Paint.Style.FILL);

        path = new Path();

        cornerProgressMap = new LinkedHashMap<>();

    }


    private void addCorner(int cornerIndex, double progress, String skillName) {
        cornerProgressMap.put(cornerIndex, progress);
        skillList.add(skillName);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // Center of screen
        if (cornerProgressMap.isEmpty()) {
            return; // Nothing to draw
        }

        int x0 = getWidth() / 2;

        int y0 = getHeight() / 2;

        // Clear any existing path

        path.reset();

        // Angle calculation to draw polygon

        double angle = 2 * Math.PI / cornerProgressMap.size();
        double radius = Math.min(x0, y0);

        //first corner

        double currentAngle = angle * 1;

        double currentRadius = radius * cornerProgressMap.get(1);

        float x = (float) (x0 + currentRadius * Math.cos(currentAngle));
        float y = (float) (y0 + currentRadius * Math.sin(currentAngle));

        path.moveTo(x, y);

        Paint textPaint = new Paint();

        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(30);


        float currentTextAngle = (float) (angle); // Start from the angle of the first corner

        int i = 1;
        for (double progress : cornerProgressMap.values()) {
            currentAngle = angle * i;
            currentRadius = radius * progress;

            x = (float) (x0 + currentRadius * Math.cos(currentAngle));
            y = (float) (y0 + currentRadius * Math.sin(currentAngle));

            //Here for text
            float textRadius = (float) (radius * 0.9f); // Adjust the distance from the center
            float textAngleIncrement = (float) (2 * Math.PI / cornerProgressMap.size());

            for (String skill : skillList) {

                // Calculate the text position
                float textX = (float) (x0-35 + textRadius * Math.cos(currentTextAngle));
                float textY = (float) (y0+10 + textRadius * Math.sin(currentTextAngle));

                // Draw the text at the calculated position
                canvas.drawText(skill, textX, textY, textPaint);

                // Increment the angle for the next text label
                currentTextAngle += textAngleIncrement;
            }
            path.lineTo(x, y);

            i++;

        }


        path.close();

        canvas.drawPath(path, paint);
    }
    public void addAllCorners(List<Corner> cornerList){
        for(Corner corner:cornerList){
            addCorner(corner.getCornerIndex(), corner.getProgress(), corner.getSkillName());
        }
        invalidate();

    }
}