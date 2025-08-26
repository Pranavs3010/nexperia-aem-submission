# Nexperia Tech - AEM Developer Challenge Submission

This repository contains the solution for the AEM Developer Challenge.

## Project Overview

I have successfully implemented the "Advanced Blog List" AEM component with all the required backend and frontend features. The project demonstrates proficiency in AEM component development, Sling Models, Servlets, and frontend technologies.

### Features Implemented

- **AEM Component:** `advancedbloglist` component created under `ui.apps`.
- **Custom Servlet:** A Sling Servlet at `/bin/getBlogPosts` fetches data from an external API.
- **Sling Model:** `AdvancedBlogListModel` provides the component's title from the author dialog.
- **JUnit Test:** A working JUnit test for the Sling Model is included in the `core` bundle.
- **Frontend Features:** The component's JavaScript includes dynamic rendering, client-side caching, search, sorting, and pagination.
- **Client Library:** All frontend code is managed in a dedicated client library.

## How to Build the Project

### Prerequisites

- Java 11 JDK (`JAVA_HOME` configured)
- Apache Maven 3.6+

### Build Command

All essential modules of the project (core, ui.apps, ui.content, and the final 'all' package) build successfully. The build fails on a non-essential `dispatcher` module due to a file checksum issue that is not relevant to the component development task.

To build all the working modules, you can use the following Maven command from the project root:

````bash
mvn clean install -pl '!dispatcher.cloud'```
*(This command instructs Maven to build everything **except** the dispatcher.cloud module.)*

Alternatively, to run the full build and see the final dispatcher error, use:

```bash
mvn clean install
````
