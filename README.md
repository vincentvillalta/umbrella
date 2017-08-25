# Umbrella

For this code challenge, you will be creating a simple weather application named “Umbrella”. 
The application will download both the current conditions and an hour by hour forecast from Weather Underground. 
This application is intended to only be released in the United States. 

We intend for this to take less than 8 hours. Please don’t spend more than 12 hours because, frankly, we value your time. 
If you think you may go over, we recommend commenting on how you’d accomplish certain tasks. 
Additionally, please locally commit your code changes regularly throughout the challenge.

In this code challenge we will be paying particular attention to the following items:

* Design fidelity: Can you accurately and efficiently implement the design as specified in the art comps.
* Functionality: Does the application meet the technical requirements and work reliably?
* Architecture: How do you structure your application and its classes? Would the application be extensible? How do you encapsulate data parsing and access?
* Coding practices and use of IDE: How do you organize your files and groups? What practices do you adhere to make the code accessible and usable to other developers? How is source control used within the application?
* Fit and finish: Do you adhere to the platform’s recommended practices?

# Getting Started

All assets to get started can be found at the git repository. 
	
The project contains reference designs, design metrics, and classes to expedite development. 
Feel free to not use the classes that we provide, but they’re there to help you out.

Weather Underground’s API provides free access to their service for developers. 
You can sign up for an API key at http://www.wunderground.com/weather/api/

# Functionality

Every time that the application becomes the foreground app, the application should fetch the weather. 
If the user has not entered a ZIP code previously, the application should automatically prompt the user for the ZIP code.

## Zip Code Entry 

The user should be able to enter the ZIP code as prescribed by the designs. 
On Android, the user should also be able to switch between Imperial and Metric representation of the data.

## Hourly Weather Display

The hourly weather has two main sections. The top section is the current conditions of the entered ZIP code. 
If the temperature is below 60˚, use the cool color as specified by the designs. If the temperature is 60˚ or above, 
use the warm color as specified by the designs.

The other section of the main weather display is the hourly forecast. The data from the API should be grouped by days. 
The highest temperature of the day should have an warm tint as specified by the designs. The lowest temperature of the 
day should have a cool tint as specified by the designs. If there is a tie for the high or low, just highlight the first 
occurrence. If there is an occurrence where the high and low are the same hour, do not use a tint color.


# Android Versions
* App should target the latest SDK
* App must support API versions 19 and up.

# Languages
* Java
* Kotlin

# APIs

The api package contains an ApiServicesProvider, which provides access to the API classe:

* WeatherApi: This is the Weather Underground API. The only implemented endpoint is /conditions/hourly.

# Resources

We have set up a few resources for you already.
All of the icons you need should already be in the appropriate drawable folders.
The colors used to indicate that the current temperature is "warm" or "cool"
are provided in colors.xml. Take special note of metrics.md, which defines a
number of metrics for styling the application.
