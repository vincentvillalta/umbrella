# Weather Screen

* The warm color is 0xFF9800.
* The cool color is 0x03A9F4.

## Current Conditions

* When the user has not yet chosen a ZIP, you may use a default ZIP of your choosing.

### Layout

* The city/state label should use standard ActionBar title styling.
* The settings cog (image: ic_settings) should be presented as a standard ActionBar menu item
* The current temperature label should utilize the "Display 3" text style from the Material design specifications. It should be horizontally centered in the view. It should always be rounded to a whole number.
* The current conditions label should utilize the "Subhead" text style from the Material design specifications. It should be horizontally centered. There should be 16dp of space between the bottom of the label and the bottom of the current conditions box.

## Hourly Forecast

* The section insets for the scrollable area is 8dp on all sides. There should be an 8dp gutter between each card. Card content should be inset by 16dp.
* The scrollable area should take the remaining space that the current conditions box doesn’t use.
* Extra space above and below the forecast cards should scroll with the content.
* Forecast cards should follow the Material design specifications for cards, including card shadows and corner radii.

### Section Header

* The day header label should be the relative date for today and tomorrow. Dates beyond tomorrow should be formatted as the weekday (e.g. "Tuesday"). It should be black and use the "Title" text style form the Material design specifications.
* The line beneath the day header is a 1dp tall divider that spans the width of the card (excluding card padding). It should be 0xCCCCCC.
* There should be 16dp of spacing between the divider line and the hourly cells.

### Hourly Cells

* Cards should default to showing 4 cells per row.
* Cells should wrap their contents' width and height.
* There should be a 12dp gutter between rows of cells.
* Cell contents should be centered horizontally.
* The weather cell date should utilize the "Body 1" style from the Material design specifications. It should be a short style (e.g. 12:00 AM)
* The weather cell icon should not be scaled or stretched. It should have 4dp of spacing on all sides.
* The weather cell temperature should utilize the "Body 1" style from the Material design specifications. It should always be rounded to a whole number.

# Settings

* The ActionBar should be present on the settings screen, and should be #757575.
* The ActionBar should display a standard up indicator.
* Tapping either the up button or the back button should take the user back to the current weather, and the user's choices should be persisted.
* Two preferences should be available to the user: ZIP and Units
* The ZIP preference should open dialog that allows the user to input a ZIP code.
* The Units preferences should open a dialog that allows the user to select between Fahrenheit and Celsius.
