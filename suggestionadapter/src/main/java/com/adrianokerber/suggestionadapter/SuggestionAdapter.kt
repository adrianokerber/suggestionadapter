package com.adrianokerber.suggestionadapter

import android.content.Context
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.Filterable


/**
 * Created by Adriano Kerber (kerberpro@gmail.com) on 6/6/2019.
 *
 * NOTE: Set as adapter of AutoCompleteTextView and be happy :D
 **/
class SuggestionAdapter(
        context: Context,
        resource: Int,
        list: List<String>,
        triggerPattern: String)
    : ArrayAdapter<String>(context, resource, list.toMutableList()), Filterable {

    private val regexFilter =
        FilterWithTrigger(this, triggerPattern, list)

    override fun getFilter(): Filter = regexFilter

    class FilterWithTrigger(
            private val attachedAdapter: ArrayAdapter<String>,
            private val triggerPattern: String,
            private val originalList: List<String>
    ) : Filter() {

        private fun getEndingToFilter(constraint: String) = constraint.substringAfter(triggerPattern)

        private fun getConstraintWithoutEnding(constraint: String): String {
            val indexWithPatterIncluded = constraint.indexOf(triggerPattern) + triggerPattern.length
            return constraint.substring(0, indexWithPatterIncluded)
        }

        private fun filterItemsBy(input: String): MutableList<String> {
            val matchingItems = mutableListOf<String>()

            if (input.isNotBlank()) {
                originalList.forEach {
                    if (it.startsWith(input)) {
                        matchingItems.add(it)
                    }
                }
            }

            return matchingItems
        }

        private fun setPrefixToList(prefix: String, list: List<String>): List<String> {
            val prefixedList = mutableListOf<String>()

            list.forEach {
                prefixedList.add(prefix + it)
            }

            return prefixedList.toList()
        }

        private fun shouldTriggerFilterFor(input: CharSequence?) =
                input != null && input.contains(triggerPattern)

        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filteringResults = FilterResults()
            val matchingItems = mutableListOf<String>()

            if (shouldTriggerFilterFor(constraint)) {
                val textInput = getEndingToFilter(constraint.toString())
                matchingItems.addAll(filterItemsBy(textInput))
            }

            filteringResults.values = matchingItems
            filteringResults.count = matchingItems.size

            return filteringResults
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            if (results != null && results.count > 0) {
                val prefix = getConstraintWithoutEnding(constraint.toString())
                val prefixedListResult = setPrefixToList(prefix, results.values as List<String>)

                attachedAdapter.clear()
                attachedAdapter.addAll(prefixedListResult)
                attachedAdapter.notifyDataSetChanged()
            } else {
                attachedAdapter.notifyDataSetInvalidated()
            }
        }
    }
}