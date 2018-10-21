package com.billdesk.app.billdesk.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.util.SparseArrayCompat;

import com.billdesk.app.billdesk.fragments.CreateCardFragment;
import com.billdesk.app.billdesk.fragments.SelectCategoryFragment;
import com.billdesk.app.billdesk.fragments.SelectProviderFragment;

public class CardsFragmentAdapter extends FragmentPagerAdapter {

    private SparseArrayCompat<Fragment> cardsFragment = new SparseArrayCompat<>(3);

    public CardsFragmentAdapter(final FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(final int position) {
        Fragment fragment = cardsFragment.get(position);
        if (fragment == null) {
            switch (position) {
                case 0:
                    fragment = new SelectCategoryFragment();
                    cardsFragment.put(0, fragment);
                    break;

                case 1:
                    fragment = new SelectProviderFragment();
                    cardsFragment.put(1, fragment);
                    break;

                case 2:
                    fragment = new CreateCardFragment();
                    cardsFragment.put(2, fragment);

                    break;
                default:
                    fragment = new SelectCategoryFragment();
                    cardsFragment.put(0, fragment);
            }
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
