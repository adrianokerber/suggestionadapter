package com.adrianokerber.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import com.adrianokerber.suggestionadapter.SuggestionAdapter
import kotlinx.android.synthetic.main.main_activity.*

/**
 * Created by Adriano Kerber (adriano.kerber@cwi.com.br) on 6/8/2019.
 **/
class MainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        /**
         * The example below is of a regular adapter
         */
        viewAutoCompleteNormal.setAdapter(ArrayAdapter<String>(
            this,
            R.layout.view_item,
            suggestionsList
        ))

        /**
         * Our custom adapter in action
         */
        viewAutoCompleteSuggestion.setAdapter(
            SuggestionAdapter(
                this,
                R.layout.view_item,
                suggestionsList,
                "@" // The string that will trigger the search to display and filter the options
            )
        )
    }

    private val suggestionsList = listOf(
        "gmail.com",
        "hotmail.com",
        "bol.com",
        "edu.mail.com",
        "mailo.com",
        "arab.mail.com",
        "aab.com",
        "bb.com.br",
        "crude.mail",
        "crazy.com",
        "de.com",
        "dee.com",
        "electronic.com",
        "force.com",
        "group.com",
        "h.com",
        "hl.cu",
        "iji.ju",
        "j.com",
        "jj.com",
        "kerber.com",
        "ker.com",
        "kkk.com"
    ).sorted()
}