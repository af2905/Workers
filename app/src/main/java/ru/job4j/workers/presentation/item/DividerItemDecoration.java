package ru.job4j.workers.presentation.item;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DividerItemDecoration extends RecyclerView.ItemDecoration {
    private int paddingTopBottom;
    private int paddingLeftRight;

    public DividerItemDecoration(int paddingTopBottom, int paddingLeftRight) {
        this.paddingTopBottom = paddingTopBottom;
        this.paddingLeftRight = paddingLeftRight;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view,
                               @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.top += paddingTopBottom;
        outRect.bottom += paddingTopBottom;
        outRect.left += paddingLeftRight;
        outRect.right += paddingLeftRight;
    }
}
