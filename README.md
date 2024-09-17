# WP Dark Mode Automation Test Suite

## Overview
This repository contains an automated test suite for validating the functionality of the WP Dark Mode plugin on a WordPress site. The suite covers a variety of scenarios including plugin activation, dark mode settings in both the admin dashboard and front end, as well as customizations.

## Scenarios 
The following scenarios are automated by the test suite:

1. **Log in to your WordPress site**.
2. **Check if the WP Dark Mode plugin is active**:
   - If active, proceed to the settings.
   - If not, install and activate the plugin.
3. **Enable Admin Dashboard Dark Mode**:
   - Go to *Controls → Admin Panel Dark Mode*.
4. **Validate that dark mode works on the Admin Dashboard**.
5. **Navigate to WP Dark Mode → Customization → Switch Settings**:
   - Change the **Floating Switch Style** to a different option.
6. **Customize the Switch Size** to 220.
7. **Reposition the Floating Switch** to the left.
8. **Disable Keyboard Shortcut** from *Accessibility Settings*.
9. **Enable Page-Transition Animation** and change the animation effect.

### Notes:
Save the settings after every change.

### Prerequisites

Before running the test suite, make sure to have the following:
- **Java** (version 20.0 or later)
- **Local** (for hosting WordPress locally)
- A **WordPress site** with the **WP Dark Mode plugin** installed

## Setup Instructions

1. **Clone the repository**:

   ```bash
   git clone https://github.com/RamisaMaliyat/Assignment.git
  
2. **Install dependencies:**:
    Ensure that Maven is set up to handle dependencies in the pom.xml file.
   ou can install dependencies by running:

   ```bash
   mvn clean install
   
4. **Install Plugin:**:
    Install the EnvFile plugin to manage environment variables.
   
5. **Set up environment variables**:
    Create a .env file in the root directory (Refer to .env.example for guidance).

## Running the Test Suite

1. Ensure your WordPress site is running (locally).
2. Run the test suite
   Run testing.xml
## GitHub Actions
The test suite is set up to run automatically using GitHub Actions. The workflow file is located in:
     **.github/workflows/run-tests.yml**
Whenever code is pushed to the repository, GitHub Actions will automatically run the test suite.

## Reporting Bugs and Feature Requests
To find bugs or have suggestions, please open an issue in the repository. 
