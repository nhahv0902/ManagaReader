package toandoan.framgia.com.rxjavaretrofit.screen.mangaDetail.mangachapter;

import toandoan.framgia.com.rxjavaretrofit.data.model.Chap;

/**
 * Created by toand on 6/25/2017.
 */

public interface OnChapterClickListtenner {
    void onChapterItemClick(Chap chap, int pos);
}
