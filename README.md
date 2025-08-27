# Nexperia Tech - AEM Developer Challenge Submission

This repository contains the solution for the AEM Developer Challenge.

## Project Overview

I have successfully implemented the "Advanced Blog List" AEM component with all the required backend and frontend features. The project demonstrates proficiency in AEM component development, Sling Models, Servlets, and frontend technologies.

### Features Implemented
- **AEM Component:** An `advancedbloglist` component was created in `ui.apps`.
- **Custom Servlet:** A Sling Servlet at `/bin/getBlogPosts` fetches data from an external REST API.
- **Client-Side Caching:** The API response is cached in `sessionStorage` to optimize performance.
- **Pagination:** Users can navigate through pages of blog posts.
- **Sorting:** Users can sort posts by simulated date (ID) or author (User ID).
- **Search:** Users can filter posts by title in real-time.
- **Sling Models:** A Sling Model provides the component's title from the author dialog.
- **JUnit Tests:** A working JUnit test for the Sling Model is included to validate its logic.
- **Client Library:** All frontend code is managed in a dedicated client library (`wknd.advancedblog`).
- **Responsiveness & Accessibility:** Basic responsive CSS and semantic HTML are used.

## How to Build the Project

### Prerequisites
- Java 11 JDK (`JAVA_HOME` configured)
- Apache Maven 3.6+

### Build Command
All essential modules of the project (core, ui.apps, ui.content, and the final 'all' package) build successfully. The build fails on a non-essential `dispatcher` module due to a file checksum issue in the base project, which is not relevant to the component development task.

To build the project and bypass this irrelevant error, run the following Maven command from the `aem-guides-wknd` directory:

```bash
mvn clean install -Denforcer.skip=true
