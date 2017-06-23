package toandoan.framgia.com.rxjavaretrofit.utils.widget.zoomimage;

import android.view.MotionEvent;

/**
 * Created by framgia on 23/06/2017.
 */

public interface OnDoubleTapListener {
    boolean onSingleTapConfirmed(MotionEvent e);

    boolean onDoubleTap(MotionEvent ev);

    boolean onDoubleTapEvent(MotionEvent e);
}
