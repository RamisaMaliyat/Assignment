## WP Dark Mode Automation Test Suite

# Overview
This repository contains an automated test suite for validating the functionality of the WP Dark Mode plugin on a WordPress site. The suite covers a variety of scenarios including plugin activation, dark mode settings in both the admin dashboard and front end, as well as customizations.

# Scenarios 
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

## Prerequisites

Before running the test suite, make sure to have the following:
- **Java** (version 20.0 or later)
- Install **Local**
- A **WordPress site** with the **WP Dark Mode plugin** installed

**Setup Instructions**


