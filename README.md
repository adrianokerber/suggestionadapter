# Suggestion Adapter
## The custom adapter for auto-complete triggered by a string

This is the SuggestionAdapter. An adapter aimed to be used (But not only) with the AutoCompleteTextView component.
The main point is that the adapter has a trigger (String) that starts the auto-complete list and will insert the user input + the suggestions from the list.

### Usage examples

#### With AutoCompleteTextView

```kotlin
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
```