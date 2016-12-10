package me.cafetorres.cp_android.fragments;

import me.cafetorres.cp_android.entities.PostalCode;

/**
 * Created by Carlos on 09/12/2016.
 */

public interface HistoryListFragmentListener {
    void addToList(PostalCode postalCode);
    void clearList();
}
